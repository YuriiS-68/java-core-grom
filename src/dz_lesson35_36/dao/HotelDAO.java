package dz_lesson35_36.dao;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Hotel;

import java.io.*;

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
            bufferedWriter.append(hotel.getStreet());
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

