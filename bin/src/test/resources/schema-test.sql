drop table if exists developer CASCADE; 
drop table if exists game CASCADE; 
create table developer (id bigint generated by default as identity, name varchar(255) not null, primary key (id));
create table game (id bigint generated by default as identity, genre varchar(255) not null, platform varchar(255) not null, title varchar(255) not null, developer_id bigint, primary key (id));
