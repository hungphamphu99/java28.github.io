USE sakila;

--------------------------------------------------------------------------------
-- 1. How many category film we have?
--------------------------------------------------------------------------------
SELECT COUNT(*) AS total_categories
FROM category;

--------------------------------------------------------------------------------
-- 2. Count the number of "ACTION" films
--------------------------------------------------------------------------------
SELECT COUNT(*) AS action_film_count
FROM film_category fc
INNER JOIN category c ON fc.category_id = c.category_id
WHERE c.name = 'Action';

--------------------------------------------------------------------------------
-- 3. Count the number of unique customers who have in rental table
--------------------------------------------------------------------------------
SELECT COUNT(DISTINCT customer_id) AS unique_customers
FROM rental;

--------------------------------------------------------------------------------
-- 4. Select the film title and length of all films that have length 
--    greater than the average film length
--------------------------------------------------------------------------------
SELECT title, length
FROM film
WHERE length > (
    SELECT AVG(length) 
    FROM film
);

--------------------------------------------------------------------------------
-- 5. Select the maximum number and the minimum number of films for each category
--    (hiểu là tìm ra giá trị max/min của số phim ở từng category)
--------------------------------------------------------------------------------
SELECT
    MAX(film_count) AS max_number_of_films,
    MIN(film_count) AS min_number_of_films
FROM (
    SELECT c.name AS category_name, COUNT(fc.film_id) AS film_count
    FROM category c
    INNER JOIN film_category fc ON c.category_id = fc.category_id
    GROUP BY c.category_id
) AS t;

--------------------------------------------------------------------------------
-- 6. Tell me each category name and the number of films for that category
--------------------------------------------------------------------------------
SELECT 
    c.name AS category_name,
    COUNT(fc.film_id) AS number_of_films
FROM category c
INNER JOIN film_category fc ON c.category_id = fc.category_id
GROUP BY c.category_id
ORDER BY number_of_films DESC;

--------------------------------------------------------------------------------
-- 7. Concatenate the film titles and length from the films table
--------------------------------------------------------------------------------
SELECT 
    CONCAT(title, ' - ', length) AS film_info
FROM film;

--------------------------------------------------------------------------------
-- 8. Extract the customers email from the 5th character onwards
--------------------------------------------------------------------------------
SELECT 
    SUBSTRING(email, 5) AS email_substring
FROM customer;

--------------------------------------------------------------------------------
-- 9. Tell me the customers first name in lower case and last name in upper case 
--    for each customer who have last name is "Smith"
--------------------------------------------------------------------------------
SELECT 
    LOWER(first_name) AS first_name_lower,
    UPPER(last_name) AS last_name_upper
FROM customer
WHERE last_name = 'Smith';

--------------------------------------------------------------------------------
-- 10. Tell me the last 3 letters of each film title from the films table
--------------------------------------------------------------------------------
SELECT 
    RIGHT(title, 3) AS last_3_letters
FROM film;

--------------------------------------------------------------------------------
-- 11. Concatenate the first three letters in the first name and last name columns
--     together from the customers table
--------------------------------------------------------------------------------
SELECT 
    CONCAT(LEFT(first_name, 3), LEFT(last_name, 3)) AS short_name
FROM customer;

--------------------------------------------------------------------------------
-- 12. Which films are over 2 hours long?
--------------------------------------------------------------------------------
SELECT 
    film_id,
    title,
    length
FROM film
WHERE length > 120;

--------------------------------------------------------------------------------
-- 13. Which category name had the most film?
--------------------------------------------------------------------------------
SELECT 
    c.name AS category_name,
    COUNT(fc.film_id) AS film_count
FROM category c
INNER JOIN film_category fc ON c.category_id = fc.category_id
GROUP BY c.category_id
ORDER BY film_count DESC
LIMIT 1;

--------------------------------------------------------------------------------
-- 14. How many actor for the film 'AFFAIR PREJUDICE'?
--------------------------------------------------------------------------------
SELECT 
    COUNT(*) AS number_of_actors
FROM film f
INNER JOIN film_actor fa ON f.film_id = fa.film_id
INNER JOIN actor a ON fa.actor_id = a.actor_id
WHERE f.title = 'AFFAIR PREJUDICE';

--------------------------------------------------------------------------------
-- 15. Which 5 customers made the most rental?
--------------------------------------------------------------------------------
SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,
    COUNT(r.rental_id) AS rentals
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
GROUP BY c.customer_id
ORDER BY rentals DESC
LIMIT 5;

--------------------------------------------------------------------------------
-- 16. Which films were shown in category 'Action'?
--------------------------------------------------------------------------------
SELECT 
    f.title AS film_title
FROM film f
INNER JOIN film_category fc ON f.film_id = fc.film_id
INNER JOIN category c ON fc.category_id = c.category_id
WHERE c.name = 'Action';

--------------------------------------------------------------------------------
-- 17. How many of the customers made a rental?
--------------------------------------------------------------------------------
SELECT 
    COUNT(DISTINCT customer_id) AS customers_who_rented
FROM rental;
