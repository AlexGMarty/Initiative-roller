public class Combatant implements Comparable<Combatant> {
    // Fields
    private String name;
    private String type;
    private int bonus;  // Initiative bonus
    private int roll = 0;  // Initiative roll

    // Constructor
    public Combatant(String name, String type, int bonus){
        this.name = name;
        this.type = type;
        this.bonus = bonus;
        this.roll();  // Sets an initial initiative roll
    }

    // roll method for rolling and storing a new initiative value
    public void roll(){
        int d20 = (int)(Math.random()*20 + 1);  // 1 to 20
        this.roll = d20 + this.bonus;
    }

    // toString() for printing the Combatant
    public String toString(){
        return String.valueOf(this.roll) + " - " + this.name + ", " + this.type;
    }

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getBonus() {
        return bonus;
    }
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getRoll() {
        return roll;
    }

    @Override
    public int compareTo(Combatant other) {
        if (this.getRoll() > other.getRoll())
            return 1;
        else if (this.getRoll() == other.getRoll()) {
            return this.getBonus() - other.getBonus();
        }
        return -1;
    }
}
