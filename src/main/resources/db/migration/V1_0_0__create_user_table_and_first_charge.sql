CREATE TABLE public.user
(
    id int8 NOT NULL,
    name varchar(255) NOT NULL ,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

INSERT INTO public.user (id, name) VALUES (1, 'Jubileu');