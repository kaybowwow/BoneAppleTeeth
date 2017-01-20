import java.util.ArrayList;
public class Player extends Character{
    double money;
    double pace;
    
    public Player(String InputName , double setMoney) {
	super(100);
	pace = 1;
	money = setMoney;
	name = InputName;
	//ArrayList <String> inventory = new ArrayList <String>();
	//inventory.add(initalInv);
	System.out.println("\n" + "Here are your initial stats: " + about());
	
    }

    public double getMoney() {
	return money;
    }

    public String about() {
	String retStr = "";
	if (isAlive()) {
	    retStr += "\nPlayer 1: " + name
		+ "\nHealth: " + health
		+ "\nMoney: " + money
		+ "\nPace: " + pace;
	}
	else {
	    retStr += "yo boy, " + name + " is dead";
	}
	return retStr;
    }

    
}
	
