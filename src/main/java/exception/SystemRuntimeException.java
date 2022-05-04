package exception;

public class SystemRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 6171978443681353425L;

    public SystemRuntimeException(String message) {
        super(message);
    }
}
