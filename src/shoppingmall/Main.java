package shoppingmall;

// File: MainApp.java - CLI to interact with the Shopping Mall system

import shoppingmall.*;
import shoppingmall.factories.*;
import shoppingmall.payment.*;
import shoppingmall.observer.*;
import shoppingmall.proxy.*;
import shoppingmall.command.*;
import shoppingmall.memento.*;
import shoppingmall.state.*;
import shoppingmall.discount.*;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Mall mall;
    private static Customer currentCustomer;
    private static CartHistory cartHistory = new CartHistory();
    private static OrderContext orderContext = new OrderContext();

    public static void main(String[] args) {
        setupMall();
        runCLI();
    }

    private static void setupMall() {
        mall = new Mall("Java Mall");

        Store bookStore = BookStoreFactory.getInstance().createStore("BookWorld", "BS001");
        Store shoeStore = ShoeStoreFactory.getInstance().createStore("ShoeZone", "SS001");
        Store gameStore = GameStoreFactory.getInstance().createStore("GameSpot", "GS001");

        // Book Store - 5 items
        bookStore.addItem(new Item("Effective Java", "B001", bookStore.getId(), 100));
        bookStore.addItem(new Item("Clean Code", "B002", bookStore.getId(), 90));
        bookStore.addItem(new Item("Refactoring", "B003", bookStore.getId(), 95));
        bookStore.addItem(new Item("Domain-Driven Design", "B004", bookStore.getId(), 120));
        bookStore.addItem(new Item("The Pragmatic Programmer", "B005", bookStore.getId(), 85));

        // Shoe Store - 5 items
        shoeStore.addItem(new Item("Nike Air", "S001", shoeStore.getId(), 120));
        shoeStore.addItem(new Item("Adidas Runner", "S002", shoeStore.getId(), 110));
        shoeStore.addItem(new Item("Puma Street", "S003", shoeStore.getId(), 100));
        shoeStore.addItem(new Item("Reebok Classic", "S004", shoeStore.getId(), 95));
        shoeStore.addItem(new Item("New Balance", "S005", shoeStore.getId(), 105));

        // Game Store - 5 items
        gameStore.addItem(new Item("Zelda", "G001", gameStore.getId(), 80));
        gameStore.addItem(new Item("Elden Ring", "G002", gameStore.getId(), 70));
        gameStore.addItem(new Item("God of War", "G003", gameStore.getId(), 85));
        gameStore.addItem(new Item("Hollow Knight", "G004", gameStore.getId(), 60));
        gameStore.addItem(new Item("Cyberpunk 2077", "G005", gameStore.getId(), 75));

        mall.addStore(bookStore);
        mall.addStore(shoeStore);
        mall.addStore(gameStore);
    }

    private static void runCLI() {
        System.out.println("Welcome to the Java Mall!");
        System.out.print("Enter your name to begin: ");
        String name = scanner.nextLine();
        currentCustomer = new Customer(name);
        mall.enter(currentCustomer);
        currentCustomer.getShoppingCart().addObserver(new EmailNotifier(name + "@example.com"));

        int choice;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. View Stores");
            System.out.println("2. Enter Store");
            System.out.println("3. View Cart");
            System.out.println("4. Save Cart State");
            System.out.println("5. Restore Cart State");
            System.out.println("6. Checkout");
            System.out.println("7. View/Leave Reviews");
            System.out.println("8. Simulate Order State");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> listStores();
                case 2 -> enterStore();
                case 3 -> viewCart();
                case 4 -> saveCart();
                case 5 -> restoreCart();
                case 6 -> checkout();
                case 7 -> reviewMenu();
                case 8 -> simulateOrderState();
                case 0 -> System.out.println("Goodbye!");
            }
        } while (choice != 0);
    }

    private static void listStores() {
        System.out.println("Available Stores:");
        Enumeration<Store> stores = mall.stores();
        while (stores.hasMoreElements()) {
            Store store = stores.nextElement();
            System.out.println("- " + store.getName() + " (" + store.getId() + ")");
        }
    }

    private static void enterStore() {
        System.out.print("Enter store ID: ");
        String storeId = scanner.nextLine();
        Store targetStore = null;
        Enumeration<Store> stores = mall.stores();
        while (stores.hasMoreElements()) {
            Store s = stores.nextElement();
            if (s.getId().equals(storeId)) {
                targetStore = s;
                break;
            }
        }
        if (targetStore != null) {
            currentCustomer.enterStore(targetStore);
            System.out.println("You have entered " + targetStore.getName());
            System.out.println("Available items:");
            Enumeration<Item> items = targetStore.items();
            while (items.hasMoreElements()) {
                Item item = items.nextElement();
                System.out.println("- " + item.getName() + " ($" + item.getPrice() + ")");
            }
            System.out.print("Enter item name to add to cart or press Enter to skip: ");
            String itemName = scanner.nextLine();
            if (!itemName.isEmpty()) {
                items = targetStore.items();
                while (items.hasMoreElements()) {
                    Item item = items.nextElement();
                    if (item.getName().equalsIgnoreCase(itemName)) {
                        currentCustomer.getShoppingCart().addItem(item);
                        break;
                    }
                }
            }
        } else {
            System.out.println("Store not found.");
        }
    }

    private static void viewCart() {
        System.out.println("Your cart contains:");
        Enumeration<Item> items = currentCustomer.getShoppingCart().items();
        while (items.hasMoreElements()) {
            Item item = items.nextElement();
            System.out.println("- " + item.getName() + " ($" + item.getPrice() + ")");
        }
    }

    private static void saveCart() {
        cartHistory.save(currentCustomer.getShoppingCart().saveStateToMemento());
        System.out.println("Cart state saved.");
    }

    private static void restoreCart() {
        if (cartHistory.hasHistory()) {
            currentCustomer.getShoppingCart().restoreFromMemento(cartHistory.restore());
        } else {
            System.out.println("No saved cart state.");
        }
    }

    private static void checkout() {
        System.out.println("Choose payment method: 1) Credit Card  2) PayPal");
        int option = Integer.parseInt(scanner.nextLine());
        if (option == 1) currentCustomer.getShoppingCart().setPaymentStrategy(new CreditCardPayment());
        else if (option == 2) currentCustomer.getShoppingCart().setPaymentStrategy(new PayPalPayment());
        else {
            System.out.println("Invalid payment option.");
            return;
        }
        currentCustomer.getShoppingCart().checkout();
    }

    private static void reviewMenu() {
        ReviewService reviews = new ReviewServiceProxy(currentCustomer.getName(), true);
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        reviews.viewReviews(productId);
        System.out.print("Write a review (leave empty to skip): ");
        String message = scanner.nextLine();
        if (!message.isEmpty()) {
            reviews.postReview(productId, message, currentCustomer.getName());
        }
    }

    private static void simulateOrderState() {
        orderContext = new OrderContext();
        orderContext.pay();
        orderContext.ship();
        orderContext.deliver();
        System.out.println("Final order state: " + orderContext.getStateName());
    }
}
