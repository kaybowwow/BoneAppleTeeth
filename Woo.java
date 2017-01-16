import cs1.Keyboard;
import java.util.ArrayList;

public class Woo{
    //====================V A R I A B L E S====================
    public final static int maxMiles = 100;
    
    private Player pat;
    private Member spouse;
    private Member child1;
    private Member child2;
    private Member child3;
    
    private ArrayList<String> inventory = new ArrayList<String>();

    private int daysTraveled;
    private static int milesTraveled;
    private int month;
    private boolean gameOver;

    private String event;
    private boolean randomEvent = false;

    private String weather = "warm"; 
    //^^^^^^^^^^^^^^^^^^^^V A R I A B L E S^^^^^^^^^^^^^^^^^^^^
    
    //==========i m p o r t a n t   t h i n g s==========
    public Woo() {
	daysTraveled = 0;
	gameOver = false;
	newGame();
    }
    
    public void newGame() {
	String s;
	String name = "";
	int selection; 

	s= "Welcome to the Oregon Trail! By what name do you go by?";
	System.out.println(s);

	try {
	    name = Keyboard.readString();
	}

        catch ( Exception e ) {
	    name = "Bob";
	    System.out.println("Unexpected input received. Default settings are used: your name is Bob."); 
	}
	
	s = "\n What is your occupation? \n";
	
	s += "\t1: Farmer\n";
	s += "\t2: Carpenter\n";
	s += "\t3: Banker\n";
	s += "\t4: Immigrant\n";

	s += "Selection: ";
	System.out.print (s);

	try {
	    selection = Keyboard.readInt();
	    if (selection == 1) {
		addItem("food");
		pat = new Farmer(name);
	    }

	    if (selection == 2) {
		addItem("wood");
		pat = new Carpenter(name);	
	    }

	    if (selection == 3) {
		addItem("clothing");
		pat = new Banker(name);		
	    }

	    if (selection == 4) {
		pat = new Immigrant(name);
	    }   
	}
	catch (Exception e) {
	    addItem("food");
	    pat = new Farmer(name);
	    System.out.println("Unexpected input received. Default settings are used: you are a farmer."); 
	}

	s = "\nWhat are your family members' names?\n";
	System.out.print (s);
	
	int membersLeft = 4;
	while (membersLeft > 0) {
	    if (membersLeft == 4) {
		s = "\n\tSpouse: ";
		System.out.print (s);
	    }
	    else {
		s = "\n\tChild " + (4 - membersLeft) + ":";
		System.out.print (s);
	    }
	    
	    try {
		name = Keyboard.readString();
		if (membersLeft == 4) {
		    spouse = new Member(name);}
		if (membersLeft == 3) {
		    child1 = new Member(name);}
		if (membersLeft == 2) {
		    child2 = new Member(name);}
		if (membersLeft == 1) {
		    child3 = new Member(name);}
	    }

	    catch ( Exception e ) {
		name = "Bobette" + (4 - membersLeft);
		System.out.println("Unexpected input received. Default settings are used: his/her name is Bobette."); 
	    }
	    membersLeft -= 1;
	}

	

	    System.out.println("\n Before you head on the road, would you like to buy anything?");
	    System.out.println("\n"+ "\t1: Yes" + "\t2: No");

	    try {
		selection = Keyboard.readInt();

		if (selection == 1) {
		    shop();
		}

		System.out.println(inventory);
		//System.out.println(pat.money); 
	    }
	    catch (Exception e) {}
	}

