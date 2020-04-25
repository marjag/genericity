/**
 * Created by marcin on 22.04.20.
 */
public interface TreeNode<T> {
    // Zwraca dane przechowywane w węzle
    public T getData();

    // Zwraca potomka z lewej strony
    public TreeNode<T> getLeftChild();

    // Zwraca potomka z prawej strony
    public TreeNode<T> getRightChild();

    // Liczba potomków - zero, jeżeli zarówno lewy i prawy są null
    default int getChildrenCount() {
        return (getLeftChild() != null ? 1 : 0)
                + (getRightChild() != null ? 1 : 0);
    }
}
