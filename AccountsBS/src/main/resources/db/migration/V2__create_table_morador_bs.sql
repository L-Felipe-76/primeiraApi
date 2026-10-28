CREATE TABLE moradores_bs (
    id UUID PRIMARY KEY,
    nome_Completo VARCHAR(250) NOT NULL,
    genero VARCHAR(1) NOT NULL,
    data_de_nascimento DATE NOT NULL,
    telefone VARCHAR(10) NOT NULL
);