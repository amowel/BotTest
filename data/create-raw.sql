create table account (id  bigserial not null, created bytea, password varchar(255), username varchar(255), instagram_connection_id int8, vk_connection_id int8, primary key (id))
create table instagram (id  bigserial not null, connected boolean, password varchar(255), username varchar(255), primary key (id))
create table user_connections (id  bigserial not null, inst_password varchar(255), token varchar(255), inst_username varchar(255), vk_id int8, primary key (id))
create table vk (id  bigserial not null, connected boolean, token varchar(255), vkid varchar(255), primary key (id))
alter table account add constraint UK_gex1lmaqpg0ir5g1f5eftyaa1 unique (username)
alter table instagram add constraint UK_23t3sgucni2waxg2wtsjh7mah unique (username)
alter table user_connections add constraint UK_rcpxum6q8cs55vl9yyhwu4gw8 unique (inst_username)
alter table user_connections add constraint UK_969mq2jooa2ilmcp8tr7t5htp unique (vk_id)
alter table account add constraint FKktkhc1gen934a45ftlw6lpeki foreign key (instagram_connection_id) references instagram
alter table account add constraint FKameijixfvvsgixbhqgwi0yk31 foreign key (vk_connection_id) references vk
