package dz_lesson19;

public class Controller {

    //Ограничения:
    //Storage может хранить файлы только поддерживаемого формата Учитывайте макс размер хранилища
    //В одном хранилище не могут хранится файлы с одинаковым айди, но могут хранится файлы с одинаковыми именами
    //Имя файла не может быть больше 10 символов, то есть файл с таким именем не может быть создан

    //1. проверить файл на null +
    //2. проверить файл и хранилище на совпадение форматов
    //3. проверить хранилище на вместимость

    public File put(Storage storage, File file)throws Exception {
        if (file == null || storage == null){
            return null;
        }

        if (checkStorageSize(storage, file))
            throw new Exception("In storage: " + storage.getId() + "The file: " + file.getId() + " - is too large");

        if (!checkOnSameId(storage, file))
            throw new Exception("A file: " + file.getId() + " with the same id already exists in the storage: -" + storage.getId());


        if (!checkFormat(storage, file))
            throw new Exception("Format this file: " + file.getId() + " and format this storage: " + storage.getId() + " - is not supported");

        int index = 0;
        for (File fileIn : storage.getFiles()) {
            if (fileIn == null){
                storage.getFiles()[index] = file;
                return file;
            }
            index++;
        }
        throw new Exception("Storage: " + storage.getId() + " - is full");
    }

    public File delete(Storage storage, File file){
        //проверить есть ли такой файл в хранилище и если есть то удалить его
        //если такого файла нет в хранилище, то ничего не делать
        if (file == null || storage.getFiles() == null){
            return null;
        }

        int index = 0;
        for(File el : storage.getFiles()){
            if (el != null && el.getId() == file.getId()){
                storage.getFiles()[index] = null;
                break;
            }
            index++;
        }
        return file;
    }

    public void  transferAll(Storage storageFrom, Storage storageTo)throws Exception{
        //проверить есть ли в хранилище пустые элементы
        //проверить совпадает ли формат файлов с форматом хранилища в которое записывается файл
        //проверить хватит ли размера в хранилище для записи файлов
        //проверить на совпадение id файлов записываемых с id файлами которые находятся в хранилище
        //иду циклом по хранилищу, беру первый элемент и вставляю его в новый массив в первый элемент, и удаляю его из старого

        if (!checkFormatForStorage(storageTo, storageFrom.getFiles()))
            throw new Exception(storageTo.getId() + "The file format is not supported by storage");

        if (!checkSizeForStorage(storageFrom, storageTo))
            throw new Exception(storageTo.getId() + "The storage can not accommodate files");

        if (!checkIdFileWithIdFilesInStorage(storageTo, storageFrom.getFiles()))
            throw new Exception(storageTo.getId() + "The storage can not contain the same files");

        if (!checkingStorageForEmptyItems(storageTo))
            throw new Exception("Storage: " + storageTo.getId() + " - is full");


        for (int i = 0; i < storageFrom.getFiles().length; i++) {
            for (int j = i; j < storageTo.getFiles().length; j++) {
                if (storageFrom.getFiles()[i] != null && storageTo.getFiles()[j] == null){
                    storageTo.getFiles()[j] = storageFrom.getFiles()[i];
                    storageFrom.getFiles()[i] = null;
                }
            }
        }
    }

    private boolean checkingStorageForEmptyItems(Storage storage){
        if (storage.getFiles() == null){
            return false;
        }

        int index = 0;
        for (File file : storage.getFiles()) {
            if (storage.getFiles()[index] != null){
                file = storage.getFiles()[index];
            }
            index++;
        }
        return true;
    }

