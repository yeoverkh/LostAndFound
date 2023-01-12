create table lost_item_information (
    lost_item_id int8 not null,
    peculiarities_id int8 not null,
    primary key (lost_item_id, peculiarities_id)
);

alter table if exists item_information
    add constraint lost_item_id_fk
    foreign key (lost_item_id) references lost_item;

alter table if exists item_information
    add constraint peculiarities_id_fk
    foreign key (peculiarities_id) references peculiarities;