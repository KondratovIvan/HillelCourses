create table users
(
    id          serial
        primary key,
    country     varchar(255),
    email       varchar(255),
    is_deleted  boolean,
    name        varchar(255),
    age         integer,
    passport_id integer
        constraint fkddyjnd93b8x7gdng15k7g429p
            references passports
);
create table photos
(
    id          serial
        primary key,
    create_date date,
    height      integer,
    is_visible  boolean,
    link_photo  varchar(255),
    width       integer,
    employee_id integer
        constraint fkbxq293jvxh5d4t6qrndovypyt
            references users
);