	public void shop() {
	    int minusMoney = 0;
	    int selection;
	    int selection2 = 0;
	    String item = "";
	    String continueShopping = "yes"; 
	
	    while ( continueShopping.equals("yes") && (pat.money > 0) ) {
		System.out.println("Welcome to the shop! Here is your current amount of available cash: " +
				   pat.money +
				   "\nWhat would you like to buy?");
		System.out.println("\n 1. oxen: $20 each" +
				   "\n 2. medicine (Cures all): $10 each" +
				   "\n 3. food : $7 each" +
				   "\n 4. heavy jacket: $15 each"
				   );
		System.out.println("\n Selection: ");

		//try {
		selection = Keyboard.readInt();
	    
		if (selection == 1) {
		    item = "oxen"; 
		    System.out.println("How many?");
		    selection2 = Keyboard.readInt();
		    minusMoney = 20 * selection2;
		    System.out.println("Estimated cost: " + minusMoney);
		    System.out.println("Remaining money: " + (pat.money-minusMoney)); 
		}

		if (selection == 2) {
		    item = "medicine"; 
		    System.out.println("How many?");
		    selection2 = Keyboard.readInt();
		    minusMoney = 10 * selection2;
		    System.out.println("Estimated cost: " + minusMoney);
		    System.out.println("Remaining money: " + (pat.money-minusMoney)); 
		}

		if (selection == 3) {
		    item = "food"; 
		    System.out.println("How much?");
		    selection2 = Keyboard.readInt();
		    minusMoney = 7 * selection2;
		    System.out.println("Estimated cost: " + minusMoney);
		    System.out.println("Remaining money: " + (pat.money-minusMoney)); 
		}
	    
		if (selection == 4) {
		    item = "clothing"; 
		    System.out.println("How many?");
		    selection2 = Keyboard.readInt();
		    minusMoney = 15 * selection2;
		    System.out.println("Estimated cost: " + minusMoney);
		    System.out.println("Remaining money: " + (pat.money-minusMoney)); 
		}

		if (minusMoney > pat.money){
		    System.out.println("You can't afford that.");
		}
		
		else{
		    //subtracting money
		    pat.money -= minusMoney;
		    //adding to inventory: 
		    for (int i = selection2; i > 0; i--) {
			addItem(item);
		    }
		}
		System.out.println("Continue shopping? Type 'yes' or 'no'");

		continueShopping = Keyboard.readString();

		if (continueShopping == "no") {
		    for (int x = 0; x < inventory.size(); x++) {
			inventory.add(inventory.get(x));
		    }
		}
	    }
	
	    System.out.println("Items in inventory: " + inventory);
	    System.out.println("Amount of money left: " + pat.money);
	}

	public boolean playTurn() {
	    int selection = 1;
	    System.out.println(pat.about());
	    System.out.println(child1.about()); 
	    System.out.println(child2.about());
	    System.out.println(child3.about());
	    System.out.println(spouse.about()); 

	    setWeather();
	    System.out.println("Current Weather: " + weather);

	    System.out.println("Days traveled: " + daysTraveled); 
	    

	    int probabilityOccuring = (int)(Math.random() * 100); 

	    if (probabilityOccuring < 50) {
		randomEvent();
		System.out.println(event);
		if (child1.hasDisease) {
		    child1.health -= 10;
		}
		if (child2.hasDisease){
		    child2.health -= 10;
		}
		if (child3.hasDisease) {
		    child3.health -=10;
		}
		if (spouse.hasDisease) {
		    spouse.health -= 10;
		}
		if (pat.hasDisease) {
		    pat.health -= 10;
		}
	    }
	    
	    System.out.println("\nWhat do you want to do?");
	    System.out.println("\t1: Continue\n" +
			       "\t2: Rest\n" +
			       "\t3: Check Location\n" +
			       "\t4: Check Inventory\n");
	    
	    selection = Keyboard.readInt();

	    if (selection == 1) {
		milesTraveled += pat.pace * 10;
		int rationCounter = pat.ration;
		while (rationCounter > 0) {
		    if (haveItem("food")) {
			System.out.println("yo eat");
			removeItem("food");
			rationCounter -= 1;
		    }
		    else {
			System.out.println("no de food!!!");
			if (pat.isAlive()) {
			    pat.health -= 10;
			}
			else {
			    return false;
			}
			rationCounter -= 1;
		    }
		}
	    }

	    if (selection == 2) {
		rest();
	    }

	    if (selection == 3) {
		if (milesTraveled <= 20){
		    System.out.println("You're near the start.");
		}
		else if (milesTraveled >= 80){
		    System.out.println("You're near the end.");
		}
		else{
		    System.out.println("You're in the middle of nowhere.");
		}
	    }

	    if (selection == 4) {
		System.out.println(inventory);
	    }

	    
	  
	    System.out.println("Miles Traveled: " + milesTraveled);
	    
	    daysTraveled += 1; 
	    

	    return true; 
    
	}

