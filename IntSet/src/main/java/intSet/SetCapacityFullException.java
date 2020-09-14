package intSet;

/**
 * this is a class for the exception that is thrown when a adding a element into a full set
 */
public class SetCapacityFullException extends Exception{

    /**
     * constructor for the exception
     * @param message error message
     */
    public SetCapacityFullException(String message) {
        super(message);
    }
}
