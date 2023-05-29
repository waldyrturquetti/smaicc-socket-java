create database smaicc;

use smaicc;

create table user (
      id       int auto_increment,
      name     varchar(50) not null,
      email    varchar(61) not null,
      password varchar(10) not null,
      constraint id
            primary key (id),
      constraint unique (email)
);

create table incident
(
    id           int auto_increment
        primary key,
    user_id      int          not null,
    date         date         not null,
    hour         time         not null,
    state        char(2)      not null,
    city         varchar(255) not null,
    neighborhood varchar(255) not null,
    street       varchar(255) not null,
    incident_type enum(
        'ALAGAMENTO', 'DESLIZAMENTO', 'ACIDENTE_DE_CARRO', 'OBSTRUCAO_DA_VIA', 'FISSURA_DA_VIA',
        'PISTA_EM_OBRAS','LENTIDAO_NA_PISTA', 'ANIMAIS_NA_PISTA', 'NEVOEIRO', 'TROMBA_DAGUA'),
    constraint fk_user_id
        foreign key (user_id) references user (id)
);


