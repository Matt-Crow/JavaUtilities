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
    private final JTextArea output;
    
    private final PDFChooser rotateOption;
    private final JButton rotateButton;
    
    private final PDFChooser splitOption;
    private final JButton splitButton;
    
    public Page(){        
        output = new JTextArea("TOTO Drag and drop PDF for each option");
        
        JPanel options = new JPanel();
        
        rotateOption = new PDFChooser((file)->{
            PDDocument input = new PDFReader(file).read();
            PDDocument output = new PageRotator().rotateBy(input, 90);
            new PDFWriter(output).save(new File(file.getAbsolutePath().replace(".pdf", "-rotated.pdf")));
        }, this);
        rotateButton = new JButton("Rotate a PDF");
        rotateButton.addActionListener((e)->rotateOption.choose());
        options.add(rotateButton);
        
        splitOption = new PDFChooser((file)->{
            PDDocument input = new PDFReader(file).read();
            List<PDDocument> output = new PDFSplitter(input).split();
            int i = 1;
            for(PDDocument doc : output){
                new PDFWriter(doc).save(new File(file.getAbsolutePath().replace(".pdf", String.format("-page-%d.pdf", i))));
                ++i;
            }
        }, this);
        splitButton = new JButton("Split a PDF");
        splitButton.addActionListener((e)->splitOption.choose());
        options.add(splitButton);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("PDF Rotator"));
        add(output);
        add(options);
    }
}
