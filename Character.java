import java.util.ArrayList;

public class Character{

    int health; //If no food: health decreases
	String[] inventory = new String[20];
	int lastOccupiedInvSlot = 0;
    String name;

    public Character(int setHealth) {
	health = setHealth;
    }
    public String getName() {
	return name;
    }
	public String[] getInventory(){
		return inventory;
	}
	public void printInventory(){
		String retstr = "[";
		for (String i : inventory){
			retstr += i;
			retstr += ",";
		}
		retstr += "]";
		System.out.println(retstr);
	}

    public int getHealth() {
	return health;
    }

    public boolean isAlive() {
	return (health > 0);
    } 
	
	public void addToInv(String item){
		inventory[lastOccupiedInvSlot] = item;
		lastOccupiedInvSlot++;
	}
}
