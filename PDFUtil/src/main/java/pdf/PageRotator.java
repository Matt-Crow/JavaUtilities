package pdf;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Iterator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import static org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode.PREPEND;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.util.Matrix;

/**
 * A huge thank you to https://stackoverflow.com/questions/40611736/rotate-pdf-around-its-center-using-pdfbox-in-java!
 * @author Matt Crow
 */
public class PageRotator {
    public PDDocument rotateBy(PDDocument doc, int degreesClockwise){
        PDDocument newDoc = new PDDocument();
        Iterator<PDPage> iter = doc.getPages().iterator();
        PDPage page;
        Matrix rotation = Matrix.getRotateInstance(Math.toRadians(-degreesClockwise), 0, 0);
        Rectangle bounds;
        PDRectangle newBox;
        while(iter.hasNext()){
            page = iter.next();
            try(
                PDPageContentStream s = new PDPageContentStream(newDoc, page, PREPEND, false);
            ){
                s.transform(rotation);                
            } catch (IOException ex) {
                throw new PDFException(ex);
            }
            
            bounds = page.getCropBox().transform(rotation).getBounds();
            newBox = new PDRectangle(
                (float)bounds.getX(),
                (float)bounds.getY(),
                (float)bounds.getWidth(),
                (float)bounds.getHeight()
            );
            page.setCropBox(newBox);
            page.setMediaBox(newBox);
            newDoc.addPage(page);
        }
        return newDoc;
    }
}
