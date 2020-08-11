package calculator;

import java.util.function.BiPredicate;

/**
 *
 * @author Matt
 */
public enum OpToken {
    AND("/\\", (x, y)-> x && y),
    OR("\\/",(x, y)-> x || y);
    
    private final String display;
    private final BiPredicate<Boolean, Boolean> f;
    
    private OpToken(String disp, BiPredicate<Boolean, Boolean> bp){
        display = disp;
        f = bp;
    }
    
    @Override
    public String toString(){
        return display;
    }
    
    public boolean evaluate(boolean x, boolean y){
        return f.test(x, y);
    }
}
