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

INSERT 
INTO public.members( 
    member_name
    , member_name_hiragana
    , member_part
    , member_part_hiragana
    , member_tel
    , member_email
    , member_password
    , member_auth
    , member_reg_date
) 
VALUES ( 
    'ã‡ùﬂéq','Ç´ÇﬁÇ›ÇÒÇ∂Ç·','ëçñ±','ÇªÇ§Çﬁ','1234','minja_k@abc.ne.jp','1234',1,'2017/7/1');

DROP TABLE public.members CASCADE;

