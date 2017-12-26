package dz_lesson35_36.dao;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Hotel;

import java.io.*;
import java.util.LinkedList;

public class HotelDAO {

    private static final String PATH_HOTEL_DB = "C:\\Users\\Skorodielov\\Desktop\\HotelDB.txt";

    public static Hotel addHotel(Hotel hotel)throws Exception{
        //проверить по id есть ли такой отель в файле
        //если нет, добавить в файл
        if (hotel == null)
            throw new BadRequestException("This " + hotel + " is not exist");

        if (checkHotel(PATH_HOTEL_DB, hotel))
            throw new BadRequestException("Hotel with id " + hotel.getId() + " already exists");

        checkingReadFile(PATH_HOTEL_DB);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_HOTEL_DB, true))){
            bufferedWriter.append(Long.toString(hotel.getId())).append(",");
            bufferedWriter.append(hotel.getCountry()).append(",");
            bufferedWriter.append(hotel.getCity()).append(",");
            bufferedWriter.append(hotel.getStreet()).append(",");
            bufferedWriter.append(hotel.getName());
            bufferedWriter.append("\n");
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH_HOTEL_DB);
        }
        return hotel;
    }

    public static void deleteHotel(Long idHotel)throws Exception{
        //считать файл
        //разбить на строки по отелям
        //если строка содержит заданный отель, удалить эту строку
        //перезаписать файл
        if (idHotel == null)
            throw new BadRequestException("This id " + idHotel + " is not exist");

        StringBuffer res = new StringBuffer();

        String[] lines = readingFromFile(PATH_HOTEL_DB).split("\n");
        int index = 0;
        for (String str : lines) {
            if (str != null && !str.equals(Long.toString(idHotel))){
                res.append(str);
                res.append("\n");
            }
            index++;
        }

        writerInFailBD(PATH_HOTEL_DB, res);
    }

    public static LinkedList<Hotel> findHotelByName(String name)throws Exception{
        //считать файл
        //разбить сплитом по символу переноса строки и найти нужную строку содержащую имя отеля
        //полученную строку разбить сплитом по запятой
        //получаю массив стрингов
        if (name == null)
            throw new BadRequestException("This name - " + name + " does not exist." );

        checkingReadFile(PATH_HOTEL_DB);

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
                        if (hotel.getName().equals(name)) {
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
        return hotels;
    }

    public static LinkedList<Hotel> findHotelByCity(String city)throws Exception{
        //считать файл
        //разбить сплитом по символу переноса строки и найти нужную строку содержащую имя отеля
        //полученную строку разбить сплитом по запятой
        //получаю массив стрингов
        if (city == null)
            throw new BadRequestException("This city - " + city + " does not exist." );

        checkingReadFile(PATH_HOTEL_DB);

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
                        if (hotel.getCity().equals(city)) {
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
        return hotels;
    }

    private static boolean checkHotel(String path, Hotel hotel)throws Exception{
        if (path == null || hotel == null)
            throw new BadRequestException("Invalid incoming data");

        String[] words = readingFromFile(path).split(",");
        int index = 0;
        for (String word : words) {
            if (word != null && word.equals(Long.toString(hotel.getId()))){
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
                checkLine(line, countLine);
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
                checkLine(line, countLine);
                result += line.concat("\n");
            }
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new IOException("Reading from file " + path + " failed");
        }
    }

    private static void checkLine(String line, int count)throws Exception{
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

        if (arrayLine.length != 5)
            throw new BadRequestException("The line " + count + " contains " + arrayLine.length + " columns in the table.");
    }

    private static void writerInFailBD(String path, StringBuffer content)throws Exception{
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))){
            bufferedWriter.append(content);
        }catch (IOException e){
            throw new IOException("Can not write to file " + path);
        }
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

