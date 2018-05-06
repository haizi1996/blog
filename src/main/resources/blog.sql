create DATABASE blog ;
use blog;
create table `user`(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  name VARCHAR(20) NOT NULL COMMENT '别名',
  username VARCHAR(20) UNIQUE COMMENT '用户名',
  password VARCHAR(20) NOT NULL COMMENT '密码',
  email VARCHAR(20) NOT NULL COMMENT '邮件',
  imageUrl VARCHAR(50) DEFAULT NULL COMMENT '头像地址'

)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

insert INTO `user` (name , username , password , email) VALUES ('张三' , 'zhangsan' , '123456' , '1051956253@qq.com');
insert INTO `user` (name , username , password , email) VALUES ('张四' , 'zhangsi' , '123456' , '1051956253@qq.com');
insert INTO `user` (name , username , password , email) VALUES ('张五' , 'zhangwu' , '123456' , '1051956253@qq.com');
insert INTO `user` (name , username , password , email) VALUES ('张六' , 'zhangliu' , '123456' , '1051956253@qq.com');
insert INTO `user` (name , username , password , email) VALUES ('张七' , 'zhangqi' , '123456' , '1051956253@qq.com');


CREATE TABLE `menu` (
  id TINYINT PRIMARY KEY AUTO_INCREMENT ,
  name VARCHAR(20) UNIQUE  COMMENT '菜单栏名称',
  url VARCHAR(20) UNIQUE COMMENT '菜单栏地址',
  priority TINYINT NOT NULL DEFAULT 5 COMMENT '优先级',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态值1代表可用'
)engine = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET =utf8;

INSERT INTO `menu` (name , url , priority ) VALUES ('用户管理' , '/users/list' , 1);
