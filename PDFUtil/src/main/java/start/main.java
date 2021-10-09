package start;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
/**
 *
 * @author Matt Crow
 */
public class main {
    public static void main(String... args){
        System.out.println("Works!");
        if(args.length >= 1){
            try (PDDocument doc = PDDocument.load(new File(args[0]))){
                System.out.println(doc);
            } catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
