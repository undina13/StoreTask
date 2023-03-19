CREATE TABLE IF NOT EXISTS users
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username  VARCHAR(100)                            NOT NULL,
    email     VARCHAR(100)                            NOT NULL,
    password  VARCHAR(100)                            NOT NULL,
    balance   INTEGER                                 NOT NULL,
    role      VARCHAR(100)                            NOT NULL,
    is_frozen BOOLEAN,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS organizations
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(100)                            NOT NULL,
    description VARCHAR(1000)                           NOT NULL,
    logo        VARCHAR(100)                            NOT NULL,
    user_id     BIGINT                                  NOT NULL,
    is_deleted  BOOLEAN,
    is_frozen   BOOLEAN,
    CONSTRAINT pk_organization PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS key_words
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    word VARCHAR(150)                            NOT NULL,
    CONSTRAINT pk_word PRIMARY KEY (id)

);

CREATE TABLE IF NOT EXISTS discounts
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    date_of_end TIMESTAMP                               NOT NULL,
    percent     INTEGER                                 NOT NULL,
    CONSTRAINT pk_discount PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS characteristics
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(200)                            NOT NULL,
    description VARCHAR(500)                            NOT NULL,
    CONSTRAINT pk_characteristic PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name            VARCHAR(100)                            NOT NULL,
    description     VARCHAR(1000)                           NOT NULL,
    organization_id BIGINT                                  NOT NULL,
    price           INTEGER                                 NOT NULL,
    count           INTEGER                                 NOT NULL,
    isAvailable     BOOLEAN,
    CONSTRAINT pk_product PRIMARY KEY (id),
    CONSTRAINT fk_product FOREIGN KEY (organization_id) REFERENCES organizations (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS discounts_products
(
    discount_id BIGINT NOT NULL,
    product_id  BIGINT NOT NULL,
    CONSTRAINT pk_discount_product PRIMARY KEY (discount_id, product_id),
    CONSTRAINT fk_discount_product FOREIGN KEY (discount_id) REFERENCES discounts (id) ON DELETE CASCADE,
    CONSTRAINT fk_product_discount FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS key_words_products
(
    key_words_id BIGINT NOT NULL,
    product_id   BIGINT NOT NULL,
    CONSTRAINT pk_key_words_product PRIMARY KEY (key_words_id, product_id),
    CONSTRAINT fk_key_words_product FOREIGN KEY (key_words_id) REFERENCES discounts (id) ON DELETE CASCADE,
    CONSTRAINT fk_product_key_words FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS product_marks
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    mark       INTEGER                                 NOT NULL,
    product_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_product_marks PRIMARY KEY (id),
    CONSTRAINT fk_product_marks_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS product_reviews
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    review     VARCHAR(1500)                           NOT NULL,
    product_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_product_reviews PRIMARY KEY (id),
    CONSTRAINT fk_product_reviews_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS purchases
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    datePurchase TIMESTAMP,
    user_id      BIGINT                                  NOT NULL,
    product_id   BIGINT                                  NOT NULL,
    price        INTEGER                                 NOT NULL,
    CONSTRAINT pk_purchases PRIMARY KEY (id),
    CONSTRAINT fk_purchases_user FOREIGN KEY (user_id) REFERENCES discounts (id) ON DELETE CASCADE,
    CONSTRAINT fk_purchases_product FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);