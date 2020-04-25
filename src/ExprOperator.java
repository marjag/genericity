/**
 * Created by marcin on 22.04.20.
 */
// Wezel operatora
public class ExprOperator implements ExprElement {
    public Operator operator;

    ExprOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}
