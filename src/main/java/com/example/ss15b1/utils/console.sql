create database mini_project;
use mini_project;

create table category(
    id int auto_increment primary key ,
    name varchar(255),
    status bit
);

create table book(
    id int auto_increment primary key ,
    category_id int,
    name varchar(255),
    price double,
    stock int,
    totalPages int,
    yearCreated int,
    author varchar(255),
    image varchar(255),
    status bit,
    foreign key (category_id) references category(id)
);