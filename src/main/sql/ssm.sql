/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/11/18 22:02:52                          */
/*==============================================================*/


drop table if exists be_using;

drop table if exists buy_device;

drop table if exists device;

drop table if exists fix_device;

drop table if exists user;

/*==============================================================*/
/* Table: be_using                                              */
/*==============================================================*/
create table be_using
(
   id                   int not null auto_increment,
   d_id                 int,
   u_place              char(20),
   u_state              int comment '0表示使用中
            1表示停用中
            2表示维修中',
   u_mark               char(200),
   primary key (id)
);

/*==============================================================*/
/* Table: buy_device                                            */
/*==============================================================*/
create table buy_device
(
   id                   int not null auto_increment,
   buyer                varchar(20),
   d_id                 int,
   b_money              float,
   b_time               time,
   b_mark               varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: device                                                */
/*==============================================================*/
create table device
(
   id                   int not null auto_increment,
   d_name               varchar(20),
   d_desc               varchar(200),
   d_price              float(5),
   primary key (id)
);

/*==============================================================*/
/* Table: fix_device                                            */
/*==============================================================*/
create table fix_device
(
   id                   int not null auto_increment,
   fixer                varchar(20),
   d_id                 int,
   fix_time             time,
   fix_mark             varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment,
   username             varchar(20),
   password             char(32),
   salt                 varchar(20) comment '用于密码加密使用',
   primary key (id)
);

alter table be_using add constraint FK_use foreign key (d_id)
      references device (id) on delete restrict on update restrict;

alter table buy_device add constraint FK_buy foreign key (d_id)
      references device (id) on delete restrict on update restrict;

alter table fix_device add constraint FK_fix foreign key (d_id)
      references device (id) on delete restrict on update restrict;

