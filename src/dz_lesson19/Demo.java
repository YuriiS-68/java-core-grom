package dz_lesson19;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        Controller controller = new Controller();
        File file = null;
        try {
            file = new File(4440, "test239", "txt", 140);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file1 = null;
        try {
            file1 = new File(4441, "test", "txt", 240);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file2 = null;
        try {
            file2 = new File(4442, "test", "txt", 100);
        }catch (Exception e) {
            e.printStackTrace();
        }
        File file3 = null;
        try {
            file3 = new File(4443, "test", "txt", 50);
        }catch (Exception e) {
            e.printStackTrace();
        }
        File file4 = null;
        try {
            file4 = new File(4444, "test35", "txt", 60);
        }catch (Exception e) {
            e.printStackTrace();
        }

        File file5 = null;
        try {
            file5 = new File(4445, "test37", "txt", 10);
        }catch (Exception e) {
            e.printStackTrace();
        }

        File file6 = null;
        try {
            file6 = new File(4446, "test37", "txt", 10);
        }catch (Exception e) {
            e.printStackTrace();
        }

        File[] files = {null, null, null, null, null};
        File[] files1 = {null, null, null, null, null};

        String[] formatsSupported = {"png", "jpg", "txt"};
        String[] formatsSupported1 = {"doc", "txt", "exe"};

        Storage storage = new Storage(543897, files, formatsSupported, "China", 700);
        Storage storage1 = new Storage(888999, files1, formatsSupported1, "Singapore", 1000);


        System.out.println("Хранилище до добавления файлов: " +  Arrays.toString(files));
        System.out.println("Хранилище1 до добавления файлов: " +  Arrays.toString(files1));
        try {
            controller.put(storage, file);
            controller.put(storage, file1);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Хранилище после добавления файлов: " + Arrays.toString(files));
        try {
            //controller.put(storage1, file2);
            //controller.put(storage1, file3);
            //controller.put(storage1, file4);
            //controller.put(storage1, file5);
            //controller.put(storage1, file6);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Хранилище1 после добавления файлов : " + Arrays.toString(files1));
        System.out.println();
        try {
            controller.transferFile(storage, storage1, 4440);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Хранилище1 после трансфера файлов: " + Arrays.toString(files1));
        System.out.println("Хранилище после трансфера файлов: " + Arrays.toString(files));
        System.out.println();
        System.out.println();
        try {
            controller.transferFile(storage1, storage, 4440);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Хранилище1 после трансфера файлов: " + Arrays.toString(files1));
        System.out.println("Хранилище после трансфера файлов: " + Arrays.toString(files));
    }
}
