create sequence user_seq
    increment by 50;

alter sequence user_seq owner to springsecurityuser;

create table role
(
    id        bigserial
        primary key,
    role_type varchar(255)
        constraint role_role_type_check
            check ((role_type)::text = ANY
        ((ARRAY ['USER'::character varying, 'ADMIN'::character varying, 'MANAGER'::character varying])::text[]))
    );

alter table role
    owner to springsecurityuser;

create table "user"
(
    id       bigint       not null
        primary key,
    email    varchar(255) not null
        unique,
    password varchar(255) not null,
    username varchar(255) not null
        unique
);

alter table "user"
    owner to springsecurityuser;

create table user_roles
(
    roles_id bigint not null
        constraint fkj9553ass9uctjrmh0gkqsmv0d
            references role,
    user_id  bigint not null
        constraint fk55itppkw3i07do3h7qoclqd4k
            references "user",
    primary key (roles_id, user_id)
);

alter table user_roles
    owner to springsecurityuser;