package dz_lesson35_36.dao;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.User;
import dz_lesson35_36.model.Utils;

import java.io.*;
import java.util.Random;

public class UserDAO {

    private static Utils utils = new Utils();
    private static GeneralDAO generalDAO = new GeneralDAO();
    //private static final String PATH_USER_DB = "C:\\Users\\Skorodielov\\Desktop\\UserDB.txt";
    //считывание данных - считывание файла
    //обработка данных - маппинг данных
    public static User registerUser(User user)throws Exception{
        //проверить на уникальность имя пользователя
        //присвоить пользователю уникальный id
        //присвоить тип пользователя, пароль и страну
        //save user to DB (file)
        if (user == null)
            throw new BadRequestException("This user is not exist");

        if (checkValidLoginName(utils.getPathUserDB(), user.getUserName()))
            throw new BadRequestException("User with name " + user.getUserName() + " already exists");


        generalDAO.checkingReadFile(utils.getPathUserDB());

        Random random = new Random();
        user.setId(random.nextLong() / 1000000000000L);
        if (user.getId() < 0){
            user.setId(-1 * user.getId());
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(utils.getPathUserDB(), true))){
            bufferedWriter.append(Long.toString(user.getId())).append(",");
            bufferedWriter.append(user.getUserName()).append(",");
            bufferedWriter.append(user.getPassword()).append(",");
            bufferedWriter.append(user.getCountry()).append(",");
            bufferedWriter.append(user.getUserType().toString());
            bufferedWriter.append("\n");
        }catch (IOException e){
            throw new IOException("Can not write to file " + utils.getPathUserDB());
        }
        return user;
    }

    public static void login(String userName, String password)throws Exception {
        if (userName == null || password == null)
            throw new BadRequestException("Username or password is not exists");

        String[] lines = generalDAO.readingFromFile(utils.getPathUserDB()).split(",");
        for (String el : lines) {
            if (el != null && el.contains(userName) && el.contains(password)) {

            }
        }
    }

    private static boolean checkValidLoginName(String path, String loginName)throws Exception{
        //считать данные из файла
        //разбить строку на слова по запятым
        //пройти по массиву слов и сравнить с введённым словом
        String[] words = generalDAO.readingFromFile(path).split(",");
        int index = 0;
        for (String word : words) {
            if (word != null && word.equals(loginName)){
                return true;
            }
            index++;
        }
        return false;
    }


    /*private static String readingFromFile(String path)throws Exception{
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
    }*/

    /*private static void checkingReadFile(String path)throws Exception{
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
    }*/

    /*private static void checkLine(String line, int count)throws Exception{
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

    private static boolean checkArrayLine(String[] arrayLine){
        for (Character ch : arrayLine[0].toCharArray()){
            if (ch != null && !Character.isDigit(ch)){
                return false;
            }
        }
        return true;
    }*/
}