    public boolean rest(){
	daysTraveled += 1;   
	return true;
    }
    
    public String randomEvent() {
	int prob = (int) (Math.random() * 100);
	Character member; 
	if (prob < 75) {
	    member = child1;
	}

	if (prob < 50) {
	    member = child2;
	}

	if (prob < 25) {
	    member = child3;
	}

	if (prob < 15) {
	    member = pat;
	}

	else {
	    member = spouse;
	}
	
	int probabilityEvent = (int)(Math.random() * 100);
	
	if (probabilityEvent != 0) {
		
	    if (probabilityEvent < 55 && weather == "rainy") {
		event = "Thunderstorm";
	    }

	    else if (probabilityEvent < 45) {
		event = member.name + " has dysentary!";
		member.hasDisease = true;
		member.disease = "Dysentary"; 
	    }

	    else if (probabilityEvent < 40) {
		event = member.name + " has cholera!";
		member.hasDisease = true;
		member.disease = "Cholera"; 
	    }

	    else if (probabilityEvent < 37) {
		event = "Wagon wheel broke";
		System.out.print("One of your wagon wheels broke."); 
	    }

	    else if (probabilityEvent < 30) {
		event = "Oxen injury";
		System.out.println("One of your oxen has been injured."); 
	    }

	    else if (probabilityEvent < 25) {
		event = "Earthquake";
		System.out.println("There is an earthquake! Your pace, health, and food supplies have been reduced.");
		pat.pace = 1; 
		    
	    }

	    else if (probabilityEvent < 20) {
		event = member.name + " has typhoid fever!";
		member.hasDisease = true;
		member.disease = "Typhoid Fever"; 
	    }

	    else if (probabilityEvent < 15) {
		event = member.name + " got snatched away by an eagle!"; 
		member.health -= 100;
	    }
	}

	else {
	    event = "none";
	}
    
	   
	//probabilityEvent = 0;
	//probabilityOccuring = 0;
    
	return event;
    }
	

    public String setWeather() {
	if ( (daysTraveled % 3) == 0) {
	    weather = "windy";
	}

	if ( (daysTraveled % 5) == 0) {
	    weather = "cold";
	}
	
	if (daysTraveled % 7 == 0) {
	    weather = "rainy";
	}

	if ( (daysTraveled % 15) == 0) {
	    weather = "dry";
	}
	
	else {
	    weather = "warm";
	}

	return weather; 
	
    }
    
    
	//^^^^^^^^^^i m p o r t a n t   t h i n g s^^^^^^^^^^

	//==========h e l p e r   f u n c t i o n s==========
	public int getFirstIndexOf (String item) {
	    if (haveItem(item)) {
		for (int x = 0 ; x < inventory.size() ; x += 1) {
		    if (inventory.get(x) == item) {
			return x;
		    }
		}
	    }
	    return -1;
	}

	public void addItem (String item) {
	    inventory.add(item);
	}

	public void removeItem(String item) {
	    if (haveItem(item)) {
		inventory.remove(getFirstIndexOf(item));
	    }
	}

	public boolean haveItem (String item) {
	    for (int x = 0 ; x < inventory.size() ; x += 1) {
		if (inventory.get(x) == item) {
		    return true;
		}
	    }
	    return false;
	}
	//^^^^^^^^^^h e l p e r f u n c t i o n s^^^^^^^^^^

	
    
	public static void main (String[] args){
	    Woo game = new Woo();

	    milesTraveled = 0;

	    while (milesTraveled < maxMiles) {
		if (!game.playTurn())
		    break;
		System.out.println();
	    }
	    System.out.println("Your game is over.");
	}
    }
