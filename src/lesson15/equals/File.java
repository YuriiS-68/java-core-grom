package lesson15.equals;

/**
 * Created by Skorodielov on 12.07.2017.
 */
public class File {
    private int size;
    private String path;
    private String extension;

    public File(int size, String path, String extension) {
        this.size = size;
        this.path = path;
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        return path.equals(file.path);
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (size != file.size) return false;
        if (!path.equals(file.path)) return false;
        if (!extension.equals(file.extension)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + path.hashCode();
        result = 31 * result + extension.hashCode();
        return result;
    }*/
}
