CREATE TABLE transaction_infos (
    trans_id INT AUTO_INCREMENT,
    service_id varchar(80) UNIQUE,
    username varchar(255),
    order_id INTEGER,
    response_code varchar(20),
    response_desc varchar(255),
    org_amount DECIMAL(20),
    processed_amount DECIMAL(20),
    discount DECIMAL(20),
    trans_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_on DATETIME,
    trans_status char(1),
    PRIMARY KEY (trans_id)
);


select * from databases_info
select * from masterdatabase:databases_info

insert into transaction_infos (service_id, username, sales_id, order_id) values ('GET', 'M', 'S', 1);

select * from transaction_infos

INSERT INTO transaction_infos (service_id, username, sales_id, order_id, response_code, response_desc, processed_amount, discount, description, trans_time, last_updated_on, trans_status)