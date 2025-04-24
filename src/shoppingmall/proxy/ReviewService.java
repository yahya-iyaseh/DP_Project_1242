package shoppingmall.proxy;

// Proxy Pattern:
// Only authenticated users can post/view reviews
// Guests or unauthorized users are blocked
public interface ReviewService {
    void viewReviews(String productId);

    void postReview(String productId, String review, String user);
}