package dz_lesson19;

public class File {
    private long id;
    private String name;
    private String format;
    private long size;

    public File(long id, String name, String format, long size) throws Exception {
        if (!checkOnLengthNameFile(name))
            throw new Exception(" File name can't be more 10 chars. File with this name can't be created");

        this.id = id;
        this.name = name;
        this.format = format;
        this.size = size;

    }

    public static boolean checkOnLengthNameFile(String name){
            if (name.isEmpty() || name.length() > 10) {
                return false;
            }
        return true;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public long getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
