package vn.demo.demo.api;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.entity.Review;
import vn.demo.demo.model.request.CreateReviewRequest;
import vn.demo.demo.model.request.UpdateReviewRequest;
import vn.demo.demo.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewApi {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> getReviews(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "5") Integer pageSize,
                                        @RequestParam Integer movieId) {
        return ResponseEntity.ok(reviewService.getReviewsByMovie(movieId, page, pageSize));
    }

    @PostMapping
    public ResponseEntity<?> createReview(@Valid @RequestBody CreateReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Integer id,
                                          @Valid @RequestBody UpdateReviewRequest request) {
        return ResponseEntity.ok(reviewService.updateReview(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Đã xóa review thành công");
    }
}
