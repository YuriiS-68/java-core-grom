package dz_lesson35_36.dao;

import dz_lesson35_36.exception.BadRequestException;
import dz_lesson35_36.model.User;

import java.io.*;
import java.util.Random;

public class UserDAO {

    //считывание данных - считывание файла
    //обработка данных - маппинг данных
    public static User registerUser(User user)throws Exception{
        //проверить на уникальность имя пользователя
        //присвоить пользователю уникальный id
        //присвоить тип пользователя, пароль и страну
        //save user to DB (file)
        if (user == null)
            throw new BadRequestException("This user is not exist");

        final String PATH;
        PATH = "C:\\Users\\Skorodielov\\Desktop\\UserDB.txt";

        if (checkValidLoginName(PATH, user.getUserName()))
            throw new BadRequestException("User with name " + user.getUserName() + " already exists");

        Random random = new Random();
        user.setId(random.nextLong());
        if (user.getId() < 0){
            user.setId(-1 * user.getId());
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH, true))){
            bufferedWriter.append(Long.toString(user.getId())).append(",");
            bufferedWriter.append(user.getUserName()).append(",");
            bufferedWriter.append(user.getPassword()).append(",");
            bufferedWriter.append(user.getCountry()).append(",");
            bufferedWriter.append(user.getUserType().toString());
            bufferedWriter.append("\n");
        }catch (IOException e){
            throw new IOException("Can not write to file " + PATH);
        }
        return user;
    }

    public static void login(String userName, String password)throws Exception{
        if (userName == null || password == null)
            throw new BadRequestException("Username or password is not exists");

        final String PATH;
        PATH = "C:\\Users\\Skorodielov\\Desktop\\UserDB.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))){
            String line;
            String result = "";
            while ((line = br.readLine()) != null){
                result += line.concat("\n");
            }
            String[] lines = result.split(",");
            for (String el : lines){
                if (el != null && el.contains(userName) && el.contains(password)){

                }
            }
        }


    }

    private static boolean checkValidLoginName(String path, String loginName)throws Exception{
        //считать данные из файла
        //разбить строку на слова по запятым
        //пройти по массиву слов и сравнить с введённым словом
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            String result = "";
            while ((line = br.readLine()) != null){
                result += line.concat("\n");
            }
            String[] words = result.split(",");
            int index = 0;
            for (String word : words) {
                if (word != null && word.equals(loginName)){
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
