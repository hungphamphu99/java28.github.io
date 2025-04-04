
--------------------------------------------------------------------------------
-- 1. Tạo bảng Product
--------------------------------------------------------------------------------
CREATE TABLE Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    price DOUBLE,
    quantity INT
);

--------------------------------------------------------------------------------
-- 2. Thêm 10 sản phẩm vào bảng Product
--------------------------------------------------------------------------------
INSERT INTO Product (product_name, price, quantity)
VALUES
    ('Laptop', 1500, 10),
    ('Phone', 800, 5),
    ('Watch', 200, 20),
    ('Tablet', 400, 8),
    ('Camera', 900, 3),
    ('Speaker', 120, 25),
    ('Headphone', 50, 40),
    ('Monitor', 300, 4),
    ('Keyboard', 30, 20),
    ('Mouse', 25, 30);

--------------------------------------------------------------------------------
-- 3. Thêm cột 'description' vào bảng Product
--------------------------------------------------------------------------------
ALTER TABLE Product
ADD COLUMN description VARCHAR(255);

--------------------------------------------------------------------------------
-- 4. Cập nhật giá của sản phẩm có product_id = 1 thành 99
--------------------------------------------------------------------------------
UPDATE Product
SET price = 99
WHERE product_id = 1;

--------------------------------------------------------------------------------
-- 5. Tăng giá của sản phẩm có product_id = 2 lên 10%
--------------------------------------------------------------------------------
UPDATE Product
SET price = price * 1.1
WHERE product_id = 2;

--------------------------------------------------------------------------------
-- (Tiếp theo, các thao tác thực hiện trên bảng Person đã có sẵn)
--------------------------------------------------------------------------------

--------------------------------------------------------------------------------
-- 6. Cập nhật tên (fullname) của người có id = 10 thành 'John Doe'
--------------------------------------------------------------------------------
UPDATE Person
SET fullname = 'John Doe'
WHERE id = 10;

--------------------------------------------------------------------------------
-- 7. Đổi quốc gia (country) của người có id = 3 thành 'Canada'
--------------------------------------------------------------------------------
UPDATE Person
SET country = 'Canada'
WHERE id = 3;

--------------------------------------------------------------------------------
-- 8. Sửa birthday và country cho người có id = 7
--------------------------------------------------------------------------------
UPDATE Person
SET birthday = '1990-05-15',
    country  = 'Việt Nam'
WHERE id = 7;

--------------------------------------------------------------------------------
-- 9. Tăng mức lương (salary) của người có id = 40 lên 10%
--------------------------------------------------------------------------------
UPDATE Person
SET salary = salary * 1.10
WHERE id = 40;

--------------------------------------------------------------------------------
-- 10. Giảm lương 15% cho những người có salary > 6000
--------------------------------------------------------------------------------
UPDATE Person
SET salary = salary * 0.85
WHERE salary > 6000;
