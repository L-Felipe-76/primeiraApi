CREATE TABLE contatos_de_emergencia_bs (
    id UUID PRIMARY KEY,
    nome_completo VARCHAR(250) NOT NULL,
    telefone VARCHAR(10) NOT NULL,
    descricao VARCHAR(2000) NOT NULL
);