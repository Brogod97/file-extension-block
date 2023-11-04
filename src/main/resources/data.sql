insert into extension(name, type) values ('bat', 'F');
insert into extension(name, type) values ('cpl', 'F');
insert into extension(name, type) values ('com', 'F');
insert into extension(name, type) values ('cmd', 'F');
insert into extension(name, type) values ('exe', 'F');
insert into extension(name, type) values ('scr', 'F');
insert into extension(name, type) values ('js', 'F');

insert into extension(name, type) values ('sh', 'C');
insert into extension(name, type) values ('jar', 'C');



update extension set checked = 'Y' where name in ('bat', 'com', 'scr');