set foreign_key_checks = 0;

delete from veiculo;
delete from estabelecimento;
delete from registro_estacionamento;

-- Dados inicias para os estabelecimentos
INSERT INTO estabelecimento (id, nome, cnpj, endereco_cep, endereco_logradouro, endereco_bairro, endereco_numero, endereco_complemento, endereco_cidade, endereco_estado, telefone, quantidade_vagas_motos, quantidade_vagas_carros)
VALUES (1, 'Estacionamento Central', '12.345.678/0001-90', '12345-678', 'Rua das Flores', 'Centro', '123', 'Sala 101', 'Cidade Grande', 'Estado XYZ', '(12) 3456-7890', 20, 50);

INSERT INTO estabelecimento (id, nome, cnpj, endereco_cep, endereco_logradouro, endereco_bairro, endereco_numero, endereco_complemento, endereco_cidade, endereco_estado, telefone, quantidade_vagas_motos, quantidade_vagas_carros)
VALUES (2, 'Estacionamento VIP', '98.765.432/0001-09', '54321-876', 'Avenida dos Pássaros', 'Bela Vista', '456', 'Loja 02', 'Cidade Pequena', 'Estado ABC', '(34) 5678-9012', 30, 70);

INSERT INTO estabelecimento (id, nome, cnpj, endereco_cep, endereco_logradouro, endereco_bairro, endereco_numero, endereco_complemento, endereco_cidade, endereco_estado, telefone, quantidade_vagas_motos, quantidade_vagas_carros)
VALUES (3, 'Estacionamento Premium', '11.223.334/0001-22', '98765-432', 'Rua dos Nobres', 'Jardim Real', '789', 'Bloco A', 'Cidade Nova', 'Estado DEF', '(56) 7890-1234', 40, 80);

-- Dados iniciais para os veículos
INSERT INTO veiculo (id, marca, modelo, cor, placa, tipo, estabelecimento_id)
VALUES (1, 'Volkswagen', 'Gol', 'Prata', 'ABC-1234', 'CARRO', 1);

INSERT INTO veiculo (id, marca, modelo, cor, placa, tipo, estabelecimento_id)
VALUES (2, 'Honda', 'CB 500', 'Vermelho', 'XYZ-5678', 'MOTO', 1);

INSERT INTO veiculo (id, marca, modelo, cor, placa, tipo, estabelecimento_id)
VALUES (3, 'Ford', 'Fiesta', 'Azul', 'DEF-9012', 'CARRO', 2);