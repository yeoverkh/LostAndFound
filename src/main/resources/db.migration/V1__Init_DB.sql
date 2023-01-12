create sequence hibernate_sequence start 1 increment 1;

create table currency
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);

create table assessed_value
(
    id          int8 not null,
    value       float8,
    currency_id int8,
    primary key (id)
);

create table place
(
    id                int8 not null,
    address           varchar(255),
    latitude          float8,
    longitude         float8,
    short_description varchar(255),
    primary key (id)
);

create table typ
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);

create table lost_item
(
    id                int8 not null,
    date              date,
    filename          varchar(255),
    name              varchar(255),
    quantity          int4 not null,
    time              time,
    assessed_value_id int8,
    place_id          int8 not null,
    type_id           int8 not null,
    primary key (id)
);

create table unit
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);

create table peculiarities
(
    id      int8 not null,
    name    varchar(255),
    value   varchar(255),
    unit_id int8 not null,
    primary key (id)
);

alter table if exists assessed_value
    add constraint currency_id_fk
    foreign key (currency_id) references currency;

alter table if exists lost_item
    add constraint assessed_value_id_fk
    foreign key (assessed_value_id) references assessed_value;

alter table if exists lost_item
    add constraint place_id_fk
    foreign key (place_id) references place;

alter table if exists lost_item
    add constraint type_id_fk
    foreign key (type_id) references typ;

alter table if exists peculiarities
    add constraint unit_id_fk
    foreign key (unit_id) references unit;
