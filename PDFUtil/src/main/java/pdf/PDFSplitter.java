package pdf;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author Matt Crow
 */
public class PDFSplitter {
    private final PDDocument doc;
    
    public PDFSplitter(PDDocument doc){
        this.doc = doc;
    }
    
    public final List<PDDocument> split(){
        List<PDDocument> newDocs = new LinkedList<>();
        PDDocument newDoc;
        PDPage page;
        for(Iterator<PDPage> iter = doc.getPages().iterator(); iter.hasNext(); ){// do nothing at the end
            newDoc = new PDDocument();
            page = iter.next();
            newDoc.addPage(page);
            newDocs.add(newDoc);
        }
        return newDocs;
    }
}
