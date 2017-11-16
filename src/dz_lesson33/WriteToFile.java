package dz_lesson33;

import java.io.*;

public class WriteToFile {

    public static void writeToFileFromConsole(String path){

        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;

        System.out.println("Enter file content to write in the file:");

        try {
            String line;
            writer = new FileWriter(path, true);
            bufferedWriter = new BufferedWriter(writer);

            do {
                line = br.readLine();

                bufferedWriter.append("\n");
                bufferedWriter.append(line);
            }
            while (!line.equals("wr"));
        }catch (IOException e){
            System.err.println("Can't write to file with path " + path);
        }
        finally {
            try {
                br.close();
                reader.close();
                if (bufferedWriter != null){
                    bufferedWriter.close();
                }
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                System.err.println("File with path " + path + " not found" );
            }
        }
    }

    public static void readFileByConsolePath()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please, enter file path to enter:");

        try {
            String line = br.readLine();
            readFile(line);
        }catch (IOException e){
            System.out.println("Can't read file by path" + br.readLine());
        }finally {
            try {
                br.close();
            }catch (IOException e){
                System.out.println("Closing streams failed");
            }
        }
    }

    private static void readFile(String path){
        FileReader reader;
        try {
            reader = new FileReader(path);
        }catch (FileNotFoundException e){
            System.err.println("File with path " + path + " not found");
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
}
