-- we have to execute this query to create database for Inventory management system
create database Inventory;
-- we have to run this query to use the database we just have created
use database Inventory;

-- we have to execute query to create table for caterories
CREATE TABLE Categories (
    category_id INT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);
 
-- we have to execute this query to create table for products
CREATE TABLE Products (
    product_id INT PRIMARY KEY,
    category_id INT,
    product_name VARCHAR(100) NOT NULL,
    unit_price DECIMAL(10, 2),
    expiry_date DATE,
    stock_count INT DEFAULT 0,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);
 
-- we have to execute this query to create table to store sales data
CREATE TABLE SalesTransactions (
    transaction_id INT PRIMARY KEY,
    product_id INT,
    quantity_sold INT,
    total_revenue DECIMAL(10, 2),
    sale_date DATE,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES Products(product_id)
);