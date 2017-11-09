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
            String command = "wr";
            String line;
            String line1 = br.readLine();
            if ((line = br.readLine()).equals(command)) {
                writer = new FileWriter(path, true);
                bufferedWriter = new BufferedWriter(writer);

                bufferedWriter.append("\n");
                bufferedWriter.append(line1);
            }
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
}
