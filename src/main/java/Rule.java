public class Rule {
    private String name;
    private String[] inputs;
    private String output;

    public Rule(String name, String[] inputs, String output) {
        this.name = name;
        this.inputs = inputs;
        this.output = output;
    }

    public String getName() {
        return name;
    }

    public String[] getInputs() {
        return inputs;
    }

    public String getOutput() {
        return output;
    }
}
