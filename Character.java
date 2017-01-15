import java.util.ArrayList;

public class Character{

    int health; //If no food: health decreases
    //int lastOccupiedInvSlot = 0;
    String name;

    public Character(int setHealth) {
	health = setHealth;
    }
    public String getName() {
	return name;
    }
    /*   public String[] getInventory(){
	return inventory;
    }
    
    ***You can just do S.o.p(inventory) because inventory is an ArrayList
    and ArrayLists have a built in toString ***
    public void printInventory(){
	String retstr = "[";
	for (String i : inventory){
	    retstr += i;
	    retstr += ",";
	}
	retstr += "]";
	System.out.println(retstr);
	}*/

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
	    retStr += "\nPlayer 1: " + name
		+ "\nHealth: " + health;
	}
	else {
	    retStr += "yo boy, " + name + " is dead";
	}
	return retStr;
    }
}
