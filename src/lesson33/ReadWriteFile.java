package lesson33;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class ReadWriteFile {
    public static void main(String[] args)throws InterruptedException {

        readFile("C:\\Users\\Skorodielov\\Desktop\\Test1.txt");
        writeFile("C:\\Users\\Skorodielov\\Desktop\\Test1.txt", "HELLO !");

    }

    private static void readFile(String path){
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
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(br);
        }
    }

    private static void writeFile(String path, String content)throws InterruptedException{
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
            return;
        }
        finally {
            IOUtils.closeQuietly(bufferedWriter);
            IOUtils.closeQuietly(writer);
        }
    }
}
