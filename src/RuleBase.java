import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class RuleBase {

    private ArrayList<Rule> rules;

    public RuleBase(String fileName) {
        rules = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                String[] premisesStr = parts[0].split(",");
                String[] resultStr = parts[1].split(",");
                ArrayList<Fact> premises = new ArrayList<>();
                ArrayList<Fact> result = new ArrayList<>();
                for (String premiseStr : premisesStr) {
                    String[] factStr = premiseStr.split("/");
                    Fact fact = new Fact(factStr[0], Integer.parseInt(factStr[1]), Boolean.parseBoolean(factStr[2]));
                    premises.add(fact);
                }
                for (String resultStr2 : resultStr) {
                    String[] factStr = resultStr2.split("/");
                    Fact fact = new Fact(factStr[0], Integer.parseInt(factStr[1]), Boolean.parseBoolean(factStr[2]));
                    result.add(fact);
                }
                Rule rule = new Rule(premises, result);
                rules.add(rule);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }
}
