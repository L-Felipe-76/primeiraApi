CREATE TABLE accounts_bs (
    id UUID PRIMARY KEY,
    nome_completo VARCHAR(250) NOT NULL,
    genero VARCHAR(1) NOT NULL,
    data_de_nascimento DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefone VARCHAR(10) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    cidade VARCHAR(250) NOT NULL,
    bairro VARCHAR(250) NOT NULL,
    rua VARCHAR(250) NOT NULL,
    numero_casa VARCHAR(5) NOT NULL,
    referencias_rua VARCHAR(2000)
);