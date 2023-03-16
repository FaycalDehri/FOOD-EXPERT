import java.util.Comparator;

public class RulePremisesComparator implements Comparator<Rule> {
    @Override
    public int compare(Rule rule1, Rule rule2) {
        int size1 = rule1.getPremises().size();
        int size2 = rule2.getPremises().size();
        return Integer.compare(size2, size1);
    }
}

