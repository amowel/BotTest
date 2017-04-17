create table user_connections IF NOT EXISTS (id  bigserial not null, inst_password varchar(255), token varchar(255), inst_username varchar(255), vk_id int8, primary key (id))
alter table user_connections add constraint UK_rcpxum6q8cs55vl9yyhwu4gw8 unique (inst_username)
alter table user_connections add constraint UK_969mq2jooa2ilmcp8tr7t5htp unique (vk_id)
