--
-- addon script; needed to delete and set the sequences again
--

drop sequence sequence_genericlevelid;
drop sequence sequence_productId;
-- does not exist anymore:
--drop sequence sequence_priceSegmentId;

-- took out the dropping of the view-tables; separate script and ant task added to build script
-- needed so that the manually inserted entries dont prohibit future insertions because the sequence starts with 1
-- N O T E  that in PostgreSQL prior to version 7.4 the oracle syntax "create sequence xxx start with ..." does not work. hence this postgreSQL version of the script
create sequence sequence_genericlevelid start 100;
create sequence sequence_productId start 100;
-- does not exist anymore
--create sequence sequence_priceSegmentId start 100;

-- check sk code for solution with views