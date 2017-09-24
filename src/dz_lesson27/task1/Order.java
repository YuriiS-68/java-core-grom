package dz_lesson27.task1;

public class Order {
    private long id;
    private int price;
    private String currency;
    private String itemName;
    private String shopIdentificator;

    public Order(long id, int price, String currency, String itemName, String shopIdentificator) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.itemName = itemName;
        this.shopIdentificator = shopIdentificator;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", itemName='" + itemName + '\'' +
                ", shopIdentificator='" + shopIdentificator + '\'' +
                '}';
    }
}
