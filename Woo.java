import cs1.Keyboard;
import java.util.ArrayList;

public class Woo{
    //====================V A R I A B L E S====================
    public final static int maxMiles = 200;
    
    private Player pat;
    private Member spouse;
    private Member child1;
    private Member child2;
    private Member child3;
    
    private ArrayList<String> inventory = new ArrayList<String>();
    private int weight = 0;
    private int carrying = 0;

    private int numAlive;
    private int days = 0;
    private static int milesTraveled;
    private String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    private int month = 2;
    

    private String event;
    private boolean randomEvent = false;

    private String weather = "warm";

    private boolean isInventoryFull = false; 
    //^^^^^^^^^^^^^^^^^^^^V A R I A B L E S^^^^^^^^^^^^^^^^^^^^
    
    //==========i m p o r t a n t   t h i n g s==========
    public Woo() {
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
		addItem("wagonFixer");
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
		s = "\n\tChild " + (4 - membersLeft) + ": ";
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

	    printInventory();
	    //System.out.println(pat.money); 
	}
	catch (Exception e) {}
    }

    private Object[][] shopItems = { {"oxen",40},{"standardMedicine",15},{"goodMedicine",25},{"ultraMedicine",50},{"superiorMedicine",75},{"food",7},{"clothing",15},{"wagonFixer",7} };
    
    public void shop() {
	int cost = 0;
	int selection;
	int selection2 = 0;
	String item = "";
	String continueShopping = "yes"; 
	
	while ( continueShopping.equals("yes") && (pat.money > 0) && (! isInventoryFull)) {
	    System.out.println("Welcome to the shop! Here is your current amount of available cash: " +
			       pat.money +
			       "\nWhat would you like to buy?");
	    System.out.println("\t1: Oxen: $40 each\n" +
			       "\t2: Standard Medicine (+15 Health): $15 each\n" +
			       "\t3: Good Medicine (+25 Health): $25 each\n" +
			       "\t4: Ultra Medicine (+75 Health): $50 each\n" +
			       "\t5: Superior Medicine (+100 Health) : $75 each\n" +
			       "\t6: Food : $7 each\n" +
			       "\t7: Heavy jacket: $15 each\n" +
			       "\t8: Wagon fixing items: $7 each\n"  
			       );
	    System.out.println("\n Selection: ");

	    //try {
	    selection = Keyboard.readInt();

	    System.out.println("How many?");
	    selection2 = Keyboard.readInt();
	    cost = (int)(shopItems[selection-1][1]) * selection2;
	    System.out.println("Estimated Cost: " + cost);
	    if (pat.money > cost) {
		System.out.println("Remaining money: " + (pat.money - cost));
		pat.money -= cost;
		for (int i = selection2; i > 0; i--) {
		    addItem((shopItems[selection-1][0]).toString());
		}
	    }
	    else {
		System.out.println("You can't afford that.");
	    }

	    if (isInventoryFull) {
		System.out.println("Inventory is full. You will now start the game."); 
	    }
	    
	    else {
		System.out.println("Continue shopping? Type 'yes' or 'no'");

		continueShopping = Keyboard.readString();

		if (continueShopping == "no") {
		    for (int x = 0; x < inventory.size(); x++) {
			inventory.add(inventory.get(x));
		    }
		}
	    }
	}
	
	printInventory(); 
	System.out.println("Amount of money left: " + pat.money);
    }

    public boolean playTurn() {
	int selection = 1;
	int selection2 = 1; 

	if (pat.isAlive()) {
	    numAlive = 1;
	    if (spouse.isAlive()) {numAlive += 1;}
	    if (child1.isAlive()) {numAlive += 1;}
	    if (child2.isAlive()) {numAlive += 1;}
	    if (child3.isAlive()) {numAlive += 1;}
	    if (numAlive != 5) {
		if (! child1.isAlive()) {
		    System.out.println(child1.name + " is dead!");
		}

		if (! child2.isAlive()) {
		    System.out.println(child2.name + " is dead!");
		}


		//pace depends on the number of oxen in the inventory
		pat.pace = 1.0 + (0.1 * countItem("oxen"));
		   

		if (! child3.isAlive()) {
		    System.out.println(child3.name + " is dead!");
		}

		if (! spouse.isAlive()) {
		    System.out.println(spouse.name + " is dead!");
		}
	    }
    
		
	    
	    setWeather();
	    System.out.println(months[month] + " " + (days+1) + ", 1849");
	    System.out.println("Current Weather: " + weather);
	    System.out.println("Days traveled: " + days);
	    System.out.println("Miles Traveled: " + milesTraveled);
	    
	    int probabilityOccuring = (int)(Math.random() * 100); 

	    if (probabilityOccuring < 50) {
		randomEvent();
	    }

	    if (pat.hasDisease) {
		pat.health -= 10;
	    }
	    	
	    if (child1.hasDisease) {
		child1.health -= 10;
	    }
	    if (child2.hasDisease){
		child2.health -= 10;
	    }
	    if (child3.hasDisease) {
		child3.health -= 10;
	    }
	    if (spouse.hasDisease) {
		spouse.health -= 10;
	    }
	   

	    boolean continuee = false;
	    while (!continuee){    
		System.out.println("\nWhat do you want to do?");
		System.out.println("\t1: Continue\n" +
				   "\t2: Rest\n" +
				   "\t3: Check Location\n" +
				   "\t4: Check Inventory\n" +
				   "\t5: Check Stats\n" +
				   "\t6: Use item\n" +
				   "\t7: Hunt");
	    
		selection = Keyboard.readInt();

		//we will revolutionize the consumption of food products !!!
		if (selection == 1) {
		    continuee = true;
		    milesTraveled += pat.pace * 10;
		    eat();
		}

		if (selection == 2) {
		    rest();
		}

		if (selection == 3) {
		    if (milesTraveled <= (maxMiles/5)){
			System.out.println("You're near the start.");
		    }
		    else if (milesTraveled >= ((4 * maxMiles)/5)){
			System.out.println("You're near the end.");
		    }
		    else{
			System.out.println("You're in the middle of nowhere.");
		    }
		}

		if (selection == 4) {
		    printInventory();
		}

		if (selection == 5) {
		    System.out.println(pat.about());
		    System.out.println(child1.about()); 
		    System.out.println(child2.about());
		    System.out.println(child3.about());
		    System.out.println(spouse.about());
		}

		if (selection == 6) {
		    System.out.println("Here is your current inventory:" + "\n");
		    printInventory(); 
		    System.out.println("What would you like to use?");
		    System.out.println("\t1: Medicine\n" +
				       "\t2: Wagon Fixing Stuff");
		    
		    selection2 = Keyboard.readInt();

		    if (selection2 == 1) {
			int healthIncrease;

			Character member = pat;

			System.out.println("Here are the current stats of your party:");
			System.out.println(pat.about());
			System.out.println(child1.about()); 
			System.out.println(child2.about());
			System.out.println(child3.about());
			System.out.println(spouse.about());

			System.out.println("\nWho would you like to apply it to?");
			System.out.println("\t1:" + pat.name + "\n" +
					   "\t2:" + child1.name + "\n" +
					   "\t3:" + child2.name + "\n" +
					   "\t4:" + child3.name + "\n" +
					   "\t5:" + spouse.name);

			selection2 = Keyboard.readInt();

			if (selection2 == 1) {
			    member = pat;
			}

			if (selection2 == 2) {
			    member = child1;
			}
			if (selection2 == 3) {
			    member = child2;
			}
			if (selection2 == 4) {
			    member = child3;
			}

			if (selection2 == 5) {
			    member = spouse;
			}
			
			System.out.println("What kind of medicine?");
			System.out.println("\t1: Standard medicine\n" +
					   "\t2: Good medicine\n" +
					   "\t3: Ultra medicine\n" +
					   "\t4: Superior medicine\n"); 
			
			selection2 = Keyboard.readInt();

			if (selection2 == 1) {
			    if (haveItem("standardMedicine")) {
				member.health += 15;
				member.hasDisease = false;
			    }
			    else {
				System.out.println("You don't have that item!");
			    }
			
			}
		      
			if (selection2 == 2) {
			    if (haveItem("goodMedicine")) {
				member.health += 25;
				member.hasDisease = false;
			    }
			    else {
				System.out.println("You don't have that item!");
			    }
			}
			    
			if (selection2 == 3) {
			    if (haveItem("ultraMedicine")) {
				member.health += 75;
				member.hasDisease = false;
			    }
			    else {
				System.out.println("You don't have that item!");
			    }
			}
			    
			if (selection2 == 4) {
			    if (haveItem("superiorMedicine")) {
				member.health += 100;
				member.hasDisease = false; 
			    }
			    else {
				System.out.println("You don't have that item!");
			    }
			}

		
		    }

		    if (selection2 == 2) {
			if (haveItem("wagonFixer")) {
			    if (event.equals("BrokenWheel")) {
				event = "none";
				pat.pace = 2;
			    }
			    else {
				System.out.println("There's nothing to fix!");
			    }
			}
		    }
		}

		if (selection2 == 7) {
		    hunt();
		}
		    
		
	    
		//stuff done at the end of every playTurn()
		//progression of days and months
		days += 1;
		if ((days+1 > 30) && ((month % 2) == 0)) {
		    month += 1;
		    days = (days + 1)%30;
		}
		if ((days+1 > 31) && ((month % 2) == 1)) {
		    month += 1;
		    days = (days + 1)%31;
		}
	    }
	  
	    return true;
	}
	return false;
    }
	    
    public boolean rest(){
	days += 1;
	pat.health += 20;
	spouse.health += 20;
	child1.health += 20;
	child2.health += 20;
	child3.health += 20;
	return true;
    }
    
    public String randomEvent() {
	event = "";
	
	int prob = ((int) (Math.random() * 10));
	Character member; 
	if (prob < 2) {
	    member = child1;
	}

	if (prob >= 2 && prob < 4) {
	    member = child2;
	}

	if (prob >= 4 && prob < 6) {
	    member = child3;
	}

	if (prob >=7 && prob < 9) {
	    member = spouse;
	}
	
	else {
	    member = pat;
	}
	
	int probabilityEvent = (int)(Math.random() * 100);
	
	if ( (probabilityEvent != 0) && (days > 0)) {
		
	    if (probabilityEvent < 55 && weather == "rainy") {
		System.out.println("There is a thunderstorm!" + "\n" + "Your pace has been reduced to 1, you've lost 2 food items, and all members' health has been reduced by 5.");
		event = "Thunderstorm";
		pat.pace = 1;
		removeItem("food");
		removeItem("food");
		pat.health -= 5;
		spouse.health -= 5;
		child1.health -= 5;
		child2.health -= 5;
		child3.health -= 5; 
	    }

	    else if (probabilityEvent < 45) {
		event = "dysentery"; 
		System.out.println( member.name + " has dysentery! " + "\n" + member.name + "'s health has been reduced by 30. " + member.name + "'s health will keep reducing by 10 until medicine is received." ); 
		member.health -= 30;
		member.hasDisease = true;
		member.disease = "Dysentery";
	    }

	    else if (probabilityEvent < 40) {
		event = "cholera"; 
		System.out.println( member.name + " has cholera!" + "\n" + member.name + "'s health has been reduced by 30. " + member.name + "'s health will keep reducing by 10 until medicine is received." );
		member.health -= 30;
		member.hasDisease = true;
		member.disease = "Cholera";
	    }

	    else if (probabilityEvent < 37) {
		System.out.println("One of your wagon wheels broke." + "\n" + " You are trapped in your current location for 2 days and your pace has been reduced to 1. It will stay as 1 until you fix it."); 
		event = "BrokenWheel";
		pat.pace = 1;
		days += 2; 
	    }

	    else if (probabilityEvent < 30) {
		System.out.println("One of your oxen has died."); 
		event = "OxenDeath";
		removeItem("oxen"); 
	    }

	    else if (probabilityEvent < 25) {
		System.out.println("There is an earthquake!" + "\n" + "Your pace has been reduced to 1, your health has been decreased by 7, and your food supplies have been reduced by 3.");
		event = "Earthquake";
		pat.pace = 1;

		int i = 0; 
		while (i < 3) { 
		    removeItem("food");
		    i += 1;
		}

		pat.health -= 7;
		spouse.health -= 7;
		child1.health -= 7;
		child2.health -= 7;
		child3.health -= 7;	    
	    }

	    else if (probabilityEvent < 20) {
		System.out.println( member.name + " has Typhoid Fever! " + "\n" + member.name + "'s health has been reduced by 50. " + member.name + "'s health will keep reducing by 10 until medicine is received." );
		event = "TyphoidFever";
		member.hasDisease = true;
		member.disease = "Typhoid Fever";
		member.health -= 50; 
	    }

	    else if (probabilityEvent < 10) {
		event = "EagleSnatch"; 
	        System.out.println (member.name + " got snatched away by an eagle!" + "\n" + member.name + " has died."); 
		member.health -= 100;
	    }
	}

	else {
	    event = "none";
	}

	return event;
    }
	

    //Sets the weather condition for the day
    //Algorithm: every __ days, ___ weather condition will occur 
    public String setWeather() {
	if ( (days % 3) == 0) {
	    weather = "windy";
	}

	if ( (days % 5) == 0) {
	    weather = "cold";
	}
	
	if (days % 7 == 0) {
	    weather = "rainy";
	}

	if ( (days % 15) == 0) {
	    weather = "dry";
	}
	
	else {
	    weather = "warm";
	}

	return weather; 
    }
    //prints contents of inventory in the format
    //item1: quantity
    //item2: quantity
    public void printInventory() {
	String retStr = "";
	String item = "";
	for (int x = 0 ; x < inventory.size() ; x += 1) {
	    if (inventory.get(x) != item) {
		item = inventory.get(x);
		retStr += item + ": " + countItem(item) + "\n";
	    }
	}
	System.out.println(retStr);
    }

    public void eat() {
	for (int x = 0 ; x < numAlive ; x += 1) {
	    if (haveItem("food")) {
		//System.out.println("we got chipotle.");
		removeItem("food");
	    }
	    else {
		//System.out.println("oh no potato famine");
		if (pat.isAlive() && x == 0) {
		    pat.health -= 25;
		    if (!pat.isAlive()) {
			System.out.println(pat.name + "'s life slips away . . .");}}
		if (spouse.isAlive() && x == 1) {
		    spouse.health -= 25;
		    if (!spouse.isAlive()) {
			System.out.println(spouse.name + "'s life slips away . . .");}}
		if (child1.isAlive() && x == 2) {
		    child1.health -= 25;
		    if (!child1.isAlive()) {
			System.out.println(child1.name + "'s life slips away . . .");}}	
		if (child2.isAlive() && x == 3) {
		    child2.health -= 25;
		    if (!child2.isAlive()) {
			System.out.println(child2.name + "'s life slips away . . .");}}
		if (child3.isAlive() && x == 4) {
		    child3.health -= 25;
		    if (!child3.isAlive()) {
			System.out.println(child3.name + "'s life slips away . . .");}}
	    }
	}
    }

    public void hunt() {
	System.out.println("Welcome to hunting! Here's how the game will work:\n");
	System.out.println("You're going to have 7 chances to guess a random number picked by the computer between 1-30 inclusive.");
	System.out.println("If you get the number right with 3 or less guesses, then you will be rewarded with 7 food items.");
	System.out.println("If you get the number right with 5 or less guesses, then you will be rewarded with 5 food items.");
	System.out.println("If you get the number right with 7 or less guesses, then you will be rewarded with 3 food items.");
	System.out.println("If you don't guess the number, you will not receive any food.");
	System.out.println("Ready?");

	int firstNumber = 1;        //First number index (range)
	int lastNumber = 30;       //Last number index (range) 
	int number = (int)(Math.random()*100);  //Randomly selected number from 1-100
	int userNumber = -1;  //Number inputted by the user. Initial value is set to -1 in order to initiate the while loop 
	int numTries = 0;  // The number of tries it takes for the user to guess the correct number 
	
	while (numTries < 7) {
	    System.out.println("Guess a number between " + firstNumber + "-" + lastNumber);
	    userNumber = Keyboard.readInt();
	    if (userNumber > number) {
		lastNumber = userNumber - 1;
		numTries += 1;
		System.out.println("Too high, Try again...");
	    }
	    
	    if (userNumber < number) {
		firstNumber = userNumber + 1;
		numTries += 1;
		System.out.println("Too low, Try again...");
	    }

	    if (userNumber == number) {
		int i = 0;
		if (numTries <=3) {
		    System.out.println("Congrats, you received 7 food items.");
		    while (i < 7) {
			addItem("food");
		    }
		}

		if (numTries <= 5) {
		    System.out.println("Congrats, you received 5 food items.");
		    while (i < 5) {
			addItem("food");
		    }
		}

		if (numTries <= 7) {
		    System.out.println("Congrats, you received 3 food items.");
		    while (i < 3) {
			addItem("food");
		    }
		}
	    }
	}

	if (numTries > 7) {
	    System.out.println("Sorry, you received no food items. Try again next time!");
	}
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
        if (inventory.size() < 31) {
	    if (haveItem(item)) {
		inventory.add(getFirstIndexOf(item) , item);
	    }
	    else {
		inventory.add(item);
	    }
	}
	else {
	    System.out.println("Your inventory is full.");
	    isInventoryFull = true; 
	}
	
    }

    public void removeItem(String item) {
	if (haveItem(item)) {
	    inventory.remove(getFirstIndexOf(item));
	}
    }

    public boolean haveItem (String item) {
	for (int x = 0 ; x < inventory.size() ; x += 1) {
	    if (inventory.get(x).equals(item)) {
		return true;
	    }
	}
	return false;
    }

    public int countItem (String item) {
	int quantity = 0;
	if (haveItem(item)) {
	    for (int x = 0 ; x < inventory.size() ; x += 1) {
		if (inventory.get(x) == item) {
		    quantity += 1;
		}
	    }
	}
	return quantity;
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
