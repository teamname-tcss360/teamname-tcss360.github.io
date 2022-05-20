package src;

/**
 * ExportException class that is used
 * if the exportSettings method has an error.
 *
 * @author  Trevor Tomlin
 * @version 1.0
 * @since   2022-05-18
 */

public class ExportException extends Exception {

    public ExportException(String message) {

        super(message);

    }

}
