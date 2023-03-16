import java.util.ArrayList;

public class Rule {

    //Les premisses de notre règle
    private ArrayList<Fact> premises;

    //Le resultat de notre règle
    private ArrayList<Fact> result;

    //Pour check si la règle est désactivée ou pas
    private boolean state;


    public Rule(){

    }


    public Rule(ArrayList<Fact> premises, ArrayList<Fact>  result){
        this.premises = premises;
        this.result = result;
        this.state = true;
    }

    public ArrayList<Fact> getPremises() {
        return premises;
    }

    public ArrayList<Fact>  getResult() {
        return result;
    }

    public boolean getState(){
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setPremises(ArrayList<Fact> premises) {
        this.premises = premises;
    }


    public void setResult(ArrayList<Fact>  result) {
        this.result = result;
    }

    //pour vérifier si une règle est applicable à une certaine base de faits ou pas
    public boolean applicable(ArrayList<Fact> factBase) {
        for (Fact premise : premises) {
            boolean found = false;
            for (Fact fact : factBase) {
                //The heart of the function
                if (premise.getName().equalsIgnoreCase(fact.getName()) && premise.getQuantity() <= fact.getQuantity()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }



}
