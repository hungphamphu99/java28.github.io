package vn.demo.demo.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.entity.Review;
import vn.demo.demo.entity.User;
import vn.demo.demo.exception.BadRequestException;
import vn.demo.demo.exception.NotFoundException;
import vn.demo.demo.model.dto.UserDTO;
import vn.demo.demo.model.request.CreateReviewRequest;
import vn.demo.demo.model.request.UpdateReviewRequest;
import vn.demo.demo.repository.MovieRepository;
import vn.demo.demo.repository.ReviewRepository;
import vn.demo.demo.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final HttpSession session;

    private User getCurrentUser() {
        UserDTO dto = (UserDTO) session.getAttribute("currentUser");
        if (dto == null) throw new BadRequestException("Bạn chưa đăng nhập");
        return userRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng"));
    }

    public Page<Review> getReviewsByMovie(Integer movieId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return reviewRepository.findByMovie_Id(movieId, pageable);
    }

    public Review createReview(CreateReviewRequest request) {
        User user = getCurrentUser();

        Movie movie = movieRepository.findByIdAndStatusTrue(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phim có id = " + request.getMovieId()));

        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .movie(movie)
                .user(user)
                .build();
        return reviewRepository.save(review);
    }

    public Review updateReview(Integer id, UpdateReviewRequest request) {
        User user = getCurrentUser();

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy review"));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Không có quyền cập nhật review");
        }

        review.setContent(request.getContent());
        review.setRating(request.getRating());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        User user = getCurrentUser();

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy review"));

        if (!review.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Không có quyền xoá review");
        }

        reviewRepository.delete(review);
    }
}
