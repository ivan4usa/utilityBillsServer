--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Homebrew)
-- Dumped by pg_dump version 14.5 (Homebrew)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: utility_db; Type: SCHEMA; Schema: -; Owner: root
--

CREATE SCHEMA utility_db;


ALTER SCHEMA utility_db OWNER TO root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account; Type: TABLE; Schema: utility_db; Owner: root
--

CREATE TABLE utility_db.account (
    id bigint NOT NULL,
    icon character varying(100),
    company character varying(255),
    edrpou character varying(8),
    account_number character varying(30),
    house_id bigint NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE utility_db.account OWNER TO root;

--
-- Name: Account_id_seq; Type: SEQUENCE; Schema: utility_db; Owner: root
--

ALTER TABLE utility_db.account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME utility_db."Account_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: bill; Type: TABLE; Schema: utility_db; Owner: root
--

CREATE TABLE utility_db.bill (
    id bigint NOT NULL,
    amount numeric(12,2) DEFAULT 0.00 NOT NULL,
    counter_start integer,
    counter_end integer,
    comment character varying(20),
    tariff character varying(255),
    account_id bigint NOT NULL,
    date_start date NOT NULL,
    date_end date NOT NULL
);


ALTER TABLE utility_db.bill OWNER TO root;

--
-- Name: bill_id_seq; Type: SEQUENCE; Schema: utility_db; Owner: root
--

ALTER TABLE utility_db.bill ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME utility_db.bill_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: house; Type: TABLE; Schema: utility_db; Owner: root
--

CREATE TABLE utility_db.house (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    address character varying(255),
    city character varying(100),
    country character varying(100),
    zip character varying(5),
    user_id bigint NOT NULL
);


ALTER TABLE utility_db.house OWNER TO root;

--
-- Name: house_id_seq; Type: SEQUENCE; Schema: utility_db; Owner: root
--

ALTER TABLE utility_db.house ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME utility_db.house_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: payment; Type: TABLE; Schema: utility_db; Owner: root
--

CREATE TABLE utility_db.payment (
    id bigint NOT NULL,
    amount numeric(12,2) DEFAULT 0.00 NOT NULL,
    account_id bigint NOT NULL,
    bill_id bigint,
    pay_date date NOT NULL,
    payment_file bytea
);


ALTER TABLE utility_db.payment OWNER TO root;

--
-- Name: payment_id_seq; Type: SEQUENCE; Schema: utility_db; Owner: root
--

ALTER TABLE utility_db.payment ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME utility_db.payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: user; Type: TABLE; Schema: utility_db; Owner: root
--

CREATE TABLE utility_db."user" (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(100) NOT NULL,
    first_name character varying(40),
    last_name character varying(40)
);


ALTER TABLE utility_db."user" OWNER TO root;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: utility_db; Owner: root
--

ALTER TABLE utility_db."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME utility_db.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: account; Type: TABLE DATA; Schema: utility_db; Owner: root
--

COPY utility_db.account (id, icon, company, edrpou, account_number, house_id, name) FROM stdin;
12	faBurn	Gas Company	1111111	1234567890	14	Gas
13	faTint	Water Company	222222	987654321	14	Water
\.


--
-- Data for Name: bill; Type: TABLE DATA; Schema: utility_db; Owner: root
--

COPY utility_db.bill (id, amount, counter_start, counter_end, comment, tariff, account_id, date_start, date_end) FROM stdin;
30	26.00	0	13		2	13	2022-01-01	2022-01-01
31	42.00	0	14		3	12	2022-01-01	2022-01-01
33	12.00	0	4		3	12	2022-01-01	2022-01-01
\.


--
-- Data for Name: house; Type: TABLE DATA; Schema: utility_db; Owner: root
--

COPY utility_db.house (id, name, address, city, country, zip, user_id) FROM stdin;
14	My House			Ukraine		1
15	new	1340 Walnut st, apt 3	Baraboo	United States	53913	1
\.


--
-- Data for Name: payment; Type: TABLE DATA; Schema: utility_db; Owner: root
--

COPY utility_db.payment (id, amount, account_id, bill_id, pay_date, payment_file) FROM stdin;
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: utility_db; Owner: root
--

COPY utility_db."user" (id, email, password, first_name, last_name) FROM stdin;
1	test2@mail.com	$2a$10$/oeUsSL4B5X0mY/BR2S42.DaviNjiWstYYW397rWPLKwhuqcM/qX2	Ivan	Pol\n
3	test@mail.com	$2a$10$bRlmC6jLFPmuJX0StGamfOQ6D43W7nJRCog7P0zVgcmPKzdjjtIfy	\N	\N
4	test3@mail.com	$2a$10$n5foO6zjDgOkRf2qAgEbT..DpuckpQGpneeTzrDTXqqz8/BfJMYdS	\N	\N
16	test4@mail.com	$2a$10$9CMsXgwoPxlrrCSudWkPR.gdqT3RHK0D8t./sJU1IVnE.adgvc2B.	\N	\N
17	test5@mail.com	$2a$10$pCxhJTWmgOE7gBBFEXaIWuZqfAu9I7hefuOZUj3idsw6vehOdFTP.	\N	\N
18	test6@mail.com	$2a$10$aIgDoyf0WJcRo67l6ATQ2evD1B77dQ7Ze6vawxVn5QNjyMymkXc5m	\N	\N
19	test7@mail.com	$2a$10$Uso.j9qoCbYYQFSfwQ07v.MDpbBh4rvZSXDqXVXGHM8YkBYEAx8Dq	\N	\N
\.


--
-- Name: Account_id_seq; Type: SEQUENCE SET; Schema: utility_db; Owner: root
--

SELECT pg_catalog.setval('utility_db."Account_id_seq"', 15, true);


--
-- Name: bill_id_seq; Type: SEQUENCE SET; Schema: utility_db; Owner: root
--

SELECT pg_catalog.setval('utility_db.bill_id_seq', 35, true);


--
-- Name: house_id_seq; Type: SEQUENCE SET; Schema: utility_db; Owner: root
--

SELECT pg_catalog.setval('utility_db.house_id_seq', 18, true);


--
-- Name: payment_id_seq; Type: SEQUENCE SET; Schema: utility_db; Owner: root
--

SELECT pg_catalog.setval('utility_db.payment_id_seq', 97, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: utility_db; Owner: root
--

SELECT pg_catalog.setval('utility_db.user_id_seq', 51, true);


--
-- Name: account Account_pkey; Type: CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db.account
    ADD CONSTRAINT "Account_pkey" PRIMARY KEY (id);


--
-- Name: bill bill_pkey; Type: CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db.bill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (id);


--
-- Name: house house_pkey; Type: CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db.house
    ADD CONSTRAINT house_pkey PRIMARY KEY (id);


--
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- Name: user unique_email; Type: CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db."user"
    ADD CONSTRAINT unique_email UNIQUE (email);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: fki_FK_account_house; Type: INDEX; Schema: utility_db; Owner: root
--

CREATE INDEX "fki_FK_account_house" ON utility_db.account USING btree (house_id);


--
-- Name: fki_FK_bill_account; Type: INDEX; Schema: utility_db; Owner: root
--

CREATE INDEX "fki_FK_bill_account" ON utility_db.bill USING btree (account_id);


--
-- Name: fki_FK_house_user; Type: INDEX; Schema: utility_db; Owner: root
--

CREATE INDEX "fki_FK_house_user" ON utility_db.house USING btree (user_id);


--
-- Name: fki_FK_payment_account; Type: INDEX; Schema: utility_db; Owner: root
--

CREATE INDEX "fki_FK_payment_account" ON utility_db.payment USING btree (account_id);


--
-- Name: fki_FK_payment_bill; Type: INDEX; Schema: utility_db; Owner: root
--

CREATE INDEX "fki_FK_payment_bill" ON utility_db.payment USING btree (bill_id);


--
-- Name: account FK_account_house; Type: FK CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db.account
    ADD CONSTRAINT "FK_account_house" FOREIGN KEY (house_id) REFERENCES utility_db.house(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: bill FK_bill_account; Type: FK CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db.bill
    ADD CONSTRAINT "FK_bill_account" FOREIGN KEY (account_id) REFERENCES utility_db.account(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: house FK_house_user; Type: FK CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db.house
    ADD CONSTRAINT "FK_house_user" FOREIGN KEY (user_id) REFERENCES utility_db."user"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: payment FK_payment_account; Type: FK CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db.payment
    ADD CONSTRAINT "FK_payment_account" FOREIGN KEY (account_id) REFERENCES utility_db.account(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: payment FK_payment_bill; Type: FK CONSTRAINT; Schema: utility_db; Owner: root
--

ALTER TABLE ONLY utility_db.payment
    ADD CONSTRAINT "FK_payment_bill" FOREIGN KEY (bill_id) REFERENCES utility_db.bill(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


--
-- PostgreSQL database dump complete
--

