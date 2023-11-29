import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReferenceFiles{
    public static <E> void inputFile(FileWriter writer, E input){
        try{
            writer.write(input.toString());
        }catch(IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    public static ArrayList<String> outputFile(File file){
        ArrayList<String> toRead = new ArrayList<>();
        try{
            Scanner reader = new Scanner(file);
            while(file.exists() && reader.hasNext()){
                toRead.add(reader.nextLine());
                System.out.println(reader.nextLine());
            }
            reader.close();
            sortArrayList(toRead);
        }catch(IOException e){
            e.printStackTrace();
        }
        return toRead;
    }
    private static ArrayList<String> sortArrayList(ArrayList<String> toSort){
        //uses selection sort
        int pos;
        String temp;
        for(int k=0; k<toSort.size(); k++){
            pos = k;
            for (int j = k+1; j < toSort.size(); j++) {
                char c1 = toSort.get(k).charAt(0);
                char c2 = toSort.get(k).charAt(0);
                if (c1 < c2){
                    pos = j;
                }
            }
            temp = toSort.get(pos);
            toSort.set(pos,toSort.get(k));
            toSort.set(k,temp);
        }
        return toSort;
    }
    protected static void saveCharsToFile(Map<Boolean,Character> chars){
        try {
            File f = new File("CharacterFile");
            FileWriter w = new FileWriter(f);
            for (boolean b : chars.keySet()) {
                String s = "";
                if (b) {
                    s += "Main, ";
                } else {
                    s += "Side, ";
                }
                Character c = chars.get(b);
                s += c.getName() + ", " + c.getSpecies() + ", " + c.getPower();
                inputFile(w,s);
            }
            w.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static GridPane buildChar(boolean isRandom,ArrayList<Character> myChars){
        GridPane gridPane = new GridPane();
        if(!isRandom){
            myChars.add(buildCharManual(gridPane));
        }else{
            myChars.add(buildCharRandom(gridPane));
        }
        return gridPane;
    }
    private static Character buildCharManual(GridPane grid){
        Character myChar = new Character();
        HBox hBox = new HBox();
        grid.setGridLinesVisible(true);

        TextField nameField = new TextField();
        String buildName = buildField(grid,0, "Name:", nameField);
        myChar.setName(buildName);

        TextField ageField = new TextField();
        String buildAge = buildField(grid,1,"Age:",ageField);
        myChar.setAge(Integer.parseInt(buildAge));

        TextField speciesField = new TextField();
        String buildSpecies = buildField(grid,2, "Name:", speciesField);
        myChar.setSpecies(new Species(buildSpecies));

        TextField powerNameField = new TextField();
        String buildPowerName = buildField(grid,3,"Power (type left, level right):",powerNameField);
        TextField powerLevelField = new TextField();
        grid.add(powerLevelField, 2,3);
        myChar.setPower(new Power(buildPowerName,
                Integer.parseInt(powerLevelField.getText())));

        TextField setMainField = new TextField();
        String buildSetMain = buildField(grid,4,"Is this an MC?:",setMainField);
        if(buildSetMain.equalsIgnoreCase("yes")){
            myChar.setIsMain(true);
        }
        hBox.getChildren().add(new Label(myChar.toString()));

        return myChar;
    }
    private static Character buildCharRandom(GridPane grid){
        Character myChar = Character.buildRandom();
        HBox hBox = new HBox();

        grid.add(new Label("Name: " + myChar.getName()),0,0);
        grid.add(new Label("Age: " + myChar.getAge()),0,1);
        grid.add(new Label("Species: " + myChar.getSpecies().getSpeciesName()),0,2);
        grid.add(new Label("Power: "),0,3);
        grid.add(new Label("Type- " + myChar.getPower().getType()),1,3);
        grid.add(new Label("Level- " + myChar.getPower().getLevel()),2,3);
        hBox.getChildren().add(new Label(myChar.toString()));

        return myChar;
    }
    private static String buildField(GridPane grid,int row,String label,TextField name){
        grid.add(new Label(label),0,row);
        grid.add(name,1,row);
        return name.getText();
    }

    protected static HBox displayChar(ArrayList<Character> myChars){
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        PriorityQueue<Character> chars = Character.buildPQ(myChars);
        ToggleGroup toggleGroup = new ToggleGroup();

        int size = myChars.size();
        RadioButton[] buttons = new RadioButton[size];
        for(int b=0; b<size; b++){
            Character toAdd = chars.remove();
            buttons[b] = new RadioButton(toAdd.getName());
            buttons[b].setUserData(toAdd.toString());
            buttons[b].setToggleGroup(toggleGroup);
            vBox.getChildren().add(buttons[b]);
        }
        hBox.getChildren().add(vBox);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue,
                                Toggle toggle, Toggle t1) {
                RadioButton rb = (RadioButton) toggleGroup.getSelectedToggle();
                if(rb != null){
                    hBox.getChildren().add(new Label((String) rb.getUserData()));
                }
            }
        });
        return hBox;
    }
    protected static VBox displayAllChars(ArrayList<Character> myChars){
        PriorityQueue<Character> chars = Character.buildPQ(myChars);
        VBox vBox = new VBox();
        int size = myChars.size();
        for(int k=0; k<size; k++){
            vBox.getChildren().add(new Label(chars.remove().toString()));
        }
        return vBox;
    }
    protected static VBox displayPowerSet(Hashtable<Power,Integer> map){
        VBox vBox = new VBox();
        ArrayList<String> s = new ArrayList<>();
        for(Power p : map.keySet()){
            s.add(map.get(p) + ", " + p.getType());
        }
        ArrayList<String> sorted = sortArrayList(s);
        for (String k: sorted) {
            vBox.getChildren().add(new Label((k)));
        }
        return vBox;
    }

    protected static VBox displayTypes(){
        ArrayList<String> powers = Power.powerTypes;
        ArrayList<String> species = new ArrayList<>();
        for(String[] s : Species.nameOptions){
            species.add(s[0]);
            species.add(s[1]);
            species.add(s[2]);
        }
        BinarySearchTree tree = Species.buildTree(species);

        VBox vBox = new VBox();
        vBox.getChildren().add(new Label("Powers:"));
        for(String p : powers){
            vBox.getChildren().add(new Label(p));
        }
        vBox.getChildren().add(new Label("Species:"));
        for(String s : tree){
            vBox.getChildren().add(new Label(s));
        }
        return vBox;
    }
}
