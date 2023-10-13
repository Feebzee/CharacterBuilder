import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BuilderGUI extends Application {
    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void start(Stage stage){
        Character myChar = new Character();
        ArrayList<Character> myChars = new ArrayList<>();
        BorderPane borderPane = new BorderPane();
        HBox buttons = new HBox();

        Button buildChar = new Button("New Character");
        buildChar.setOnAction(actionEvent ->{
            buildChar(borderPane, myChar, myChars);
        });
        buttons.getChildren().add(buildChar);

        Button seeChars = new Button("View Characters");
        seeChars.setOnAction(actionEvent -> {
            VBox view = new VBox();
            for(int i=0; i< myChars.size(); i++) {
                view.getChildren().add(new Label(myChars.get(i).getName()));
            }
            borderPane.setCenter(view);
        });
        buttons.getChildren().add(seeChars);
        borderPane.setTop(buttons);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
    }

    public static void buildChar(BorderPane borderPane, Character myChar, ArrayList<Character> myChars){
        GridPane grid = new GridPane(100,100);
        HBox displayChar = new HBox();

        grid.setGridLinesVisible(true);
        grid.add(new Label("Name: "), 0,0);
        TextField nameField = new TextField();
        grid.add(nameField, 1,0);
        myChar.setName(nameField.getText());

        TextField ageField = new TextField();
        grid.add(new Label("Age: "), 0,1);
        grid.add(ageField, 1,1);
        myChar.setAge(Integer.parseInt(ageField.getText()));

        TextField speciesField = new TextField();
        grid.add(new Label("Species: "), 0,2);
        grid.add(speciesField, 1,2);
        myChar.setSpecies(new Species(speciesField.getText()));

        grid.add(new Label("Power (type left, level right): "), 0,3);
        TextField powerNameField = new TextField();
        TextField powerLevelField = new TextField();
        grid.add(powerNameField, 1,3);
        grid.add(powerLevelField, 2,3);
        myChar.setPower(new Power(powerNameField.getText(),
                Integer.parseInt(powerLevelField.getText()),
                myChar.getSpecies().getSpeciesName()));
        displayChar.getChildren().add(new Label(myChar.toString()));

        borderPane.setCenter(grid);
        borderPane.setBottom(displayChar);
        myChars.add(myChar);

        Scene scene = new Scene(borderPane);
    }
}
