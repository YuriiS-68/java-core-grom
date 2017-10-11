package Awebinar;

import java.util.Date;

public class Concert {
    private long id;
    private int ticketsCount;
    private Date date;
    private int price;
    private String address;

    public Concert(long id, int ticketsCount, Date date, int price, String address) {
        this.id = id;
        this.ticketsCount = ticketsCount;
        this.date = date;
        this.price = price;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }

    public Date getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }
}
