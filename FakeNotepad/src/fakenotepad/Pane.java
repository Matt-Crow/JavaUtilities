package fakenotepad;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author Matt
 */
public class Pane extends JPanel{
    private final JTextArea textPlace;
    private File selectedFile;
    public Pane(){
        super();
        selectedFile = null;
        
        setLayout(new BorderLayout());
        
        JMenuBar menu = new JMenuBar();
        add(menu, BorderLayout.PAGE_START);
        
        JButton create = new JButton("Create a new file");
        create.addActionListener((e)->{
            JFileChooser choose = new JFileChooser();
            choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(choose.showOpenDialog(choose) == JFileChooser.APPROVE_OPTION){
                String name = JOptionPane.showInputDialog("Enter a name for this file:");
                File f = new File(choose.getSelectedFile().getAbsolutePath() + "/" + name);
                try {
                    f.createNewFile();
                } catch (IOException ex) {
                    reportError(ex);
                }
                openFile(f);
            }
        });
        menu.add(create);
        
        JButton load = new JButton("Open a file");
        load.addActionListener((e)->{
            JFileChooser choose = new JFileChooser();
            choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if(choose.showOpenDialog(choose) == JFileChooser.APPROVE_OPTION){
                File f = choose.getSelectedFile();
                openFile(f);
            }
        });
        menu.add(load);
        
        JButton save = new JButton("Save file");
        save.addActionListener((e)->{
            if(selectedFile == null){
                JFileChooser choose = new JFileChooser();
                choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if(choose.showOpenDialog(choose) == JFileChooser.APPROVE_OPTION){
                    String name = JOptionPane.showInputDialog("Enter a name for this file:");
                    File f = new File(choose.getSelectedFile().getAbsolutePath() + "/" + name);
                    saveToFile(f);
                }
            } else {
                saveToFile(selectedFile);
            }
        });
        menu.add(save);
        
        textPlace = new JTextArea("no file selected");
        textPlace.setTabSize(4);
        
        JScrollPane scrolly = new JScrollPane(textPlace);
        add(scrolly, BorderLayout.CENTER);
        
        revalidate();
        repaint();
        setBackground(Color.white);
    }
    
    public void openFile(File f){
        selectedFile = f;
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            StringBuilder b = new StringBuilder();
            while(read.ready()){
                b.append(read.readLine()).append("\n");
            }
            read.close();
            textPlace.setText(b.toString());
        } catch (IOException ex) {
            reportError(ex);
        }
    }
    public void saveToFile(File f){
        selectedFile = f;
        try{
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
            write.write(textPlace.getText());
            write.close();
        } catch(IOException ex){
            reportError(ex);
        }
    }
    
    private void reportError(Exception ex){
        JTextArea text = new JTextArea(ex.getMessage());
        text.setEditable(false);
        
        JScrollPane scroll = new JScrollPane(text);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(this, scroll, "Nooooo! Bad!", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
        //save to a file
    }
}
