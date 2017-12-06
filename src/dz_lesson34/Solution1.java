package dz_lesson34;

import java.io.*;

public class Solution1 {

    public static void transferSentences(String fileFromPath, String fileToPath, String word)throws Exception{
        //проверить что такие файлы существуют
        //проверить права на чтение из файла и запись в файл
        //найти заданное слово в предложении (предложением считается текст находящийся между двух точек и длинной больше 10ти символов)
        //создаю объект стрингбафер, создаю пустую строку и считываю информацию из файла и построчно записываю в новую строку
        //записываю предложения в массив разбив их по точкам сплитом
        //иду циклом по массиву и если предложение содержит заданное слово добавляю его в стрингбафер
        //записываю в новый файл
        //считать предложение из файла
        //записать все предложения содержащие заданное слово в файл
        //удалить все предложения содержащие данное слово из файла
        validate(fileFromPath, fileToPath);

        transferFromFileSentencesOnWord(fileFromPath, fileToPath, word);

    }

    private static void transferFromFileSentencesOnWord(String pathFrom, String pathTo, String word)throws IOException{
        StringBuffer resContainsWord = new StringBuffer();
        StringBuffer resWithoutWord = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(pathFrom))) {
            String line;
            String result = "";

            while ((line = br.readLine()) != null){
                result += line.concat("\n");
            }

            String[] sentences = result.trim().split("\\.");

            for (String sentence : sentences) {
                if (sentence != null && sentence.contains(word) && sentence.toCharArray().length > 10){
                    resContainsWord.append(sentence.trim()).append('.');
                    resContainsWord.append("\n");
                }

                if (sentence != null && !sentence.contains(word) || sentence.toCharArray().length <= 10){
                    resWithoutWord.append(sentence.trim()).append('.').trimToSize();
                    resWithoutWord.append("\n");
                }
            }

            writeToFile(pathTo, resContainsWord);

            resWithoutWord.replace(resWithoutWord.length() - 1, resWithoutWord.length(), "");

            writeToFile(pathFrom, resWithoutWord);

        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File does not exist " + pathFrom);
        }catch (IOException e){
            throw new IOException("Reading from file " + pathFrom + " failed");
        }
    }

    private static void writeToFile(String path, StringBuffer contentToWrite)throws IOException{
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))){
            bufferedWriter.append(contentToWrite);
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
