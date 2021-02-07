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
        username VARCHAR(255) UNIQUE,
        is_admin CHAR(1) DEFAULT 'N',
        PRIMARY KEY (id),
        CONSTRAINT id UNIQUE (id)
    )

CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_users_phone ON users (phone);
CREATE INDEX idx_users_username ON users (username);


drop table users 
select * from users order by 1 desc

select id, email, enabled, first_name, last_name, password, phone, username from users where enabled = true

select id, email, enabled, first_name, last_name, password, phone, username from users where username = 'Damon' and firstname = 'Damon'


update users set enabled = false,first_name = 'Donner',last_name = 'Darker' where username = 'Don'

select id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active from products where is_active = 'Y' 

select * from users





select email, first_name, last_name from users where id = '1' order by 1 desc
select * from roles 
select * from user_roles 


select * from users where username = 'ahassan';

select id from users where username is not null and username = 'mikhalaq' and phone = '01112112233' and email = 'mikhalaq@gmail.com' 

[mikhalaq, 01112112233, mikhalaq@gmail.com]