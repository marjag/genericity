/**
 * Created by marcin on 22.04.20.
 */
// Wyrazenie moze zawierac stale typu double
public class ExprNumber implements ExprElement {
    public double value = 0;
    ExprNumber(double value) { this.value = value; }
    @Override
    public String toString() { return String.valueOf(value); }

}
