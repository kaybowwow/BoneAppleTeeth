import java.util.ArrayList;

public class Character{

    protected int health; 
	int maxhealth = 100;
	boolean alive;
	boolean buried;
    //int lastOccupiedInvSlot = 0;
    String name;

    boolean hasDisease = false;
    boolean onWagon = true;
    String disease; 

    public Character() {
	health = maxhealth;
	alive = true;
	buried = false;
    }
    public String getName() {
	return name;
    }
    public int getHealth() {
	return health;
    }
	public int setHealth(int a){
		int woah = health;
		health = a;
		if (health > maxhealth){health = maxhealth;}
		if (health < 0){alive = false;}
		return woah;
	}
	public int addHealth(int a){
		int woah = health;
		health += a;
		if (health>maxhealth){health = maxhealth;}
		if (health < 0){alive = false;}
		return woah; //this method is for convenience
	}
	
    /*    public void addToInv(String item){
	inventory[lastOccupiedInvSlot] = item;
	lastOccupiedInvSlot++;
	} */
    public String about() {
	String retStr = "";
	if (alive) {
	    retStr += name + "'s Stats: " 
		+ "\nHealth: " + health;
	}
	else {
	    retStr += "yo boy, " + name + " is dead";
	}
	return retStr;
    }
}
