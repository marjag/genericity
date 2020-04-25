/**
 * Created by marcin on 22.04.20.
 */
public class BaseNode<T> implements TreeNode<T> {
    protected T data; // dane
    protected TreeNode<T> left = null;
    protected TreeNode<T> right = null;

    public BaseNode(T data) { this.data = data; }
    public BaseNode(T data, BaseNode<T> left, BaseNode<T> right) {
        this(data);
        this.left = left;
        this.right = right;
    }
    @Override
    public T getData() { return data; }

    @Override
    public TreeNode<T> getLeftChild() { return left; }
    public void setLeftChild(TreeNode<T> node) { left = node; }

    @Override
    public TreeNode<T> getRightChild() { return right; }
    public void setRightChild(TreeNode<T> node) { right = node; }
}
