package jankowiak.kamil.exceptions;

public class AppException extends RuntimeException {

    private String exceptionMessage;

    public AppException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
