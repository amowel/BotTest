create table Account (id  bigserial not null, created bytea, password varchar(255), username varchar(255), primary key (id))
create table instagram (id  bigserial not null, password varchar(255), username varchar(255), account_id int8, primary key (id))
create table vk (id  bigserial not null, token varchar(255), vkid varchar(255), account_id int8, primary key (id))
alter table Account add constraint UK_de34gsw4qt8auhffbna969ahp unique (username)
alter table instagram add constraint UK_23t3sgucni2waxg2wtsjh7mah unique (username)
alter table instagram add constraint FKb02c8syo1peis23anhkqq2o3i foreign key (account_id) references Account
alter table vk add constraint FK9rn9mvou3uixlhdps7o9xsq47 foreign key (account_id) references Account
