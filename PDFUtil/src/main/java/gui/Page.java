package gui;

import java.io.File;
import java.util.List;
import javax.swing.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import pdf.PDFReader;
import pdf.PDFSplitter;
import pdf.PDFWriter;
import pdf.PageRotator;

/**
 *
 * @author Matt Crow
 */
public class Page extends JPanel {    
    private final PDFOption rotateOption;
    private final PDFOption splitOption;
    
    public Page(){
        JPanel options = new JPanel();
        
        rotateOption = new PDFOption("Rotate", "Choose file to rotate", (file)->{
            PDDocument input = new PDFReader(file).read();
            PDDocument output = new PageRotator().rotateBy(input, 90);
            new PDFWriter(output).save(new File(file.getAbsolutePath().replace(".pdf", "-rotated.pdf")));
        });
        options.add(rotateOption);
        
        splitOption = new PDFOption("Split", "Choose file to split", (file)->{
            PDDocument input = new PDFReader(file).read();
            List<PDDocument> output = new PDFSplitter(input).split();
            int i = 1;
            for(PDDocument doc : output){
                new PDFWriter(doc).save(new File(file.getAbsolutePath().replace(".pdf", String.format("-page-%d.pdf", i))));
                ++i;
            }
        });
        options.add(splitOption);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("PDF Rotator"));
        add(options);
    }
}
