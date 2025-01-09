USE sakila;

-- 1. Lấy danh sách tên các diễn viên (actors) có họ bắt đầu bằng chữ “S”.
SELECT actor_id, CONCAT(first_name, ' ', last_name) AS actor_name
FROM actor
WHERE last_name LIKE 'S%';

-- 2. Lấy danh sách các bộ phim (films) được phát hành trong khoảng từ năm 2000 đến năm 2010.
SELECT film_id, title, release_year
FROM film
WHERE release_year BETWEEN 2000 AND 2010;

-- 3. Lấy danh sách các bộ phim (films) có rating là “PG” hoặc “PG-13”.
SELECT film_id, title, rating
FROM film
WHERE rating IN ('PG', 'PG-13');

-- 4. Lấy danh sách diễn viên (actors) và số lượng bộ phim (films) mà họ tham gia, sắp xếp theo số lượng bộ phim giảm dần.
SELECT a.actor_id, CONCAT(a.first_name, ' ', a.last_name) AS actor_name, COUNT(fa.film_id) AS film_count
FROM actor a
JOIN film_actor fa ON a.actor_id = fa.actor_id
GROUP BY a.actor_id, actor_name
ORDER BY film_count DESC;

-- 5. Lấy danh sách các bộ phim (films) và thể loại (categories) của chúng.
SELECT f.film_id, f.title, c.name AS category
FROM film f
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON fc.category_id = c.category_id;

-- 6. Lấy danh sách các bộ phim (films) và tổng số diễn viên (actors) tham gia trong mỗi bộ phim, sắp xếp theo tổng số diễn viên giảm dần.
SELECT f.film_id, f.title, COUNT(fa.actor_id) AS actor_count
FROM film f
JOIN film_actor fa ON f.film_id = fa.film_id
GROUP BY f.film_id, f.title
ORDER BY actor_count DESC;

-- 7. Lấy danh sách các diễn viên (actors) có số lượng bộ phim (films) tham gia lớn hơn 30.
SELECT a.actor_id, CONCAT(a.first_name, ' ', a.last_name) AS actor_name, COUNT(fa.film_id) AS film_count
FROM actor a
JOIN film_actor fa ON a.actor_id = fa.actor_id
GROUP BY a.actor_id, actor_name
HAVING film_count > 30;

-- 8. Lấy danh sách các diễn viên (actors) không tham gia trong bất kỳ bộ phim nào.
SELECT a.actor_id, CONCAT(a.first_name, ' ', a.last_name) AS actor_name
FROM actor a
LEFT JOIN film_actor fa ON a.actor_id = fa.actor_id
WHERE fa.film_id IS NULL;

-- 9. Lấy danh sách bộ phim (films) và tổng doanh thu (revenue) của từng bộ phim, sắp xếp theo tổng doanh thu giảm dần.
SELECT f.film_id, f.title, SUM(p.amount) AS total_revenue
FROM film f
JOIN inventory i ON f.film_id = i.film_id
JOIN rental r ON i.inventory_id = r.inventory_id
JOIN payment p ON r.rental_id = p.rental_id
GROUP BY f.film_id, f.title
ORDER BY total_revenue DESC;

-- 10. Lấy danh sách các bộ phim (films) và thể loại (categories) của chúng, với điều kiện mỗi bộ phim thuộc thể loại “Horror”.
SELECT f.film_id, f.title, c.name AS category
FROM film f
JOIN film_category fc ON f.film_id = fc.film_id
JOIN category c ON fc.category_id = c.category_id
WHERE c.name = 'Horror';
