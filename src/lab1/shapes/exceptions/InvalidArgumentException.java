package lab1.shapes.exceptions;


public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "InvalidArgumentException: " + getMessage();
    }
}
