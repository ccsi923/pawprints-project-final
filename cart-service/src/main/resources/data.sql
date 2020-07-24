INSERT INTO cart (customer_id, subtotal, purchase_date) VALUES
(2, 607, '2020-02-27'),
(2, 1214, '2019-02-27'),
(2, 1821, '2018-02-27');

INSERT INTO orders (total, order_date, user_id, animal_id, payment, status_order) VALUES
(607, '2020-02-27T12:26:30.107', 2, 1, 'CREDITCARD', 'OPEN'),
(1214, '2019-02-27T12:26:30.107',2, 1, 'CREDITCARD', 'OPEN'),
(1821, '2018-02-27T12:26:30.107', 2, 1, 'CREDITCARD', 'CLOSED');

INSERT INTO cart_item (cart_id, product_type, product_price, requested_quantity,  order_id) VALUES
(1, 'LINEAGE', 129, 1, 1),
(1, 'HEALTH', 199, 1, 1),
(1, 'PAWPRINTS', 279, 1, 1),
(1, 'LINEAGE', 129, 2, 2),
(1, 'HEALTH', 199, 2, 2),
(1, 'PAWPRINTS', 279, 2, 2),
(1, 'LINEAGE', 129, 3, 3),
(1, 'HEALTH', 199, 3, 3),
(1, 'PAWPRINTS', 279, 3, 3);

