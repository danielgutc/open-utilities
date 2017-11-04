insert into resources (id, type_id, spec_id, code) values (1, 1, 1001, 'up-1');
insert into resources (id, type_id, spec_id, code) values (2, 1, 1001, 'up-2');
insert into resources (id, type_id, spec_id, code) values (3, 2, 2001, 'mtr-1');
insert into resources (id, type_id, spec_id, code) values (4, 3, 7001, 'dch-1');
insert into resources (id, type_id, spec_id, code) values (5, 3, 6001, 'dch-2');

insert into relations (from_res_id, to_res_id, type_id) values (1, 3, 1); -- UP -> Meter
insert into relations (from_res_id, to_res_id, type_id) values (1, 4, 2); -- UP -> Channel
insert into relations (from_res_id, to_res_id, type_id) values (3, 5, 3); -- Meter -> Channel