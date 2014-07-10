# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table topic (
  topic_id                  bigint not null,
  topic_name                varchar(255),
  topic_subject             varchar(255),
  topic_comment             varchar(255),
  constraint pk_topic primary key (topic_id))
;

create sequence topic_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists topic;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists topic_seq;

