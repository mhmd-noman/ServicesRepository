CREATE TABLE wishlists (
    id INT AUTO_INCREMENT,
    username varchar(80),
    wishlist varchar(512),
    last_updated_on DATETIME,
    PRIMARY KEY (id, username),
    CONSTRAINT fk_users_username FOREIGN KEY (username) REFERENCES users (username)
);

CREATE INDEX idx_wishlist_wishlist ON wishlists (wishlist);