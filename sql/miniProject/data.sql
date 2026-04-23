-- we have to execute this query to insert data into categories table
INSERT INTO Categories (category_id, category_name) VALUES 
(1, 'Dairy & Eggs'),
(2, 'Fresh Produce'),
(3, 'Frozen Foods');
 
-- we have to execute this query to insert data into the products table
INSERT INTO Products (product_id, product_name, category_id, stock_count, unit_price, expiry_date) VALUES 
(1001, 'Milk', 1, 75, 2.50, '2026-04-18'),   
(1002, 'Yogurt', 1, 15, 4.00, '2026-04-17'),     
(1003, 'Spinach Bag', 2, 60, 3.00, '2026-04-19'),       
(1004, 'la pino Frozen Pizza', 3, 100, 8.50, '2027-10-12'),     
(1005, 'Organic kashmir Apples', 2, 40, 5.00, '2026-05-20');    
 
-- we have to execute this query to insert data into the sales Transactions table
INSERT INTO SalesTransactions (transaction_id, product_id, quantity_sold, sale_date, total_revenue) VALUES 
(501, 1001, 10, '2026-03-15', 25.00),
(502, 1002, 2, '2026-03-20', 8.00),
(503, 1005, 5, '2026-04-05', 25.00);  