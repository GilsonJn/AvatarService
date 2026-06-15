# ESTÁGIO 1: Build (Ambiente de Compilação)
FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
# Compila a aplicação gerando o executável final de forma isolada
RUN mvn clean package -DskipTests

# ESTÁGIO 2: Produção (Imagem Enxuta e Blindada)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Criação de utilizador e grupo non-root para mitigar escalada de privilégios
RUN addgroup -S mapgroup && adduser -S mapuser -G mapgroup

# Copia apenas o executável do Estágio 1, deixando o código-fonte para trás
COPY --from=builder /build/target/avatar_service-0.0.1-SNAPSHOT.jar ./app.jar

# Altera o dono do ficheiro para o utilizador restrito
RUN chown -R mapuser:mapgroup /app

# Força o container a rodar com o utilizador sem privilégios
USER mapuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]