CREATE TABLE orders (
    order_id INT AUTO_INCREMENT,
    order_description varchar(80),
    cust_name varchar(30),
    cust_phone varchar(20),
    cust_phone2 varchar(20),
    cust_address varchar(120),
    cust_address2 varchar(120),
    cust_email varchar(80),
    area varchar(30),
    city varchar(30),
    state varchar(30),
    country varchar(30), 
    purchased_amount DECIMAL(10,2),
    order_org_amount DECIMAL(10,2),
    order_rtl_amount DECIMAL(10,2),
    order_calc_discount DECIMAL(10,2),
    profit DECIMAL(10,2),
    created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    cancelled_at DATETIME,
    order_status char(1) DEFAULT 'Y',
    PRIMARY KEY (order_id)
);

CREATE INDEX idx_created_on ON orders (created_on);
CREATE INDEX idx_cust_name ON orders (cust_name);
CREATE INDEX idx_cust_phone ON orders (cust_phone);
CREATE INDEX idx_cust_email ON orders (cust_email);
CREATE INDEX idx_order_org_amount ON orders (order_org_amount);
CREATE INDEX idx_order_rtl_amount ON orders (order_rtl_amount);
CREATE INDEX idx_order_calc_discount ON orders (order_calc_discount);




CREATE TABLE order_products (
    order_id INT,
    product_id INT,
    product_name varchar(80),
    product_quantity INTEGER,
    product_purchased_price DECIMAL(10,2),
    product_org_price DECIMAL(10,2),
    product_rtl_price DECIMAL(10,2),
    product_discount DECIMAL(10,2),
    product_net_price DECIMAL(10,2),
    product_profit DECIMAL(10,2),
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_orders_order_id FOREIGN KEY (order_id) REFERENCES orders (order_id),
    CONSTRAINT fk_products_product_id FOREIGN KEY (product_id) REFERENCES products (id)
);



drop table order_products;
drop table orders;
order_products:
order_id, product_id, prod_quantity, org_price, rtl_price, discount

order_id, order_description, created_on, cancelled_at, cust_name, cust_phone, cust_phone2, cust_address, cust_address2, cust_email, area, city, state, country, order_org_amount, order_rtl_amount, order_calc_discount, order_status


insert into orders (order_description, cust_name, cust_phone, area, city) values ('First Order', 'Damon', '01236589696', 'DHA', 'Lahore');



INSERT INTO orders (order_id, order_description, cust_name, cust_phone, cust_phone2, cust_address, cust_address2, cust_email, area, city, state, country, order_org_amount, order_rtl_amount, order_calc_discount, created_on, cancelled_at, order_status) VALUES (1, 'First Order', 'Damon', '01236589696', '01236589696', 'adr', 'adr2', 'a@gmail.com', 'DHA', 'Lahore', 'Punjab', 'Pakistan', 12, 12, 12, '2020-10-04 17:55:10', '2020-10-04 17:55:10', 'Y');

select * from products
select * from orders where order_id = 15;
select * from order_products where order_id = 15;

select * from orders where created_on between '2019-01-11' and '2021-01-11 16:12:01'

insert into order_products (order_id, product_id, product_quantity, product_org_price, product_rtl_price, product_discount, product_net_price) values (1, 1, '5', 100.00, 80.00, 20, 400.00);

select * from orders 

INSERT INTO order_products (order_id, product_id, product_quantity, product_org_price, product_rtl_price, product_discount, product_net_price) VALUES (1, 1, 5, 100, 80, 20, 400);

select * from order_products op, products p where op.product_id = p.id;

select * from products

select o.order_id, o.order_description, o.cust_name, o.cust_phone, o.cust_phone2, o.cust_address, o.cust_address2, o.cust_email, o.area, o.city, o.state, o.country, o.order_org_amount, o.order_rtl_amount, o.order_calc_discount, o.created_on, o.cancelled_at, o.order_status, op.product_quantity, op.product_org_price, op.product_rtl_price, op.product_discount, op.product_net_price, p.id, p.name, p.company, p.category, p.flavour, p.quantity, p.weight, p.servings, p.serving_size, p.price, p.discount, p.mfg_date, p.expiry_date, p.bar_code, p.direction_to_use, p.description, p.created_on, p.last_updated_on, p.is_active from orders o, order_products op, products p where o.order_id = op.order_id and op.product_id = p.id and o.order_id in ('1','2') 

select * from orders
select * from order_products where order_id = '7';
select * from maximafitness.order_products

select * from users
UPDATE users SET first_name=CASE WHEN first_name IS NULL OR first_name='' THEN '300' ELSE first_name+' 300' END WHERE id=1; 
UPDATE users SET first_name=CASE WHEN first_name IS NULL OR first_name='' THEN 'Agree' ELSE first_name+', '+'Agree' END WHERE id='1'  

update users set first_name = ISNULL(first_name, '') + '123456' where id = 1

UPDATE users SET first_name = first_name + ',' + 'Darek' WHERE id=1; 

UPDATE Employee SET DEPARTMENT=CASE WHEN DEPARTMENT IS NULL OR DEPARTMENT='' THEN 'Agree' ELSE DEPARTMENT+', '+'Agree' END WHERE EMPLOYEE_ID=3  
select o.order_id, o.order_description, o.cust_name, o.cust_phone, o.cust_phone2, o.cust_address, o.cust_address2, o.cust_email, o.area, o.city, o.state, o.country, o.order_org_amount, o.order_rtl_amount, o.order_calc_discount, o.created_on, o.cancelled_at, o.order_status, op.product_quantity, op.product_org_price, op.product_rtl_price, op.product_discount, op.product_net_price, p.id, p.name, p.company, p.category, p.flavour, p.quantity, p.weight, p.servings, p.serving_size, p.price, p.discount, p.mfg_date, p.expiry_date, p.bar_code, p.direction_to_use, p.description, p.created_on, p.last_updated_on, p.is_active from orders o, order_products op, products p where o.order_id = op.order_id and op.product_id = p.id 
select o.order_id, o.order_description, o.cust_name, o.cust_phone, o.cust_phone2, o.cust_address, o.cust_address2, o.cust_email, o.area, o.city, o.state, o.country, o.order_org_amount, o.order_rtl_amount, o.order_calc_discount, o.created_on, o.cancelled_at, o.order_status, op.product_quantity, op.product_org_price, op.product_rtl_price, op.product_discount, op.product_net_price, p.id, p.name, p.company, p.category, p.flavour, p.quantity, p.weight, p.servings, p.serving_size, p.price, p.discount, p.mfg_date, p.expiry_date, p.bar_code, p.direction_to_use, p.description, p.created_on, p.last_updated_on, p.is_active from orders o, order_products op, products p where o.order_id = op.order_id and op.product_id = p.id and o.order_id = 1 order by o.created_on desc 