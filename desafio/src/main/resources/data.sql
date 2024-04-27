set foreign_key_checks = 0;

delete from veiculo;
delete from estabelecimento;
delete from registro_estacionamento;

-- Dados inicias para os estabelecimentos
INSERT INTO estabelecimento (id, nome, cnpj, endereco, telefone, quantidade_vagas_motos, quantidade_vagas_carros)
VALUES (1, 'Estacionamento Central', '123.456.789/0001-01', 'Av. Paulista, 1001', '(11) 1234-5678', 50, 100);

INSERT INTO estabelecimento (id, nome, cnpj, endereco, telefone, quantidade_vagas_motos, quantidade_vagas_carros)
VALUES (2, 'Garagem do Parque', '987.654.321/0001-02', 'Rua Augusta, 500', '(11) 9876-5432', 30, 80);

INSERT INTO estabelecimento (id, nome, cnpj, endereco, telefone, quantidade_vagas_motos, quantidade_vagas_carros)
VALUES (3, 'Pátio dos Carros', '333.222.111/0001-03', 'Av. Faria Lima, 2000', '(11) 4567-8901', 40, 120);

-- Dados iniciais para os veículos
INSERT INTO veiculo (id, marca, modelo, cor, placa, tipo, estabelecimento_id)
VALUES (1, 'Volkswagen', 'Gol', 'Prata', 'ABC-1234', 'CARRO', 1);

INSERT INTO veiculo (id, marca, modelo, cor, placa, tipo, estabelecimento_id)
VALUES (2, 'Honda', 'CB 500', 'Vermelho', 'XYZ-5678', 'MOTO', 1);

INSERT INTO veiculo (id, marca, modelo, cor, placa, tipo, estabelecimento_id)
VALUES (3, 'Ford', 'Fiesta', 'Azul', 'DEF-9012', 'CARRO', 2);