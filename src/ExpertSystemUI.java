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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        Scene scene = new Scene(root, 800, 900); // Set the width and height of the Scene to 600 and 400, respectively
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

                File file = new File("C:\\Users\\acer\\Desktop\\TP\\Technologie des Agents\\FOOD-EXPERT\\resources\\recepies\\"+ selectedRecipe + ".txt");
                StringBuilder sb = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                } catch (IOException e1) {
                    // Handle the exception here, e.g. show an error message
                    e1.printStackTrace();
                    return; // or throw a custom exception, or do something else
                }
                String recipeText = sb.toString();
                showRecipeWindow(selectedRecipe.toUpperCase() +"\n \n"+ recipeText);
                recipeOptionsStage.close();
            }
        });

        Scene recipeOptionsScene = new Scene(recipeOptionsLayout);
        recipeOptionsStage.setScene(recipeOptionsScene);
        recipeOptionsStage.show();
    }

    @Override
    public void start(Stage primaryStage) {


        RuleBase ruleBase = new RuleBase("C:\\Users\\acer\\Desktop\\TP\\Technologie des Agents\\FOOD-EXPERT\\resources\\recepies\\rule_base.txt");
        ArrayList<Rule> rules = ruleBase.getRules();

        primaryStage.setTitle("FOOD EXPERT!");

        // Create form components
        Label welcomeLabel = new Label("Welcome to Food expert! \n Tell me what's inside your fridge");
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yes you can make the recipe", yesButton, noButton);
                alert.setHeaderText(null);
                alert.showAndWait().ifPresent(response -> {
                    if (response == yesButton) {
                        // TODO: Open recipe page
                        // If you want to pass a text to the recipe page, you can do it like this:

//                        String filePath = "path/to/your/file.txt";
//
//                        try {
//                            recipeText = new String(Files.readAllBytes(Paths.get(filePath)));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                         // Replace with the actual file name
                        File file = new File("C:\\Users\\acer\\Desktop\\TP\\Technologie des Agents\\FOOD-EXPERT\\resources\\recepies\\"+ goal + ".txt");
                        StringBuilder sb = new StringBuilder();
                        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                sb.append(line).append("\n");
                            }
                        } catch (IOException e1) {
                            // Handle the exception here, e.g. show an error message
                            e1.printStackTrace();
                            return; // or throw a custom exception, or do something else
                        }
                        String recipeText = sb.toString();

                        //String recipeText = "This is the recipe for " + goal;
                        showRecipeWindow(goal.toUpperCase()+ "\n \n" + recipeText);
                    }
                });
            } else {
                // Show dialog box with "See what else you can make" and "Ok, I will remain hungry" buttons
                ButtonType seeButton = new ButtonType("See what else you can make", ButtonBar.ButtonData.OK_DONE);
                ButtonType remainButton = new ButtonType("Exit page", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.ERROR, "This recipe cannot be made with the available ingredients", seeButton, remainButton);
                alert.setHeaderText(null);
                alert.showAndWait().ifPresent(response -> {
                    if (response == seeButton) {
                        // TODO: Show recipe options page
                        // If you want to pass the recipe options to the page, you can do it like this:
//                        possibleRecipes.get(0).getName()
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
