package lesson24.exercise;

public class Sys {
    private int filesCount;
    private String location;

    public Sys(int filesCount, String location) {
        this.filesCount = filesCount;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "filesCount=" + filesCount +
                ", location='" + location + '\'' +
                '}';
    }
}
