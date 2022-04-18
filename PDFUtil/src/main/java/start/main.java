package start;

import gui.Frame;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import pdf.PDFCombiner;
import pdf.PDFReader;
import pdf.PDFWriter;
import pdf.PageRotator;
/**
 *
 * @author Matt Crow
 */
public class main {
    public static void main(String... args){
        if(args.length >= 2){
            // no time, must spaghetti code!
            String cmd = args[0];
            switch (cmd){
                case "rotate": {
                    PDFReader reader = new PDFReader(new File(args[1]));
                    PDDocument doc = reader.read();
                    PDDocument rotated = new PageRotator().rotateBy(doc, 90);
                    PDFWriter writer = new PDFWriter(rotated);
                    writer.save(new File(args[1].replace(".pdf", "-rotated.pdf")));
                    break;
                }
                case "combine": {
                    List<PDDocument> docs = new ArrayList<>();
                    for(int i = 1; i < args.length; ++i){
                        PDFReader reader = new PDFReader(new File(args[i]));
                        PDDocument doc = reader.read();
                        docs.add(doc);
                    }
                    PDDocument combined = new PDFCombiner(docs).combine();
                    PDFWriter writer = new PDFWriter(combined);
                    writer.save(new File(args[1].replace(".pdf", "-combined.pdf")));
                    break;
                }
                default: {
                    System.err.printf("Bad command: \"%s\"", cmd);
                }
            }
        } else {
            new Frame(); 
        }
    }
}
