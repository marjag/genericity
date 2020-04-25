import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by marcin on 22.04.20.
 */
class TreeUtils {
    public static <T> void visit(final TreeTraversal traversal, final TreeNode<T> node, final Consumer<T> consumer) {
        visitNodes(traversal, node, (TreeNode<T> n) -> {
            consumer.accept(n.getData());
        });

    }
    public static <T> void visitNodes(final TreeTraversal traversal, final TreeNode<T> node, final Consumer<TreeNode<T>> consumer) {
        switch (traversal) {
            case IN_ORDER -> {
                if (node.getChildrenCount() > 0) {
                    visitNodes(traversal, node.getLeftChild(), consumer);
                    consumer.accept(node);
                    visitNodes(traversal, node.getRightChild(), consumer);
                } else {
                    consumer.accept(node);
                }
            }
            case POST_ORDER -> {
                if (node.getChildrenCount() > 0) {
                    visitNodes(traversal, node.getLeftChild(), consumer);
                    visitNodes(traversal, node.getRightChild(), consumer);
                    consumer.accept(node);
                } else {
                    consumer.accept(node);
                }
            }
            case PRE_ORDER -> {
                if (node.getChildrenCount() > 0) {
                    consumer.accept(node);
                    visitNodes(traversal, node.getLeftChild(), consumer);
                    visitNodes(traversal, node.getRightChild(), consumer);
                } else {
                    consumer.accept(node);
                }
            }
        }
    }

    public static <T> void forEachLeaf(final TreeNode<T> node, final Consumer<? super T> consumer) {
        if (node.getChildrenCount() > 0) {
            visitNodes(TreeTraversal.IN_ORDER, node, n -> {
                if (n.getChildrenCount() == 0) {
                    consumer.accept(n.getData());
                }

            });
        }
    }

    public static <T extends Number> List<T> filter(final TreeNode<T> root, Predicate<? super T> pred, List<T> result) {
        visitNodes(TreeTraversal.IN_ORDER, root, n -> {
            if (pred.test(n.getData())) {
                result.add(n.getData());
            }
        });
        return result;
    }

    public static <T extends Comparable<T>> T min(final TreeNode<T> node) {
        var ref = new Object() {
            T min = node.getData();
        };
        if (node.getChildrenCount() > 0) {
            visitNodes(TreeTraversal.IN_ORDER, node, n -> {
                if (ref.min.compareTo(n.getData()) > 0) {
                    ref.min = n.getData();
                }
            });
        }
        return ref.min;
    }

}