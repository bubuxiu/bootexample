DROP DATABASE IF EXISTS bubuxiu;
CREATE DATABASE IF NOT EXISTS bubuxiu  character set utf8 collate utf8_general_ci;
 
/* user表 */
drop table if exists buxiu_user;
create table buxiu_user(
  userid  int(11) NOT NULL  AUTO_INCREMENT  comment '自增加主键',
  email varchar(128)  comment '邮箱,检索条件',
  username varchar(64) comment '姓名,检索条件',
  gender tinyint(4) comment '性别，1男，2女',
  password  varchar(32) NOT NULL  comment '登录密码',
  mobileno  varchar(30) comment '手机号,检索条件',
  status  tinyint(4) default '1' comment '1未激活，2激活（正常），3停用（冻结）',
  registertime varchar(30) not null comment '注册时间',
  logintime varchar(30) comment '登录时间',
  loginip varchar(128)  comment '登录来源',
  loginstatus tinyint comment '登录状态，0未登录，1登录',
  primary key (userid)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment '用户表';


