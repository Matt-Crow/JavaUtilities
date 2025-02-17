package calculator;

import static java.lang.System.out;

/**
 *
 * @author Matt
 */
public abstract class AbstractTreeNode<T> {
    private AbstractTreeNode<T> left;
    private AbstractTreeNode<T> right;
    private final T value;
    
    public AbstractTreeNode(T val){
        value = val;
        left = null;
        right = null;
    }
    
    public final AbstractTreeNode<T> setLeft(AbstractTreeNode<T> l){
        if(l == null){
            throw new NullPointerException(); 
        }
        if(this.equals(l)){
            throw new IllegalArgumentException("Cannot set left to this");
        }
        if(left != null){
            throw new UnsupportedOperationException("Left is already set");
        }
        left = l;
        
        return this;
    }
    public final AbstractTreeNode<T> getLeft(){
        return left;
    }
    
    public final AbstractTreeNode<T> setRight(AbstractTreeNode<T> r){
        if(r == null){
            throw new NullPointerException(); 
        }
        if(this.equals(r)){
            throw new IllegalArgumentException("Cannot set right to this");
        }
        if(right != null){
            throw new UnsupportedOperationException("Right is already set");
        }
        right = r;
        
        return this;
    }
    public final AbstractTreeNode<T> getRight(){
        return right;
    }
    
    public T getValue(){
        return value;
    }
    
    protected abstract boolean evaluate();
    
    public void print(){
        out.print("(");
        if(left != null){
            left.print();
        }
        out.print(value);
        if(right != null){
            right.print();
        }
        out.print(")");
    }
}
