import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.*;

public class BuilderGUI extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage){
        ArrayList<Character> myChars = new ArrayList<>();
        BorderPane borderPane = new BorderPane();
        GridPane[] grid = new GridPane[2];
        Button[] switchGrids = new Button[2];
        HBox[] hBoxes = new HBox[3];
        VBox[] vBoxes  = new VBox[2];

        //setting the scene
        borderPane.setTop(hBoxes[0]);
        borderPane.setCenter(grid[0]);
        borderPane.setRight(ReferenceFiles.displayTypes());

        //save characters to a new file
        Button saveToFile = new Button("Save characters");
        saveToFile.setOnAction(actionEvent -> {
            ReferenceFiles.saveCharsToFile(Character.buildMap(myChars));
        });
        hBoxes[0].getChildren().addAll(saveToFile);
        hBoxes[1].getChildren().addAll(saveToFile);

        //for building characters, setting grid/hboxes[0]
        switchGrids[0] = new Button("View characters");
        Button buildCharMan = new Button("Build new character");
        Button buildCharRand = new Button("Add random character");
        hBoxes[0].getChildren().addAll(buildCharMan,buildCharRand,switchGrids[0]);
        switchGrids[0].setOnAction(actionEvent -> {
            borderPane.getChildren().clear();
            borderPane.setTop(hBoxes[1]);
            borderPane.setCenter(grid[1]);
        });
        buildCharMan.setOnAction(actionEvent ->{
            grid[0].getChildren().clear();
            grid[0] = ReferenceFiles.buildChar(false,myChars);
        });
        buildCharRand.setOnAction(actionEvent ->{
            grid[0].getChildren().clear();
            grid[0] = ReferenceFiles.buildChar(true,myChars);
        });

        //for viewing characters and the group's overall power set,
        //setting grid/hboxes[1]
        switchGrids[1] = new Button("Add more characters");
        Button seeAChar = new Button("View 1 Character");
        Button seeAllChars = new Button("View all Characters");
        Button displayCharsPowerSet = new Button("See your team's overall power set");
        hBoxes[1].getChildren().addAll(seeAChar,seeAllChars,
                displayCharsPowerSet,switchGrids[1]);
        switchGrids[1].setOnAction(actionEvent -> {
            borderPane.getChildren().clear();
            borderPane.setTop(hBoxes[0]);
            borderPane.setCenter(grid[0]);
        });
        seeAChar.setOnAction(actionEvent -> {
            for(int i=0; i< myChars.size(); i++) {
                hBoxes[2] = ReferenceFiles.displayChar(myChars);
                grid[1].add(hBoxes[2],1,0,2,1);
            }
        });
        seeAllChars.setOnAction(actionEvent -> {
            for(int i=0; i< myChars.size(); i++) {
                vBoxes[0] = ReferenceFiles.displayAllChars(myChars);
                grid[1].add(vBoxes[0],3,0,1,2);
            }
        });
        Hashtable<Power,Integer> table = Power.buildHashtable(myChars);
        displayCharsPowerSet.setOnAction(actionEvent -> {
            vBoxes[1] = ReferenceFiles.displayPowerSet(table);
            grid[1].add(vBoxes[1],1,1,1,2);
        });

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
    }
}
