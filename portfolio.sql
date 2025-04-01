create database portfolio;

use portfolio;

 create table account(
 id int not null auto_increment
 , login_id varchar(20)
 , password varchar(255)
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(id)
 );

 insert into account(login_id, password, created_date_time, updated_date_time)
 values ('admin1', '$2a$10$BWi6SLqZRJyVvJyufjTtHeYXNNhpNY9rxaVl9fBOE.1t3QF98B.cO', now(), now());

create table http_interface(
 id int not null auto_increment
 , cookies varchar(255)
 , referer varchar(255)
 , local_addr varchar(255)
 , remote_addr varchar(255)
 , remote_host varchar(255)
 , request_uri varchar(255)
 , user_agent varchar(255)
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(id)
 );

 create table achievement(
  id int not null auto_increment
 , created_date_time datetime
, title varchar(255)
 , description varchar(255)
 , achieved_date date
 , host varchar(255)
 , is_active bit
 , updated_date_time datetime
 , primary key(id)
 );

 create table introduction(
  id int not null auto_increment
 , content varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(id)
 );

 create table link(
  id int not null auto_increment
 , name varchar(255)
 , content varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(id)
 );

 create table skill(
  id int not null auto_increment
 , name varchar(255)
 , skill_type varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(id)
 );

 create table experience(
  id int not null auto_increment
 , title varchar(255)
 , description varchar(255)
 , start_year smallint
 , start_month tinyint
 , end_year smallint
 , end_month tinyint
 , is_active bit
, created_date_time datetime
 , updated_date_time datetime
 , primary key(id)
 );

 create table experience_detail(
  id int not null auto_increment
 , experience_id int
 , content varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(id)
 , foreign key(experience_id) references experience(id)
 );

 create table project(
  id int not null auto_increment
 , name varchar(255)
 , description varchar(255)
 , start_year smallint
 , start_month tinyint
 , end_year smallint
 , end_month tinyint
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(id)
 );

 create table project_detail(
  id int not null auto_increment
 , project_id int
 , content varchar(255)
 , url varchar(255)
 , is_active bit
 , created_date_time datetime
 , updated_date_time datetime
 , primary key(id)
 , foreign key(project_id) references project(id)
 );

 create table project_skill(
  id int not null auto_increment
 , project_id int
 , skill_id int
 , created_date_time datetime
  , updated_date_time datetime
  , primary key(id)
  , foreign key(project_id) references project(id)
  , foreign key(skill_id) references skill(id)
 );
