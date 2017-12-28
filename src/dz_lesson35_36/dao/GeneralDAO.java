package dz_lesson35_36.dao;

import dz_lesson35_36.exception.BadRequestException;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GeneralDAO {
    private static final String PATH_HOTEL_DB = "C:\\Users\\Skorodielov\\Desktop\\HotelDB.txt";
    private static final String PATH_ROOM_DB = "C:\\Users\\Skorodielov\\Desktop\\RoomDB.txt";
    private static final String PATH_USER_DB = "C:\\Users\\Skorodielov\\Desktop\\UserDB.txt";
    private static final String PATH_ORDER_DB = "C:\\Users\\Skorodielov\\Desktop\\OrderDB.txt";
    private static final DateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static String getPathUserDb() {
        return PATH_USER_DB;
    }

    public static String readingFromFile(String path)throws Exception{
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

    public static void checkingReadFile(String path)throws Exception{
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

    public static void checkLine(String line, int count, int lengthArray)throws Exception{
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

    public static int checkLength(String path){
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

    public static void writerInFailBD(String path, StringBuffer content)throws Exception{
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))){
            bufferedWriter.append(content);
        }catch (IOException e){
            throw new IOException("Can not write to file " + path);
        }
    }
}
