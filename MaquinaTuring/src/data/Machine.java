package data;
import java.util.List;

public class Machine {
    //Constroi a maquina
    private final List<String> states;      //conjunto de estados
    private final String alphabet;          //alfabeto da entrada
    private final String tapeAlphabet;      //alfabeto da entrada da fita
    private final char blankSymbol = '$';   //alfabeto da entrada
    private final String initialState;            //estado inicial
    private final List<String> finalStates; //estados finais
    private final List<Rule> rules;         //conjunto de regras

    public Machine(List<String> states , String alphabet, String tapeAlphabet, List<Rule> rules,String initialState, List<String> finalStates){
        this.states = states;
        this.alphabet = alphabet;
        this.tapeAlphabet = tapeAlphabet;
        this.rules = rules;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    @Override
    public String toString() {
        return  "\nEstados = " + states +
                "\nAlfabeto = " + alphabet + '\'' +
                "\nAlfabeto da fita = " + tapeAlphabet + '\'' +
                "\nSimbolo branco = " + blankSymbol +
                "\nEstado inicial = " + initialState + '\'' +
                "\nEstados finais = " + finalStates +
                "\nConjunto de regras = " + rules;
    }
}
