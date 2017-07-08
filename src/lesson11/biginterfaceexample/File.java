package lesson11.biginterfaceexample;

/**
 * Created by Skorodielov on 24.06.2017.
 */
public class File {
    private String name;
    private String path;
    private String extension;
    private int size;

    public File(String name, String path, String extension, int size) {
        this.name = name;
        this.path = path;
        this.extension = extension;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    public int getSize() {
        return size;
    }
    //4
}
