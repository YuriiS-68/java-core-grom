package dz_lesson35_36.demo;

import dz_lesson35_36.dao.RoomDAO;
import dz_lesson35_36.model.Hotel;
import dz_lesson35_36.model.Room;
import dz_lesson35_36.model.Filter;

import java.util.Date;

public class DemoRoom {
    public static void main(String[] args)throws Exception {


        Hotel hotel1 = new Hotel(111111, "Ukraine", "Kiev", "Hrehsatik", "Sputnik");
        Hotel hotel2 = new Hotel(222222, "Ukraine", "Harkiv", "Sumskaya", "Meteor");

        Room room1 = new Room(1001, 3, 50.00, true, true, new Date(), hotel1);
        Room room2 = new Room(2002, 5, 120.00, true, true, new Date(), hotel1);
        Room room3 = new Room(3003, 3, 40.00, true, true, new Date(), hotel2);
        Room room4 = new Room(4004, 3, 40.00, true, true, new Date(), hotel2);
        Room room5 = new Room(5005, 5, 120.00, false, false, new Date(), hotel1);
        Room room6 = new Room(6006, 3, 40.00, true, true, new Date(), hotel2);
        Room room7 = new Room(7007, 3, 40.00, false, false, new Date(), hotel2);

        Filter filter = new Filter(5, 0, false, false, null, null, "Kiev");

        /*RoomDAO.addRoom(room1);
        RoomDAO.addRoom(room2);
        RoomDAO.addRoom(room3);
        RoomDAO.addRoom(room4);
        RoomDAO.addRoom(room5);
        RoomDAO.addRoom(room6);
        RoomDAO.addRoom(room7);
        */

        /*RoomDAO.deleteRoom(room1);
        RoomDAO.deleteRoom(room2);
        RoomDAO.deleteRoom(room3);
        RoomDAO.deleteRoom(room4);
        RoomDAO.deleteRoom(room5);
        RoomDAO.deleteRoom(room6);
        RoomDAO.deleteRoom(room7);
        */

        System.out.println("RoomsFinish - " + RoomDAO.findRooms(filter));

    }
}
