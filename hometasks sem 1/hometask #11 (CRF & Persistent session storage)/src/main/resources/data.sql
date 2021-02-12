create table tomcat_sessions
(
    id varchar(100) not null primary key,
    valid char(1) not null,
    maxinactive int not null,
    lastaccess bigint,
    data bytea,
    app varchar(20)
);
