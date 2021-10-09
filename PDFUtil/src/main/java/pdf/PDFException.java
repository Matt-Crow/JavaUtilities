/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

/**
 *
 * @author Matt Crow
 */
public class PDFException extends RuntimeException {
    private final Exception containedException;
    
    /**
     * Creates a new instance of <code>PDFException</code> without detail
     * message.
     */
    public PDFException(Exception containedException) {
        this.containedException = containedException;
    }

    /**
     * Constructs an instance of <code>PDFException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PDFException(String msg, Exception containedException) {
        super(msg);
        this.containedException = containedException;
    }
}
