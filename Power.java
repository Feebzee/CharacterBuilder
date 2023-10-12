import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Power {
    private String type;
    private ArrayList<String> powerTypes = ReferenceFiles.outputFile(new File("PowerTypes.txt"));
    private int level;
    private String speciesName;
    public Power(){
        type = "";
        level = 0;
        speciesName = "";
    }
    public Power(String type, int level, String speciesName){
        this.type = type;
        this.level = level;
        this.speciesName = speciesName;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }
    public String getSpeciesName() {
        return speciesName;
    }

    public int[] getRange() {
        Species toCheck = new Species(getSpeciesName());
        int rN = toCheck.getNameNum();
        String type = getType();
        int tN = 0;

        for(int i=0; i<powerTypes.size(); i++){
            if(type.compareTo(powerTypes.get(i)) == 0){
                if(powerTypes.get(i).toLowerCase() == "no power"){
                    tN = 0;
                }
                tN = i + 1;
                break;
            }
        }

        int[] powerRange = {(tN*50)+(getLevel()*25)+(rN*10), ((tN*100)+(getLevel()*50)+(rN*20))};
        return powerRange;
    }

    public String toString() {
        return  "Power Type: " + getType() +
                ", Level: " + getLevel() +
                ", Range:  " + getRange() + ".";
    }

    public static void main(String[] args) {
        ArrayList<String> toPrint = ReferenceFiles.outputFile(new File("PowerTypes.txt"));
        System.out.println(toPrint.indexOf(5));
    }
}
