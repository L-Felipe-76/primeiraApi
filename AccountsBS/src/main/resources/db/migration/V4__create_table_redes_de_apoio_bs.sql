CREATE TABLE redes_de_apoio_bs (
    id UUID PRIMARY KEY,
    cep VARCHAR(9) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    cidade VARCHAR(250) NOT NULL,
    bairro VARCHAR(250) NOT NULL,
    integrantes INTEGER NOT NULL
);