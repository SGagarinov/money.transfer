package infrastructure.repository.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
