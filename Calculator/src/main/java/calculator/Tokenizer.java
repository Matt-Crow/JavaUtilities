package calculator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Matt
 */
public class Tokenizer {
    public static Enum[] tokenize(String s){
        ArrayList<Enum> ret = new ArrayList<>();
        
        StringBuilder currToken = new StringBuilder();
        for(char c : s.toCharArray()){
            if(!Character.isWhitespace(c)){
                currToken.append(c);
            }
            if(recognizeToken(currToken.toString())){
                ret.add(toToken(currToken.toString()));
                currToken.delete(0, currToken.length());
            }
            System.out.println(c);
        }
        if(currToken.length() != 0){
            ret.add(toToken(currToken.toString()));
        }
        
        return ret.toArray(new Enum[ret.size()]);
    }
    
    private static boolean recognizeToken(String s){
        return Arrays.stream(new String[]{"true", "false", "/\\", "\\/"})
            .anyMatch((st)->st.equalsIgnoreCase(s));
    }
    
    private static Enum toToken(String s){
        Enum ret = null;
        if(s.equalsIgnoreCase("true")){
            ret = BoolToken.TRUE; 
        } else if(s.equalsIgnoreCase("false")){
            ret = BoolToken.FALSE;
        } else if(s.equals("/\\")){
            ret = OpToken.AND;
        } else if(s.equals("\\/")){
            ret = OpToken.OR;
        }else {
            throw new NullPointerException("Cannot find enum for " + s);
        }
        return ret;
    }
}
