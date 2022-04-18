package pdf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author Matt Crow <mattcrow19@gmail.com>
 */
public class PDFCombiner {
    private final List<PDDocument> src;
    
    public PDFCombiner(Collection<PDDocument> src){
        this.src = new ArrayList<>();
        this.src.addAll(src);
    }
    
    public final PDDocument combine(){
        PDDocument combined = new PDDocument();
        src.forEach((doc)->{
            doc.getPages().forEach((page)->{
                combined.addPage(page);
            });
        });
        return combined;
    }
}
