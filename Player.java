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
    public String about() {
	String retStr = "";
	if (alive) {
	    retStr += "\nPlayer: " + name
		+ "\nHealth: " + health
		+ "\nMoney: " + money
		+ "\nPace: " + Math.floor(pace * 100)/100;
	}
	else {
	    retStr += name + " is dead";
	}
	return retStr;
    }

    
}
	
