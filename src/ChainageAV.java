import java.util.ArrayList;


public class ChainageAV {

    public static boolean chainageAV(ArrayList<Fact> factBase, ArrayList<Rule> rules, Fact goal) {

        boolean newFactsInferred = true;

        while (newFactsInferred) {
            //Boolean that will be updated each time there is a new rule in the base to loop again
            newFactsInferred = false;
            ArrayList<Rule> applicableRules = new ArrayList<>();
            //we loop through the rules
            for (Rule rule : rules) {

                if (rule.getState() && factBase.containsAll(rule.getPremises())) {
                    applicableRules.add(rule);
                }
            }

            applicableRules.sort(new RulePremisesComparator());


            for (Rule rule : applicableRules) {

                //we check if all the premises are satisfied in a certain rule and that the rule isn't deactivated
                //TODO : This condition might create problems if so change it to a methode

                ArrayList<Fact> results = rule.getResult();
                //we add the new results to the fact base
                for (Fact result : results) {
                    if (!factBase.contains(result)) {
                        factBase.add(result);
                        System.out.println("Rule was applied and new conclusion " + result.getName() + " was added");
                        newFactsInferred = true;
                        //we deactivate the rule
                        rule.setState(false);
                    }
                }



            }

        }

        System.out.println(factBase.toString());
        return(factBase.toString().contains(goal.getName()));
//
//        else System.out.println("Goal wasn't reached");
//
//        for(Fact fact : factBase){
//            System.out.println(fact.getName());
//        }
    }
//    public static boolean isGoalReachable(ArrayList<Fact> factBase, ArrayList<Rule> rules, Fact goal) {
//        // Check if the goal is already in the fact base
//        for (Fact fact : factBase) {
//            if (fact.getName().equalsIgnoreCase(goal.getName())) {
//                return true;
//            }
//        }
//
//        // Check if any rule can directly infer the goal
//        for (Rule rule : rules) {
//            if (rule.getState() && rule.getResult().contains(goal) && factBase.containsAll(rule.getPremises())) {
//                return true;
//            }
//        }
//
//        // If the goal can't be reached directly, find all facts that can potentially lead to the goal
//        ArrayList<Fact> possibleGoals = new ArrayList<>();
//        for (Fact fact : factBase) {
//            if (fact.getRecipe()) {
//                for (Rule rule : rules) {
//                    if (rule.getState() && rule.getResult().contains(fact) && factBase.containsAll(rule.getPremises())) {
//                        possibleGoals.add(fact);
//                        break;
//                    }
//                }
//            }
//        }
//        return false;
//    }
}