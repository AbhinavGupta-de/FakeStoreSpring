CREATE TABLE cart
(
    id      BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    date    VARCHAR(255),
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

CREATE TABLE cart_products
(
    cart_id     BIGINT NOT NULL,
    products_id BIGINT NOT NULL
);

CREATE TABLE category
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id          BIGINT           NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    price       DOUBLE PRECISION NOT NULL,
    image       VARCHAR(255),
    category_id BIGINT,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE cart_products
    ADD CONSTRAINT uc_cart_products_products UNIQUE (products_id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE cart_products
    ADD CONSTRAINT fk_carpro_on_cart FOREIGN KEY (cart_id) REFERENCES cart (id);

ALTER TABLE cart_products
    ADD CONSTRAINT fk_carpro_on_product FOREIGN KEY (products_id) REFERENCES product (id);