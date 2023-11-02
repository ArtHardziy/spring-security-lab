insert into user(id, username, password, email)
values (1, admin, $2a$12$Tp7UBeUmGBxwgOfUKdj13eyDGGMhg56F / 5UrHW9 / ncPM7 / Va7OzuS, admin@app.com),
       (2, manager, $2a$12$XjjsNML7jn2W2MB4FCFsRO0cgtjJUz8EN9qSt / 2E54fC5TcZM4f4K, manager@app.com),
       (3, user, $2a$12$w4.T8pgVUDamga / x0HqSo.3h.aI / LHz3NN3jdCPXY3nAsk / aSMbbK, user@app.com);

insert into role(id, role_type)
values (1, ADMIN),
       (2, MANAGER),
       (3, USER);

insert into user_roles(roles_id, user_id)
values (1, 1),
       (2, 2),
       (3, 3);