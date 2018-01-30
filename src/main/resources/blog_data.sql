create database blog_data;

use blog_data;
drop table if exists user;

# 用户表
create table user(
id int(11) not null auto_increment,
name varchar(20) not null,
email varchar(50) not null,
username varchar(20) not null,
password varchar(100) not null,
avatar varchar(200),
primary key(id)
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
tags varchar(10),
comment_id int(11),
vote_id int(11),
category_id int(11),
primary key(id)
)engine=innodb default charset = utf8;

# 评论表
drop table if exists comment;
create table comment(
id int(11) not null auto_increment,
content varchar(500) not null,
user_id int(11),
createtime timestamp,
primary key(id)
)engine=innodb default charset = utf8;

# 目录表
drop table if exists category;
create table category(
id int(11) not null auto_increment,
name varchar(30) not null,
user_id int(11),
primary key(id)
)engine=innodb default charset = utf8;

# 点赞表
drop table if exists vote;
create table vote(
id int(11) not null auto_increment,
user_id int(11),
createtime timestamp,
primary key(id)
)engine=innodb default charset = utf8;