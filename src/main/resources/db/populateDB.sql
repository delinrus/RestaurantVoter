DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM menus;
DELETE FROM menu_items;
DELETE FROM votes;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants(name)
VALUES 	('Континенталь'),
        ('Дон помидор');

INSERT INTO menus(restaurant_id, date)
VALUES 	(100000, today()),
        (100000, '2020-08-21'),
        (100001, today()),
        (100001, '2020-08-21');

INSERT INTO users(email, password)
VALUES 	('user@mail.ru', 'user'),
        ('admin@mail.ru', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100006),
       ('ADMIN', 100007),
       ('USER', 100007);

INSERT INTO menu_items(menu_id, name, price)
VALUES 	(100002, 'Божественная яишенка', 1000),
        (100002, 'Блинчики', 500),
        (100002, 'Бутерброд с сёмгой', 900),
        (100003, 'Омлет', 1500),
        (100003, 'Эко лаваш из шпината', 1200),
        (100003, 'Манная каша с вишневым вареньем', 300),
        (100004, 'Пицца с ветчиной и овощами', 1000),
        (100004, 'Пицца Карбонара', 1200),
        (100004, 'Хачапури', 900),
        (100005, 'Мега-пицца', 10000),
        (100005, 'Сырники с малиновым вареньем', 290),
        (100005, 'Яблочный пирог', 700),
        (100005, 'Морс ягодный', 200),
        (100005, 'Пирожки с хреном', 300),
        (100005, 'Божественный свиток', 999999);

INSERT INTO votes(user_id, restaurant_id, date)
VALUES (100006, 100000, '2020-08-20'),
       (100006, 100001, '2020-08-21'),
       (100007, 100000, today()),
       (100007, 100000, '2020-08-20');