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
    private static final DateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static Room addRoom(Room room)throws Exception{
        if (room == null)
            throw new BadRequestException("This " + room + " is not exist");

        if (checkRoom(PATH_ROOM_DB, room))
            throw new BadRequestException("Room with id " + room.getId() + " already exists");

        String date = FORMAT.format(room.getDateAvailableFrom());

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

    public static void deleteRoom(Long idRoom)throws Exception{
        if (idRoom == null)
            throw new BadRequestException("This id " + idRoom + " is not exist");

        StringBuffer res = new StringBuffer();

        String[] lines = readingFromFile(PATH_ROOM_DB).split("\n");
        int index = 0;
        for (String str : lines) {
            if (str != null && !str.equals(Long.toString(idRoom))){
                res.append(str);
                res.append("\n");
            }
            index++;
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

        LinkedList<Room> foundRooms = new LinkedList<>();

        for (Room el : findsRoomInFile(PATH_ROOM_DB)) {
            if (el.getNumberOfGuests() == filter.getNumberOfGuests() || filter.getNumberOfGuests() == 0 && el.getPrice() == filter.getPrice() || filter.getPrice() == 0){
                if (el.getDateAvailableFrom().compareTo(filter.getDateAvailableFrom()) >= 0 || filter.getDateAvailableFrom() == null) {
                    if (el.isPetsAllowed() == filter.isPetsAllowed() && el.isBreakfastIncluded() == filter.isBreakfastIncluded()) {
                        if (el.getHotel().getCountry().equals(filter.getCountry()) || filter.getCountry() == null && el.getHotel().getCity().equals(filter.getCity()) || filter.getCity() == null) {
                            foundRooms.add(el);
                        }
                    }
                }
            }
        }
        return foundRooms;
    }

    private static LinkedList<Room> findsRoomInFile(String path)throws Exception {
        if (path == null)
            throw new BadRequestException("This path to file - " + path + " does not exist.");

        LinkedList<Room> rooms = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int countLine = 0;
            while ((line = br.readLine()) != null) {
                countLine++;
                checkLine(line, countLine, checkLength(path));
                String[] fields = line.split(",");
                Room room = new Room();
                room.setId(Long.parseLong(fields[0]));
                room.setNumberOfGuests(Integer.parseInt(fields[1]));
                room.setPrice(Double.parseDouble(fields[2]));
                room.setBreakfastIncluded(Boolean.parseBoolean(fields[3]));
                room.setPetsAllowed(Boolean.parseBoolean(fields[4]));
                room.setDateAvailableFrom(FORMAT.parse(fields[5]));
                String idHotel = "";
                for (Character ch : fields[6].toCharArray()) {
                    if (ch != null && Character.isDigit(ch)) {
                        idHotel += ch;
                    }
                }
                room.setHotel(findHotelById(Long.parseLong(idHotel)));
                rooms.add(room);
            }
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + path + " failed");
        }
        return rooms;
    }

    private static Hotel findHotelById(Long id)throws Exception{
        if (id == null)
            throw new BadRequestException("This does  " + id + " not exist ");

        LinkedList<Hotel> hotels = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_HOTEL_DB))){
            String line;
            int countLine = 0;
            while ((line = br.readLine()) != null){
                countLine++;
                checkLine(line, countLine, checkLength(PATH_HOTEL_DB));
                String[] result = line.split("\n");
                int index = 0;
                for (String el : result){
                    if (el != null){
                        String[] fields = el.split(",");
                        Hotel hotel = new Hotel();
                        hotel.setId(Long.parseLong(fields[0]));
                        if (hotel.getId() == id) {
                            hotel.setCountry(fields[1]);
                            hotel.setCity(fields[2]);
                            hotel.setStreet(fields[3]);
                            hotel.setName(fields[4]);
                            hotels.add(hotel);
                        }
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + PATH_HOTEL_DB + " failed");
        }
        return hotels.getFirst();
    }

    private static boolean checkRoom(String path, Room room)throws Exception{
        if (path == null || room == null)
            throw new BadRequestException("Invalid incoming data");

        String[] words = readingFromFile(path).split(",");
        int index = 0;
        for (String word : words) {
            if (word != null && word.equals(Long.toString(room.getId()))){
                return true;
            }
            index++;
        }
        return false;
    }

    private static String readingFromFile(String path)throws Exception{
        if(path == null)
            throw new BadRequestException("This path " + path + " is not exists");

        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            int countLine = 0;
            while ((line = br.readLine()) != null){
                countLine++;
                checkLine(line, countLine, checkLength(path));
                result += line.concat("\n");
            }
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + path + " failed");
        }
        return result;
    }

    private static void checkLine(String line, int count, int lengthArray)throws Exception{
        //проверить чтобы строка была не пустая
        //проверить чтобы начиналась с цифрового символа
        //проверить чтобы длина массива была 5
        if (line == null)
            throw new BadRequestException("Invalid incoming data");

        if (count != 0 && line.isEmpty())
            throw new BadRequestException("The line " + count + " nothing contains");

        String[] arrayLine = line.split(",");

        if (!checkArrayLine(arrayLine))
            throw new BadRequestException("In this line " + count + " an error in the column id");

        if (arrayLine.length != lengthArray)
            throw new BadRequestException("The line " + count + " contains " + arrayLine.length + " columns in the table.");
    }

    private static int checkLength(String path){
        int arrayLength = 0;
        switch (path) {
            case PATH_HOTEL_DB:
                arrayLength = 5;
                break;
            case PATH_ROOM_DB:
                arrayLength = 11;
                break;
        }
        return arrayLength;
    }

    private static boolean checkArrayLine(String[] arrayLine){
        for (Character ch : arrayLine[0].toCharArray()){
            if (ch != null && !Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }
}
