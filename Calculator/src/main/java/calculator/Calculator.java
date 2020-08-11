package calculator;

import java.util.Scanner;
import static java.lang.System.out;
import java.util.Arrays;

/**
 *
 * @author Matt
 */
public class Calculator {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String ip = "";
        //out.print("Enter a statement to calculate: ");
        ip = "true \\/ FaLSe /\\";
        out.println(Arrays.toString(Tokenizer.tokenize(ip)));
    }
}