    private boolean checkIdFileWithIdFilesInStorage(Storage storage, File[] files){
        //сравнить id файлов из одного хранилища с id файлов в другом хранилище
        //если есть хоть одно совпадение, то возвращаю фолс, если совпадений нет - тру
        if (storage.getFiles() == null){
            return false;
        }

        for (int i = 0; i < files.length; i++) {
            for (int j = 0; j < storage.getFiles().length; j++) {
                if (files[i] != null && files[i].getId() != 0 && storage.getFiles()[j] != null && storage.getFiles()[j].getId() == files[i].getId()){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkSizeForStorage(Storage storageFrom, Storage storageTo){
        //определить размер хранилища в которое будем записывать файлы
        //определить размер хранилища из которого будем передавать файлы
        //если размер оставшегося свободного места в хранилище куда записываем файлы будет больше или равно суммы размеров записываемых файлов то возвращаю тру
        if (storageFrom.getStorageSize() == 0 || storageFrom.getFiles() == null){
            return false;
        }

        long sumSizeFileInStorageTo = 0;
        for (int i = 0; i < storageTo.getFiles().length; i++) {
            if (storageTo.getFiles()[i] != null && storageTo.getFiles()[i].getSize() != 0){
                sumSizeFileInStorageTo += storageTo.getFiles()[i].getSize();
            }
        }

        long sumSizeFileInStorageFrom = 0;
        for (int i = 0; i < storageFrom.getFiles().length; i++) {
            if (storageFrom.getFiles()[i] != null && storageFrom.getFiles()[i].getSize() != 0){
                sumSizeFileInStorageFrom += storageFrom.getFiles()[i].getSize();
            }
        }

        if ((storageTo.getStorageSize() - sumSizeFileInStorageTo) < sumSizeFileInStorageFrom){
            return false;
        }
        return true;
    }

    private boolean checkFormatForStorage(Storage storage, File[] files){
        if (storage.getFiles() == null){
            return false;
        }

        for (int i = 0; i < files.length; i++) {
            for (int j = 0; j < storage.getFormatsSupported().length; j++) {
                if (storage.getFormatsSupported()[j].equals(files[i].getFormat()) && files[i].getFormat() != null){
                    return true;
                }
            }
        }
        return false;
    }


    public void transferFile(Storage storageFrom, Storage storageTo, long id)throws Exception{
        //проверить есть ли свободное место для записи файла в хранилище
        //проверить совпадает ли формат файла с форматами хранилища в которое записывается файл
        //проверить хватит ли размера в хранилище для записи файла
        //проверить на совпадение id файлов записываемого с id файлами которые находятся в хранилище
        //найти файл который надо перезаписать в другое хранилище
        if (id == 0 || storageFrom.getFiles() == null)
            throw new Exception("Null pointer Exception");

        if (!checkingStorageForEmptyItems(storageTo))
            throw new Exception("Storage: " + storageTo.getId() + " - is full");

        if (!checkFormat(storageTo, fileFoundById(storageFrom, id)))
            throw new Exception("Format this file: " + fileFoundById(storageFrom, id) + " and format this storage: " + storageTo.getId() + " - is not supported");

        if (checkStorageSize(storageTo, fileFoundById(storageFrom, id)))
            throw new Exception("In storage: " + storageTo.getId() + "The file: " + fileFoundById(storageFrom, id) + " - is too large");

        if (!checkOnSameId(storageTo, fileFoundById(storageFrom, id)))
            throw new Exception("A file: " + fileFoundById(storageFrom, id) + " with the same id already exists in the storage: -" + storageTo.getId());

        for (int i = 0; i < storageFrom.getFiles().length; i++) {
            for (int j = i; j < storageTo.getFiles().length; j++) {
                if (storageFrom.getFiles()[i] != null && storageFrom.getFiles()[i].equals(fileFoundById(storageFrom, id)) && storageTo.getFiles()[j] == null){
                    storageTo.getFiles()[j] = storageFrom.getFiles()[i];
                    storageFrom.getFiles()[i] = null;
                    break;
                }
            }
        }
    }

    private File fileFoundById(Storage storageFrom, long id){
        for(File el : storageFrom.getFiles()){
            if (el != null && id == el.getId()){
                return el;
            }
        }
        return null;
    }

    private boolean checkOnSameId(Storage storage, File file){
        if (file == null || storage.getFiles() == null){
            return false;
        }

        for(File el : storage.getFiles()){
            if (el != null && file.getId() == el.getId()){
                return false;
            }
        }
        return true;
    }

    private boolean checkStorageSize(Storage storage, File file){
        if (file == null || storage.getFiles() == null){
            return false;
        }

        long sumSizeFiles = 0;
        for (File el : storage.getFiles()) {
            if (el != null) {
                sumSizeFiles += el.getSize();
            }
        }

        if (storage.getStorageSize() - sumSizeFiles <= file.getSize()){
            return true;
        }
        return false;
    }

    private boolean checkFormat(Storage storage, File file){
        // пройти по массиву стринг где лежат форматы и сравнить формат файла с форматами в хранилище, если есть совпадение то выход из цикла
        if (file == null || storage.getFormatsSupported() == null){
            return false;
        }

        boolean format = true;
        for(String el : storage.getFormatsSupported()){
            if (el.equals(file.getFormat())){
                format = true;
                break;
            }
            else format = false;
        }
        return format;
    }
}
