import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BuilderGUI extends Application {
    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void start(Stage stage){
        Character myChar = new Character();
        BorderPane borderPane = new BorderPane();
        GridPane grid = new GridPane(100,100);
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

        borderPane.setCenter(grid);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
    }
}
