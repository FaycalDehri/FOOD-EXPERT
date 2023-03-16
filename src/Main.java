import java.util.*;

public class Main {


    public static void main(String[] args) {
        //Map<String, Boolean> factBase = new HashMap<>();
//        factBase.put("D", true);
//        factBase.put("O", true);
//        factBase.put("G", true);

//        ArrayList<String> factBase = new ArrayList<>();
//        factBase.add("D");
//        factBase.add("O");
//        factBase.add("G");
//
//        ArrayList<Rule> rules = new ArrayList<>();
//
//        rules.add(new Rule(new ArrayList<>(Arrays.asList("A","B")),new ArrayList<>(Arrays.asList("F"))));
//        rules.add(new Rule(new ArrayList<>(Arrays.asList("F","H")),new ArrayList<>(Arrays.asList("I"))));
//        rules.add(new Rule(new ArrayList<>(Arrays.asList("D","H","G")),new ArrayList<>(Arrays.asList("A"))));
//        rules.add(new Rule(new ArrayList<>(Arrays.asList("O","G")),new ArrayList<>(Arrays.asList("H"))));
//        rules.add(new Rule(new ArrayList<>(Arrays.asList("E","H")),new ArrayList<>(Arrays.asList("B"))));
//        rules.add(new Rule(new ArrayList<>(Arrays.asList("G","A")),new ArrayList<>(Arrays.asList("B"))));
//        rules.add(new Rule(new ArrayList<>(Arrays.asList("G","H")),new ArrayList<>(Arrays.asList("P"))));
//        rules.add(new Rule(new ArrayList<>(Arrays.asList("G","H")),new ArrayList<>(Arrays.asList("Q"))));
//        rules.add(new Rule(new ArrayList<>(Arrays.asList("D","O","G")),new ArrayList<>(Arrays.asList("J"))));
//


//        ArrayList<Rule> rules = new ArrayList<>();
//        rules.add(new Rule((ArrayList<String>) List.of("A", "B"), "F"));
//        rules.add(new Rule(List.of("F", "H"), "I"));
//        rules.add(new Rule(List.of("D", "H", "G"), "A"));
//        rules.add(new Rule(List.of("O", "G"), "H"));
//        rules.add(new Rule(List.of("E", "H"), "B"));
//        rules.add(new Rule(List.of("G", "A"), "B"));
//        rules.add(new Rule(List.of("G", "H"), "P"));
//        rules.add(new Rule(List.of("G", "H"), "Q"));
//        rules.add(new Rule(List.of("D", "G", "G"), "J"));
//        rules.add(new Rule(List.of("D", "G", "U"), "W"));

//        ChainageAV.chainageAV(factBase,rules,"I");
        // Creating the facts
        Fact fact1 = new Fact("Huile", 5,false);
        Fact fact2 = new Fact("eggs", 2,false);
        Fact fact3 = new Fact("cheese", 3,false);
        Fact fact4 = new Fact("thon", 10,false);
        Fact fact5 = new Fact("mimosa", 1,true);
        Fact fact6= new Fact("mayonnaise", 1,false);
        Fact fact7= new Fact("potato", 1,false);
        Fact fact8= new Fact("frites", 1,true);
        Fact fact9= new Fact("frites omelette", 1,true);
        Fact fact10= new Fact("omelettes", 1,true);
        Fact fact11= new Fact("beurre", 1,false);



        // Creating the initial fact base
        ArrayList<Fact> factBase = new ArrayList<>();
        factBase.add(fact1);
        factBase.add(fact2);
        factBase.add(fact3);

        // Creating the goal fact
        Fact goal = new Fact("frites", 1,true);

        // Creating the rules
        Rule rule1 = new Rule(new ArrayList<>(Arrays.asList(fact1, fact2)), new ArrayList<>(Collections.singletonList(fact6)));
        Rule rule2 = new Rule(new ArrayList<>(Arrays.asList(fact3)), new ArrayList<>(Collections.singletonList(fact5)));
        Rule rule3 = new Rule(new ArrayList<>(Arrays.asList(fact6, fact4)), new ArrayList<>(Collections.singletonList(fact5)));
        Rule rule4 = new Rule(new ArrayList<>(Arrays.asList(fact7, fact1)), new ArrayList<>(Collections.singletonList(fact8)));
        Rule rule5 = new Rule(new ArrayList<>(Arrays.asList(fact8, fact10)), new ArrayList<>(Collections.singletonList(fact9)));


        // Adding the rules to the rule base
        ArrayList<Rule> ruleBase = new ArrayList<>();
        ruleBase.add(rule1);
        ruleBase.add(rule2);
        ruleBase.add(rule3);
        ruleBase.add(rule4);
        ruleBase.add(rule5);

        // Running the chainageAV algorithm
        ChainageAV.chainageAV(factBase, ruleBase, goal);
        RuleBase ruleBase2 = new RuleBase("recipes.txt");
        ArrayList<Rule> rules = ruleBase2.getRules();


    }
}
