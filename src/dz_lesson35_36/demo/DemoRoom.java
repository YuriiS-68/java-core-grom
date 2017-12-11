package dz_lesson35_36.demo;

import dz_lesson35_36.dao.RoomDAO;
import dz_lesson35_36.model.Hotel;
import dz_lesson35_36.model.Room;

import java.util.Date;

public class DemoRoom {
    public static void main(String[] args)throws Exception {


        Hotel hotel1 = new Hotel(111111, "Ukraine", "Kiev", "Hrehsatik", "Sputnik");
        Hotel hotel2 = new Hotel(222222, "Ukraine", "Harkiv", "Sumskaya", "Meteor");

        Room room1 = new Room(1001, 3, 50.00, true, false, new Date(), hotel1);
        Room room2 = new Room(2002, 5, 120.00, true, true, new Date(), hotel1);
        Room room3 = new Room(3003, 2, 40.00, false, false, new Date(), hotel2);

        //RoomDAO.addRoom(room2);
        //RoomDAO.deleteRoom(room3);
        //RoomDAO.findRooms();

    }
}
