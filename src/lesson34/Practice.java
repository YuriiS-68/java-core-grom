package lesson34;

import java.io.*;

public class Practice {
    public static void main(String[] args)throws Exception {
        copyFileContent("C:\\Users\\Skorodielov\\Desktop\\Test1.txt", "C:\\Users\\Skorodielov\\Desktop\\Test3.txt");
    }

    public static void copyFileContent(String fileFromPath, String fileToPath)throws Exception{
        //проверить что файлы существуют
        //проверить права
        //считать контент файла from
        //записать контент в файл to

        validate(fileFromPath, fileToPath);

        writeToFile(fileToPath, readFromFile(fileFromPath));
    }

    private static StringBuffer readFromFile(String path){
        StringBuffer res = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null){
                res.append(line);
                res.append("\n");
            }
            res.replace(res.length() - 1, res.length(), "");
        }catch (FileNotFoundException e){
            System.err.println("File does not exist");
        }catch (IOException e){
            System.err.println("Reading from file " + path + " failed");
        }
        return res;
    }

    private static void writeToFile(String path, StringBuffer contentToWrite){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))){
            bufferedWriter.append(contentToWrite);
        }catch (IOException e){
            System.err.println("Can not write to file");
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
