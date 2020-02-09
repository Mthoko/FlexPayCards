DROP SCHEMA IF EXISTS flexpaydb;
CREATE SCHEMA flexpaydb;
USE flexpaydb;
DROP TABLE IF EXISTS Cards;
CREATE TABLE Cards(
card_id int not null unique primary key,
card_number int(16) not null unique,
card_status varchar(25) not null,
created_date date,
activated_date date,
primary_secondary_indicator boolean not null,
expiry_date date not null,
available_balance decimal,
actual_balance decimal
);
DROP TABLE IF EXISTS Card_Holder;
CREATE TABLE Card_Holder(
gov_id int not null unique primary key,
title varchar(20) not null,
first_name varchar(100),
last_name varchar(100),
date_of_birth date,
card_id int,
 foreign key (card_id) references Cards(card_id)
);
