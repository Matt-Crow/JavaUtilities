package calculator;

/**
 *
 * @author Matt
 */
public class OperatorTreeNode extends AbstractTreeNode<OpToken>{

    public OperatorTreeNode(OpToken val) {
        super(val);
    }

    @Override
    protected boolean evaluate() {
        if(getLeft() == null || getRight() == null){
            throw new NullPointerException();
        }
        return ((OpToken)getValue()).evaluate(getLeft().evaluate(), getRight().evaluate());
    }
    
}
