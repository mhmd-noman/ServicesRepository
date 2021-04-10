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
    purchase_price DECIMAL(10,2),
    org_price DECIMAL(10,2),
    discount DECIMAL(10,2),
    mfg_date DATE, 
    expiry_date DATE,
    bar_code char(13),
    direction_to_use varchar(1024),
    description varchar(1024),
    created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_on DATETIME,
    is_active char(1) DEFAULT 'Y',
    image varchar(1024),
    PRIMARY KEY (id)
);

CREATE INDEX idx_name ON products (name);
CREATE INDEX idx_company ON products (company);
CREATE INDEX idx_category ON products (category);
CREATE INDEX idx_flavour ON products (flavour);
CREATE INDEX idx_weight ON products (weight);
CREATE INDEX idx_org_price ON products (org_price);

drop table order_products
drop table products


insert into products (name, quantity, company, flavour, weight) values ('Nitro Tech', 20, 'Muscletech', 'Chocolate', 4);

select * from products 

update products set quantity = quantity - 5 where id = 1;
update products set quantity = (quantity - 2) where id = 1

INSERT INTO products (id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active) VALUES (1, 'Nitro Tech', 'Muscletech', null, 'Chocolate', 20, 4, '30', null, 12000, 5, '2020-06-06', '2020-06-06', '554411223366', null, null, '2020-10-03 19:19:21', null, 'Y');

select * from products 

delete from products where id = 2

select id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active from products where id is not null and is_active = 'Y'

INSERT INTO products (id, name, company, category, flavour, quantity, weight, servings, serving_size, packaging, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active) VALUES (2, 'ON Whey', null, 'Protein', 'Vanilla', null, '5', '30', null, null, 14800, 5, '2020-02-01', '2022-06-24', '4455663321', '1 Serving Per Day', 'Optimum Nutrition', '2020-10-11 19:18:45', null, 'N');


select * from order_products

select id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active from products where is_active = 'Y' and name = ? and price = ? and servings = ? and weight = ? 

select id, name, company, category, flavour, quantity, weight, servings, serving_size, price, discount, mfg_date, expiry_date, bar_code, direction_to_use, description, created_on, last_updated_on, is_active from products where id is not null and is_active = 'Y' limit 5, 5


select * from products