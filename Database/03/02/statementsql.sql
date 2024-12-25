USE management;
-- Lấy ra danh sách các nghề nghiệp (job) duy nhất mà người trong bảng không làm trùng lặp.
SELECT job
FROM Person
GROUP BY job
HAVING COUNT(*) = 1;

-- Lấy ra danh sách các người có nghề nghiệp (job) là ‘Manager’ và lương (salary) lớn hơn 70000.
SELECT *
FROM Person
WHERE job = 'Manager' AND salary > 70000;

-- Lấy ra người có tuổi từ 25 đến 35.
SELECT *
FROM Person
WHERE YEAR(CURDATE()) - YEAR(birthday) BETWEEN 25 AND 35;

-- Lấy ra danh sách các quốc gia (country) và tổng số lượng người từng quốc gia.
SELECT address AS country, COUNT(*) AS person_count
FROM Person
GROUP BY address;

-- Tính số lượng nam (gender = ‘Male’) và nữ (gender = ‘Female’) trong bảng.
SELECT gender, COUNT(*) AS count
FROM Person
GROUP BY gender;

-- Lấy ra số lượng người có cùng nghề nghiệp (job) và quốc gia (country).
SELECT job, address AS country, COUNT(*) AS person_count
FROM Person
GROUP BY job, address;

-- Lấy ra danh sách người theo thứ tự giảm dần của ngày sinh (birthday).
SELECT *
FROM Person
ORDER BY birthday DESC;

-- Lấy ra các nghề nghiệp (job) và tổng số lượng người làm nghề đó, nhưng chỉ hiển thị những nghề nghiệp có ít nhất 3 người làm.
SELECT job, COUNT(*) AS person_count
FROM Person
GROUP BY job
HAVING person_count >= 3;
