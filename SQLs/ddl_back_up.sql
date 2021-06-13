-- databases_info

CREATE TABLE databases_info (
    id INT AUTO_INCREMENT,
    db_code varchar(80) UNIQUE,
    name varchar(80),   
    url varchar(255),
    user varchar(80),
    password varchar(80),
    api_base_uri varchar(80),
    content_type varchar(80),
    token_prefix varchar(20),
    token varchar(250),
    auth_username varchar(80),
    auth_password varchar(80),
    PRIMARY KEY(id, db_code)
);

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
        username VARCHAR(255) UNIQUE,
        is_admin CHAR(1) DEFAULT 'N',
        PRIMARY KEY (id),
        CONSTRAINT id UNIQUE (id)
    )

CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_users_phone ON users (phone);
CREATE INDEX idx_users_username ON users (username);

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
    image1 varchar(1024),
    image2 varchar(1024),
    image3 varchar(1024),
    image4 varchar(1024),
    image5 varchar(1024),
    PRIMARY KEY (id)
);

CREATE INDEX idx_name ON products (name);
CREATE INDEX idx_company ON products (company);
CREATE INDEX idx_category ON products (category);
CREATE INDEX idx_flavour ON products (flavour);
CREATE INDEX idx_weight ON products (weight);
CREATE INDEX idx_org_price ON products (org_price);


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


-- Transaction Infos

CREATE TABLE transaction_infos (
    trans_id INT AUTO_INCREMENT,
    service_id varchar(80),
    username varchar(255),
    order_id INTEGER,
    response_code varchar(20),
    response_desc varchar(255),
    purchased_amount DECIMAL(10,2),
    org_amount DECIMAL(10,2),
    processed_amount DECIMAL(10,2),
    discount DECIMAL(10,2),
    profit DECIMAL(10,2),
    trans_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_on DATETIME,
    trans_status char(1),
    PRIMARY KEY (trans_id)
);

CREATE INDEX idx_username ON transaction_infos (username);
CREATE INDEX idx_order_id ON transaction_infos (order_id);
CREATE INDEX idx_trans_time ON transaction_infos (trans_time);



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

-- wishlists

CREATE TABLE wishlists (
    id INT AUTO_INCREMENT,
    username varchar(80),
    wishlist varchar(512),
    last_updated_on DATETIME,
    PRIMARY KEY (id, username),
    CONSTRAINT fk_users_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE INDEX idx_wishlist_wishlist ON wishlists (wishlist);