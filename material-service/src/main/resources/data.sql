INSERT INTO product(name, type,  min_stock, total_remaining_units, price_per_unit, purchase_units) VALUES
('sample boxes', 'INIT_KIT', 10, 300, 0.30, 0),
('test sticks', 'INIT_KIT', 20, 600, 0.10, 0),
('sterilized bags', 'INIT_KIT', 20, 1000, 0.05, 0),
('brochures', 'INIT_KIT', 30, 600, 1.50, 0),
('shipping boxes', 'RESULT_KIT', 10, 300, 1.0, 0),
('product brochure', 'RESULT_KIT', 10, 300, 3.50, 0),
('gift', 'RESULT_KIT', 10, 300, 1.50, 0);


INSERT INTO orders (comments, status) VALUES
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN'),
('No comments','OPEN');

INSERT INTO order_line (product_type, order_id, requested_quantity) VALUES
('LINEAGE', 1, 1),
('HEALTH', 1, 1),
('PAWPRINTS', 1, 1),
('LINEAGE', 2, 2),
('HEALTH', 2, 2),
('PAWPRINTS', 2, 2),
('LINEAGE', 3, 3),
('HEALTH', 3, 3),
('HEALTH', 3, 3),
('HEALTH', 4, 3),
('HEALTH', 4, 3),
('HEALTH', 5, 3),
('PAWPRINTS', 5, 3),
('PAWPRINTS', 6, 3),
('PAWPRINTS', 7, 3),
('PAWPRINTS', 8, 3),
('PAWPRINTS', 9, 3),
('PAWPRINTS', 10, 3),
('PAWPRINTS', 11, 3),
('PAWPRINTS', 12, 3),
('PAWPRINTS', 13, 3),
('PAWPRINTS', 14, 3),
('PAWPRINTS', 14, 3),
('PAWPRINTS', 15, 3),
('PAWPRINTS', 16, 3),
('PAWPRINTS', 17, 3),
('PAWPRINTS', 18, 3),
('PAWPRINTS', 19, 3),
('PAWPRINTS', 20, 3),
('LINEAGE', 8, 3),
('LINEAGE', 9, 3),
('LINEAGE', 10, 3),
('LINEAGE', 11, 3),
('LINEAGE', 12, 3),
('LINEAGE', 13, 3);



INSERT INTO provider_order (comments, status) VALUES
('No comments','PENDING'),
('No comments','PENDING'),
('No comments','FULL'),
('No comments','FULL'),
('No comments','PENDING');



INSERT INTO provider_order_line (product_id, order_id, requested_quantity, price, order_date) VALUES
(1, 1, 100 , 1821, '2015-05-27'),
(2, 1, 200 ,607, '2011-07-27'),
(3, 2, 200 ,1214, '2010-02-27'),
(4, 3, 200 ,1821, '2020-08-27'),
(5, 4, 300 ,607, '2020-09-27'),
(4, 5, 200 ,1214, '2019-02-27'),
(5, 4,100,1821, '2013-02-27');
