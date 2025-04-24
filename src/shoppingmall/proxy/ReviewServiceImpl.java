package shoppingmall.proxy;

import java.util.*;

public class ReviewServiceImpl implements ReviewService {
    private Map<String, List<String>> reviews = new HashMap<>();

    @Override
    public void viewReviews(String productId) {
        List<String> productReviews = reviews.getOrDefault(productId, new ArrayList<>());
        if (productReviews.isEmpty()) {
            System.out.println("No reviews yet for product " + productId);
        } else {
            System.out.println("Reviews for " + productId + ":");
            productReviews.forEach(System.out::println);
        }
    }

    @Override
    public void postReview(String productId, String review, String user) {
        reviews.putIfAbsent(productId, new ArrayList<>());
        reviews.get(productId).add(user + ": " + review);
        System.out.println("Review posted by " + user);
    }
}
