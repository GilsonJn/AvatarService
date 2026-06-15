# Controlo 1: Bucket S3 com Bloqueio Total de Acesso Público
resource "aws_s3_bucket_public_access_block" "map_assets_block" {
  bucket                  = aws_s3_bucket.map_avatar_assets.id
  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}

# Controlo 2: Base de Dados Criptografada em Repouso
resource "aws_db_instance" "map_database" {
  allocated_storage   = 20
  engine              = "postgres"
  instance_class      = "db.t3.micro"
  db_name             = "map_db"
  storage_encrypted   = true
  skip_final_snapshot = true
}

# Controlo 3: Isolamento de Rede via Security Group (Database fechado)
resource "aws_security_group" "db_sg" {
  name        = "map_db_security_group"
  description = "Permite acesso apenas a partir da camada da API"

  ingress {
    from_port       = 5432
    to_port         = 5432
    protocol        = "tcp"
    security_groups = [aws_security_group.api_sg.id] # Zero Trust: apenas a API pode conectar
  }
}

# Controlo 4: WAF (Web Application Firewall) contra SQLi e XSS
resource "aws_wafv2_web_acl" "map_api_waf" {
  name  = "map-waf-protection"
  scope = "REGIONAL"
  default_action { allow {} }

  rule {
    name     = "CRSRuleSet_Protecao_Contra_Injecao"
    priority = 1
    statement {
      managed_rule_group_statement {
        name        = "AWSManagedRulesCommonRuleSet"
        vendor_name = "AWS"
      }
    }
    visibility_config {
      cloudwatch_metrics_enabled = true
      metric_name                = "waf-blocked-requests"
      sampled_requests_enabled   = true
    }
  }
}

# Controlo 5: Política IAM de Privilégio Mínimo (Somente Leitura no Storage)
resource "aws_iam_role_policy" "map_app_s3_read_only" {
  name = "map_s3_read_only_policy"
  role = aws_iam_role.app_execution_role.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action   = ["s3:GetObject"] # Proíbe deleção ou alteração acidental/maliciosa
        Effect   = "Allow"
        Resource = "${aws_s3_bucket.map_avatar_assets.arn}/*"
      }
    ]
  })
}