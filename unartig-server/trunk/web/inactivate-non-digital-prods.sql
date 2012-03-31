-- after de-connecting from coloplaza unartig needs to de-activate all non-digital products in the database

-- which are the digital products?
select * from products p
join producttypes pt on p.producttypeid = pt.producttypeid
where pt.digitalproduct = true

-- set all others to inactive
update products p
set inactive = true
from producttypes pt
where p.producttypeid = pt.producttypeid
and pt.digitalproduct is false;