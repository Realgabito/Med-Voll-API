CREATE TABLE pacientes(
    id bigint not null auto_increment,
        nombre varchar(100) not null,
        email varchar(100) not null unique,
        documento_identidad varchar(6) not null unique,
        calle varchar(100) not null,
        barrio varchar(100) not null,
        ciudad varchar(100) not null,
        complemento varchar(100) not null,

        activo tinyint not null,

        primary key(id)
);