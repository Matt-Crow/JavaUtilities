package pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author Matt Crow
 */
public class PDFReader {
    private final File pdf;
    
    public PDFReader(File pdf){
        this.pdf = pdf;
    }
    
    public final PDDocument read() throws PDFException {
        PDDocument doc = null;
        try (
            InputStream in = new FileInputStream(pdf);
        ){
            doc = PDDocument.load(in);
        } catch (IOException ex) {
            throw new PDFException(ex);
        }
        return doc;
    }
}
