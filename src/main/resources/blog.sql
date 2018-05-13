create DATABASE blog DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use blog;
create table `user`(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  name VARCHAR(20) NOT NULL COMMENT '别名',
  username VARCHAR(20) UNIQUE COMMENT '用户名',
  password VARCHAR(40) NOT NULL COMMENT '加密后的密码',
  email VARCHAR(20) NOT NULL COMMENT '邮件',
  imageUrl VARCHAR(50) DEFAULT NULL COMMENT '头像地址',
  remark VARCHAR(100) NOT NULL DEFAULT '这个人很懒，什么都没留下' COMMENT '备注',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态,1:正常状态 , 0代表冻结状态，2代表删除',
  userGroup_Id INT NOT NULL DEFAULT 1 COMMENT '用户组',
  operator VARCHAR(20) NOT NULL DEFAULT '' COMMENT '操作者',
  create_time DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  operate_time DATETIME NULL DEFAULT now() COMMENT '最后操作时间',
  last_login_time DATETIME NOT NULL DEFAULT now() COMMENT '最后一次登录时间',
  last_modify_password_Time DATETIME NOT NULL DEFAULT now() COMMENT '密码最后修改时间',
  operate_ip VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次操作的ip'

)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '用户表';

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
)engine = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET =utf8 COMMENT '菜单栏表';

INSERT INTO `menu` (name , url , priority ) VALUES ('用户管理' , '/users/list' , 1);



CREATE TABLE `userGroup`(
  id INT AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(20) UNIQUE NOT NULL COMMENT '用户组的组名',
  parent_id INT NOT NULL DEFAULT 0 COMMENT '上层yoghurt组ID',
  level TINYINT NOT NULL DEFAULT 1 COMMENT '层级',
  seq TINYINT NOT NULL DEFAULT 1 COMMENT '相同层级排序关键字',
  remark VARCHAR(200) DEFAULT '' COMMENT '备注',
  operator VARCHAR(20) NOT NULL DEFAULT '' COMMENT '操作者',
  create_time DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  operate_time DATETIME NULL DEFAULT now() COMMENT '最后操作时间',
  operate_ip VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次操作的ip',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态'

)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '用户组表';



CREATE TABLE `ACLModule`(
  id INT AUTO_INCREMENT PRIMARY KEY ,
  name VARCHAR(20) UNIQUE NOT NULL COMMENT '权限名',
  parent_Id INT NOT NULL DEFAULT 0 COMMENT '上层权限模块ID',
  level TINYINT NOT NULL DEFAULT 1 COMMENT '层级',
  seq TINYINT NOT NULL DEFAULT 1 COMMENT '相同层级排序关键字',
  remark VARCHAR(200) DEFAULT '' COMMENT '备注',
  operator VARCHAR(20) NOT NULL DEFAULT '' COMMENT '操作者',
  create_time DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  operate_time DATETIME NULL DEFAULT now() COMMENT '最后操作时间',
  operate_ip VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次操作的ip',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态'
)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '权限模块表';


CREATE TABLE `authority`(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  code VARCHAR(20) NOT NULL DEFAULT '' COMMENT '权限码',
  name VARCHAR(20) UNIQUE COMMENT '权限名',
  ACLModuleId VARCHAR(20) NOT NULL DEFAULT  1 COMMENT '权限模块ID',
  email VARCHAR(20) NOT NULL COMMENT '邮件',
  url VARCHAR(50) DEFAULT NULL COMMENT '请求的URL,可以是正则表达式',
  type INT NOT NULL DEFAULT 1 COMMENT '类型 1:菜单,2:按钮,3:其它',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态,1:正常状态 , 0代表冻结状态，2代表删除',
  seq TINYINT NOT NULL DEFAULT 1 COMMENT '权限在该权限模块下的排序,有小到大排序',
  remark VARCHAR(100) NOT NULL DEFAULT '' COMMENT '备注',
  operator VARCHAR(20) NOT NULL DEFAULT '' COMMENT '操作者',
  create_time DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  operate_time DATETIME NULL DEFAULT now() COMMENT '最后操作时间',
  operate_ip VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次操作的ip'
)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '权限表';


CREATE TABLE `role`(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  name VARCHAR(20) UNIQUE DEFAULT '' COMMENT '角色名',
  type TINYINT NOT NULL DEFAULT 0 COMMENT '角色类型 0管理员:,1:普通,2:其它',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态,1:正常状态 , 0代表冻结状态，2代表删除',
  seq TINYINT NOT NULL DEFAULT 1 COMMENT '权限在该权限模块下的排序,有小到大排序',
  remark VARCHAR(200) NOT NULL DEFAULT '' COMMENT '备注',
  operator VARCHAR(20) NOT NULL DEFAULT '' COMMENT '操作者',
  create_time DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  operate_time DATETIME NULL DEFAULT now() COMMENT '最后操作时间',
  operate_ip VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次操作的ip'
)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '角色表';


INSERT INTO `role` (name ) VALUES (  '普通博主' );

CREATE TABLE `role_user`(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  role_id INT NOT NULL DEFAULT 1 COMMENT '角色ID',
  user_id INT NOT NULL DEFAULT 1 COMMENT '用户Id',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态 1:正常',
  operator VARCHAR(20) NOT NULL DEFAULT '' COMMENT '操作者',
  create_time DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  operate_time DATETIME NULL DEFAULT now() COMMENT '最后操作时间',
  operate_ip VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次操作的ip'
)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '角色用户表';

insert into `role_user` (user_id , role_id) values (1,1),(2,1),(3,1),(4,1),(5,1);
insert into `role_user` (user_id , role_id) values (13,1),(14,1);

CREATE TABLE `role_ACl`(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  role_id INT NOT NULL DEFAULT 1 COMMENT '角色ID',
  ACl_id INT NOT NULL DEFAULT 1 COMMENT '权限Id',
  operator VARCHAR(20) NOT NULL DEFAULT '' COMMENT '操作者',
  create_time DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  operate_time DATETIME NULL DEFAULT now() COMMENT '最后操作时间',
  operate_ip VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次操作的ip'
)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '角色权限表';

CREATE TABLE `syslog`(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  type INT NOT NULL DEFAULT 0 COMMENT '权限更新类型 1:用户组,2:用户,3:权限模块,4:权限,5:角色,6:角色用户关系,7:角色权限关系',
  target_Id INT NOT NULL DEFAULT 1 COMMENT '基于type后指定的对象ID,比如用户,权限,角色的主键id',
  old_Value TEXT COMMENT '旧值,更新操作oldValue-->newValue,删除操作new_Value=null，插入操作:old_Value=null',
  new_Value TEXT COMMENT '新值',
  operator VARCHAR(20) NOT NULL DEFAULT '' COMMENT '操作者',
  create_time DATETIME NOT NULL DEFAULT now() COMMENT '创建时间',
  operate_time DATETIME NULL DEFAULT now() COMMENT '最后操作时间',
  operate_ip VARCHAR(20) NOT NULL DEFAULT '' COMMENT '最后一次操作的ip',
  status TINYINT NOT NULL COMMENT '当前是否复原过,0:没有,1:复原过'
)ENGINE = innodb AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT '系统相关日志';
