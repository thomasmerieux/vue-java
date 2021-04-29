DROP TABLE IF EXISTS vue.favorite;
DROP TABLE IF EXISTS vue.watch_list;
DROP TABLE IF EXISTS vue.user_list_movie;
DROP TABLE IF EXISTS vue.user_list;



create table vue.favorite
(
    id integer primary key generated always as identity,
    movie_id integer not null unique
);

create table vue.watch_list
(
    id integer primary key generated always as identity,
    movie_id integer not null unique
);


create table vue.user_list
(
    id integer primary key generated always as identity,
    label varchar(255)
);



create table vue.user_list_movie
(
    id integer primary key generated always as identity,
    movie_id integer not null,
    list_id integer not null
        constraint fk_list
            references vue.user_list(id),
    unique (movie_id, list_id)
);




insert into vue.favorite (movie_id) values (11);
insert into vue.watch_list (movie_id) values (22);
insert into vue.user_list (label) values ('My first list');
insert into vue.user_list_movie (movie_id, list_id) values (11, 1);