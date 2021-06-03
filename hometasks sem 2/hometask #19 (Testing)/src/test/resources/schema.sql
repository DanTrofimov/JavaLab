drop table if exists todos;
create table todos (
      id serial primary key,
      text varchar(255)
);
