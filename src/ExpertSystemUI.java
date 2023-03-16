import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ExpertSystemUI extends Application {

    private ArrayList<Fact> factBase = new ArrayList<>();


    public static void main(String[] args) {

        launch(args);
    }

    private void showRecipeWindow(String recipeText) {
        Stage stage = new Stage();
        stage.setTitle("Recipe");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        Label recipeLabel = new Label(recipeText);
        recipeLabel.setStyle("-fx-font-size: 20px");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(recipeLabel, closeButton);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private ArrayList<String> getRecipeNames(ArrayList<Fact> possibleRecipes){
        ArrayList<String> recipeNames = new ArrayList<>();
        for (Fact fact : possibleRecipes) {
            recipeNames.add(fact.getName());
        }
        return recipeNames;
    }

    private void showRecipeOptionsWindow(ArrayList<Fact> possibleRecipes) {
        Stage recipeOptionsStage = new Stage();
        recipeOptionsStage.setTitle("Recipe Options");

        VBox recipeOptionsLayout = new VBox(10);
        recipeOptionsLayout.setPadding(new Insets(10));
        recipeOptionsLayout.setAlignment(Pos.CENTER);

        Label recipeOptionsLabel = new Label("Choose a recipe to view:");

        ListView<String> recipeOptionsListView = new ListView<>();
        recipeOptionsListView.getItems().addAll(getRecipeNames(possibleRecipes));

        Button backButton = new Button("Back");
        Button nextButton = new Button("View");

        HBox buttonLayout = new HBox(10);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(backButton, nextButton);

        recipeOptionsLayout.getChildren().addAll(recipeOptionsLabel, recipeOptionsListView, buttonLayout);

       // backButton.setOnAction(e -> recipeOptionsStage.close());
        nextButton.setOnAction(e -> {
            String selectedRecipe = recipeOptionsListView.getSelectionModel().getSelectedItem();
            if (selectedRecipe != null) {
                showRecipeWindow("this is the recipe for"+selectedRecipe);
                recipeOptionsStage.close();
            }
        });

        Scene recipeOptionsScene = new Scene(recipeOptionsLayout);
        recipeOptionsStage.setScene(recipeOptionsScene);
        recipeOptionsStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
//        Fact fact1 = new Fact("Huile", 5,false);
//        Fact fact2 = new Fact("eggs", 2,false);
//        Fact fact3 = new Fact("cheese", 3,false);
//        Fact fact4 = new Fact("thon", 10,false);
//        Fact fact5 = new Fact("mimosa", 1,true);
//        Fact fact6= new Fact("mayonnaise", 1,false);
//        Fact fact7= new Fact("potato", 1,false);
//        Fact fact8= new Fact("frites", 1,true);
//        Fact fact9= new Fact("frites omelette", 1,true);
//        Fact fact10= new Fact("omelettes", 1,true);
//        Fact fact11= new Fact("beurre", 1,false);
//
//
//        // Creating the rules
//        Rule rule1 = new Rule(new ArrayList<>(Arrays.asList(fact1, fact2)), new ArrayList<>(Collections.singletonList(fact6)));
//        Rule rule2 = new Rule(new ArrayList<>(Arrays.asList(fact3)), new ArrayList<>(Collections.singletonList(fact5)));
//        Rule rule3 = new Rule(new ArrayList<>(Arrays.asList(fact6, fact4)), new ArrayList<>(Collections.singletonList(fact5)));
//        Rule rule4 = new Rule(new ArrayList<>(Arrays.asList(fact7, fact1)), new ArrayList<>(Collections.singletonList(fact8)));
//        Rule rule5 = new Rule(new ArrayList<>(Arrays.asList(fact8, fact10)), new ArrayList<>(Collections.singletonList(fact9)));
//        Rule rule6 = new Rule(new ArrayList<>(Arrays.asList(fact2, fact11)), new ArrayList<>(Collections.singletonList(fact10)));
//
//        // Adding the rules to the rule base
//        ArrayList<Rule> ruleBase = new ArrayList<>();
//        ruleBase.add(rule1);
//        ruleBase.add(rule2);
//        ruleBase.add(rule3);
//        ruleBase.add(rule4);
//        ruleBase.add(rule5);
//        ruleBase.add(rule6);

        RuleBase ruleBase = new RuleBase("C:\\Users\\Fayçal\\Desktop\\SII\\S2\\TECH_AGENTS\\TP\\food_expert\\src\\rule_base.txt");
        ArrayList<Rule> rules = ruleBase.getRules();

        primaryStage.setTitle("FOOD EXPERT!");

        // Create form components
        Label welcomeLabel = new Label("Welcome to Food expert! \n Tell me what's inside that fridge");
        Label nameLabel = new Label("Ingredients:");
        TextField nameTextField = new TextField();
        Label quantityLabel = new Label("Quantity:");
        Label contentsLabel = new Label("Contents");
        TextField quantityTextField = new TextField();
        TextField goalTextField = new TextField();
        Label goalLabel = new Label("What do you want to make ?");
        Button addButton = new Button("Add");
        Button searchButton = new Button("Search");
        Button submitButton = new Button("Submit");
        ListView<String> factListView = new ListView<>();

        // Add event listeners
        addButton.setOnAction(e -> {
            String name = nameTextField.getText();
            int quantity = Integer.parseInt(quantityTextField.getText());
            Fact fact = new Fact(name, quantity);
            factBase.add(fact);
            nameTextField.clear();
            quantityTextField.clear();
            factListView.getItems().add(fact.getName() + " - " + fact.getQuantity());
        });


        submitButton.setOnAction(e -> {
            String goal = goalTextField.getText();
            Fact goalFact = new Fact(goal,0);
            //We store all the possible recipes in the arrayList possible recipes
            ArrayList<Fact> possibleRecipes = ForwardChaining.forwardChaining(rules, factBase, goalFact);
            boolean canMakeRecipe=false;
           //we loop through them if we find the recipe we return true
            for(Fact recipe : ForwardChaining.forwardChaining(rules, factBase, goalFact)){
                if(recipe.getName().equalsIgnoreCase(goal)){
                    canMakeRecipe=true;
                    break;
                }
            }

             // Call the function to check if the recipe can be made
            if (canMakeRecipe) {
                // Show dialog box with "Yes" and "No" buttons
                ButtonType yesButton = new ButtonType("Yes, take me to the recipe", ButtonBar.ButtonData.OK_DONE);
                ButtonType noButton = new ButtonType("Ok, I don't need to see the recipe", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yes you can make the recipe you imbecil", yesButton, noButton);
                alert.setHeaderText(null);
                alert.showAndWait().ifPresent(response -> {
                    if (response == yesButton) {
                        // TODO: Open recipe page
                        // If you want to pass a text to the recipe page, you can do it like this:
                        String recipeText = "This is the recipe for " + goal;
                        showRecipeWindow(recipeText);
                    }
                });
            } else {
                // Show dialog box with "See what else you can make" and "Ok, I will remain hungry" buttons
                ButtonType seeButton = new ButtonType("See what else you can make", ButtonBar.ButtonData.OK_DONE);
                ButtonType remainButton = new ButtonType("Ok, I will remain hungry", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.ERROR, "You can't make the recipe :(", seeButton, remainButton);
                alert.setHeaderText(null);
                alert.showAndWait().ifPresent(response -> {
                    if (response == seeButton) {
                        // TODO: Show recipe options page
                        // If you want to pass the recipe options to the page, you can do it like this:
                        showRecipeOptionsWindow(possibleRecipes);
                    }
                });
            }
        });


        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.add(welcomeLabel, 0, 0, 3, 1);
        layout.addRow(1, nameLabel, nameTextField);
        layout.addRow(2, quantityLabel, quantityTextField);
        layout.add(addButton, 0, 3);
        GridPane.setColumnSpan(addButton, 2);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        layout.getColumnConstraints().addAll(columnConstraints, columnConstraints);
        GridPane.setHalignment(addButton, HPos.CENTER); // Center the addButton within its cell
// Set a margin of 20 pixels around the addButton
        GridPane.setMargin(addButton, new Insets(0, 0, 0, 100));
        layout.addRow(5,goalLabel,goalTextField);
        layout.addRow(4, contentsLabel, factListView);
        layout.add(submitButton, 0, 6);
        GridPane.setColumnSpan(submitButton, 2);
        layout.getColumnConstraints().addAll(columnConstraints, columnConstraints);
        GridPane.setHalignment(submitButton, HPos.CENTER); // Center the submitButton within its cell
// Set a margin of 20 pixels around the submitButton
        GridPane.setMargin(submitButton, new Insets(0, 0, 0, 100));




        welcomeLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #8BC34A; -fx-padding: 20px;");
        nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #757575;");
        quantityLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #757575;");
        contentsLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #757575;");
        goalLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #757575;");
        addButton.setStyle("-fx-background-color: #8BC34A; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 15px;");
        submitButton.setStyle("-fx-background-color: #8BC34A; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius:  15px;");
        factListView.setStyle("-fx-background-color: #F5F5DC; -fx-font-size: 16px; -fx-padding: 10px; -fx-border-color: #8BC34A; -fx-border-width: 2px;");



        // Set scene and show stage
        Scene scene = new Scene(layout, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
