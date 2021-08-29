set mode MySQL;

insert into account (id, balance) values (12345678, 1000000) on duplicate key update id = 12345678;
insert into account (id, balance) values (88888888, 1000000) on duplicate key update id = 88888888;