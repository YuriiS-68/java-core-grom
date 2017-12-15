package dz_lesson35_36.dao;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Filter;
import dz_lesson35_36.model.Hotel;
import dz_lesson35_36.model.Room;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class RoomDAO {

    private static final String PATH_HOTEL_DB = "C:\\Users\\Skorodielov\\Desktop\\HotelDB.txt";
    private static final String PATH_ROOM_DB = "C:\\Users\\Skorodielov\\Desktop\\RoomDB.txt";

    public static Room addRoom(Room room)throws Exception{
        if (room == null)
            throw new BadRequestException("This " + room + " is not exist");

        if (checkRoom(PATH_ROOM_DB, room))
            throw new BadRequestException("Room with id " + room.getId() + " already exists");

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(room.getDateAvailableFrom());

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_ROOM_DB, true))){
            bufferedWriter.append(Long.toString(room.getId())).append(",");
            bufferedWriter.append(Integer.toString(room.getNumberOfGuests())).append(",");
            bufferedWriter.append(Double.toString(room.getPrice())).append(",");
            bufferedWriter.append(Boolean.toString(room.isBreakfastIncluded())).append(",");
            bufferedWriter.append(Boolean.toString(room.isPetsAllowed())).append(",");
            bufferedWriter.append(date).append(",");
            bufferedWriter.append(room.getHotel().toString());
            bufferedWriter.append("\n");
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH_ROOM_DB);
        }
        return room;
    }

    public static void deleteRoom(Room room)throws Exception{
        if (room == null)
            throw new BadRequestException("This " + room + " is not exist");

        StringBuffer res = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_ROOM_DB))){
            String line;
            String result = "";
            while ((line = br.readLine()) != null){
                result += line.concat("\n");
            }
            String[] lines = result.split("\n");
            int index = 0;
            for (String str : lines) {
                if (str != null && !str.contains(Long.toString(room.getId()))){
                    res.append(str);
                    res.append("\n");
                }
                index++;
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + PATH_ROOM_DB + " failed");
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_ROOM_DB))){
            bufferedWriter.append(res);
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH_ROOM_DB);
        }
    }

    public static Collection findRooms(Filter filter)throws Exception{
        if (filter == null)
            throw new BadRequestException("This filter - " + filter + " does not exist." );

        LinkedList<Room> roomsFinish = new LinkedList<>();

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                int index = 0;
                for (Room el : findsRoomInFile(PATH_ROOM_DB)) {
                    if (el.getNumberOfGuests() == filter.getNumberOfGuests() || el.getPrice() == filter.getPrice() || el.isBreakfastIncluded() == filter.isBreakfastIncluded()) {
                        if (el.isPetsAllowed() == filter.isPetsAllowed() || el.getDateAvailableFrom() == filter.getDateAvailableFrom()) {
                            if (el.getHotel().getCountry().equals(filter.getCountry()) || el.getHotel().getCity().equals(filter.getCity())){
                                roomsFinish.add(el);
                            }
                        }
                    }
                    index++;
                }
        return roomsFinish;
    }

    public static void bookRoom(long roomId, long userId, long hotelId)throws Exception{
        if (roomId == 0 || userId == 0 || hotelId == 0)
            throw new BadRequestException("Data not valid");


    }

    private static LinkedList<Room> findsRoomInFile(String path)throws Exception {
        if (path == null)
            throw new BadRequestException("This path to file - " + path + " does not exist.");

        LinkedList<Room> rooms = new LinkedList<>();

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                Room room = new Room();
                room.setId(Long.parseLong(fields[0]));
                room.setNumberOfGuests(Integer.parseInt(fields[1]));
                room.setPrice(Double.parseDouble(fields[2]));
                room.setBreakfastIncluded(Boolean.parseBoolean(fields[3]));
                room.setPetsAllowed(Boolean.parseBoolean(fields[4]));
                room.setDateAvailableFrom(format.parse(fields[5]));
                String idHotel = "";
                for (Character ch : fields[6].toCharArray()) {
                    if (ch != null && Character.isDigit(ch)) {
                        idHotel += ch;
                    }
                }
                room.setHotel(findHotelById(Long.parseLong(idHotel)));
                rooms.add(room);
            }
        }
        return rooms;
    }

    private static Hotel findHotelById(Long id)throws Exception{
        if (id == null)
            throw new BadRequestException("This does  " + id + " not exist ");

        LinkedList<Hotel> hotels = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_HOTEL_DB))){
            String line;
            while ((line = br.readLine()) != null){
                String[] result = line.split("\n");
                int index = 0;
                for (String el : result){
                    if (el != null){
                        String[] fields = el.split(",");
                        Hotel hotel = new Hotel();
                        hotel.setId(Long.parseLong(fields[0]));
                        hotel.setCountry(fields[1]);
                        hotel.setCity(fields[2]);
                        hotel.setStreet(fields[3]);
                        hotel.setName(fields[4]);
                        hotels.add(hotel);
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + PATH_HOTEL_DB + " failed");
        }

        for (Hotel el : hotels){
            if (el.getId() == id){
                hotels.add(el);
                break;
            }
        }
        return hotels.getFirst();
    }

    private static boolean checkRoom(String path, Room room)throws Exception{

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            String result = "";
            while ((line = br.readLine()) != null){
                result += line.concat("\n");
            }
            String[] words = result.split(",");
            int index = 0;
            for (String word : words) {
                if (word != null && word.contains(Long.toString(room.getId()))){
                    return true;
                }
                index++;
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + path + " failed");
        }
        return false;
    }
}
