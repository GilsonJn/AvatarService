CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         ativo TINYINT,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         pontos_experiencia INT NOT NULL,
                         nivel INT NOT NULL,
                         data_cadastro DATE NOT NULL
);