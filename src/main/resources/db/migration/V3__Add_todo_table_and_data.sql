

create table todos (
                           id int primary key auto_increment,
                           text varchar(100),
                           done bit
);

insert into todos (text, done) values ( 'Done todo',1);
insert into todos (text, done) values ( 'Done todo',2);

