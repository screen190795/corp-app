-- Table: public.incident

-- DROP TABLE public.incident;

CREATE TABLE IF NOT EXISTS public.incident
(
    id integer NOT NULL,
    title character varying COLLATE pg_catalog."default",
    priority character varying COLLATE pg_catalog."default",
    reporter_id integer NOT NULL,
    assignee_id integer,
    status character varying COLLATE pg_catalog."default",
    comment character varying COLLATE pg_catalog."default",
    CONSTRAINT incident_pkey PRIMARY KEY (id),
    CONSTRAINT assignee_key FOREIGN KEY (assignee_id)
        REFERENCES public.staff (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT reporter_key FOREIGN KEY (reporter_id)
        REFERENCES public.reporter (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.incident
    OWNER to postgres;


-- Table: public.manager

-- DROP TABLE public.manager;

CREATE TABLE IF NOT EXISTS public.manager
(
    id integer NOT NULL,
    fio character varying COLLATE pg_catalog."default",
    user_id integer NOT NULL,
    CONSTRAINT manager_pkey PRIMARY KEY (id),
    CONSTRAINT fklx8n4x9vqj3lqv8cj9ienwrv6 FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_key FOREIGN KEY (id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.manager
    OWNER to postgres;


    -- Table: public.reporter

    -- DROP TABLE public.reporter;

    CREATE TABLE IF NOT EXISTS public.reporter
    (
        id integer NOT NULL,
        fio character varying COLLATE pg_catalog."default",
        email character varying COLLATE pg_catalog."default" NOT NULL,
        phone character varying COLLATE pg_catalog."default",
        user_id integer NOT NULL,
        CONSTRAINT reporter_pkey PRIMARY KEY (id),
        CONSTRAINT fkgtud0n4xlqd2aextve08f5bu4 FOREIGN KEY (user_id)
            REFERENCES public."user" (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
        CONSTRAINT user_key FOREIGN KEY (id)
            REFERENCES public."user" (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
            NOT VALID
    )

    TABLESPACE pg_default;

    ALTER TABLE public.reporter
        OWNER to postgres;


-- Table: public.staff

-- DROP TABLE public.staff;

CREATE TABLE IF NOT EXISTS public.staff
(
    id integer NOT NULL,
    fio character varying COLLATE pg_catalog."default",
    manager_id integer,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    phone character varying COLLATE pg_catalog."default",
    user_id integer NOT NULL,
    CONSTRAINT staff_pkey PRIMARY KEY (id),
    CONSTRAINT fkbhogfndgswrqk696i1s2stk2g FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT manager_key FOREIGN KEY (manager_id)
        REFERENCES public.manager (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT user_key FOREIGN KEY (id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE public.staff
    OWNER to postgres;

    -- Table: public.user

    -- DROP TABLE public."user";

    CREATE TABLE IF NOT EXISTS public."user"
    (
        active boolean NOT NULL,
        password character varying(255) COLLATE pg_catalog."default" NOT NULL,
        roles character varying(255) COLLATE pg_catalog."default" NOT NULL,
        username character varying(255) COLLATE pg_catalog."default" NOT NULL,
        id integer NOT NULL,
        CONSTRAINT user_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

    ALTER TABLE public."user"
        OWNER to postgres;