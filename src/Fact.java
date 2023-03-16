

// This class represents knowledges i.e BaseDefaits will be a an array of facts
public class Fact {

    private String name;
    private int quantity;
    private boolean recipe; //we use this to check if we reached a leaf

    public Fact(String name, int quantity){
            this.name = name;
            this.quantity = quantity;
            this.recipe = false;
    }
    public Fact(String name,int quantity,boolean recipe){
        this.name = name;
        this.quantity = quantity;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public boolean getRecipe() {
        return recipe;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRecipe(boolean recipe) {
        this.recipe = recipe;
    }
}
