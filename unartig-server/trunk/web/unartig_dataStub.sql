--
-- Unartig Studio Server data (no rowid of 0 for mysql)
--

--
-- Price Segments
--
-- 3-er segment:
INSERT INTO PriceSegments (priceSegmentId, priceSegmentName) VALUES (1, '10x15 fuer 5.00 CHF');
-- 5-er segment:
INSERT INTO PriceSegments (priceSegmentId, priceSegmentName) VALUES (2, '10x15 fuer 10.00 CHF');
-- unused segment:
insert into PriceSegments (pricesegmentId,priceSegmentName) values (99,'unused');

--
-- Set up category "Festliche Anlaesse", Sportliche Anlaesse
--
INSERT INTO GenericLevels (genericlevelid, hierarchy_level,navtitle,longtitle,description) VALUES (1, 'CATEGORY','fest_anlaesse','Festliche Anlässe','Bälle und andere festliche Anlässe');
INSERT INTO GenericLevels (genericlevelid, hierarchy_level,navtitle,longtitle,description) VALUES (2, 'CATEGORY','sport_anlaesse','Sportliche Anlässe','Sportliche Anlässe');

--
-- Product List (one entry for each oips product)
--
-- 10 er format
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (1,'10x15','6301','10-er Format','500',330,230,2950,130,180,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (2,'10x15','6501','10-er Format','1000',650,440,5900,130,180,true,1,2);
-- 11 er format
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (3,'11x17','6304','11-er Format','550',360,245,3250,100,150,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (4,'11x17','6504','11-er Format','1200',780,530,6900,180,240,true,1,2);
-- 13 er format
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (5,'13x19','6302','13-er Format','700',460,320,4200,180,240,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (6,'13x19','6502','13-er Format','1400',950,620,8400,180,240,true,1,2);
-- 20 er format
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (7,'20x30','6303','20-er Format','1400',890,620,8200,180,240,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (8,'20x30','6503','20-er Format','2800',1800,1250,16500,180,240,true,1,2);
-- Poster 30 x 45
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (9,'30x45','6324','Poster 30 x 45','2900',1890,1290,16900,180,240,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (10,'30x45','6524','Poster 30 x 45','4900',3200,2200,29000,180,240,true,1,2);
-- Poster 40 x 60
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (11,'40x60','6317','Poster 40 x 60','4200',2750,1890,24900,180,240,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (12,'40x60','6517','Poster 40 x 60','7900',5200,3500,47000,180,240,true,1,2);
-- Poster 50 x 75
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (13,'50x75','6310','Poster 50 x 75','5400',3590,2400,31900,180,240,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (14,'50x75','6510','Poster 50 x 75','9900',6500,4400,58000,180,240,true,1,2);
-- Foto Mouse Pad
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (15,'Photo Mouse Pad','6316','Photo Mouse Pad','2900',1900,1290,17000,180,240,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (16,'Photo Mouse Pad','6516','Photo Mouse Pad','3900',2600,1750,23000,180,240,true,1,2);
-- NEW
-- Digi photos
-- IMPORTANT: adjust the oipsPIDs here; will always be the same pid
-- Product name will be shown in the shopping cart
-- ##
-- Digi photo 600 x 400
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (17,'Digi Foto 600x400','998','Digi Foto 600x400',295,190,99999,99999,600,400,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (18,'Digi Foto 600x400','998','Digi Foto 600x400','800',360,99999,99999,600,400,true,1,99);
-- Digi photo original
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (19,'Digitales Negativ','998','Digitales Negativ','990',650,99999,99999,600,400,true,1,1);
INSERT INTO Products (productid,productname,oipspid,oipsname,oipsPriceCHF,oipsPriceEUR,oipsPriceGBP,oipsPriceSEK,oipswidth,oipsheight,oipsvariablelength,oipsgroupid,priceSegmentId) VALUES (20,'Digitales Negativ','998','Digitales Negativ','2990',1900,99999,99999,600,400,true,1,2);
-- todo: tshirts, price segments


-- Views (tables instead of views are created by hbm2dll. delete first)


    CREATE OR REPLACE VIEW vReportProdCount as
    select distinct customer.lastname,customer.country as "country",event.longtitle as "event",album.navtitle as "album",album.genericlevelid as "albumid",quantity, orders.uploadcompleteddate as "date", orders.orderid as "orderid",
    CASE WHEN prod.productid=1 THEN count(prod.productid) ELSE 0    END as "10-er_1",
    CASE WHEN prod.productid=2 THEN count(prod.productid) ELSE 0    END as "10-er_2",
    CASE WHEN prod.productid=3 THEN count(prod.productid) ELSE 0    END as "11-er_1",
    CASE WHEN prod.productid=4 THEN count(prod.productid) ELSE 0    END as "11-er_2",
    CASE WHEN prod.productid=5 THEN count(prod.productid) ELSE 0    END as "13-er_1",
    CASE WHEN prod.productid=6 THEN count(prod.productid) ELSE 0    END as "13-er_2",
    CASE WHEN prod.productid=7 THEN count(prod.productid) ELSE 0    END as "20-er_1",
    CASE WHEN prod.productid=8 THEN count(prod.productid) ELSE 0    END as "20-er_2",
    CASE WHEN prod.productid=9 THEN count(prod.productid) ELSE 0    END as "30x45_1",
    CASE WHEN prod.productid=10 THEN count(prod.productid) ELSE 0    END as "30x45_2",
    CASE WHEN prod.productid=11 THEN count(prod.productid) ELSE 0    END as "40x60_1",
    CASE WHEN prod.productid=12 THEN count(prod.productid) ELSE 0    END as "40x60_2",
    CASE WHEN prod.productid=13 THEN count(prod.productid) ELSE 0    END as "50x75_1",
    CASE WHEN prod.productid=14 THEN count(prod.productid) ELSE 0    END as "50x75_2",
    CASE WHEN prod.productid=15 THEN count(prod.productid) ELSE 0    END as "MousePad_1",
    CASE WHEN prod.productid=16 THEN count(prod.productid) ELSE 0    END as "MousePad_2",
    CASE WHEN prod.productid=17 THEN count(prod.productid) ELSE 0    END as "DigiFoto_1",
    CASE WHEN prod.productid=18 THEN count(prod.productid) ELSE 0    END as "DigiFoto_2",
    CASE WHEN prod.productid=19 THEN count(prod.productid) ELSE 0    END as "DigitalNegative_1",
    CASE WHEN prod.productid=20 THEN count(prod.productid) ELSE 0    END as "DigitalNegative_2",
    sum (prod.oipspricechf / 100.0) * quantity as "TOTAL"
    from orderitems oi
    inner join orders on oi.orderid = orders.orderid
    inner join customer on orders.customerid = customer.customerid
    inner join photos p on p.photoid = oi.photoid
    inner join products prod on oi.productid = prod.productid
    inner join genericlevels album on p.albumid = album.genericlevelid
    inner join genericlevels event on album.eventid = event.genericlevelid
    group by event,album,prod.productid,quantity,orders.uploadcompleteddate,album.genericlevelid,orders.orderid,customer.lastname,country
    order by event,album;


CREATE OR REPLACE VIEW vReportConsProdCountByOrder as
Select event,album,albumid,date,orderid,country,
	CASE WHEN country='CHE' THEN orderid END  as "orderid_chf",
	CASE WHEN country='DEU' THEN orderid END  as "orderid_eur",
	CASE WHEN country='CHE' THEN sum(quantity * "10-er_1" + quantity * "10-er_2") ELSE 0 END  as "prod10_er_chf",
	CASE WHEN country='CHE' THEN sum(quantity * "11-er_1" + quantity * "11-er_2") ELSE 0 END  as "prod11_er_chf",
	CASE WHEN country='CHE' THEN sum(quantity * "13-er_1" + quantity * "13-er_2") ELSE 0 END  as "prod13_er_chf",
	CASE WHEN country='CHE' THEN sum(quantity * "20-er_1" + quantity * "20-er_2") ELSE 0 END  as "prod20_er_chf",
	CASE WHEN country='CHE' THEN sum(quantity * "30x45_1" + quantity * "30x45_2") ELSE 0 END  as "prod30x45_chf",
	CASE WHEN country='CHE' THEN sum(quantity * "40x60_1" + quantity * "40x60_2") ELSE 0 END  as "prod40x60_chf",
	CASE WHEN country='CHE' THEN sum(quantity * "50x75_1" + quantity * "50x75_2") ELSE 0 END  as "prod50x75_chf",
	CASE WHEN country='CHE' THEN sum(quantity * "MousePad_1" + quantity * "MousePad_2") ELSE 0 END  as "prodMousepad_chf",
	CASE WHEN country='CHE' THEN sum(quantity * "DigiFoto_1" + quantity * "DigiFoto_2") ELSE 0 END  as "prodDigiFoto_chf",
	CASE WHEN country='CHE' THEN sum(quantity * "DigitalNegative_1" + quantity * "DigitalNegative_2") ELSE 0 END  as "prodDigitalNegative_chf",
	CASE WHEN country='DEU' THEN sum(quantity * "10-er_1" + quantity * "10-er_2") ELSE 0 END  as "prod10_er_eur",
	CASE WHEN country='DEU' THEN sum(quantity * "11-er_1" + quantity * "11-er_2") ELSE 0 END  as "prod11_er_eur",
	CASE WHEN country='DEU' THEN sum(quantity * "13-er_1" + quantity * "13-er_2") ELSE 0 END  as "prod13_er_eur",
	CASE WHEN country='DEU' THEN sum(quantity * "20-er_1" + quantity * "20-er_2") ELSE 0 END  as "prod20_er_eur",
	CASE WHEN country='DEU' THEN sum(quantity * "30x45_1" + quantity * "30x45_2") ELSE 0 END  as "prod30x45_eur",
	CASE WHEN country='DEU' THEN sum(quantity * "40x60_1" + quantity * "40x60_2") ELSE 0 END  as "prod40x60_eur",
	CASE WHEN country='DEU' THEN sum(quantity * "50x75_1" + quantity * "50x75_2") ELSE 0 END  as "prod50x75_eur",
	CASE WHEN country='DEU' THEN sum(quantity * "MousePad_1" + quantity * "MousePad_2") ELSE 0 END  as "prodMousepad_eur",
	CASE WHEN country='DEU' THEN sum(quantity * "DigiFoto_1" + quantity * "DigiFoto_2") ELSE 0 END  as "prodDigiFoto_eur",
	CASE WHEN country='DEU' THEN sum(quantity * "DigitalNegative_1" + quantity * "DigitalNegative_2") ELSE 0 END  as "prodDigitalNegative_eur",
	CASE WHEN country='CHE' THEN sum("TOTAL") ELSE 0 END  as "TOTAL_CHF",
	CASE WHEN country='DEU' THEN sum("TOTAL") ELSE 0 END  as "TOTAL_EUR"
from vReportProdCount
group by event,album,albumid,date,orderid,country
order by event, album;

DROP TABLE vreportallconsprodcountdate;
CREATE OR REPLACE VIEW vReportAllConsProdCountDate AS
select date,event,album,albumid,
	sum("prod10_er_chf") as "prod10_er_chf",sum("prod10_er_eur") as "prod10_er_eur",sum("prod11_er_chf") as "prod11_er_chf",sum("prod11_er_eur") as "prod11_er_eur",sum("prod13_er_chf") as "prod13_er_chf",sum("prod13_er_eur") as "prod13_er_eur",sum("prod20_er_chf") as "prod20_er_chf",sum("prod20_er_eur") as "prod20_er_eur",sum("prod30x45_chf") as "prod30x45_chf",sum("prod30x45_eur") as "prod30x45_eur",sum("prod40x60_chf") as "prod40x60_chf",sum("prod40x60_eur") as "prod40x60_eur",sum("prod50x75_chf") as "prod50x75_chf",sum("prod50x75_eur") as "prod50x75_eur",sum("prodMousepad_chf") as "prodMousepad_chf",sum("prodMousepad_eur") as "prodMousepad_eur",sum("prodDigiFoto_chf") as "prodDigiFoto_chf",sum("prodDigiFoto_eur") as "prodDigiFoto_eur",sum("prodDigitalNegative_chf") as "prodDigitalNegative_chf",sum("prodDigitalNegative_eur") as "prodDigitalNegative_eur",
	(sum("prod10_er_chf") + sum("prod11_er_chf") + sum("prod13_er_chf") + sum("prod20_er_chf") + sum("prod30x45_chf") + sum("prod40x60_chf") + sum("prod50x75_chf") + sum("prodMousepad_chf") +sum("prodDigiFoto_chf") +sum("prodDigitalNegative_chf")) as "TOTAL_PHOTOS_CHF",
	(sum("prod10_er_eur") + sum("prod11_er_eur") + sum("prod13_er_eur") + sum("prod20_er_eur") + sum("prod30x45_eur") + sum("prod40x60_eur") + sum("prod50x75_eur") + sum("prodMousepad_eur") +sum("prodDigiFoto_eur") +sum("prodDigitalNegative_eur")) as "TOTAL_PHOTOS_EUR",
	count("orderid_chf") as "TOTAL_BEST_PER_ALBUM_CHF",
	count("orderid_eur") as "TOTAL_BEST_PER_ALBUM_EUR",
  	sum("TOTAL_CHF") as "TOTAL_PER_ALBUM_CHF",
  	sum("TOTAL_EUR") as "TOTAL_PER_ALBUM_EUR"
from vReportConsProdCountByOrder
group by date,event,album,albumid
order by event,album;

