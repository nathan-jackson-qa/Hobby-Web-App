drop table if exists game CASCADE;
create table game (id integer generated by default as identity, developerid integer not null, genre varchar(255) not null, platform varchar(255) not null, title varchar(255) not null, primary key (id));