CREATE TABLE wishlists (
    id INT AUTO_INCREMENT,
    username varchar(80),
    wishlist varchar(512),
    last_updated_on DATETIME,
    PRIMARY KEY (id, username),
    CONSTRAINT fk_users_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE INDEX idx_wishlist_wishlist ON wishlists (wishlist);

select * from products
select * from users

update wishlists set wishlist = CONCAT(wishlist, ",2") where username = 'damon';
select * from wishlists

insert into wishlists (username, wishlist) values ('damon','1'); 