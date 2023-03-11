

// This class represents knowledges i.e BaseDefaits will be a an array of facts
public class Fact {

    private String name;
    private boolean value;

    public Fact(String name,boolean value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public boolean getValue(){
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
