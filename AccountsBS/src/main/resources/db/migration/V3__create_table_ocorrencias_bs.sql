CREATE TABLE ocorrencias_bs (
    id UUID PRIMARY KEY,
    titulo_ocorrencia VARCHAR(250) NOT NULL,
    descricao VARCHAR(2000) NOT NULL,
    tag VARCHAR(1) NOT NULL,
    data_hora TIMESTAMP NOT NULL
);