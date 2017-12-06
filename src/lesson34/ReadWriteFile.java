package lesson34;

import java.io.*;

public class ReadWriteFile {
    public static void main(String[] args) {

        //readFile("C:\\Users\\Skorodielov\\Desktop\\Test2.txt");
        writeFile("C:\\Users\\Skorodielov\\Desktop\\Test3.txt", "HELLO 2 !");

    }

    public static void readFile(String path){

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        } catch (FileNotFoundException e){
            System.err.println("File does not exist");
        } catch (IOException e) {
            System.err.println("Reading from file " + path + " failed");
        }
    }

    public static void writeFile(String path, String content){

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))){
            //TODO use other constructor

            bufferedWriter.append("\n");
            bufferedWriter.append(content);
        }catch (IOException e){
            System.err.println("Can not write to file");
        }
    }
}
