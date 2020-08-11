package calculator;

/**
 *
 * @author Matt
 */
public class BooleanTreeNode extends AbstractTreeNode<BoolToken>{
    public BooleanTreeNode(boolean b){
        this((b) ? BoolToken.TRUE : BoolToken.FALSE);
    }
    public BooleanTreeNode(BoolToken val) {
        super(val);
    }

    @Override
    protected boolean evaluate() {
        return ((BoolToken)getValue()).getValue();
    }
    
}
