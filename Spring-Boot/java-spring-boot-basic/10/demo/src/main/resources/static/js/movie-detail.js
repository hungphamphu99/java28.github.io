/* movie-detail.js */

// Hàm khởi tạo (gọi khi load trang)
const initPage = () => {
    // Gọi getReviews(1) để load danh sách review ban đầu
    getReviews(1);

    // Xử lý rating, form submit, ...
    initReviewForm();
};
async function addFavorite(movieId) {
    try {
        // Gọi API thêm phim vào favorites (giả sử userId được fix trong backend)
        const response = await axios.post('/api/favorites/add', { movieId: movieId });
        alert("Phim đã được thêm vào danh sách yêu thích!");
    } catch (error) {
        console.error(error);
        alert("Có lỗi xảy ra khi thêm phim vào danh sách yêu thích.");
    }
}

// Hàm xử lý form tạo review (gần giống như bạn đã có)
const initReviewForm = () => {
    const reviewForm = document.getElementById("review-form");
    const reviewContentEl = document.getElementById("review-content");
    const stars = document.querySelectorAll(".star");
    const ratingValue = document.getElementById("rating-value");
    let currentRating = 0;

    // Sự kiện di chuột (mouseover, mouseout) cho từng ngôi sao
    stars.forEach((star) => {
        star.addEventListener("mouseover", () => {
            resetStars(stars);
            const rating = parseInt(star.getAttribute("data-rating"));
            highlightStars(stars, rating);
        });

        star.addEventListener("mouseout", () => {
            resetStars(stars);
            highlightStars(stars, currentRating);
        });

        star.addEventListener("click", () => {
            currentRating = parseInt(star.getAttribute("data-rating"));
            ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
            highlightStars(stars, currentRating);
        });
    });

    // Hàm resetStars
    function resetStars(stars) {
        stars.forEach((star) => {
            star.classList.remove("active");
        });
    }

    // Hàm highlightStars
    function highlightStars(stars, rating) {
        stars.forEach((star) => {
            const starRating = parseInt(star.getAttribute("data-rating"));
            if (starRating <= rating) {
                star.classList.add("active");
            }
        });
    }

    // Submit form tạo review
    reviewForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        // Kiểm tra rating
        if (currentRating === 0) {
            alert("Vui lòng chọn rating");
            return;
        }

        // Kiểm tra nội dung
        const content = reviewContentEl.value.trim();
        if (!content) {
            alert("Vui lòng nhập nội dung");
            return;
        }

        // Tạo payload
        const payload = {
            content: content,
            rating: currentRating,
            movieId: movie.id
        };

        try {
            await axios.post('/api/reviews', payload);
            alert("Tạo review thành công");

            // Đóng modal (Bootstrap 5)
            const modalEl = document.getElementById('modal-review');
            const modalInstance = bootstrap.Modal.getInstance(modalEl) || new bootstrap.Modal(modalEl);
            modalInstance.hide();

            // Reset form
            reviewForm.reset();
            currentRating = 0;
            resetStars(stars);
            ratingValue.textContent = "";

            // Cập nhật danh sách review
            getReviews(1);
        } catch (error) {
            console.log(error);
            alert("Có lỗi xảy ra khi tạo review");
        }
    });
};

// Gọi initPage khi load
document.addEventListener("DOMContentLoaded", initPage);
