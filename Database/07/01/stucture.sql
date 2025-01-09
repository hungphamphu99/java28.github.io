CREATE DATABASE IF NOT EXISTS mana;
USE mana;
-- Tạo bảng Customers
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY AUTO_INCREMENT,
    FullName VARCHAR(100) NOT NULL,
    PhoneNumber VARCHAR(15) UNIQUE NOT NULL,
    Email VARCHAR(100),
    Address TEXT,
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tạo bảng Drivers
CREATE TABLE Drivers (
    DriverID INT PRIMARY KEY AUTO_INCREMENT,
    FullName VARCHAR(100) NOT NULL,
    PhoneNumber VARCHAR(15) UNIQUE NOT NULL,
    VehicleInfo VARCHAR(100),
    Rating FLOAT DEFAULT 0,
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Tạo bảng Rides
CREATE TABLE Rides (
    RideID INT PRIMARY KEY AUTO_INCREMENT,
    CustomerID INT NOT NULL,
    DriverID INT NOT NULL,
    StartLocation VARCHAR(255) NOT NULL,
    EndLocation VARCHAR(255) NOT NULL,
    RideDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    TotalFare DECIMAL(10, 2) NOT NULL,
    PaymentMethod VARCHAR(50) NOT NULL, -- Cash, Credit Card, E-wallet
    Rating INT CHECK (Rating BETWEEN 1 AND 5),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (DriverID) REFERENCES Drivers(DriverID)
);

-- Tạo bảng PaymentMethods
CREATE TABLE PaymentMethods (
    PaymentMethodID INT PRIMARY KEY AUTO_INCREMENT,
    MethodName VARCHAR(50) NOT NULL
);
