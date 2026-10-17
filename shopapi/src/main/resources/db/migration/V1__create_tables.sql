CREATE TABLE customers(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE
);
CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    product VARCHAR(100) NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price NUMERIC(12,2) NOT NULL CHECK (price > 0),
    customer_id BIGINT NOT NULL REFERENCES customers (id)
);

CREATE INDEX idx_orders_customer_id ON orders (customer_id);