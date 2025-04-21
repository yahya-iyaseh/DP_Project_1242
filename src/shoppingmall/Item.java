package shoppingmall;

public class Item
{
    private String name;
    private String id;
    private String storeId;
    private double price;

    public Item(String name, String id, String storeId, double price){
        this.name = name;
        this.id = id;
        this.storeId = storeId;
        this.price = price;
    }

    public String getName(){return name;};
    public String GetId(){return id;};
    public String getStoreId(){return storeId;};
    public double getPrice(){return price;};
}
