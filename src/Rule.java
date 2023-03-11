import java.util.ArrayList;

public class Rule {

    //Les premisses de notre règle
    private ArrayList<String> premises;

    //Le resultat de notre règle
    private ArrayList<String> result;

    //Pour check si la règle est désactivée ou pas
    private boolean state;


    public Rule(){

    }


    public Rule(ArrayList<String> premises, ArrayList<String>  result){
        this.premises = premises;
        this.result = result;
        this.state = true;
    }

    public ArrayList<String> getPremises() {
        return premises;
    }

    public ArrayList<String>  getResult() {
        return result;
    }

    public boolean getState(){
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setPremises(ArrayList<String> premises) {
        this.premises = premises;
    }

    public void setAntecedents(ArrayList<String> antecedents) {
        this.premises = antecedents;
    }

    public void setResult(ArrayList<String>  result) {
        this.result = result;
    }

    //pour vérifier si une règle est applicable à une certaine base de faits ou pas
    public boolean applicable (ArrayList<String> facts){
                return facts.containsAll(premises);
    }


}
