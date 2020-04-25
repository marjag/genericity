/**
 * Created by marcin on 22.04.20.
 */
// Oraz operatory artymetyczne
enum Operator {
    ADD ('+', 2),
    SUBTRACT ('-', 2),
    MULTUPLY ('*', 2),
    DIVIDE ('/', 2);
    public char symbol; // symbol operatora
    public int arity; // liczba argumentow

    private Operator(char symbol, int arity) {
        this.symbol = symbol;
        this.arity = arity;
    }
}
