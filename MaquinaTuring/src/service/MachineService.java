package service;

import data.Machine;
import data.Rule;
import data.Tape;

import java.util.ArrayList;
import java.util.List;

public class MachineService {

    public Machine TMachine;
    private Tape Tape;
    public String word;
    public String State;
    public Boolean acceptance = false;
    public String finalTape;
    public String oldTape;
    private List<Rule> appliedRules = new ArrayList<>();
    private List<String> tapeStates = new ArrayList<>();

    public MachineService(Machine machine,Tape tape,String Word){
        this.TMachine = machine;
        this.Tape = tape;
        this.word = Word;
        this.State = TMachine.getInitialState();
        this.oldTape = word;
    }

    public Boolean belongsToAlphabet(){
        for( int i = 0 ; i < word.length() ; i++){
            if(TMachine.getAlphabet().indexOf(word.charAt(i)) == -1){
                return false;
            }
        }
        return true;
    }

    public Rule getApplicableRule(String State){
        for(int i = 0; i < TMachine.getRules().size();i++){
            //Caso a regra for aplicavel
            if(State.equals(TMachine.getRules().get(i).getSourceState()) && Tape.getTape().charAt(Tape.getPointer()) == TMachine.getRules().get(i).getSymbol())
                    return TMachine.getRules().get(i);
        }
        return null;
    }

    public int ProcessMachine(){
        if(!belongsToAlphabet()){
            return 0; //ERRO! PALAVRA NÃO PERTENCE A LINGUAGEM
        }else{
            applyRule();
        }
        return 1;
    }

    public void applyRule(){
        Rule newRule = getApplicableRule(State);
        //Caso não tiver regras mais para ser aplicada e o estado por estado final
        if(newRule == null && TMachine.getFinalStates().contains(State)){
            //System.out.println("Palavra aceita!");
            finalTape = Tape.getTape();
            takeBlankSymbols();
            //System.out.println("Fita final --> [ " + finalTape +" ] \n");
            this.acceptance = true;
            return ;
        }
        if(newRule == null){
            tapeStates.add("Não existe regra aplicavel...  ");
            //System.out.println("Não achei nenhuma regra... :( \n");
            //System.out.println("Palavra rejeitada...");
            this.acceptance = false;
        } else{

            //Adiciona os estados de fita passo a passo(Será utilizado posteriormente)
            tapeStates.add(Tape.toString());

            //Adiciona a regra à lista de regras aplicadas
            this.appliedRules.add(newRule);

            //System.out.println("ACHEI UMA REGRA!");
            //System.out.println(newRule.toString() +"\n");

            //Escreve o novo simbolo na fita
            Tape.changeTapePos(newRule.getTargetSymbol());

            //Anda com o ponteiro (Ou para a direita ou para esquerda)
            Tape.setPointer(newRule.getDirection());

            //Estado atual vira novo estado
            State = newRule.getTargetState();

            //Chama denovo a função para continuar o processamento
            applyRule();
        }
    }

    public void takeBlankSymbols(){
        int j = 0;
        String newFinalString = this.finalTape;

        //Descobrir o indice ao qual termina os simbolos brancos antecedentes à fita
        while(newFinalString.charAt(j) == '$')
            j++;

        int i = j;
        while(true){
            if(newFinalString.charAt(i) == '$'){
                setFinalTape(newFinalString.substring(j,i));
            break;}
            i++;
        }
    }

    public void setFinalTape(String finalTape){
        this.finalTape = finalTape;
    }

    public String getFinalTape (){
        return this.finalTape;
    }

    public String getOldTape (){
        return this.oldTape;
    }

    public String separateRules(){
        StringBuilder separatedRules = new StringBuilder();
        for(int i = 0; i < appliedRules.size();i++)
        separatedRules.append(i + 1).append(": ").append(appliedRules.get(i).toString()).append(" Fita --> ").append(tapeStates.get(i)).append('\n');
        return separatedRules.toString();
    }

    public String separateRulesNot(){
        StringBuilder separatedRulesNot = new StringBuilder();
        int i = 0;
        for(; i < appliedRules.size()-1;i++)
            separatedRulesNot.append(i + 1).append(": ").append(appliedRules.get(i).toString()).append(" Fita --> ").append(tapeStates.get(i)).append('\n');

        separatedRulesNot.append(i + 1).append(": ").append(tapeStates.get(i + 1));

        return separatedRulesNot.toString();
    }

    public String rulesAppliedToString(){
        if(acceptance)
            return "Passo a passo: " + '\n' + separateRules();
        else
            return "Passo a passo: " + '\n' + separateRulesNot();
    }

    public List<Rule> getAppliedRules() {
        return appliedRules;
    }

    public void setAppliedRules(List<Rule> appliedRules) {
        this.appliedRules = appliedRules;
    }

    public List<String> getTapeStates() {
        return tapeStates;
    }

    public void setTapeStates(List<String> tapeStates) {
        this.tapeStates = tapeStates;
    }

    public data.Tape getTape() {
        return Tape;
    }

    public void setTape(data.Tape tape) {
        Tape = tape;
    }
}
