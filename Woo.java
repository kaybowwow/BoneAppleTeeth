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
	    name = Keyboard.readWord();
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
		addItem("corn");
		pat = new Farmer(name);
	    }

	    if (selection == 2) {
		addItem("wood");
		pat = new Carpenter(name);	
	    }

	    if (selection == 3) {
		addItem("winter jacket");
		pat = new Banker(name);		
	    }

	    if (selection == 4) {
		addItem("Blah");
		pat = new Immigrant(name);
	    }   
	}
	catch (Exception e) {
	    addItem("corn");
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
		name = Keyboard.readWord();
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
		    System.out.println("Number: ");
		    selection2 = Keyboard.readInt();
		    minusMoney = 20 * selection2;
		}

		if (selection == 2) {
		    item = "medicine"; 
		    System.out.println("How many?");
		    System.out.println("Number: ");
		    selection2 = Keyboard.readInt();
		    minusMoney = 10 * selection2;
		}

		if (selection == 3) {
		    item = "food"; 
		    System.out.println("How many?");
		    System.out.println("Number: ");
		    selection2 = Keyboard.readInt();
		    minusMoney = 7 * selection2; 
		}
	    
		if (selection == 4) {
		    item = "heavy jacket"; 
		    System.out.println("How many?");
		    System.out.println("Number: ");
		    selection2 = Keyboard.readInt();
		    minusMoney = 15 * selection2; 
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
	    System.out.println("\nWhat do you want to do?");
	    System.out.println("\t1: Continue\n\t2: Rest");
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
		System.out.println("Miles Traveled: " + milesTraveled);
	    }

	    if (selection == 2) {
		rest();
	    }
	    return true;
	}

        public boolean rest(){
	    System.out.println("\t1: Continue\n\t2: Check Location\n\t3: Check Inventory");
	    int selection = Keyboard.readInt();
		
	    if (selection == 1){
		System.out.println("Back to the trail!");
	    }
	    else if (selection == 2){
		if (milesTraveled <= 20){
		    System.out.println("You're near the start.");
		}
		else if (milesTraveled >= 80){
		    System.out.println("You're near the end.");
		}
		else{
		    System.out.println("You're in the middle of nowhere.");
		}
		rest();
	    }
	    else if (selection == 3){
		System.out.println(inventory);
		rest();
	    }
	    return true;
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
