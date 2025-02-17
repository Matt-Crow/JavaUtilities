package calculator;

import java.util.Arrays;

/**
 *
 * @author Matt
 */
public class Calculator {
    public static void main(String[] args){
        String ip = "";
        ip = "true \\/ FaLSe /\\";
        System.out.println(Arrays.toString(Tokenizer.tokenize(ip)));
    }
}
