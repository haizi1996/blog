create DATABASE blog ;
use blog;
create table `user`(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  username VARCHAR(20) UNIQUE ,
  password VARCHAR(20) NOT NULL ,
  email VARCHAR(20) NOT NULL

)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;