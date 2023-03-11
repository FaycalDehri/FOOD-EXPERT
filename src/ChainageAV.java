import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChainageAV {

    public static void chainageAV(ArrayList<String> factBase, ArrayList<Rule> rules,String goal) {

        boolean newFactsInferred = true;

        while (newFactsInferred) {
            //Boolean that will be updated each time there is a new null in the base to loop again
            newFactsInferred = false;
            //we loop through the rules
            for (Rule rule : rules) {
                //we check if all the premises are satisfied in a certain rule and that the rule isn't deactivated
                //TODO : This condition might create problems if so change it to a methode
                if (rule.getState() && factBase.containsAll(rule.getPremises())) {
                    ArrayList<String> results = rule.getResult();
                    //we add the new results to the fact base
                    for(String result : results){
                        if(!factBase.contains(result)){
                            factBase.add(result);
                            System.out.println("Rule was applied and new conclusion "+result+" was added");
                            newFactsInferred = true;
                            //we deactivate the rule
                            rule.setState(false);
                        }
                    }
                }
            }
        }
        if(factBase.contains(goal)) System.out.println("Goal reached");
        else System.out.println("Goal wasn't reached");
    }


    // This methode is used to check if all premises are satisfied given a certain rule
//    private boolean checkPremises(Rule rule) {
//        for (String premise : rule.getPremises()) {
//            if (!factBase.containsKey(premise) || !factBase.get(premise)) {
//                return false;
//            }
//        }
//        return true;
//    }

}