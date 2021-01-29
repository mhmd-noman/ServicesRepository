-- Users

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

-- Products

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

ALTER TABLE products
ADD COLUMN image varchar(1024);

-- Orders 

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
    order_org_amount DECIMAL(20),
    order_rtl_amount DECIMAL(20),
    order_calc_discount DECIMAL(20),
    created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    cancelled_at DATETIME,
    order_status char(1) DEFAULT 'Y',
    PRIMARY KEY (order_id)
);

CREATE INDEX idx_cust_name ON orders (cust_name);
CREATE INDEX idx_created_on ON orders (created_on);
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
    product_org_price DECIMAL(20),
    product_rtl_price DECIMAL(20),
    product_discount DECIMAL(20),
    product_net_price DECIMAL(20),
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_orders_order_id FOREIGN KEY (order_id) REFERENCES orders (order_id),
    CONSTRAINT fk_products_product_id FOREIGN KEY (product_id) REFERENCES products (id)
);

-- Contact Us

CREATE TABLE contact_us (
    id INT AUTO_INCREMENT,
    name varchar(30),
    email varchar(30),
    phone varchar(20),
    message varchar(1024),
    created_on DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE INDEX idx_contact_us_name ON contact_us (name);
CREATE INDEX idx_contact_us_email ON contact_us (email);
CREATE INDEX idx_contact_us_phone ON contact_us (phone);

