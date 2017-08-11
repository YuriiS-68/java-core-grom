package lesson20.task2.exception;

public class LimitExceeded extends Exception {
    public LimitExceeded(String message) {
        super(message);
    }
}
