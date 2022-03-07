drop database if exists attendance;
create database attendance default character set utf8mb4 collate utf8mb4_unicode_ci;
use attendance;

create table `test`
(
    `id` int,
    `name` varchar(255),
    `age` int
);
INSERT INTO test VALUES(1, 'yzy', 18);
INSERT INTO test VALUES(2, 'wy', 12);
INSERT INTO test VALUES(3, 'wayson', 17);
INSERT INTO test VALUES(3, 'shutong', 12);
INSERT INTO test VALUES(3, 'tianyu', 16);