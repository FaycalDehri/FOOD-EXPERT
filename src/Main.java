import java.util.*;

public class Main {


    public static void main(String[] args) {
        //Map<String, Boolean> factBase = new HashMap<>();
//        factBase.put("D", true);
//        factBase.put("O", true);
//        factBase.put("G", true);

        ArrayList<String> factBase = new ArrayList<>();
        factBase.add("D");
        factBase.add("O");
        factBase.add("G");

        ArrayList<Rule> rules = new ArrayList<>();

        rules.add(new Rule(new ArrayList<>(Arrays.asList("A","B")),new ArrayList<>(Arrays.asList("F"))));
        rules.add(new Rule(new ArrayList<>(Arrays.asList("F","H")),new ArrayList<>(Arrays.asList("I"))));
        rules.add(new Rule(new ArrayList<>(Arrays.asList("D","H","G")),new ArrayList<>(Arrays.asList("A"))));
        rules.add(new Rule(new ArrayList<>(Arrays.asList("O","G")),new ArrayList<>(Arrays.asList("H"))));
        rules.add(new Rule(new ArrayList<>(Arrays.asList("E","H")),new ArrayList<>(Arrays.asList("B"))));
        rules.add(new Rule(new ArrayList<>(Arrays.asList("G","A")),new ArrayList<>(Arrays.asList("B"))));
        rules.add(new Rule(new ArrayList<>(Arrays.asList("G","H")),new ArrayList<>(Arrays.asList("P"))));
        rules.add(new Rule(new ArrayList<>(Arrays.asList("G","H")),new ArrayList<>(Arrays.asList("Q"))));
        rules.add(new Rule(new ArrayList<>(Arrays.asList("D","O","G")),new ArrayList<>(Arrays.asList("J"))));



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

        ChainageAV.chainageAV(factBase,rules,"I");

    }
}
