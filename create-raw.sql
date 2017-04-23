create table Account (id  bigserial not null, created bytea, password varchar(255), username varchar(255), instagramConnection_id int8, vkConnection_id int8, primary key (id))
create table instagram (id  bigserial not null, password varchar(255), username varchar(255), primary key (id))
create table vk (id  bigserial not null, token varchar(255), vkid varchar(255), primary key (id))
alter table Account add constraint UK_de34gsw4qt8auhffbna969ahp unique (username)
alter table instagram add constraint UK_23t3sgucni2waxg2wtsjh7mah unique (username)
alter table Account add constraint FKkcyie4iqy8qg49hvitngn5c1k foreign key (instagramConnection_id) references instagram
alter table Account add constraint FKtpyobi0qpa7ueiiu7ffdawmtd foreign key (vkConnection_id) references vk
