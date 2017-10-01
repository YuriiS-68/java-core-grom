package lesson29;

public class File implements Comparable<File>{
    private String fileName;
    private long sizeInBytes;

    public File(String fileName, long sizeInBytes) {
        this.fileName = fileName;
        this.sizeInBytes = sizeInBytes;
    }

    public String getFileName() {
        return fileName;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    @Override
    public int compareTo(File file) {
        int res = 0;

        if (file.getSizeInBytes() > this.getSizeInBytes())
            return 1;
        else if (file.getSizeInBytes() < this.getSizeInBytes())
            return -1;

        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        return fileName.equals(file.fileName);
    }

    @Override
    public int hashCode() {
        return fileName.hashCode();
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + fileName + '\'' +
                ", sizeInBytes=" + sizeInBytes +
                '}';
    }

}
