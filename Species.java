import java.util.Arrays;

public class Species {
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

    @Override
    public String toString() {
        return "Species Name='" + speciesName + '.';
    }

    //name checkers
    protected int getNameNum(){
        String rN = getSpeciesName();
        int num = 0;
        for(int g=0; g<nameOptions.length; g++){
            if(rN == nameOptions[g]) {
                num = g  +1;
                break;
            }
        }
        return num;
    }
    private String[] nameOptions = {"human", "dwarf", "mermaids",
            "satyrs", "dragonborn", "fae",
            "light elf", "dark elf", "wizard", "demons"};
    private String checkName(String name){
        String checkedName = "";
        int g = nameOptions.length;

        for(int i=0; i<g; i++){
            if(name == nameOptions[i]){
                checkedName = name;
                break;
            }else if(name != nameOptions[g]){
                checkedName = nameOptions[0];
            }
        }
        return checkedName;
    }
}
