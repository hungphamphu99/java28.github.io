use mavenmovies;

/*
1.	We will need a list of all staff members, including their first and last names, 
email addresses, and the store identification number where they work. 
*/ 
SELECT 
    first_name,
    last_name,
    email,
    store_id
FROM staff;


/*
2.	We will need separate counts of inventory items held at each of your two stores. 
*/ 
SELECT 
    store_id, 
    COUNT(*) AS inventory_count
FROM inventory
GROUP BY store_id;


/*
3.	We will need a count of active customers for each of your stores. Separately, please. 
*/
SELECT 
    store_id,
    COUNT(*) AS active_customer_count
FROM customer
WHERE active = TRUE
GROUP BY store_id;


/*
4.	In order to assess the liability of a data breach, we will need you to provide a count 
of all customer email addresses stored in the database. 
*/
SELECT 
    COUNT(email) AS total_email_addresses
FROM customer
WHERE email IS NOT NULL;


/*
5_1.	We are interested in how diverse your film offering is as a means of understanding how likely 
you are to keep customers engaged in the future. Please provide a count of unique film titles 
you have in inventory at each store.
*/
SELECT 
    i.store_id,
    COUNT(DISTINCT f.title) AS unique_film_titles
FROM inventory AS i
JOIN film AS f ON i.film_id = f.film_id
GROUP BY i.store_id;


/*
5_2. And then provide a count of the unique categories of films you provide.
*/
SELECT 
    COUNT(DISTINCT c.name) AS unique_film_categories
FROM film_category AS fc
JOIN category AS c ON fc.category_id = c.category_id;


/*
6.	We would like to understand the replacement cost of your films. 
Please provide the replacement cost for the film that is least expensive to replace, 
the most expensive to replace, and the average of all films you carry. ``	
*/
SELECT 
    MIN(replacement_cost) AS least_expensive,
    MAX(replacement_cost) AS most_expensive,
    AVG(replacement_cost) AS average_cost
FROM film;


/*
7.	We are interested in having you put payment monitoring systems and maximum payment 
processing restrictions in place in order to minimize the future risk of fraud by your staff. 
Please provide the average payment you process, as well as the maximum payment you have processed.
*/

SELECT 
    AVG(amount) AS average_payment,
    MAX(amount) AS max_payment
FROM payment;

/*
8.	We would like to better understand what your customer base looks like. 
Please provide a list of all customer identification values, with a count of rentals 
they have made all-time, with your highest volume customers at the top of the list.
*/
SELECT 
    customer_id,
    COUNT(rental_id) AS rental_count
FROM rental
GROUP BY customer_id
ORDER BY rental_count DESC;


/* 
9. My partner and I want to come by each of the stores in person and meet the managers. 
Please send over the managers’ names at each store, with the full address 
of each property (street address, district, city, and country please).  
*/ 
SELECT 
    CONCAT(s.first_name, ' ', s.last_name) AS manager_name,
    a.address, a.district, city.city, country.country
FROM store st
JOIN staff s ON st.manager_staff_id = s.staff_id
JOIN address a ON st.address_id = a.address_id
JOIN city ON a.city_id = city.city_id
JOIN country ON city.country_id = country.country_id;

	
/*
10.	I would like to get a better understanding of all of the inventory that would come along with the business. 
Please pull together a list of each inventory item you have stocked, including the store_id number, 
the inventory_id, the name of the film, the film’s rating, its rental rate and replacement cost. 
*/
SELECT 
    store_id, inventory_id, f.title AS film_name, f.rating, f.rental_rate, f.replacement_cost
FROM inventory i
JOIN film f ON i.film_id = f.film_id;



/* 
11.	From the same list of films you just pulled, please roll that data up and provide a summary level overview 
of your inventory. We would like to know how many inventory items you have with each rating at each store. 
*/
SELECT 
    store_id, f.rating, COUNT(*) AS inventory_count
FROM inventory i
JOIN film f ON i.film_id = f.film_id
GROUP BY store_id, f.rating;


/* 
12. Similarly, we want to understand how diversified the inventory is in terms of replacement cost. We want to 
see how big of a hit it would be if a certain category of film became unpopular at a certain store.
We would like to see the number of films, as well as the average replacement cost, and total replacement cost, 
sliced by store and film category. 
*/ 


/*
13.	We want to make sure you folks have a good handle on who your customers are. Please provide a list 
of all customer names, which store they go to, whether or not they are currently active, 
and their full addresses – street address, city, and country. 
*/
SELECT 
    CONCAT(first_name, ' ', last_name) AS customer_name,
    store_id, active,
    a.address, city.city, country.country
FROM customer c
JOIN address a ON c.address_id = a.address_id
JOIN city ON a.city_id = city.city_id
JOIN country ON city.country_id = country.country_id;


/*
14.	We would like to understand how much your customers are spending with you, and also to know 
who your most valuable customers are. Please pull together a list of customer names, their total 
lifetime rentals, and the sum of all payments you have collected from them. It would be great to 
see this ordered on total lifetime value, with the most valuable customers at the top of the list. 
*/

    
/*
15. My partner and I would like to get to know your board of advisors and any current investors.
Could you please provide a list of advisor and investor names in one table? 
Could you please note whether they are an investor or an advisor, and for the investors, 
it would be good to include which company they work with. 
*/
SELECT 
    a.first_name, a.last_name, 'Advisor' AS role, NULL AS company_name
FROM advisor a
UNION
SELECT 
    i.first_name, i.last_name, 'Investor' AS role, i.company_name
FROM investor i;


/*
16. We're interested in how well you have covered the most-awarded actors. 
Of all the actors with three types of awards, for what % of them do we carry a film?
And how about for actors with two types of awards? Same questions. 
Finally, how about actors with just one award? 
*/

