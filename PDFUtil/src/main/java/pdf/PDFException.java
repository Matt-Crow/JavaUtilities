package pdf;

/**
 * @author Matt Crow
 */
public class PDFException extends RuntimeException {
    
    public PDFException(Exception containedException) {
        super(containedException);
    }

    public PDFException(String msg, Exception containedException) {
        super(msg, containedException);
    }
}
