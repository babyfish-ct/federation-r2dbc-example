create table department(
    id long not null,
    name varchar(100) not null,
    constraint pk_department primary key(id),
    constraint uq_department unique(name)
);

insert into department(id, name) values(1, 'Develop');

insert into department(id, name) values(2, 'Test');
