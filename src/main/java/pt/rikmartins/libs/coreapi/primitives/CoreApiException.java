package pt.rikmartins.libs.coreapi.primitives;

public class CoreApiException extends Exception {
    public CoreApiException() {
        super();
    }

    public CoreApiException(String message) {
        super(message);
    }

    public CoreApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreApiException(Throwable cause) {
        super(cause);
    }

    protected CoreApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
