-- создаем БД
create database "quest";

-- создаем схему
create schema "data_storage";
drop schema "data_storage" cascade;

-- таблица ответов
create table data_storage.answer
(
    answer_id bigserial primary key,
    question  bigint references data_storage.question (question_id),
    correct   boolean,
    "text"    text
);

insert into data_storage.answer(answer_id, question, correct, "text")
values (1, 1, true, 'Да'),
       (2, 1, false, 'Нет'),

       (3, 4, true, 'принять вызов'),
       (4, 4, false, 'отклонить вызов'),
       (5, 5, true, 'подняться на мостик'),
       (6, 5, false, 'отказаться подниматься'),
       (7, 7, true, 'рассказать правду о себе'),
       (8, 7, false, 'солгать')
;

drop table data_storage.answer;

-- таблица игр
create table data_storage.game
(
    game_id          bigserial primary key,
    player           bigint references data_storage.user (user_id),
    quest            bigint references data_storage.quest (quest_id),
    current_question bigint,
    game_state       varchar(32),
    "date"           date
);

insert into data_storage.game(game_id, player, quest, current_question, game_state, "date")
values (1, 1, 1, null, 'WIN', '1999-01-08')
;

drop table data_storage.game;

-- таблица квестов
create table data_storage.quest
(
    quest_id    bigserial primary key,
    "name"      varchar(32),
    description varchar(128),
    author      bigint references data_storage.user (user_id)
);

insert into data_storage.quest(quest_id, "name", description, author)
values (1, 'тест квест', 'тест квест', 1),
       (2, 'JR Quest', 'Ты попал на корабль пришельцев. Попробуй вернуться домой живым!', 1)
;

drop table data_storage.quest;

-- таблица вопросов
create table data_storage.question
(
    question_id        bigserial primary key,
    quest              bigint references data_storage.quest (quest_id),
    correct_question   bigint,
    incorrect_question bigint,
    "text"             text
);

insert into data_storage.question(question_id, quest, correct_question, incorrect_question, "text")
values (1, 1, 2, 3, 'да или нет?'),
       (2, 1, null, null, 'ты победил!'),
       (3, 1, null, null, 'ты проиграл!'),

       (4, 2, 5, 6, 'Ты потерял память. Принять вызов НЛО?'),
       (5, 2, 7, 8, 'Ты принимаешь вызов. Поднимаешься на мостик к капитану?'),
       (6, 2, null, null, 'Ты отклонил вызов. Поражение'),
       (7, 2, 9, 10, 'Ты поднялся на мостик. Ты кто?'),
       (8, 2, null, null, 'Ты не пошел на переговоры. Поражение'),
       (9, 2, null, null, 'Тебя вернули домой! Победа'),
       (10, 2, null, null, 'Твою ложь разоблачили! Поражение')
;

drop table data_storage.question;

-- таблица пользователей
create table data_storage.user
(
    user_id  bigserial primary key,
    login    varchar(128) unique,
    password varchar(128) not null,
    role     varchar(32)
);

insert into data_storage.user(user_id, login, password, role)
values (1, 'Misha', '123', 'ADMIN'),
       (2, 'Sanya', '123', 'USER'),
       (3, 'Nastya', '123', 'GUEST');

drop table data_storage.user;

