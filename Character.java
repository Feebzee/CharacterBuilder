import java.util.*;

public class Character implements Comparable<Character>{
    private boolean isMain;
    String name;
    int age;
    Species species;
    Power power;
    public Character(){
        name = "";
        age = 0;
        species = new Species();
        power = new Power();
    }
    public Character(String name, int age, Species species, Power power){
        this.name = name;
        this.age = age;
        this.species = species;

        if(!species.getPowers().contains(power)){
            this.power = species.getPowers().get(0);
        }else {
            this.power = power;
        }
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setSpecies(Species species) {
        this.species = species;
    }
    public Species getSpecies() {
        return species;
    }
    public void setPower(Power power) {
        this.power = power;
    }
    public Power getPower() {
        return power;
    }

    public void setPowerLevel(int level) {
        this.power.setLevel(level);
    }
    public void setIsMain(boolean isMain){
        this.isMain = isMain;
    }
    public boolean getIsMain(){
        return isMain;
    }
    @Override
    public String toString() {
        String yOrN = "a main character";
        if(!isMain) {
           yOrN = "not " + yOrN;
        }
        yOrN = ", is" + yOrN;
        return "Character{" +
                "name='" + name + yOrN + ", age=" + age +
                ", species=" + species + ", power=" + power + '}';
    }
    @Override
    public int compareTo(Character o) {
        //compares whether or not 2 characters are MCs or not
        return Boolean.compare(o.isMain,this.isMain);
    }

    //for part 2
    public static Character buildRandom(){
        Random rand = new Random();
        String[] randNames = {"Shelby Dollwhip","Sally Jaxon",
                "Frank Sanbernice", "Ferdinanand Van Boris",
                "Blurg McBlurg","Drog Draft","Barthoriel"};

        int r = randNames.length;
        int l = Species.nameOptions.length;
        int t = Power.powerTypes.size();
        Species s = new Species(Species.nameOptions[rand.nextInt(l)][rand.nextInt(l)]);
        Power p = new Power(Power.powerTypes.get(rand.nextInt(t)),
                rand.nextInt(9));

        return new Character(randNames[rand.nextInt(r)],
                rand.nextInt(75),s,p);
    }
    public static Map<Boolean,Character> buildMap(List<Character> chars){
        Map<Boolean,Character> toReturn = new HashMap<>();
        for(Character c : chars){
            toReturn.put(c.isMain,c);
        }
        return toReturn;
    }
    //for part 3
    public static PriorityQueue<Character> buildPQ(List<Character> chars){
        Comparator<Character> comp = Character::compareTo;
        PriorityQueue<Character> c = new PriorityQueue<>(chars.size(),comp);

        for (Character character : chars){
            c.add(character);
        }
        return c;
    }
}
