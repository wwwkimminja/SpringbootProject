create table public.members (
  member_id serial not null
  , member_name character varying(100) not null
  , member_name_hiragana character varying(100) not null
  , member_part character varying(100) not null
  , member_part_hiragana character varying(100) not null
  , member_tel character varying(100) not null
  , member_email character varying(100) not null
  , member_password character varying(100) not null
  , member_auth smallint default 0 not null
  , member_reg_date timestamp(6) without time zone default now() not null
  , member_mod_date timestamp(6) without time zone default now()
  , primary key (member_id)
);
