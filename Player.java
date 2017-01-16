import java.util.ArrayList;
public class Player extends Character{
    double money;
    int pace;//1-slow , 2-average , 3-fast
    int ration;
    
    public Player(String InputName , double setMoney) {
	super(100);
	pace = 2;
	ration = 5;
	money = setMoney;
	name = InputName;
	//ArrayList <String> inventory = new ArrayList <String>();
	//inventory.add(initalInv);
	System.out.println("\n" + "Here are your initial stats: " + about());
	
    }

    public double getMoney() {
	return money;
    }

    public int changeRation(int newRation) {
	ration = newRation;
	return ration;
    }

    public int changePace(int newPace) {
	pace = newPace; //newPace is the set of positive integers between 1 and 3 inclusively; error handling will be done later
	return pace;
    }

    public String about() {
	String retStr = "";
	if (isAlive()) {
	    retStr += "\nPlayer 1: " + name
		+ "\nHealth: " + health
		+ "\nMoney: " + money
	        + "\nRation Size: " + ration
		+ "\nPace: " + pace;
	}
	else {
	    retStr += "yo boy, " + name + " is dead";
	}
	return retStr;
    }

    
}
	
