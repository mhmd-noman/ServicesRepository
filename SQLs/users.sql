CREATE TABLE
    users
    (
        id INT NOT NULL AUTO_INCREMENT,
        email VARCHAR(255) NOT NULL,
        enabled bit NOT NULL,
        first_name VARCHAR(255),
        last_name VARCHAR(255),
        password VARCHAR(255),
        phone VARCHAR(255),
        username VARCHAR(255),
        is_admin CHAR(1) DEFAULT 'N',
        PRIMARY KEY (id),
        CONSTRAINT id UNIQUE (id)
    )


select * from users order by 1 desc

select id, email, enabled, first_name, last_name, password, phone, username from users where enabled = true

select id, email, enabled, first_name, last_name, password, phone, username from users where username = 'Damon' and firstname = 'Damon'

ALTER TABLE users(
    id INTEGER SERIAL
);
update users set enabled = false,first_name = 'Donner',last_name = 'Darker' where username = 'Don'

select id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active from products where is_active = 'Y' 

select * from users

ALTER TABLE users
ALTER COLUMN id SERIAL;

ALTER TABLE users
MODIFY COLUMN id Serial;

ALTER TABLE users
MODIFY COLUMN id INTEGER AUTO_INCREMENT;

ALTER TABLE users
ADD COLUMN is_admin char(1) DEFAULT 'N';


select email, first_name, last_name from users where id = '1' order by 1 desc
select * from roles 
select * from user_roles 


INSERT INTO  maximafitness . users  ( id ,  email ,  enabled ,  first_name ,  last_name ,  password ,  phone ,  username ) VALUES ('1', 'damon@gmail.com', 1 , 'Damon', 'Darek', 'Temp/123', '03214556987', 'damon');
INSERT INTO  maximafitness . users  ( id ,  email ,  enabled ,  first_name ,  last_name ,  password ,  phone ,  username ) VALUES ('2', 'stefen@gmail.com', 1 , 'Stefen', 'Darek', 'Temp/321', '03214556999', 'stefen');
INSERT INTO  maximafitness . users  ( id ,  email ,  enabled ,  first_name ,  last_name ,  password ,  phone ,  username ) VALUES ('3', 'kathryne@gmail.com', 1 , 'Kathryne', 'Willic', 'Temp/123', '03214551122', 'kathryne');

INSERT INTO  maximafitness . roles  ( role_id ,  name ) VALUES ('1', 'admin');
INSERT INTO  maximafitness . roles  ( role_id ,  name ) VALUES ('2', 'guest');
INSERT INTO  maximafitness . roles  ( role_id ,  name ) VALUES ('3', 'customer');

INSERT INTO  maximafitness . user_roles  ( user_role_id, role_id, user_id) VALUES ('1', 1, 1);
