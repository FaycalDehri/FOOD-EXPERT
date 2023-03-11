

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.List;



public class Main {
    public static List<Rule> extractRulesFromXml() {
        List<Rule> rules = new ArrayList<Rule>();

        try {
            // Load the XML file
            File xmlFile = new File("C:\\Users\\acer\\Desktop\\TP\\Technologie des Agents\\JsonExtractor\\src\\main\\resources\\rules.xml");

            // Create a DocumentBuilderFactory and DocumentBuilder to parse the file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Normalize the document to ensure proper parsing
            doc.getDocumentElement().normalize();

            // Get all "regle" elements in the document
            NodeList regleList = doc.getElementsByTagName("regle");

            // Loop through each "regle" element and extract its data
            for (int i = 0; i < regleList.getLength(); i++) {
                Element regleElement = (Element) regleList.item(i);

                // Extract the name, inputs, and output attributes from the "regle" element
                String name = regleElement.getElementsByTagName("nom").item(0).getTextContent();
                NodeList premissesList = regleElement.getElementsByTagName("premisse");
                String[] inputs = new String[premissesList.getLength()];
                for (int j = 0; j < premissesList.getLength(); j++) {
                    inputs[j] = premissesList.item(j).getTextContent();
                }
                String output = regleElement.getElementsByTagName("conclusion").item(0).getTextContent();

                // Create a new Rule object and add it to the rules list
                Rule rule = new Rule(name, inputs, output);
                rules.add(rule);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rules;
    }

    public static void main(String[] args) {
        ArrayList<Rule> rules = (ArrayList<Rule>) extractRulesFromXml();
        for (Rule rule : rules) {
            String inputs = String.join(", ", rule.getInputs());
            System.out.println(rule.getName() + ": " + inputs + " -> " + rule.getOutput());
        }
    }
}
