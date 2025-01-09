
USE mana;

-- Thêm dữ liệu vào bảng Customers
INSERT INTO Customers (FullName, PhoneNumber, Email, Address)
VALUES
('Nguyen Van A', '0987654321', 'nguyenvana@gmail.com', 'Hanoi'),
('Tran Thi B', '0123456789', 'tranthib@gmail.com', 'HCMC');

-- Thêm dữ liệu vào bảng Drivers
INSERT INTO Drivers (FullName, PhoneNumber, VehicleInfo)
VALUES
('Le Van C', '0981234567', 'Toyota Vios - 30H-12345'),
('Pham Thi D', '0976543210', 'Honda City - 29A-67890');

-- Thêm dữ liệu vào bảng Rides
INSERT INTO Rides (CustomerID, DriverID, StartLocation, EndLocation, TotalFare, PaymentMethod, Rating)
VALUES
(1, 1, 'Ba Dinh, Hanoi', 'Cau Giay, Hanoi', 100000, 'Cash', 5),
(2, 2, 'District 1, HCMC', 'District 7, HCMC', 150000, 'Credit Card', 4);

-- Thêm dữ liệu vào bảng PaymentMethods
INSERT INTO PaymentMethods (MethodName)
VALUES
('Cash'), ('Credit Card'), ('E-wallet');
