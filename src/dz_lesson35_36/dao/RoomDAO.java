package dz_lesson35_36.dao;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.Hotel;
import dz_lesson35_36.model.Room;
import javafx.scene.input.DataFormat;
import javafx.util.converter.DateStringConverter;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import javax.xml.crypto.Data;
import java.awt.image.DataBuffer;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RoomDAO {

    public static Room addRoom(Room room)throws Exception{
        if (room == null)
            throw new BadRequestException("This " + room + " is not exist");

        final String PATH;
        PATH = "C:\\Users\\Skorodielov\\Desktop\\RoomDB.txt";

        if (checkRoom(PATH, room))
            throw new BadRequestException("Hotel with id " + room.getId() + " already exists");

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(room.getDateAvailableFrom());

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))){
            bufferedWriter.append(Long.toString(room.getId())).append(",");
            bufferedWriter.append(Integer.toString((int)room.getNumberOfGuests())).append(",");
            bufferedWriter.append(Double.toString(room.getPrice())).append(",");
            bufferedWriter.append(Boolean.toString(room.isBreakfastIncluded())).append(",");
            bufferedWriter.append(Boolean.toString(room.isPetsAllowed())).append(",");
            bufferedWriter.append(date).append(",");
            bufferedWriter.append(room.getHotel().toString());
            bufferedWriter.append("\n");
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH);
        }
        return room;
    }

    public static void deleteRoom(Room room)throws Exception{
        if (room == null)
            throw new BadRequestException("This " + room + " is not exist");

        final String PATH;
        PATH = "C:\\Users\\Skorodielov\\Desktop\\RoomDB.txt";

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
                if (str != null && !str.contains(Long.toString(room.getId()))){
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
