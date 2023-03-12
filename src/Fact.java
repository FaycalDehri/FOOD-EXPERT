

// This class represents knowledges i.e BaseDefaits will be a an array of facts
public class Fact {

    private String name;
    private int quantity;

    public Fact(String name,int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getquantity(){
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }
}
