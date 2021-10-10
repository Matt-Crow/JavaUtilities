package start;

import gui.Frame;
import java.io.File;
import org.apache.pdfbox.pdmodel.PDDocument;
import pdf.PDFReader;
import pdf.PDFWriter;
import pdf.PageRotator;
/**
 *
 * @author Matt Crow
 */
public class main {
    public static void main(String... args){
        System.out.println("Works!");
        if(args.length >= 1){
            PDFReader reader = new PDFReader(new File(args[0]));
            PDDocument doc = reader.read();
            PDDocument rotated = new PageRotator().rotateBy(doc, 90);
            PDFWriter writer = new PDFWriter(rotated);
            writer.save(new File(args[0].replace(".pdf", "-rotated.pdf")));
        } else {
            new Frame(); 
        }
    }
}
