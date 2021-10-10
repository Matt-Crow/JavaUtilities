package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import pdf.PDFReader;
import pdf.PDFWriter;
import pdf.PageRotator;

/**
 *
 * @author Matt Crow
 */
public class Page extends JPanel implements ActionListener {
    private final JFileChooser chooser;
    private final JTextArea output;
    private final JButton button;
    
    public Page(){
        chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("PDF", new String[]{"pdf"}));
        chooser.setMultiSelectionEnabled(false);
        chooser.addActionListener(this);
        output = new JTextArea("TOTO Drag and drop PDF");
        button = new JButton("Select File");
        button.addActionListener((e)->chooser.showOpenDialog(this));
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("PDF Rotator"));
        add(output);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PDDocument input = new PDFReader(chooser.getSelectedFile()).read();
        PDDocument output = new PageRotator().rotateBy(input, 90);
        new PDFWriter(output).save(new File(chooser.getSelectedFile().getAbsolutePath().replace(".pdf", "-rotated.pdf")));
    }
}
