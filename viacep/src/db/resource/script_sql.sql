CREATE TABLE tb_cidade (
	Id int(4) auto_increment,
    Cep varchar(9) not null,
    Logradouro varchar(100) not null,
    Complemento varchar(20),
    Bairro varchar(30) not null,
    Localidade varchar(20) not null,
    Uf char(2) not null,
    Ibge int(7) not null,
    Gia char(4) not null,
    Ddd char(2) not null,
    siafi char(4) not null,
    primary key(Id)
);