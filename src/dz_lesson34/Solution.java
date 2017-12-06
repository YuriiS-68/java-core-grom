package dz_lesson34;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;

public class Solution {

    public static void copyFileContentApacheIO(String fileFromPath, String fileToPath)throws Exception{

        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);

        FileUtils.copyFile(fileFrom, fileTo);

    }

    public static void copyFileContent1(String fileFromPath, String fileToPath)throws IOException{

        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);

        Files.copy(fileFrom.toPath(), fileTo.toPath());

    }

    public static void copyFileContent(String fileFromPath, String fileToPath)throws Exception{
        //проверить что файлы существуют
        //проверить права
        //считать контент файла from
        //записать контент в файл to

        validate(fileFromPath, fileToPath);

        writeToFile(fileToPath, readFromFile(fileFromPath));
    }


    public static void transferFileContent(String fileFromPath, String fileToPath)throws Exception{
        //проверить что такие файлы существуют
        //проверить права на чтение из файла и запись в файл
        //считать контент из файла
        //записать контент в файл
        //удалить считанный контент из файла
        validate(fileFromPath, fileToPath);

        writeToFile(fileToPath, readFromFile(fileFromPath));

        deleteFromFile(fileFromPath);
    }

    public static void transferSentences(String fileFromPath, String fileToPath, String word)throws Exception{
        //проверить что такие файлы существуют
        //проверить права на чтение из файла и запись в файл
        //найти заданное слово в предложении (предложением считается текст находящийся между двух точек и длинной больше 10ти символов)
        //надо строку разбить по точкам и занести в список, если количество символов больше 10ти
        //беру элемент и если он содержит искомое слово заношу в результирующий список
        //считать предложение из файла
        //записать все предложения содержащие заданное слово в файл
        //удалить все предложения содержащие данное слово из файла
        validate(fileFromPath, fileToPath);

        writeToFile(fileToPath, readFromFileSentencesOnWord(fileFromPath, word));

        deleteFromFileSentencesWithWord(fileFromPath, word);
    }

    private static StringBuffer readFromFileSentencesOnWord(String path, String word)throws IOException{
        StringBuffer res = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String result = "";

            while ((line = br.readLine()) != null){
                result += line.concat("\n");
            }

            String[] sentences = result.trim().split("\\.");

            for (String sentence : sentences) {
                if (sentence != null && sentence.contains(word) && sentence.toCharArray().length > 10){
                    res.append(sentence.trim()).append('.');
                    res.append("\n");
                }
            }
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist " + path);
        }catch (IOException e){
            throw new IOException("Reading from file " + path + " failed");
        }
        return res;
    }

    private static void deleteFromFileSentencesWithWord(String path, String word)throws IOException{
        //найти предложения содержащие заданное слово
        //считать текст из файла, разбить на предложения по точкам и занести в список
        //пройти по списку и если элемент не содержит заданное слово, занести в результирующую строку
        //потом записать результирующую строку в файл
        StringBuffer res = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String result = "";

            while ((line = br.readLine()) != null){
                result += line.concat("\n");
            }

            String[] sentences = result.trim().split("\\.");

            for (String sentence : sentences) {
                if (sentence != null && !sentence.contains(word) || sentence.toCharArray().length <= 10){
                    res.append(sentence.trim()).append('.').trimToSize();
                    res.append("\n");
                }
            }
            res.replace(res.length() - 1, res.length(), "");
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist " + path);
        }catch (IOException e){
            throw new IOException("Reading from file " + path + " failed");
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))){
            bufferedWriter.append(res);
        }catch (IOException e){
            throw new IOException("Can not write to file " + path);
        }
    }

    private static StringBuffer readFromFile(String path)throws IOException{
        StringBuffer res = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null){
                res.append(line);
                res.append("\n");
            }
            res.replace(res.length() - 1, res.length(), "");
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist");
        }catch (IOException e){
            throw new IOException("Reading from file " + path + " failed");
        }
        return res;
    }

    private static void writeToFile(String path, StringBuffer contentToWrite)throws IOException{
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))){
            bufferedWriter.append(contentToWrite);
        }catch (IOException e){
            throw new IOException("Can not write to file " + path);
        }
    }

    private static void deleteFromFile(String path)throws IOException{
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))){
            bufferedWriter.write("");
        }catch (IOException e){
            throw new IOException("Can not write to file " + path);
        }
    }

    private static void validate(String fileFromPath, String fileToPath)throws Exception{
        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);

        if (!fileFrom.exists()){
            throw new FileNotFoundException("File " + fileFromPath + " does not exist");
        }

        if (!fileTo.exists()){
            throw new FileNotFoundException("File " + fileToPath + " does not exist");
        }

        if (!fileFrom.canRead()){
            throw new Exception("File " + fileFromPath + " does not have permissions to be read");
        }

        if (!fileTo.canWrite()){
            throw new Exception("File " + fileToPath + " does not have permissions to be written");
        }
    }
}
