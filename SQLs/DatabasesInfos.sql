CREATE TABLE databases_info (
    id INT AUTO_INCREMENT,
    db_code varchar(80) UNIQUE,
    name varchar(80),   
    url varchar(255),
    user varchar(80),
    password varchar(80),
    PRIMARY KEY(id, db_code)
);

DROP TABLE databases_info;

select * from databases_info
INSERT INTO  databases_info  (db_code, name, url, user, password) VALUES ('MX_FIT', 'maximafitness', 'jdbc:mysql://localhost:3306/maximafitness', 'root', 'Temp/123');

select id, db_code, name, url, user, password from databases_info where db_code = 'MX_FIT';