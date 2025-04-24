package shoppingmall.proxy;

// Proxy Pattern: Proxy - controls access to real service
public class ReviewServiceProxy implements ReviewService {
    private ReviewServiceImpl realService;
    private boolean isAuthenticated;
    private String username;

    public ReviewServiceProxy(String username, boolean isAuthenticated) {
        this.realService = new ReviewServiceImpl();
        this.isAuthenticated = isAuthenticated;
        this.username = username;
    }

    @Override
    public void viewReviews(String productId) {
        if (!isAuthenticated) {
            System.out.println("Access denied. Please log in to view reviews.");
            return;
        }
        realService.viewReviews(productId);
    }

    @Override
    public void postReview(String productId, String review, String user) {
        if (!isAuthenticated || !username.equals(user)) {
            System.out.println("Access denied. You can only post as yourself after logging in.");
            return;
        }
        realService.postReview(productId, review, user);
    }
}
