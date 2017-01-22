import java.util.ArrayList;
public class Player extends Character{
    double money = 0;
    double pace;
    
    public Player(String InputName , double setMoney) {
	super();
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
	if (alive) {
	    retStr += "\nPlayer 1: " + name
		+ "\nHealth: " + health
		+ "\nMoney: " + money
		+ "\nPace: " + Math.floor(pace * 100)/100;
	}
	else {
	    retStr += "yo boy, " + name + " is dead";
	}
	return retStr;
    }

    
}
	
