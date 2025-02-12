// Danh sách các sản phẩm có trong giỏ hàng
let products = [
    { name: "Iphone 13 Pro Max", price: 3000000, brand: "Apple", count: 2 },
    { name: "Samsung Galaxy Z Fold3", price: 41000000, brand: "Samsung", count: 1 },
    { name: "IPhone 11", price: 15500000, brand: "Apple", count: 1 },
    { name: "OPPO Find X3 Pro", price: 19500000, brand: "OPPO", count: 3 },
];

// 1. In ra thông tin các sản phẩm trong giỏ hàng
toString = (product) => `${product.name} - ${product.price} - ${product.brand} - ${product.count}`;
console.log(products.map(toString).join('\n'));

// 2. Tính tổng tiền tất cả sản phẩm trong giỏ hàng
let totalPrice = products.reduce((sum, p) => sum + p.price * p.count, 0);
console.log("Tổng tiền :", totalPrice);

// 3. Tìm các sản phẩm của thương hiệu "Apple"
let appleProducts = products.filter(p => p.brand.toLowerCase() === "apple");
console.log("Sản phẩm của Apple:", appleProducts);

// 4. Tìm các sản phẩm có giá > 20000000
let expensiveProducts = products.filter(p => p.price > 20000000);
console.log("Sản phẩm giá > 20 triệu:", expensiveProducts);

// 5. Tìm các sản phẩm có chữ "pro" trong tên (không phân biệt hoa thường)
let proProducts = products.filter(p => p.name.toLowerCase().includes("pro"));
console.log("Sản phẩm có 'pro' trong tên:", proProducts);

// 6. Thêm 1 sản phẩm bất kỳ vào mảng
products.push({ name: "Xiaomi Mi 11", price: 12000000, brand: "Xiaomi", count: 2 });
console.log("Giỏ hàng sau khi thêm sản phẩm:", products);

// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung"
products = products.filter(p => p.brand.toLowerCase() !== "samsung");
console.log("Giỏ hàng sau khi xóa:", products);

// 8. Sắp xếp giỏ hàng theo price tăng dần
products.sort((a, b) => a.price - b.price);
console.log("Giỏ hàng sắp xếp theo giá tăng dần:", products);

// 9. Sắp xếp giỏ hàng theo count giảm dần
products.sort((a, b) => b.count - a.count);
console.log("Giỏ hàng sắp xếp theo số lượng giảm dần:", products);

// 10. Lấy ra 2 sản phẩm bất kỳ
let randomProducts = products.sort(() => 0.5 - Math.random()).slice(0, 2);
console.log("Lấy 2 sản phẩm ngẫu nhiên:", randomProducts);
