# 📌 Web Quản Lý Sản Phẩm

## 📷 Giao diện trang web
Dưới đây là một số ảnh chụp màn hình giao diện trang web:

- **Trang danh sách sản phẩm**
<img width="1401" alt="Screenshot 2025-03-08 at 19 51 40" src="https://github.com/user-attachments/assets/b3e325e6-d899-4c92-a1da-5f4b9addddf4" />
<img width="1488" alt="Screenshot 2025-03-08 at 19 51 47" src="https://github.com/user-attachments/assets/d81af49d-17e0-4037-bf45-4ae103d2f696" />


- **Trang danh sách sản phẩm sau khi lọc**
  <img width="1355" alt="Screenshot 2025-03-08 at 19 52 20" src="https://github.com/user-attachments/assets/73f2a206-adcb-498e-b388-2033866a903d" />


- **Trang chi tiết sản phẩm**
  <img width="886" alt="Screenshot 2025-03-08 at 19 51 54" src="https://github.com/user-attachments/assets/88e42154-a304-4bb2-be25-29c3474ccf64" />


## 🔥 Giới thiệu
Đây là một ứng dụng web đơn giản giúp hiển thị danh sách sản phẩm, hỗ trợ tìm kiếm, lọc theo giá, đánh giá và xem chi tiết từng sản phẩm.

### 🛠 Công nghệ sử dụng
- **Java Spring Boot** (Backend)
- **Thymeleaf** (Template Engine)
- **Bootstrap 5** (UI/UX)
- **Jackson** (Xử lý JSON)
- **Lombok** (Giảm boilerplate code)

## 🚀 Chức năng chính
### 1️⃣ Hiển thị danh sách sản phẩm có phân trang
- Mỗi trang hiển thị **9 sản phẩm**.
- Hỗ trợ phân trang với **Previous**, **Next**, và số trang cụ thể.

### 2️⃣ Xem chi tiết sản phẩm
- Khi nhấn vào sản phẩm, người dùng được chuyển đến trang hiển thị chi tiết sản phẩm.
- Hiển thị **tên sản phẩm, giá, mô tả, đánh giá và ảnh sản phẩm**.

### 3️⃣ Tìm kiếm sản phẩm
- Người dùng có thể nhập từ khóa vào ô tìm kiếm để tìm các sản phẩm liên quan.
- Kết quả tìm kiếm được hiển thị theo phân trang.

### 4️⃣ Lọc sản phẩm theo tiêu chí
- Người dùng có thể lọc sản phẩm theo:
  - **Khoảng giá** (Min - Max)
  - **Đánh giá** (Min - Max)
- Nếu không tìm thấy sản phẩm phù hợp, hiển thị thông báo: _"Không tìm thấy sản phẩm nào."_

## 📂 Cấu trúc dự án
```
📂 src/main/
├── java/vn/demo/demo/
│   ├── controller/
│   │   ├── ProductController.java  # Điều khiển luồng dữ liệu
│   ├── model/
│   │   ├── Product.java  # Định nghĩa đối tượng sản phẩm
│   ├── repository/
│   │   ├── ProductRepository.java  # Xử lý dữ liệu sản phẩm
│   ├── service/
│   │   ├── ProductService.java  # Xử lý logic nghiệp vụ
│   ├── utils/file/
│   │   ├── IFileReader.java  # Interface cho đọc file JSON
│   │   ├── JsonFileReader.java  # Xử lý đọc file JSON
├── resources/
│   ├── static/css/
│   │   ├── styles.css  # CSS cho index.html
│   │   ├── product-detail.css  # CSS riêng cho product-detail.html
│   ├── templates/
│   │   ├── index.html  # Trang danh sách sản phẩm
│   │   ├── product-detail.html  # Trang chi tiết sản phẩm
│   ├── products.json  # Danh sách sản phẩm
```

## 🏁 Cách chạy ứng dụng
1. **Clone repository:**
   ```bash
   git clone https://github.com/your-repo/product-management.git
   cd product-management
   ```
2. **Chạy ứng dụng Spring Boot:**
   ```bash
   mvn spring-boot:run
   ```
3. **Truy cập trình duyệt:**
   ```
   http://localhost:8080/
   ```




