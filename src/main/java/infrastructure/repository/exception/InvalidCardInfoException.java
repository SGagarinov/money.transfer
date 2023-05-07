package infrastructure.repository.exception;

public class InvalidCardInfoException extends RuntimeException {
    public InvalidCardInfoException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
