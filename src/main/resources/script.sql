CREATE TABLE chave_pix (
                           id UUID PRIMARY KEY,
                           tipo_chave VARCHAR(9),
                           valor_chave VARCHAR(77),
                           tipo_conta VARCHAR(10),
                           numero_agencia INTEGER,
                           numero_conta INTEGER,
                           nome_cliente VARCHAR(30),
                           sobrenome_cliente VARCHAR(45),
                           tp_cliente VARCHAR(2),
                           st_chave VARCHAR(1),
                           data_criacao TIMESTAMP,
                           data_atualizacao TIMESTAMP,
                           data_inativacao TIMESTAMP
);
