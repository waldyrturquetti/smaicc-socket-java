create table user (
      id       int auto_increment,
      name     varchar(50) not null,
      password varchar(10) not null,
      email    varchar(61) not null,
      constraint id
            primary key (id),
      constraint unique (email)
);

