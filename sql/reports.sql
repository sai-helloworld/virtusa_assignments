--we have to excecute this query to get items that have stock greateer than 50(>50) and are expiring with in next 7 days

SELECT product_name, stock_count, expiry_date
FROM Products p1
WHERE expiry_date BETWEEN CURRENT_DATE AND (CURRENT_DATE + INTERVAL 7 DAY)
  AND stock_count > 50;

 
-- we have to execute this query to to find items that has not been sold in last 60 days (or we can can change the date range at the interval keyword)

SELECT p1.product_name, p1.stock_count
FROM Products p1
WHERE NOT EXISTS (
    SELECT 1 
    FROM SalesTransactions s1
    WHERE s1.product_id = p1.product_id 
    AND s1.sale_date >= (CURRENT_DATE - INTERVAL 60 DAY)
);

-- we have to execute this query to get the total revenue of each category of last month (or we can change the date interval at the where clause)

SELECT c1.category_name, SUM(s1.total_revenue) AS monthly_revenue
FROM Categories c1
JOIN Products p1 ON c1.category_id = p1.category_id
JOIN SalesTransactions s1 ON p1.product_id = s1.product_id
WHERE s1.sale_date >= '2026-03-01' AND s1.sale_date <= '2026-03-31'
GROUP BY c1.category_name
ORDER BY monthly_revenue DESC;