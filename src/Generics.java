import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 22.04.20.
 */
public class Generics {
    public static void main(String[] args) {
        // Drzewo z liczbami calkowitymi:
        //      -2
        //     /  \
        //    1   -5
        //       /  \
        //      10   13
        //
        BaseNode intTree = new BaseNode<Integer>(-2,
            new BaseNode<Integer>(1),
            new BaseNode<Integer>(-5,
            new BaseNode<Integer>(10), new BaseNode<Integer>(13)
            )
        );
        System.out.print("Tree elements (in order): ");
        TreeUtils.visit(
            TreeTraversal.IN_ORDER,
            intTree,
            (Integer value) -> System.out.print(" " + value)
        );

        System.out.print("\n\nTree leaves: ");
        TreeUtils.forEachLeaf(
            intTree,
            (Integer value) -> System.out.print(" " + value)
        );
        final int [] sum = new int[1];
        TreeUtils.forEachLeaf(
            intTree,
            (Number value) -> sum[0] += value.intValue()
        );
        System.out.printf("\nSum of the leaves: %d\n", sum[0]);

        var minValue = TreeUtils.min(intTree);
        System.out.printf("\nMin value of the tree: %d\n", minValue);

        List<Number> positive = new ArrayList<>();
        System.out.print("Positive numbers: ");
        TreeUtils.filter(intTree, x -> x.intValue() > 0, positive);

        for (var x : positive) {
            System.out.printf("%d ", x);
        }
        System.out.println();



        // Drzewo wyrazenia arytmetycznego;
        //      *
        //    /   \
        //   +     2
        //  / \
        // 5  -3

        var expr = new BaseNode<ExprElement>(
            new ExprOperator(Operator.MULTUPLY),
            new BaseNode<ExprElement>(new ExprOperator(Operator.ADD),
            new BaseNode<ExprElement>(new ExprNumber(5)),
            new BaseNode<ExprElement>(new ExprNumber(-3))),
            new BaseNode<ExprElement>(new ExprNumber(2))
        );
        System.out.print("\nExpression tree elements (post order):");
        TreeUtils.visit(TreeTraversal.POST_ORDER,
            expr,
            (ExprElement el) -> System.out.print(" " + el)
        );
        System.out.printf("\nThe value of the expression is: %f\n", evaluate(expr));
    }
    // Metoda obliczajaca wartosc wyrazenia arytmetycznego.
    // Przechodzi drzewo w kolejnosci post-order, czyli zgodnie
    // z Odwrotna Notacja Polska (ang. RPN)
    public static double evaluate(TreeNode<ExprElement> expr) {
        // Stos na argumenty
        final var stack = new ArrayList<Double>();
        TreeUtils.visit(
            TreeTraversal.POST_ORDER,
            expr,
            (ExprElement el) -> {
                if (el instanceof ExprNumber) {
                    // Liczby odkladamy na stos
                    ExprNumber number = (ExprNumber)el;
                    stack.add(number.value);
                } else if (el instanceof ExprOperator) {
                    // Operator wymaga pobrania argumentow ze stosu,
                    // wykonania dzialan i wstawieniu wyniku
                    Operator op = ((ExprOperator)el).operator;
                    if (op.arity == 2) {
                        var b = stack.remove(stack.size()-1);
                        var a = stack.remove(stack.size()-1);
                        double result = 0;
                        switch(op) {
                            case ADD : result = a + b; break;
                            case SUBTRACT : result = a - b; break;
                            case MULTUPLY : result = a * b; break;
                            case DIVIDE : result = a / b; break;
                        }
                        stack.add(result);
                    }
                }
            }
        );
        return stack.get(0);
    }
}
/*
Wynik dzialania programu (wydruk na konsoli):
Tree elements (in order):
1 -2 10 -5 13
Tree leaves: 1 10 13
Sum of the leaves: 24
Min value of the tree: -5
Positive numbers: 1 10 13
Expression tree elements (post order): 5.0 -3.0 ADD 2.0 MULTUPLY
The value of the expression is: 4,000000
*/
