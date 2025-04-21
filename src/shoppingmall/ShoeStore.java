package shoppingmall;

public class ShoeStore extends Store {
    public ShoeStore(String name, String id) {
        super(name, id);
    }

    @Override
    public void enter(Customer customer) {
        customers.add(customer);
        System.out.println(customer.getName() + " entered " + name);
    }

    @Override
    public void exit(Customer customer) {
        customers.remove(customer);
        System.out.println(customer.getName() + " exited " + name);
    }

}
