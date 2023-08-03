create table mini_progress_user(
    `id` bigint(20) unsigned not null AUTO_INCREMENT,
    `open_id` varchar(1000) not null default '' comment '小程序openid',
    `avatar_big` varchar(1000) not null default '',
    `name` varchar(255) not null default '',
    primary key (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '用户信息表';
-- avatar_big  name open_id
create table personal_plan(
    `id` bigint(20) unsigned not null AUTO_INCREMENT,
    `open_id` varchar(1000) not null default '',
    `finished` int not null default 0 comment 'finished=1, unfinished=0',
    `tips` varchar(8000) not null default '' comment '某个计划',
    `plan_time` varchar(1000) not null default '' comment '计划时间，比如选择了2023-04-15',
    `create_time` bigint not null default 0 ,
    `update_time` bigint not null default 0,
    primary key (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '个人计划';
#团队成员
drop table group_members;
create table group_members(
    `id` bigint(20) unsigned not null AUTO_INCREMENT comment '团队id',
    `group_name` varchar(1000) not null default '我的团队' comment '团队名称',
    `open_id_list` varchar(8000) not null default '' comment '群成员id',
    `owner_id` varchar(1000) not null default '' comment '群主id',
    `create_time` bigint(20) not null default 0 ,
    primary key (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '团队成员表';
#团队计划
create table group_plan(
    `id`  bigint(20) unsigned not null AUTO_INCREMENT comment '团队计划id',
    `group_id` bigint(20) unsigned not null default 0 comment '所属团队id',
    `finished` int not null default 0 comment 'finished=1, unfinished=0',
    `tips` varchar(8000) not null default '' comment '某个计划',
    `open_id_list` varchar(8000) default '' comment '指定某人完成',
    `plan_time` varchar(1000) not null default '' comment '计划时间，比如选择了2023-04-15',
    `create_time` bigint not null default 0 ,
    `update_time` bigint not null default 0,
    primary key (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '团队计划表';
