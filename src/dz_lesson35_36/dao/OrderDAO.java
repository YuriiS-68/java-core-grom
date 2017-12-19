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

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dateFrom = format.format(order.getDateFrom());
        String dateTo = format.format(order.getDateTo());

        Random random = new Random();
        order.setId(random.nextLong());
        if (order.getId() < 0){
            order.setId(-1 * order.getId());
        }

        order.setUser(findUserById(userId));
        order.setRoom(findRoomById(roomId));
        order.setDateFrom(new Date());
        order.setDateTo(new Date());
        order.setMoneyPaid(totalPrice(roomId, order.getId()));

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_ORDER_DB, true))){
            bufferedWriter.append(Long.toString(order.getId())).append(",");
            bufferedWriter.append(order.getUser().toString()).append(",");
            bufferedWriter.append(order.getRoom().toString()).append(",");
            bufferedWriter.append(dateFrom).append(",");
            bufferedWriter.append(dateTo).append(",");
            bufferedWriter.append(Double.toString(totalPrice(roomId, order.getId())));
            bufferedWriter.append("\n");
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH_ROOM_DB);
        }
    }

    private static double totalPrice(Long roomId, Long orderId)throws Exception{
        String dateFrom = findOrderById(orderId).getDateFrom().toString();
        String dateTo = findOrderById(orderId).getDateTo().toString();

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Date dateStart = format.parse(dateFrom);
        Date dateFinish = format.parse(dateTo);

        long difference = dateStart.getTime() - dateFinish.getTime();

        int days = (int)(difference / (24 * 60 * 60 * 1000));

        double orderCost;

        return orderCost = findRoomById(roomId).getPrice() * days;
    }

    private static Order findOrderById(Long id)throws Exception{
        if (id == null)
            throw new BadRequestException("This does  " + id + " not exist ");

        LinkedList<Order> orders = new LinkedList<>();

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_ORDER_DB))){
            String line;
            while ((line = br.readLine()) != null){
                String[] result = line.split("\n");
                int index = 0;
                for (String el : result){
                    if (el != null){
                        String[] fields = el.split(",");
                        Order order = new Order();
                        order.setId(Long.parseLong(fields[0]));
                        order.setDateFrom(format.parse(fields[3]));
                        order.setDateFrom(format.parse(fields[4]));
                        orders.add(order);
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + PATH_ORDER_DB + " failed");
        }

        for (Order el : orders){
            if (el.getId() == id){
                orders.add(el);
                break;
            }
        }
        return orders.getFirst();
    }

    private static User findUserById(Long id)throws Exception{
        if (id == null)
            throw new BadRequestException("This does  " + id + " not exist ");

        LinkedList<User> users = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_ROOM_DB))){
            String line;
            while ((line = br.readLine()) != null){
                String[] result = line.split("\n");
                int index = 0;
                for (String el : result){
                    if (el != null){
                        String[] fields = el.split(",");
                        User user = new User();
                        user.setId(Long.parseLong(fields[0]));
                        user.setUserName(fields[1]);
                        user.setPassword(fields[2]);
                        user.setCountry(fields[3]);
                        user.setUserType(UserType.USER);
                        users.add(user);
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + PATH_ROOM_DB + " failed");
        }

        for (User el : users){
            if (el.getId() == id){
                users.add(el);
                break;
            }
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
                String[] result = line.split("\n");
                int index = 0;
                for (String el : result){
                    if (el != null){
                        String[] fields = el.split(",");
                        Room room = new Room();
                        room.setId(Long.parseLong(fields[0]));
                        room.setNumberOfGuests(Integer.parseInt(fields[1]));
                        room.setPrice(Double.parseDouble(fields[2]));
                        room.setBreakfastIncluded(Boolean.parseBoolean(fields[3]));
                        room.setPetsAllowed(Boolean.parseBoolean(fields[4]));
                        room.setDateAvailableFrom(format.parse(fields[5]));
                        rooms.add(room);
                    }
                    index++;
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + PATH_ROOM_DB + " failed");
        }

        for (Room el : rooms){
            if (el.getId() == id){
                rooms.add(el);
                break;
            }
        }
        return rooms.getFirst();
    }

    private static boolean checkIdInFiles(String path, Long id)throws Exception{
        if (path == null || id == 0 )
            throw new BadRequestException("Invalid incoming data");

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            while ((line = br.readLine()) != null){
                String[] result = line.split("\n");
                for (String el : result){
                    if (el != null && el.contains(id.toString())){
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + path + " failed");
        }
        return false;
    }
}
