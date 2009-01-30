--
-- 2008-11 Update Script
-- Execute as user postgres (create rights!! ) in db unartig
--
--
-- Table: producttypes

-- DROP TABLE producttypes;

CREATE TABLE producttypes
(
  producttypeid bigint NOT NULL,
  "name" character varying(255) NOT NULL,
  description character varying(255) NOT NULL,
  digitalproduct boolean NOT NULL,
  CONSTRAINT producttypes_pkey PRIMARY KEY (producttypeid)
)
WITH (OIDS=FALSE);
ALTER TABLE producttypes OWNER TO unartig;

----------------------------------------------------------------------------
-- Table: prices

CREATE TABLE prices
(
  priceid bigint NOT NULL,
  pricechf numeric(19,2) NOT NULL,
  priceeur numeric(19,2) NOT NULL,
  pricegbp numeric(19,2) NOT NULL,
  pricesek numeric(19,2) NOT NULL,
  "comment" character varying(255) NOT NULL,
  CONSTRAINT prices_pkey PRIMARY KEY (priceid)
)
WITH (OIDS=FALSE);
ALTER TABLE prices OWNER TO unartig;

----------------------------------------------------------------------------
-- Table: prices2producttypes

-- DROP TABLE prices2producttypes;

CREATE TABLE prices2producttypes
(
  producttypeid bigint NOT NULL,
  priceid bigint NOT NULL,
  CONSTRAINT prices2producttypes_pkey PRIMARY KEY (producttypeid, priceid),
  CONSTRAINT fke4bf89f21d9cd042 FOREIGN KEY (priceid)
      REFERENCES prices (priceid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fke4bf89f26d4eef62 FOREIGN KEY (producttypeid)
      REFERENCES producttypes (producttypeid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE prices2producttypes OWNER TO unartig;

-----------------------------------------------------------------------------
--
-- productType
--
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (2, 'digifoto 400 x 600', 'digital foto for download 400 by 600 pixels', true);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (3, 'Hochaufgelšst', 'digital foto file for download in the native resolution', true);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (4, '10x15cm Abzug', 'paper print 10 x 15 cm provided by colorplaza', false);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (5, '11x17cm Abzug', 'paper print 11 x 17 cm  provided by colorplaza', false);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (6, '13x19cm Abzug', 'paper print 13 x 19 cm provided by colorplaza', false);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (7, '20x30cm Abzug', 'paper print 20 x 30 cm provided by colorplaza', false);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (8, '30x45cm Poster', 'poster 30 x 45 cm provided by colorplaza', false);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (9, '40x60cm Poster', 'poster 40 x 60 cm provided by colorplaza', false);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (10, '50x75cm Poster', 'poster 50 x 75 cm provided by colorplaza', false);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (11, 'T-Shirt', 'T-Shirt provided by colorplaza', false);
INSERT INTO producttypes (producttypeid, name, description, digitalproduct) VALUES (12, 'Mousepad', 'mousepad provided by colorplaza', false);
-- Completed on 2007-05-03 20:01:35 W. Europe Daylight Time

--
-- prices
--
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (1, 5.00, 3.30, 2.30, 29.50, '23x2-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (8, 39.50, 26.00, 18.00, 235.00, '23x2-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (7, 54.00, 35.90, 24.00, 319.00, '23x2-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (6, 42.00, 27.50, 18.90, 249.00, '23x2-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (5, 29.00, 18.90, 12.90, 169.00, '23x2-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (4, 14.00, 8.90, 6.20, 82.00, '23x2-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (3, 7.00, 4.60, 3.20, 42.00, '23x2-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (2, 5.50, 3.60, 2.45, 32.50, '23x2-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (9, 29.00, 19.00, 12.90, 179.00, '23x2-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (10, 10.00, 6.50, 4.40, 59.00, '251x-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (18, 39.00, 26.00, 17.50, 230.00, '251x-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (17, 49.50, 32.00, 22.00, 290.00, '251x-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (16, 99.00, 65.00, 44.00, 580.00, '251x-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (15, 79.00, 52.00, 35.00, 470.00, '251x-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (14, 49.00, 32.00, 22.00, 290.00, '251x-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (13, 28.00, 18.00, 12.50, 165.00, '251x-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (12, 14.00, 9.50, 6.20, 84.00, '251x-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (11, 12.00, 7.80, 5.30, 69.00, '251x-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (19, 0.50, 0.29, 0.20, 2.55, '21xx-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (20, 0.55, 0.32, 0.21, 2.65, '21xx-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (21, 1.10, 0.75, 0.55, 6.45, '21xx-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (22, 3.90, 2.30, 1.70, 19.95, '21xx-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (23, 17.00, 11.50, 8.00, 79.00, '21xx-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (24, 27.00, 15.50, 12.00, 109.00, '21xx-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (25, 37.00, 21.50, 16.00, 179.00, '21xx-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (26, 29.50, 17.90, 11.50, 139.00, '21xx-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (27, 16.00, 9.00, 6.00, 79.00, '21xx-er Liste');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (28, 2.90, 1.90, 1.10, 14.30, 'unartig price, digital product');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (29, 9.90, 6.50, 4.60, 48.80, 'unartig price, digital');
-- Completed on 2007-05-03 19:59:00 W. Europe Daylight Time

--
-- Prices2Producttypes
--
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (2, 28);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (2, 22);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (2, 19);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (3, 26);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (3, 29);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (3, 2);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (4, 1);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (4, 10);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (4, 19);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (5, 2);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (5, 11);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (5, 20);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (6, 3);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (6, 12);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (6, 21);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (7, 4);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (7, 13);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (7, 22);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (8, 5);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (8, 14);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (8, 23);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (9, 6);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (9, 15);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (9, 24);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (10, 7);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (10, 16);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (10, 25);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (12, 9);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (12, 18);
INSERT INTO prices2producttypes (producttypeid, priceid) VALUES (12, 27);

-----------------------------------------------------------------------------
-- update new columns

-- Column: albumid

-- ALTER TABLE products DROP COLUMN albumid;

ALTER TABLE products ADD COLUMN albumid bigint;
ALTER TABLE products ALTER COLUMN albumid SET STORAGE PLAIN;

-- Column: priceid

-- ALTER TABLE products DROP COLUMN priceid;

ALTER TABLE products ADD COLUMN priceid bigint;
ALTER TABLE products ALTER COLUMN priceid SET STORAGE PLAIN;

-- Column: producttypeid

-- ALTER TABLE products DROP COLUMN producttypeid;

ALTER TABLE products ADD COLUMN producttypeid bigint;
ALTER TABLE products ALTER COLUMN producttypeid SET STORAGE PLAIN;

-----------------------------------------------------------------------------

-- Insert User Profile and Role Tables

-- Table: userroles

-- DROP TABLE userroles;

CREATE TABLE userroles
(
  userroleid bigint NOT NULL,
  rolename character varying(255),
  roledescription character varying(255),
  CONSTRAINT userroles_pkey PRIMARY KEY (userroleid)
)
WITH (OIDS=FALSE);
ALTER TABLE userroles OWNER TO unartig;



-- Table: userprofiles

-- DROP TABLE userprofiles;

CREATE TABLE userprofiles
(
  userprofileid bigint NOT NULL,
  username character varying(255) NOT NULL,
  "password" character varying(255) NOT NULL,
  firstname character varying(255) NOT NULL,
  lastname character varying(255) NOT NULL,
  emailaddress character varying(255) NOT NULL,
  phone character varying(255) NOT NULL,
  phonemobile character varying(255) NOT NULL,
  title character varying(255),
  addr1 character varying(255) NOT NULL,
  addr2 character varying(255),
  zipcode character varying(255) NOT NULL,
  city character varying(255) NOT NULL,
  state character varying(255),
  country character varying(255) NOT NULL,
  gender character varying(1) NOT NULL,
  CONSTRAINT userprofiles_pkey PRIMARY KEY (userprofileid),
  CONSTRAINT userprofiles_username_key UNIQUE (username)
)
WITH (OIDS=FALSE);
ALTER TABLE userprofiles OWNER TO unartig;



-- Table: userprofiles2userroles

-- DROP TABLE userprofiles2userroles;

CREATE TABLE userprofiles2userroles
(
  username character varying(255) NOT NULL,
  rolename character varying(255) NOT NULL,
  CONSTRAINT userprofiles2userroles_pkey PRIMARY KEY (username, rolename)
)
WITH (OIDS=FALSE);
ALTER TABLE userprofiles2userroles OWNER TO unartig;


-----------------------------------------------------------------------------
-- Alter not null constraint for unused product columns

ALTER TABLE products
   ALTER COLUMN productname DROP NOT NULL;

ALTER TABLE products
   ALTER COLUMN oipspid DROP NOT NULL;

ALTER TABLE products
   ALTER COLUMN oipsname DROP NOT NULL;


ALTER TABLE products
   ALTER COLUMN oipspricechf DROP NOT NULL;

ALTER TABLE products
   ALTER COLUMN oipspriceeur DROP NOT NULL;


ALTER TABLE products
   ALTER COLUMN oipspricegbp DROP NOT NULL;

ALTER TABLE products
   ALTER COLUMN oipspricesek DROP NOT NULL;
ALTER TABLE products
   ALTER COLUMN oipswidth DROP NOT NULL;
ALTER TABLE products
   ALTER COLUMN oipsheight DROP NOT NULL;
ALTER TABLE products
   ALTER COLUMN oipsvariablelength DROP NOT NULL;
ALTER TABLE products
   ALTER COLUMN oipsgroupid DROP NOT NULL;


-----------------------------------------------------------------------------
-- Call stored procedure to insert new products:

select insertProductsForAlbums();

-- Create additional prices for 'legacy' products:

INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (30, 3.00, 2.00, 1.10, 14.30, 'unartig price, digital legacy product');
INSERT INTO prices (priceid, pricechf, priceeur, pricegbp, pricesek, "comment") VALUES (31, 29.90, 19.00, 4.60, 48.80, 'unartig price, legazy digital product');


-----------------------------------------------------------------------------
-- Temp table productmigration


CREATE TABLE productmigration (
    productid bigint NOT NULL,
    oipspid character varying(255),
    priceid bigint,
    producttypeid bigint
);


--
-- TOC entry 1680 (class 0 OID 3007473)
-- Dependencies: 1343
-- Data for Name: productmigration; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (1, '6301', 1, 4);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (2, '6501', 10, 4);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (3, '6304', 2, 5);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (4, '6504', 11, 5);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (5, '6302', 3, 6);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (6, '6502', 12, 6);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (7, '6303', 4, 7);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (8, '6503', 13, 7);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (9, '6324', 5, 8);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (10, '6524', 14, 8);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (11, '6317', 6, 9);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (12, '6517', 15, 9);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (13, '6310', 7, 10);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (14, '6510', 16, 10);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (15, '6316', 9, 12);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (16, '6516', 18, 12);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (19, '998', 29, 3);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (17, '998', 30, 2);
INSERT INTO productmigration (productid, oipspid, priceid, producttypeid) VALUES (20, '998', 31, 3);


--
-- TOC entry 1679 (class 2606 OID 3007476)
-- Dependencies: 1343 1343
-- Name: productmigration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY productmigration
    ADD CONSTRAINT productmigration_pkey PRIMARY KEY (productid);


ALTER TABLE products
   ADD COLUMN inactive boolean;

-----------------------------------------------------------------------------
-- Problem: we need to insert 'legacy' products that have been used but later the album changed the price segment and so the entry in orderitems changed.
-- Solution: insert additional column 'inactive' in products that is used to filter out inactive products. otherwise the customer would see many similar products with different prices.
-----------------------------------------------------------------------------

--Adding products for albums that changed prices!!! (this products will be flagged inactive so that they are not for sale anymore but still available for the reporting over the orderitems)
INSERT INTO products (productid,productname,inactive,priceid,producttypeid,albumid)
	select  nextval('sequence_productid'),'anpassungen',true,pm.priceid,pm.producttypeid,photos.albumid
  	from orderitems oi
  	join productmigration pm on pm.productid = oi.productid
  	join photos on photos.photoid = oi.photoid
  	where photos.albumid not in (select p.albumid
		from products p
		where p.priceid = pm.priceid
		and p.producttypeid = pm.producttypeid)
  	group by oi.productid,photos.albumid,pm.priceid,pm.producttypeid;


-----------------------------------------------------------------------------
-- update query for changing products of orderitems to new products:
-- ### CHECK THIS ####

update orderitems oi set productid =
  (select p.productid
  from products p
  join productmigration pm on pm.productid = oi.productid
  where p.priceid = pm.priceid
  and p.producttypeid = pm.producttypeid
  and p.albumid = photos.albumid)
from photos
where photos.photoid = oi.photoid;


-- Change Table "customer" to "customers"
ALTER TABLE customer RENAME TO customers;


--
-- DONE
--



