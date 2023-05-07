package infrastructure.repository.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
