drop table user if exists;
create table user(
  id  bigint(5) primary key AUTO_INCREMENT,
  name  varchar(30),
  sex  varchar(12),
  );
drop table student if exists;
create table student(
  id  bigint(5) primary key AUTO_INCREMENT,
  name  varchar(30),
  sex  varchar(12),
  number bigint (20),
  );


drop table item if exists;
create table item(
  id  bigint(5) primary key AUTO_INCREMENT,
  name  varchar(30),
  user_id bigint(5)
);

drop table mooncard if exists;
create table mooncard(
  id  bigint(5) primary key AUTO_INCREMENT,
  name  varchar(30),
  user_id bigint(5)
);