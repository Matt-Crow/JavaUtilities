package pdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author Matt Crow
 */
public class PDFWriter {
    private final PDDocument doc;
    
    public PDFWriter(PDDocument doc){
        this.doc = doc;
    }
    
    public final void save(File to){
        try {
            doc.save(to);
        } catch (IOException ex) {
            throw new PDFException(ex);
        }
    }
}
