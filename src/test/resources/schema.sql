drop table if exists members CASCADE;
create table members
(
    id          bigint generated by default as identity,
    user_id     varchar(10) not null,
    name        varchar(10) not null,
    password    varchar(15) not null,
    primary key (id)
);

drop table if exists posts CASCADE;
create table posts
(
    id          bigint generated by default as identity,
    user_id     bigint not null,
    writer      varchar(10) not null,
    title       varchar(50) not null,
    post_date   datetime not null,
    content     varchar(255) not null,
    views       bigint,
    primary key (id),
    foreign key (user_id) references members(id)
);

drop table if exists views CASCADE;
create table views
(
    id          bigint generated by default as identity,
    post_id     bigint not null,
    user_id     bigint not null,
    primary key (id),
    foreign key (post_id) references posts(id),
    foreign key (user_id) references members(id)
);