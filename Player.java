import java.util.ArrayList;
public class Player extends Character{
    double money;
    int pace;//1-slow , 2-average , 3-fast
    int ration;

    public Player(int String InputName , double setMoney ) {
	super(100);
	pace = 2;
	ration = 5;
	money = setMoney;
	name = InputName;
	ArrayList <Comparable> inventory = new ArrayList <Comparable>();
	//temporary 
	
	System.out.println(inventory);
	
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

    
}
	
