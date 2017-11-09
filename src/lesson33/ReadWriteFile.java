package lesson33;

import java.io.*;

public class ReadWriteFile {
    public static void main(String[] args) {

        readFile("C:\\Users\\Skorodielov\\Desktop\\Test.txt");
        //writeFile("C:\\Users\\Skorodielov\\Desktop\\Test1.txt", "HELLO !");

    }

    public static void readFile(String path){
        FileReader reader;
        try {
            reader = new FileReader(path);
        }catch (FileNotFoundException e){
            System.err.println("File does not exist");
            return;
        }

        BufferedReader br = new BufferedReader(reader);
        try {
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            System.err.println("Reading from file " + path + " failed");
        } finally {
            try {
                reader.close();
                br.close();
            }catch (IOException e){
                System.err.println("Closing streams failed");
            }
        }
    }

    public static void writeFile(String path, String content){
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;

        try {
            //TODO use other constructor
            writer = new FileWriter(path, true);
            bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.append("\n");
            bufferedWriter.append(content);
        }catch (IOException e){
            System.err.println("Can not write to file");
        }
        finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (writer != null) {
                    writer.close();
                }
            }catch (IOException e){
                System.err.println("Closing streams failed");
            }
        }
    }
}
