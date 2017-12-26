package dz_lesson35_36.dao;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Room;
import dz_lesson35_36.model.Order;
import dz_lesson35_36.model.User;
import dz_lesson35_36.model.UserType;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class OrderDAO {

    private static final String PATH_HOTEL_DB = "C:\\Users\\Skorodielov\\Desktop\\HotelDB.txt";
    private static final String PATH_ROOM_DB = "C:\\Users\\Skorodielov\\Desktop\\RoomDB.txt";
    private static final String PATH_USER_DB = "C:\\Users\\Skorodielov\\Desktop\\UserDB.txt";
    private static final String PATH_ORDER_DB = "C:\\Users\\Skorodielov\\Desktop\\OrderDB.txt";
    private static final DateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static void bookRoom(long roomId, long userId, long hotelId)throws Exception{
        //проверить есть ли в файлах БД такие данные
        //если есть создать ордер и просетить ему данные по всем полям
        //сохранить в файл БД
        if (roomId == 0 || userId == 0 || hotelId == 0)
            throw new BadRequestException("Invalid incoming data");

        if(checkIdInFiles(PATH_ROOM_DB, roomId))
            throw new BadRequestException("Room with id " + roomId + " is not exist");

        if (checkIdInFiles(PATH_USER_DB, userId))
            throw new BadRequestException("User with id " + userId + " is not exist");

        if (checkIdInFiles(PATH_HOTEL_DB, hotelId))
            throw new BadRequestException("Hotel with id " + hotelId + " is not exist");

        Order order = new Order();

        String dateFrom = "23.11.2017";
        String dateTo = "06.12.2017";

        Random random = new Random();
        order.setId(random.nextLong() / 1000000000000L);
        if (order.getId() < 0){
            order.setId(-1 * order.getId());
        }

        order.setUser(findUserById(userId));
        order.setRoom(findRoomById(roomId));
        order.setDateFrom(FORMAT.parse(dateFrom));
        order.setDateTo(FORMAT.parse(dateTo));

        Date dateStart = FORMAT.parse(dateFrom);
        Date dateFinish = FORMAT.parse(dateTo);

        long difference = dateStart.getTime() - dateFinish.getTime();
        int days = (int)(difference / (24 * 60 * 60 * 1000));
        double orderCost = findRoomById(roomId).getPrice() * days;
        if (orderCost < 0){
            orderCost = -1 * orderCost;
        }

        order.setMoneyPaid(orderCost);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_ORDER_DB, true))){
            bufferedWriter.append(Long.toString(order.getId())).append(",");
            bufferedWriter.append(order.getUser().toString()).append(",");
            bufferedWriter.append(order.getRoom().toString()).append(",");
            bufferedWriter.append(dateFrom).append(",");
            bufferedWriter.append(dateTo).append(",");
            bufferedWriter.append(Double.toString(orderCost));
            bufferedWriter.append("\n");
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH_ORDER_DB);
        }
    }

    public static void cancelReservation(long roomId, long userId)throws Exception{
        if (roomId == 0 || userId == 0)
            throw new BadRequestException("Invalid incoming data");

        if(checkIdInOrderDB(PATH_ORDER_DB, roomId))
            throw new BadRequestException("Room with id " + roomId + " is not exist");

        if (checkIdInOrderDB(PATH_ORDER_DB, userId))
            throw new BadRequestException("User with id " + userId + " is not exist");

        StringBuffer res = new StringBuffer();

        String[] lines = readingFromFile(PATH_ORDER_DB).split("\n");
        int index = 0;
        for (String str : lines) {
            if (str != null && str.contains(Long.toString(userId)) && str.contains(Long.toString(roomId))) {
                str = "";
            }
            else {
                res.append(str);
                res.append("\n");
            }
            index++;
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_ORDER_DB))){
            bufferedWriter.append(res);
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH_ORDER_DB);
        }
    }

    private static User findUserById(Long id)throws Exception{
        if (id == null)
            throw new BadRequestException("This does  " + id + " not exist ");

        LinkedList<User> users = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_USER_DB))){
            String line;
            while ((line = br.readLine()) != null){
                String[] fields = line.split(",");
                int index = 0;
                for (String el : fields){
                    if (el != null){
                        User user = new User();
                        String idUser = "";
                        for (Character ch : fields[0].toCharArray()) {
                            if (ch != null && Character.isDigit(ch)) {
                                idUser += ch;
                            }
                        }
                        user.setId(Long.parseLong(idUser));
                        if (user.getId() == id) {
                            user.setUserName(fields[1]);
                            user.setPassword(fields[2]);
                            user.setCountry(fields[3]);
                            user.setUserType(UserType.USER);
                            users.add(user);
                        }
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + PATH_USER_DB + " failed");
        }
        return users.getFirst();
    }

    private static Room findRoomById(Long id)throws Exception{
        if (id == null)
            throw new BadRequestException("This does  " + id + " not exist ");

        LinkedList<Room> rooms = new LinkedList<>();

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_ROOM_DB))){
            String line;
            while ((line = br.readLine()) != null){
                String[] fields = line.split(",");
                int index = 0;
                for (String el : fields){
                    if (el != null){
                        Room room = new Room();
                        String idRoom = "";
                        for (Character ch : fields[0].toCharArray()) {
                            if (ch != null && Character.isDigit(ch)) {
                                idRoom += ch;
                            }
                        }
                        room.setId(Long.parseLong(idRoom));
                        if (room.getId() == id) {
                            room.setNumberOfGuests(Integer.parseInt(fields[1]));
                            room.setPrice(Double.parseDouble(fields[2]));
                            room.setBreakfastIncluded(Boolean.parseBoolean(fields[3]));
                            room.setPetsAllowed(Boolean.parseBoolean(fields[4]));
                            room.setDateAvailableFrom(format.parse(fields[5]));
                            rooms.add(room);
                        }
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + PATH_ROOM_DB + " failed");
        }
        return rooms.getFirst();
    }

    private static boolean checkIdInFiles(String path, Long id)throws Exception{
        if (path == null || id == 0 )
            throw new BadRequestException("Invalid incoming data");

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            int countLine = 0;
            while ((line = br.readLine()) != null){
                countLine++;
                checkLine(line, countLine, checkLength(path));
                String[] result = line.split(",");
                for (String el : result){
                    if (el != null && !el.equals(id.toString())){
                        return false;
                    }
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + path + " failed");
        }
        return true;
    }

    private static boolean checkIdInOrderDB(String path, Long id)throws Exception{
        if (path == null || id == 0 )
            throw new BadRequestException("Invalid incoming data");

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            int countLine = 0;
            while ((line = br.readLine()) != null){
                countLine++;
                checkLine(line, countLine, checkLength(path));
                String[] result = line.split(",");
                int index = 0;
                for (String el : result){
                    if (el != null && el.equals(result[index])){
                        String idWanted = "";
                        for (Character ch : result[index].toCharArray()) {
                            if (ch != null && Character.isDigit(ch)) {
                                idWanted += ch;
                            }
                        }
                        if (!idWanted.equals(id.toString())){
                            return false;
                        }
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + path + " failed");
        }
        return true;
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

    private static void checkingReadFile(String path)throws Exception{
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
            case PATH_USER_DB:
                arrayLength = 5;
                break;
            case PATH_ROOM_DB:
                arrayLength = 11;
                break;
            case PATH_ORDER_DB:
                arrayLength = 16;
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
