package lesson8.ads;

import java.util.Date;

/**
 * Created by Skorodielov on 09.06.2017.
 */
public class Ad extends BaseEntity {
    int price;
    Date dateCreated;

    public Ad(long id, int price) {
        super(id);
        this.id = id;
        this.price = price;
        this.dateCreated = new Date();
    }

    void publishAd(){
        //some logic
    }
    //4
}
