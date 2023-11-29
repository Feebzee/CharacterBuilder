import java.util.*;

public class Species implements Comparable<Species>{
    //attributes
    private String speciesName;
    public Species(){
        speciesName = "";
    }
    public Species(String speciesName){
        this.speciesName = checkName(speciesName);
    }

    public String getSpeciesName() {
        return speciesName;
    }
    public  ArrayList<Power> getPowers(){
        int nN = getNameNum();
        String pT[] = (String[]) Power.powerTypes.toArray();
        if(nN < 4){
            ArrayList<Power> l = new ArrayList<>();
            l.add(new Power(pT[0]));
            l.add(new Power(pT[1]));
            l.add(new Power(pT[2]));
            l.add(new Power(pT[3]));
            return l;
        }else if(nN < 7){
            ArrayList<Power> l = new ArrayList<>();
            l.add(new Power(pT[4]));
            l.add(new Power(pT[5]));
            l.add(new Power(pT[6]));
            l.add(new Power(pT[7]));
            return l;
        }else{
            ArrayList<Power> l = new ArrayList<>();
            l.add(new Power(pT[8]));
            l.add(new Power(pT[9]));
            l.add(new Power(pT[10]));
            l.add(new Power(pT[11]));
            return l;
        }
    }

    @Override
    public String toString() {
        return "Species{" + "speciesName='" + speciesName + '}';
    }
    @Override
    public int compareTo(Species species){
        return this.speciesName.compareTo(species.speciesName);
    }

    //for part3
    public static BinarySearchTree buildTree(List<String> specs){
        BinarySearchTree toReturn = new BinarySearchTree();
        for(String s : specs){
            Species a = new Species(s);
            toReturn.add(a);
        }
        return toReturn;
    }


    //name checkers
    public int getNameNum(){
        String rN = getSpeciesName();
        int num = 0;
        for(int g=0; g<nameOptions.length; g++){
            for(int k=0; k<nameOptions[g].length; k++){
                num++;
                if(rN == nameOptions[g][k]) {
                    break;
                }
            }
        }
        return num;
    }
    public static String[][] nameOptions = {{"human", "dwarf", "mermaids"},
            {"satyrs", "dragonborn", "fae"},
            {"light elf", "dark elf", "wizard"}};
    private String checkName(String name){
        List<String> names = new ArrayList<>();
        for(String[] n : nameOptions){
            names.addAll(Arrays.asList(n));
        }
        if(names.contains(name)){
            return name;
        }
        return names.get(0);
    }
}
