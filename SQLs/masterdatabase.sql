DROP TABLE databases_info;
CREATE TABLE databases_info (id int(-1) NOT NULL AUTO_INCREMENT, db_code varchar(80) NOT NULL, name varchar(80), url varchar(255), user varchar(80), password varchar(80), PRIMARY KEY (id, db_code), CONSTRAINT db_code UNIQUE (db_code)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO databases_info (id, db_code, name, url, user, password) VALUES (1, 'MX_FIT', 'maximafitness', 'jdbc:mysql://localhost:3306/maximafitness', 'root', 'Temp/123');
