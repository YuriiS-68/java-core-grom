package dz_lesson35_36.controller;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Hotel;
import dz_lesson35_36.service.HotelService;

public class HotelController {

    private HotelService hotelService = new HotelService();

    public Hotel addHotel(Hotel hotel)throws Exception{
        if (hotel == null)
            throw new BadRequestException("This " + hotel + " is not exist");

        return hotelService.addHotel(hotel);
    }
}
