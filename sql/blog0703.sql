use blog0703;
# 1. user
create table if not exists `user` (
                                      user_id	int	primary key auto_increment NOT NULL,
                                      `name`	varchar(40)	NOT NULL,
    nickname	varchar(40)		NOT NULL,
    `password`	varchar(100)		NOT NULL,
    useabled	tinyint(1)		NOT NULL default 1,
    phone_num	varchar(15)	NOT NULL,
    gender	tinyint(1)		NOT NULL default 0,
    avatar_path	varchar(500)		NOT NULL,
    create_time	timestamp		NOT NULL default now(),
    update_time	timestamp		NOT NULL default now(),
    key (`name`),
    key (`phone_num`)
    ) engine=innodb charset=utf8;

# 2. User_Role_Relation
create table if not exists `User_Role_Relation` (
                                                    ur_id	int	primary key auto_increment	NOT NULL,
                                                    user_id	int	NOT NULL,
                                                    role_id	int		NOT NULL,
                                                    create_time	timestamp		NOT NULL default now(),
    update_time	timestamp		NOT NULL default now(),
    key(`user_id`)

    ) engine=innodb charset=utf8;

# 3. Role
create table if not exists role (
                                    role_id	int	primary key auto_increment	NOT NULL,
                                    role_name	varchar(100)	NOT NULL,
    useabled	tinyint(1)		NOT NULL,
    create_time	timestamp		NOT NULL default now(),
    update_time	timestamp		NOT NULL default now(),
    key (`role_name`)
    ) engine=innodb charset=utf8;


# 4.Role_Auth_Relation
create table if not exists Role_Auth_Relation (
                                                  ra_id	int	primary key auto_increment	NOT NULL,
                                                  role_id	int	NOT NULL,
                                                  auth_id	int		NOT NULL,
                                                  create_time	timestamp	NOT NULL default now(),
    update_time	timestamp	NOT NULL default now(),
    key(role_id)
    ) engine=innodb charset=utf8;


# 5. Authority
create table if not exists Authority(
                                        auth_id	int	primary key auto_increment	NOT NULL,
                                        useabled	tinyint(1)	NOT NULL,
    auth_menu_name	varchar(100)		NOT NULL,
    auth_menu_path	varchar(500)		NOT NULL,
    create_time	timestamp		NOT NULL default now(),
    update_time	timestamp		NOT NULL default now()
    ) engine=innodb charset=utf8;


# 6. user_event
create table if not exists user_event(
                                         event_id	int	primary key auto_increment	NOT NULL,
                                         user_id	int	NOT NULL,
                                         user_ip	int(10)	NOT NULL,
    user_action	varchar(100)	NOT NULL,
    create_time	timestamp		NOT NULL default now(),
    key (user_id),
    key (user_ip)
    ) engine=innodb charset=utf8;


# 7. fans_follow
create table if not exists fans_follow (
                                           ff_id	int		primary key auto_increment	NOT NULL,
                                           user_id	int	NOT NULL,
                                           fans_user_id	int	NOT NULL,
                                           status	tinyint(1)		NOT NULL,
    create_time	timestamp		NOT NULL default now(),
    update_time	timestamp		NOT NULL default now(),
    key(user_id),
    key(fans_user_id)
    ) engine=innodb charset=utf8;


# 8. message
create table if not exists message (
                                       msg_id	int	primary key auto_increment		NOT NULL,
                                       from_user	int	NOT NULL,
                                       to_user	int	NOT NULL,
                                       msg_title	varchar(200)		NOT NULL,
    msg_text	varchar(1000)		NOT NULL,
    `status`	tinyint(1)		NOT NULL,
    create_time	timestamp		NOT NULL default now(),
    update_time	timestamp		NOT NULL default now()
    ) engine=innodb charset=utf8;
create index from_user on message(from_user);
alter table message add index from_user(from_user);

# 9. blog
create table if not exists blog(
                                   blog_id	int	primary key auto_increment	NOT NULL,
                                   title	varchar(500)	NOT NULL,
    front_cover_path	varchar(500)		NOT NULL,
    content	varchar(5000)		NOT NULL,
    viewer_count	int		NOT NULL default 0,
    like_count	int		NOT NULL default 0,
    user_id	int	NOT NULL,
    create_time	timestamp		NOT NULL default now(),
    update_time	timestamp		NOT NULL default now()
    ) engine=innodb charset=utf8;
alter table blog add index user_id(user_id);

## 10. blog_type
create table if not exists blog_type(
                                        type_id	int	primary key auto_increment	NOT NULL,
                                        blog_id	int	NOT NULL,
                                        type_code_key	varchar(10)	NOT NULL
    ) engine=innodb charset=utf8;
create index blog_id on blog_type(blog_id);


## 11. blog_like
create table if not exists blog_like(
                                        bl_id	int	primary key auto_increment	NOT NULL,
                                        blog_id	int	NOT NULL,
                                        user_id	int		NOT NULL,
                                        key(blog_id)
    ) engine=INNODB charset=utf8;


## 12. system_common_param
create table if not exists system_common_param (
    code_type	varchar(50)		NOT NULL,
    code_key	varchar(10)		NOT NULL,
    code_value	varchar(10)		NOT NULL,
    `desc`	varchar(1000)		NOT NULL,
    useabled	tinyint(1)		NOT NULL
    )engine=INNODB charset=utf8;