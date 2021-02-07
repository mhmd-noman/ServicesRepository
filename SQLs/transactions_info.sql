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


drop table transaction_infos

select * from databases_info
select * from masterdatabase:databases_info

insert into transaction_infos (service_id, username, sales_id, order_id) values ('GET', 'M', 'S', 1);
INSERT INTO transaction_infos (service_id, username, order_id, response_code, response_desc, org_amount, processed_amount, discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
select * from transaction_infos

INSERT INTO transaction_infos (service_id, username, sales_id, order_id, response_code, response_desc, processed_amount, discount, description, trans_time, last_updated_on, trans_status)