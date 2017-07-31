package lesson18.exercise1;

public class Task {

    public static void main(String[] args) {
        FileStorage fileStorage = new FileStorage();
        String[] fileNames = {"test1", "test2"};
        fileStorage.setFiles(fileNames);

        CodeStorage codeStorage = new CodeStorage();
        String[] codeNames = {"test", "test1", "test2"};
        codeStorage.setFiles(codeNames);

        PictureStorage pictureStorage = new PictureStorage();
        String[] pictureNames = {"test", "", "test1", "", "test2", ""};
        pictureStorage.setFiles(pictureNames);

        System.out.println("Start printing name...");
        printer(fileStorage);
        System.out.println("Done");

        System.out.println("Start printing name...");
        printer(codeStorage);
        System.out.println("Done");

        System.out.println("Start printing name...");
        printer(pictureStorage);
        System.out.println("Done");

    }

    public static void printer(Storage storage){
        String[] names = storage.getFiles();

        try {
            System.out.println("5th name is " + names[4]);
        } catch (Exception e){
            System.out.println("5th name can not be found...");
            //System.err.println(e.getMessage());
        }

    }
}
