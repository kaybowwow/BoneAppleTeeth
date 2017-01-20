import java.util.ArrayList;

public class Character{

    int health; //If no food: health decreases
    //int lastOccupiedInvSlot = 0;
    String name;

    boolean hasDisease = false;
    boolean onWagon = true;
    String disease; 

    public Character(int setHealth) {
	health = setHealth;
    }
    public String getName() {
	return name;
    }
    public int getHealth() {
	return health;
    }

    public boolean isAlive() {
	return (health > 0);
    } 
	
    /*    public void addToInv(String item){
	inventory[lastOccupiedInvSlot] = item;
	lastOccupiedInvSlot++;
	} */
    public String about() {
	String retStr = "";
	if (isAlive()) {
	    retStr += name + "'s Stats: " 
		+ "\nHealth: " + health;
	}
	else {
	    retStr += "yo boy, " + name + " is dead";
	}
	return retStr;
    }
}
