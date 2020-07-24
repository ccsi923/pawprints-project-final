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
('PAWPRINTS', 3, 3);

