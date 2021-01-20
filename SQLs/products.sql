CREATE TABLE products (
    id INT AUTO_INCREMENT,
    name varchar(80) UNIQUE,
    company varchar(255),
    category varchar(80),
    flavour varchar(80),
    quantity INTEGER,
    weight varchar(20),
    servings varchar(80),
    serving_size varchar(80),
    packaging varchar(80),
    price DECIMAL(20),
    discount DECIMAL(20),
    mfg_date DATE, 
    expiry_date DATE,
    bar_code char(13),
    direction_to_use varchar(1024),
    description varchar(1024),
    created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_on DATETIME,
    is_active char(1) DEFAULT 'Y',
    PRIMARY KEY (id)
);

ALTER TABLE products(
    image varchar(1024),
);

ALTER TABLE products
ADD COLUMN image varchar(1024);



ALTER TABLE users
  DROP COLUMN image;

drop table products;

insert into products (name, quantity, company, flavour, weight) values ('Nitro Tech', 20, 'Muscletech', 'Chocolate', 4);

select * from products 

INSERT INTO products (id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active) VALUES (1, 'Nitro Tech', 'Muscletech', null, 'Chocolate', 20, 4, '30', null, 12000, 5, '2020-06-06', '2020-06-06', '554411223366', null, null, '2020-10-03 19:19:21', null, 'Y');

select * from products 

delete from products where id = 2

select id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active from products where id is not null and is_active = 'Y'

INSERT INTO products (id, name, company, category, flavour, quantity, weight, servings, serving_size, packaging, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active) VALUES (2, 'ON Whey', null, 'Protein', 'Vanilla', null, '5', '30', null, null, 14800, 5, '2020-02-01', '2022-06-24', '4455663321', '1 Serving Per Day', 'Optimum Nutrition', '2020-10-11 19:18:45', null, 'N');


select * from order_products

select id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active from products where is_active = 'Y' and name = ? and price = ? and servings = ? and weight = ? 