package gui;

import java.awt.Component;
import java.io.File;
import java.util.function.Consumer;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Matt Crow
 */
public class PDFChooser {
    private final Component parent;
    private final Consumer<File> withFile;
    private final JFileChooser chooser;
    
    public PDFChooser(Consumer<File> withFile, Component parent){
        this.parent = parent;
        this.withFile = withFile;
        chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("PDF", new String[]{"pdf"}));
        chooser.setMultiSelectionEnabled(false);
    }
    
    public final void choose(){
        if(chooser.showOpenDialog(this.parent) == JFileChooser.APPROVE_OPTION){
            withFile.accept(chooser.getSelectedFile());
        }
    }
}
