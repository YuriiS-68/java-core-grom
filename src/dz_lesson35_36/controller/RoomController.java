package dz_lesson35_36.controller;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Room;
import dz_lesson35_36.service.RoomService;

public class RoomController {

    private RoomService roomService = new RoomService();

    public Room addRoom(Room room)throws Exception {
        if (room == null)
            throw new BadRequestException("This " + room + " is not exist");

        return roomService.addRoom(room);
    }
}
