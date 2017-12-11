package dz_lesson35_36.dao;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Hotel;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class HotelDAO {

    public static Hotel addHotel(Hotel hotel)throws Exception{
        //проверить по id есть ли такой отель в файле
        //если нет, добавить в файл
        if (hotel == null)
            throw new BadRequestException("This " + hotel + " is not exist");

        final String PATH;
        PATH = "C:\\Users\\Skorodielov\\Desktop\\HotelDB.txt";

        if (checkHotel(PATH, hotel))
            throw new BadRequestException("Hotel with id " + hotel.getId() + " already exists");

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))){
            bufferedWriter.append(Long.toString(hotel.getId())).append(",");
            bufferedWriter.append(hotel.getCountry()).append(",");
            bufferedWriter.append(hotel.getCity()).append(",");
            bufferedWriter.append(hotel.getStreet()).append(",");
            bufferedWriter.append(hotel.getName());
            bufferedWriter.append("\n");
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH);
        }
        return hotel;
    }

    public static void deleteHotel(Hotel hotel)throws Exception{
        //считать файл
        //разбить на строки по отелям
        //если строка содержит заданный отель, удалить эту строку
        //перезаписать файл
        if (hotel == null)
            throw new BadRequestException("This " + hotel + " is not exist");

        final String PATH;
        PATH = "C:\\Users\\Skorodielov\\Desktop\\HotelDB.txt";

        StringBuffer res = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))){
            String line;
            String result = "";
            while ((line = br.readLine()) != null){
                result += line.concat("\n");
            }
            String[] lines = result.split("\n");
            int index = 0;
            for (String str : lines) {
                if (str != null && !str.contains(Long.toString(hotel.getId()))){
                    res.append(str);
                    res.append("\n");
                }
                index++;
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + PATH + " failed");
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH))){
            bufferedWriter.append(res);
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH);
        }
    }

    public static LinkedList<Hotel> findHotelByName(String name)throws Exception{
        //считать файл
        //разбить сплитом по символу переноса строки и найти нужную строку содержащую имя отеля
        //полученную строку разбить сплитом по запятой
        //получаю массив стрингов
        if (name == null)
            throw new BadRequestException("This name - " + name + " does not exist." );

        final String PATH;
        PATH = "C:\\Users\\Skorodielov\\Desktop\\HotelDB.txt";

        LinkedList<Hotel> hotels = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))){
            String line;

            while ((line = br.readLine()) != null){
                String[] result = line.split("\n");
                int index = 0;
                for (String el : result){
                    if (el != null && el.contains(name)){
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
            throw new IOException("Reading from file " + PATH + " failed");
        }
        return hotels;
    }

    public static LinkedList<Hotel> findHotelByCity(String city)throws Exception{
        //считать файл
        //разбить сплитом по символу переноса строки и найти нужную строку содержащую имя отеля
        //полученную строку разбить сплитом по запятой
        //получаю массив стрингов
        if (city == null)
            throw new BadRequestException("This city - " + city + " does not exist." );

        final String PATH;
        PATH = "C:\\Users\\Skorodielov\\Desktop\\HotelDB.txt";

        LinkedList<Hotel> hotels = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))){
            String line;

            while ((line = br.readLine()) != null){
                String[] result = line.split("\n");
                int index = 0;
                for (String el : result){
                    if (el != null && el.contains(city)){
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
            throw new IOException("Reading from file " + PATH + " failed");
        }
        return hotels;
    }

    private static boolean checkHotel(String path, Hotel hotel)throws Exception{

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            String result = "";
            while ((line = br.readLine()) != null){
                result += line.concat("\n");
            }
            String[] words = result.split(",");
            int index = 0;
            for (String word : words) {
                if (word != null && word.contains(Long.toString(hotel.getId()))){
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

