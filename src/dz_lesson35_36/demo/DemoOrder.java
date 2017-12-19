package dz_lesson35_36.demo;

import dz_lesson35_36.dao.OrderDAO;
import dz_lesson35_36.model.Hotel;
import dz_lesson35_36.model.Room;

import java.util.Date;

public class DemoOrder {
    public static void main(String[] args) {

        Hotel hotel1 = new Hotel(111111, "Ukraine", "Kiev", "Hrehsatik", "Sputnik");
        Hotel hotel2 = new Hotel(222222, "Ukraine", "Harkiv", "Sumskaya", "Meteor");

        Room room1 = new Room(1001, 3, 50.00, false, true, new Date(), hotel1);
        Room room2 = new Room(2002, 5, 120.00, true, true, new Date(), hotel1);
        Room room3 = new Room(3003, 2, 30.00, false, false, new Date(), hotel2);
        Room room4 = new Room(4004, 3, 50.00, true, false, new Date(), hotel2);

        Room room5 = new Room(5005, 5, 120.00, false, false, new Date(), hotel1);
        Room room6 = new Room(6006, 3, 40.00, true, true, new Date(), hotel2);
        Room room7 = new Room(7007, 3, 40.00, false, true, new Date(), hotel2);

        //OrderDAO.bookRoom(1001, 4869082911216578273, 111111 );

    }
}
