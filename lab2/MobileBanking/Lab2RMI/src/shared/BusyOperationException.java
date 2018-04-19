package shared;

/**
 *
 * @author Micael
 */
public class BusyOperationException extends Exception {

    public BusyOperationException() {}

    public BusyOperationException(String message) {
        super(message);
    }

    public BusyOperationException(Throwable cause) {
        super(cause);
    }

    public BusyOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}
