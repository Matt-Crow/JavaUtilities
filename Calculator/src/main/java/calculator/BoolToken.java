package calculator;

/**
 *
 * @author Matt
 */
public enum BoolToken {
    TRUE("true", true),
    FALSE("false", false);
    
    private final String display;
    private final boolean value;
    
    private BoolToken(String disp, boolean val){
        display = disp;
        value = val;
    }
    
    public boolean getValue(){
        return value;
    }
    @Override
    public String toString(){
        return display;
    }
}
