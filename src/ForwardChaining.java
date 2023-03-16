import java.util.ArrayList;

public class ForwardChaining {
    public static ArrayList<Fact> forwardChaining(ArrayList<Rule> rules, ArrayList<Fact> factBase, Fact goal) {
        boolean newFactsAdded = true;
        ArrayList<Fact> allRecipes = new ArrayList<>();

        while (newFactsAdded) {
            newFactsAdded = false;
            for (Rule rule : rules) {
                //System.out.println(factBase.toString());
                if (rule.applicable(factBase)) {
                    ArrayList<Fact> result = rule.getResult();
                    for (Fact fact : result) {
                        if (!factBase.contains(fact)) {
                            factBase.add(fact);
                            System.out.println("The fact was added is " + fact.getName());
                            newFactsAdded = true;
                        }
                    }
                }
            }

        }
        boolean exists = false;
        for(Fact fact : factBase){
            System.out.println(fact.getName());
            if(fact.getName().equals(goal.getName())){
                exists = true;
                break;
            }else{
                exists = false;
            }
        }
        System.out.println("Goal is : " + goal.getName());
        for(Fact fact : factBase){
            if(fact.getRecipe()){
               // System.out.println("Une recette à été trouvé ! "+fact.getName());
                //we add all recipes that the user can make with the given ingredients
                allRecipes.add(fact);
            }
        }
        return allRecipes;
    }

}
