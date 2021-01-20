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






INSERT INTO contact_us (id, name, email, phone, message) VALUES (1, 'Damon', 'dmn@gmail.com', '092123657789', 'This message is to Test!');
INSERT INTO contact_us (id, name, email, phone, message) VALUES (1, 'Stefhen', 'stn@gmail.com', '092123657788', 'This message is to Test2!');

select * from contact_us

[Stile, stl@gmail.com, 092123657787, This is to Test3]
insert into contact_us (name, email, phone, message) VALUES ('Stile2', 'stl@gmail.com', '092123657787', 'This is to Test3');