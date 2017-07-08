package lesson11.biginterfaceexample;

/**
 * Created by Skorodielov on 24.06.2017.
 */
public class SimpleReader implements Readable {

    //Class object = new
    //object.
    //null - empty object

    static String test;

    public static void main(String[] args) {
        System.out.println(test);

        //null pointer exception
    }

    @Override
    public void readFilesFromStorage(Storage storage) {
        for (File file : storage.getFiles()) {
            if (file != null)
                System.out.println(file.getName());
            else
                System.out.println(file);
        }
    }
    //4
}
