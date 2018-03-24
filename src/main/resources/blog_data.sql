create database blog_data;

use blog_data;


# 用户表
drop table if exists user;
create table user(
id int(11) not null auto_increment,
name varchar(20) not null,
email varchar(50) not null,
username varchar(20) not null,
password varchar(100) not null,
role int(11) not null,
avatar varchar(200) not null,
primary key(id),
unique key user_name_unique (username) using btree
)engine=innodb default charset = utf8;

# 博客表
drop table if exists blog;
create table blog(
id int(11) not null auto_increment,
title varchar(50) not null,
summary varchar(300) not null,
content text not null,
user_id int(11),
create_time timestamp,
read_size int(11),
comment_size int(11),
vote_size int(11),
tags varchar(1000),
category_id int(11),
primary key(id)
)engine=innodb default charset = utf8;

# 评论表
drop table if exists comment;
create table comment(
id int(11) not null auto_increment,
blog_id int(11) not null,
user_id int(11) not null,
content varchar(500) not null,
primary key(id)
)engine=innodb default charset = utf8;

# 目录表
drop table if exists category;
create table category(
id int(11) not null auto_increment,
user_id int(11) not null,
content varchar(500) not null,
primary key(id)
)engine=innodb default charset = utf8;

# 点赞表
drop table if exists vote;
create table vote(
id int(11) not null auto_increment,
blog_id int(11) not null,
user_id int(11) not null,
primary key(id)
)engine=innodb default charset = utf8;