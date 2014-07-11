# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table comment (
  comment_id                bigint not null,
  topic_topic_id            bigint,
  comment_text              varchar(255),
  comment_date              timestamp,
  constraint pk_comment primary key (comment_id))
;

create table topic (
  topic_id                  bigint not null,
  topic_name                varchar(255),
  topic_subject             varchar(255),
  topic_comment             varchar(255),
  creation_date             timestamp,
  constraint pk_topic primary key (topic_id))
;

create sequence comment_seq;

create sequence topic_seq;

alter table comment add constraint fk_comment_topic_1 foreign key (topic_topic_id) references topic (topic_id) on delete restrict on update restrict;
create index ix_comment_topic_1 on comment (topic_topic_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists comment;

drop table if exists topic;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists comment_seq;

drop sequence if exists topic_seq;

