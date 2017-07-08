package lesson8.ads;

/**
 * Created by Skorodielov on 09.06.2017.
 */
public class HouseAd extends Ad {
    String ownerName;
    String address;
    double square;
    int floors;

    public HouseAd(long id, int price, String ownerName, String address, double square, int floors) {
        super(id, price);
        this.ownerName = ownerName;
        this.address = address;
        this.square = square;
        this.floors = floors;
    }

    boolean checkOwner(){
        Owners owners = new Owners();

        for (String owner : owners.owners ) {
            if (ownerName == owner)
                return true;
        }
        return false;
    }
    //4
}
