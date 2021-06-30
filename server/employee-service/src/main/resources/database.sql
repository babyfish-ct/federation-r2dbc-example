create table employee(
    id long not null,
    name varchar(100) not null,
    department_id long, -- remote foreign key point to other micro-service
    constraint pk_department primary key(id)
);

insert into employee(id, name, department_id) values(1, 'Alexander', 1);

insert into employee(id, name, department_id) values(2, 'Joanna', 1);

insert into employee(id, name, department_id) values(3, 'Raphael', 2);

insert into employee(id, name, department_id) values(4, 'Faustina', 2);
