public class Character {
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
        this.power = power;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setSpecies(Species species) {
        this.species = species;
    }
    public void setPower(Power power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public Species getSpecies() {
        return species;
    }
    public Power getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Character name= " + name +
                "\nAge= " + age +
                "\nSpecies= " + species.toString() +
                "\nPower= " + power.toString() + '.';
    }
}
