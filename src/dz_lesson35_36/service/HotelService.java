package dz_lesson35_36.service;

import dz_lesson35_36.dao.HotelDAO;
import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Hotel;

public class HotelService {

    private static HotelDAO hotelDAO = new HotelDAO();

    public static Hotel addHotel(Hotel hotel)throws Exception{
        if (hotel == null)
            throw new BadRequestException("This " + hotel + " is not exist");

        return hotelDAO.addHotel(hotel);
    }

    public static void deleteHotel(Long idHotel)throws Exception{
        if (idHotel == null)
            throw new BadRequestException("This id " + idHotel + " is not exist");
    }


}
