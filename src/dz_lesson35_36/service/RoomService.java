package dz_lesson35_36.service;

import dz_lesson35_36.dao.RoomDAO;
import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Room;

public class RoomService {

    private RoomDAO roomDAO = new RoomDAO();

    public Room addRoom(Room room)throws Exception {
        if (room == null)
            throw new BadRequestException("This " + room + " is not exist");

        return roomDAO.addRoom(room);
    }
}
