package data;

public class Rule {
    private final String sourceState; //Estado inicial
    private final char symbol; //Simbolo lido
    private final String targetState; //Proximo estado
    private final String targetSymbol; //O que sera colocado na fita
    private final String direction; //O que será feito na fita

    public Rule(String sourceState, char symbol, String targetState, String TSymbol, String dir) {
        this.sourceState = sourceState;
        this.symbol = symbol;
        this.targetState = targetState;
        this.targetSymbol = TSymbol; // O que sera colocado na fita
        this.direction = dir;
    }

    public String getSourceState() {
        return sourceState;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getTargetState() {
        return targetState;
    }

    public String getTargetSymbol() {
        return targetSymbol;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public String toString() {
            return "(" + sourceState + "," + symbol +  ")" + " \u2192 " + "(" + targetState + "," + targetSymbol + "," + direction +  ")";
    }
}