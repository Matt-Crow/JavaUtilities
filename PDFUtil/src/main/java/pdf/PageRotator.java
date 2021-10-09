package pdf;

import java.io.IOException;
import java.util.Iterator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import static org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode.PREPEND;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.util.Matrix;

/**
 *
 * @author Matt Crow
 */
public class PageRotator {
    // https://stackoverflow.com/questions/40611736/rotate-pdf-around-its-center-using-pdfbox-in-java
    public PDDocument rotateBy(PDDocument doc, int degreesClockwise){
        PDDocument newDoc = new PDDocument();
        Iterator<PDPage> iter = doc.getPages().iterator();
        PDPage page;
        PDRectangle box;
        float[] t;
        while(iter.hasNext()){
            page = iter.next();
            box = page.getCropBox();
            t = new float[]{
                (box.getLowerLeftX() + box.getUpperRightX()) / 2,
                (box.getLowerLeftY() + box.getUpperRightY()) / 2
            };
            try(
                PDPageContentStream s = new PDPageContentStream(newDoc, page, PREPEND, false);
            ){
                s.transform(Matrix.getTranslateInstance(t[0], t[1]));
                s.transform(Matrix.getRotateInstance(Math.toRadians(-degreesClockwise), 0, 0));
                s.transform(Matrix.getTranslateInstance(-t[0], -t[1]));
            } catch (IOException ex) {
                throw new PDFException(ex);
            }
            newDoc.addPage(page);
            System.out.println("rotate");
        }
        return newDoc;
    }
}
