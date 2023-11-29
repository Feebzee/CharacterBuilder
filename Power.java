import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Power {
    private String type;
    public static ArrayList<String> powerTypes = ReferenceFiles.outputFile(new File("PowerTypes.txt"));
    private int level;
    public Power(){
        type = "";
        level = 0;
    }
    public Power(String type){
        this.type = type;
        level = 0;
    }
    public Power(String type,int level){
        this.type = type;
        if(level > 9){
            level = 9;
        }
        this.level = level;
    }

    public String getType() {
        return type;
    }
    public void setLevel(int level) {
        if(level > 9){
            level = 9;
        }
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
    @Override
    public String toString() {
        return "Power{" + "type='" + type + ", level=" + level + '}';
    }

    //average of the power range of each power option of a species
    public static int getAvgSpeciesPower(Species s){
        int[] toReturn = new int[2];
        for(Power p : s.getPowers()){
            int tN = Power.getTn(p);
            int level = p.getLevel();
            int sN = s.getNameNum();
            int[] powerRange = {(tN*50)+(level*25)+(sN*10), ((tN*100)+(level*50)+(sN*20))};
            toReturn[0] += powerRange[0];
            toReturn[1] += powerRange[1];
        }
        toReturn[0] = toReturn[0]/s.getPowers().size();
        toReturn[1] = toReturn[1]/s.getPowers().size();
        return (toReturn[0] + toReturn[1])/2;
    }
    //power range available for a character
    public static int[] getCharRange(Character c) {
        Species toCheck = c.getSpecies();
        Power p = c.getPower();
        int sN = toCheck.getNameNum();
        int tN = Power.getTn(p);
        int level = p.getLevel();

        int[] powerRange = {(tN*50)+(level*25)+(sN*10), ((tN*100)+(level*50)+(sN*20))};
        return powerRange;
    }
    private static int getTn(Power type){
        int tN = 0;
        for(int i=0; i<powerTypes.size(); i++){
            if(type.getType().compareTo(powerTypes.get(i)) == 0){
                if(powerTypes.get(i).toLowerCase() == "no power"){
                    tN = 1;
                    break;
                }
                tN = i + 1;
                break;
            }
        }
        return tN;
    }

    //for part3
    public static Hashtable<Power,Integer> buildHashtable(List<Character> list){
        Hashtable<Power,Integer> toReturn = new Hashtable<>();
        for(Character c : list){
            Power p = c.getPower();
            if(!toReturn.containsKey(p)){
                toReturn.put(p,1);
            }else{
                int num = toReturn.get(p);
                toReturn.put(p,num);
            }
        }
        return toReturn;
    }
}
