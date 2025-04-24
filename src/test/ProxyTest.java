package test;

import org.junit.jupiter.api.Test;
import shoppingmall.proxy.*;

public class ProxyTest {

    @Test
    public void testGuestCannotViewReviews() {
        ReviewService guest = new ReviewServiceProxy("guest", false);
        guest.viewReviews("B001"); // Should deny access
    }

    @Test
    public void testAuthenticatedCanPostReview() {
        ReviewService user = new ReviewServiceProxy("Yahya", true);
        user.postReview("B001", "Awesome Book!", "Yahya"); // Should allow
        user.viewReviews("B001");
    }

    @Test
    public void testImpersonationBlocked() {
        ReviewService user = new ReviewServiceProxy("Yahya", true);
        user.postReview("B001", "Fake Review", "Hacker"); // Should deny
    }
}
