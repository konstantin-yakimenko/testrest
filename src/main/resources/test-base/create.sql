CREATE SCHEMA interest;

SET search_path TO interest,public;

CREATE TABLE interest.city
(
  city_id bigserial primary key,
  title   varchar(100) not null
);
CREATE TABLE interest.country
(
  country_id bigserial primary key,
  title      varchar(100) not null
);

CREATE TYPE interest.sex AS ENUM ('m', 'f');

CREATE TABLE interest.person
(
  person_id      bigserial primary key,
  name           varchar(50)              not null,
  sex            sex                      not null,
  birth          timestamp,
  email          varchar(100) unique      null,
  approve_email  timestamp WITH TIME ZONE null,
  phone_number   varchar(16) unique       null,
  approve_phone  timestamp WITH TIME ZONE null,
  country_id     bigint,
  city_id        bigint,
  status         varchar(200),
  premium_start  timestamp WITH TIME ZONE,
  premium_finish timestamp WITH TIME ZONE,
  about          text                     null,
  create_date    timestamp WITH TIME ZONE not null default now(),
  update_date    timestamp with time zone not null default now(),
  constraint person_country_fkey foreign key (country_id)
    references country (country_id)
    on update no action on delete no action,
  constraint person_city_fkey foreign key (city_id)
    references city (city_id)
    on update no action on delete no action
);

CREATE OR REPLACE FUNCTION interest.update_person_update_date()
  RETURNS TRIGGER AS $$
BEGIN
  NEW.update_date = now();
  RETURN NEW;
END;
$$ language 'plpgsql'
;
CREATE TRIGGER update_person_date BEFORE UPDATE ON person FOR EACH ROW EXECUTE PROCEDURE  update_person_update_date();

CREATE TABLE interest.interest
(
  interest_id bigserial primary key,
  person_id   bigint not null,
  title       text   not null,
  create_date timestamp WITH TIME ZONE not null default now(),
  update_date timestamp with time zone not null default now(),
  constraint interest_person_fkey foreign key (person_id)
    references person (person_id)
    on update cascade on delete cascade
);

CREATE OR REPLACE FUNCTION interest.update_interest_update_date()
  RETURNS TRIGGER AS $$
BEGIN
  NEW.update_date = now();
  RETURN NEW;
END;
$$ language 'plpgsql'
;
CREATE TRIGGER update_interest_date BEFORE UPDATE ON interest FOR EACH ROW EXECUTE PROCEDURE  update_interest_update_date();

insert into city(title)
values ('Moscow'), ('New-York'), ('London');
insert into country(title)
values ('Russia'), ('USA'), ('Great Britain');
