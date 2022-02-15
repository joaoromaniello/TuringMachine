package data;
public class Tape {

    private String Tape;
    private int Pointer;

    public Tape(String word) {
        word = word.concat("$$$");
        word = "$$$".concat(word);
        this.Tape = word;
        this.Pointer = 3;
    }

    public Tape(String tape, int pointer) {
        Tape = tape;
        Pointer = pointer;
    }

    public int getPointer() {
        return Pointer;
    }

    public void setPointer(String str){
        if(str.equals("R"))
            this.Pointer++;
        if(str.equals("L"))
            this.Pointer--;
        
    }

    public void changeTapePos(String newChar){
        StringBuilder newTape = new StringBuilder(getTape());
        newTape.setCharAt(Pointer, newChar.charAt(0));
        Tape = newTape.toString();
    }

    public String getTape() {
        return Tape;
    }

    public String toString(){
        int i = 0;
        StringBuilder tapeDivider = new StringBuilder();
        tapeDivider.append("[ ");
        for(;i<Tape.length();i++){
            if(i != getPointer())
            tapeDivider.append(Tape.charAt(i)).append(" ");
            else{
                tapeDivider.append('(');
                tapeDivider.append(Tape.charAt(i));
                tapeDivider.append(')');
                tapeDivider.append(" ");
            }

        }
        tapeDivider.append(" ]");
        return tapeDivider.toString();
    }

}

