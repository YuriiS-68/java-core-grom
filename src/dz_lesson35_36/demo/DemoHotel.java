package dz_lesson35_36.demo;

import dz_lesson35_36.dao.HotelDAO;
import dz_lesson35_36.model.Hotel;

public class DemoHotel {
    public static void main(String[] args)throws Exception {

        Hotel hotel1 = new Hotel(111111, "Ukraine", "Kiev", "Hrehsatik");
        Hotel hotel2 = new Hotel(222222, "Ukraine", "Harkiv", "Sumskaya");
        Hotel hotel3 = new Hotel(333333, "Ukraine", "Lviv", "Bolotnaya");
        Hotel hotel4 = new Hotel(111111, "Ukraine", "Dnepr", "Centralnaya");

        HotelDAO.addHotel(hotel2);
        //HotelDAO.addHotel(hotel2);
        //HotelDAO.addHotel(hotel3);
        //HotelDAO.addHotel(hotel4);
        //HotelDAO.deleteHotel(hotel2);
    }
}
