show tables;
select * from movies;
select * from ratings;
select * from users;
select * from watch_history;



--1. top rated moview

SELECT 
    m.title, 
    AVG(r.rating) AS avg_rating, 
    COUNT(r.rating) AS total_reviews
FROM Movies m
JOIN Ratings r ON m.movie_id = r.movie_id
GROUP BY m.movie_id
HAVING total_reviews > 5
ORDER BY avg_rating DESC
LIMIT 10;



-- 2. Most Popular Genres

SELECT 
    m.genre, 
    COUNT(wh.history_id) AS total_watches
FROM Movies m
JOIN Watch_History wh ON m.movie_id = wh.movie_id
GROUP BY m.genre
ORDER BY total_watches DESC;




--3. Recommend Movies Based on Similar Users

SELECT DISTINCT m.title
FROM Movies m
JOIN Ratings r ON m.movie_id = r.movie_id
WHERE r.user_id IN (
    SELECT user_id 
    FROM Ratings 
    WHERE movie_id IN (SELECT movie_id FROM Ratings WHERE user_id = 3 AND rating >= 4)
    AND user_id != 1
)
AND m.movie_id NOT IN (SELECT movie_id FROM Watch_History WHERE user_id = 3) 
AND r.rating >= 4
LIMIT 5;


-- 4. Analyzing User Behavior Patterns

SELECT 
    CASE 
        WHEN u.age < 18 THEN 'Under 18'
        WHEN u.age BETWEEN 18 AND 35 THEN '18-35'
        ELSE '35+' 
    END AS age_group,
    m.genre,
    COUNT(*) AS watch_count
FROM Users u
JOIN Watch_History wh ON u.user_id = wh.user_id
JOIN Movies m ON wh.movie_id = m.movie_id
GROUP BY age_group, m.genre
ORDER BY age_group, watch_count DESC;



--5. Detecting Trending Movies

SELECT 
    m.title,
    m.genre,
    COUNT(DISTINCT wh.history_id) AS recent_views,
    ROUND(AVG(r.rating), 1) AS avg_rating,
    (COUNT(DISTINCT wh.history_id) * AVG(r.rating)) AS trending_score
FROM Movies m
JOIN Watch_History wh ON m.movie_id = wh.movie_id
JOIN Ratings r ON m.movie_id = r.movie_id
WHERE wh.watch_date > DATE_SUB(curdate(), INTERVAL 10 DAY)
GROUP BY m.movie_id
HAVING recent_views >= 2 
ORDER BY trending_score DESC
LIMIT 10;