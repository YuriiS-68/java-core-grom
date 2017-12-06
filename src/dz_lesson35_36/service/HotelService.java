package dz_lesson35_36.service;

import dz_lesson35_36.dao.HotelDAO;
import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Hotel;

public class HotelService {

    private HotelDAO hotelDAO = new HotelDAO();

    public Hotel addHotel(Hotel hotel)throws Exception{
        if (hotel == null)
            throw new BadRequestException("This " + hotel + " is not exist");

        return hotelDAO.addHotel(hotel);
    }
}
