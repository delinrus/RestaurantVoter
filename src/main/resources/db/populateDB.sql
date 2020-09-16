DELETE FROM users;
DELETE FROM menu_menu_item;
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

INSERT INTO menu_items(restaurant_id, name, price)
VALUES 	(100000, 'Божественная яишенка', 1000),
        (100000, 'Блинчики', 500),
        (100000, 'Бутерброд с сёмгой', 900),
        (100000, 'Омлет', 1500),
        (100000, 'Эко лаваш из шпината', 1200),
        (100000, 'Манная каша с вишневым вареньем', 300),
        (100001, 'Пицца с ветчиной и овощами', 1000),
        (100001, 'Пицца Карбонара', 1200),
        (100001, 'Хачапури', 900),
        (100001, 'Мега-пицца', 10000),
        (100001, 'Сырники с малиновым вареньем', 290),
        (100001, 'Яблочный пирог', 700),
        (100001, 'Морс ягодный', 200),
        (100001, 'Пирожки с хреном', 300),
        (100001, 'Божественный свиток', 999999);

INSERT INTO menu_menu_item(menu_id, menu_item_id)
VALUES 	    (100002,100008),
            (100002,100009),
            (100002,100010),
            (100003,100011),
            (100003,100012),
            (100003,100013),
            (100004,100014),
            (100004,100015),
            (100004,100016),
            (100005,100017),
            (100005,100018),
            (100005,100019),
            (100005,100020),
            (100005,100021),
            (100005,100022);

INSERT INTO votes(user_id, restaurant_id, date)
VALUES (100006, 100000, '2020-08-20'),
       (100006, 100001, '2020-08-21'),
       (100007, 100000, today()),
       (100007, 100000, '2020-08-20');