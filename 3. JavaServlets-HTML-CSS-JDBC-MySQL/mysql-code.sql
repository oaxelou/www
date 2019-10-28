create database usersdb;
use usersdb;
create table users(
username varchar(100) not null,
password varchar(100) not null,
primary key(username));  