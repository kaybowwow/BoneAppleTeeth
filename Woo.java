import cs1.Keyboard;
import java.util.ArrayList;

public class Woo{
    //====================V A R I A B L E S====================
    public final static int maxMiles = 750;
    
    private Player pat;
    private Member spouse;
    private Member child1;
    private Member child2;
    private Member child3;
    private Character[] fam = new Character[5];
    
    private ArrayList<String> inventory = new ArrayList<String>();
    private Object[][] items = { {"oxen",40,0},{"standardMedicine",15,1},{"goodMedicine",25,1},{"ultraMedicine",50,1},{"superiorMedicine",75,1},{"food",7,5},{"clothing",15,7},{"wood",5,15}};
    //item name, item price, item weight
    private int weight = 0;
    private int cartCapacity = 10000;

    private int numAlive;
    private int days = 0;
    private static int milesTraveled;
    
    private String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    private int month;
    private Object[][] landmarks = { {"Apple Town",0},{"Bone Town",150},{"Mykolyk River",300},{"Ursula City",450},{"Yekateri River",600},{"Oregon City",750} };
    private String currentLandmark = (String)landmarks[1][0];
    private String nextCheckpoint = (String)landmarks[1][0];
    private int nextCheckpointMiles = (int)landmarks[1][1];    

    private String event;
    private boolean randomEvent = false;

    private String weather = "warm";

    private boolean isInventoryFull = false;

    private double temperature; 
    //^^^^^^^^^^^^^^^^^^^^V A R I A B L E S^^^^^^^^^^^^^^^^^^^^
    
    //==========i m p o r t a n t   t h i n g s==========
    public Woo() {
	newGame();
    }
    
    public void newGame() {
	String s;
	String name = "";
	int selection; 
	int selection3;

	System.out.println("Welcome to the Oregon Trail! By what name do you go by?");

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
	    if (selection == 1) {inventory.add("food"); pat = new Farmer(name);}
	    if (selection == 2) {inventory.add("wood"); pat = new Carpenter(name);}
	    if (selection == 3) {inventory.add("clothing"); pat = new Banker(name);}
	    if (selection == 4) {pat = new Immigrant(name);}   
	}
	catch (Exception e) {addItem("food"); pat = new Farmer(name);
	    System.out.println("Unexpected input received. Default settings are used: you are a farmer."); 
	}

	System.out.print("\nWhat are your family members' names?\n");
	
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
	fam[0] = pat;
	fam[1] = spouse;
	fam[2] = child1;
	fam[3] = child2;
	fam[4] = child3;

	
	System.out.println("What month would you like to start in?");
	System.out.println("\t1: January\n" +
			   "\t2: February\n" +
			   "\t3: March\n" +
			   "\t4: April\n");
	try {
	    selection = Keyboard.readInt();

	    for (int i = 1; i < 5; i++) {
		if (i == selection) {
		    month = i-1;
		}
	    }
	}

	catch (Exception e) {}
		
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

    public void crossRiver() {
	int selection;
	int riverDepth = (int)(Math.random()*5) + 2;
	int riverWidth = (int)(Math.random()*30) + 20;
	System.out.println("The river is " + riverDepth + " feet deep and " + riverWidth + " feet wide.\n" +
			   "What would you like to do?");
	System.out.println("\t1: Ford the river!\n" +
			   "\t2: Caulk your wagon and float across the river\n" +
			   "\t3: Pay the ferryman to sail you across");
	System.out.print("\n Selection: ");

	selection = Keyboard.readInt();

	if (selection == 1) {
	    try {
		for (int x = 0 ; x < 3 ; x += 1) {
		    System.out.print(". ");
		    Thread.sleep(750);}}
	    catch (Exception e){
		System.out.println(". . . ");}
	    if (riverDepth > 3) {
		for (Character bro : fam) {
		    if ((Math.random()*100) > 50) {
			if (bro.alive){
			    bro.addHealth(-100);
			    System.out.println(bro.name + " has drowned!");}}}}
	    if (riverDepth == 3) {
		for (String item : inventory) {
		    if ((Math.random()*100) > 95) {
			System.out.println("You lost " + item + " in the river.");
			removeItem(item);}}}
	    else {
		System.out.println("Onwards! Ho!");
		Continue();}
	}
	if (selection == 2) {
	    try {
		for (int x = 0 ; x < 3 ; x += 1) {
		    System.out.print(". ");
		    Thread.sleep(750);}}
	    catch (Exception e) {
		System.out.println(". . . ");}
	    if (riverWidth > 40) {
		if ((Math.random()*100) > 80) {
		    event = "BrokenWheel";
		    System.out.println("The fast river currents pushed rocks that damaged your wagon. You're slowed down.");
		    pat.pace = 0.5;
		}
	    }
	    else {
		System.out.println("Onwards! Ho!");
		Continue();}
	}
	if (selection == 3) {
	    pat.money -= ((riverWidth + riverDepth) * 2);
	    System.out.println("You paid the ferryman $" + ((riverWidth + riverDepth) * 2) + " to take you across the river.");
	    Continue();}
    }
    
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
			       "\t5: Superior Medicine (+120 Health) : $75 each\n" +
			       "\t6: Food : $7 each\n" +
			       "\t7: Heavy jacket : $15 each\n" +
			       "\t8: Wood : $5 each\n"
			       );
	    System.out.println("\n Selection: ");

	    //try {
	    selection = Keyboard.readInt();

	    System.out.println("How many?");
	    selection2 = Keyboard.readInt();
	    cost = (int)(items[selection-1][1]) * selection2;
	    System.out.println("Estimated Cost: " + cost);
	    if (pat.money > cost) {
		System.out.println("Remaining money: " + (pat.money - cost));
		//pat.money -= cost;
		for (int i = selection2; i > 0; i--) {
		    if((addItem((items[selection-1][0]).toString())));{
			pat.money -= (int)items[selection-1][1]; 
			//if ((weight is too big) or (inventory is full)) and addItem fails, don't spend money
			//this doesn't work
		    }
		}
	    }
	    else {
		System.out.println("You can't afford that.");
	    }

	    if (isInventoryFull) {
		System.out.println("Inventory is full. Hit the road."); 
	    }
	    
	    else {
		System.out.println("Continue shopping? Type 'yes' or 'no'");

		continueShopping = Keyboard.readString();
	    }
	}
	
	printInventory(); 
	System.out.println("Amount of money left: " + pat.money);
    }

    public boolean playTurn() {
	int selection = 1;
	int selection2 = 1; 
	int selection3 = 1;
	String s = "";

	if (pat.alive) {
	    numAlive = 0;
	    for (Character bro : fam){if(bro.alive){numAlive++;}}
	    if (numAlive != 5) {
		for (Character bro : fam){
		    if (!bro.alive){
			System.out.println(bro.name + " is dead!");
			System.out.println("You bury " + bro.name + "...");
		    }
		}
	    }
		
	    updateWeight();
	    setWeather();
	    setTemperature(); 
	    System.out.println(months[month] + " " + (days+1) + ", 1849");
	    System.out.println("Current Weather: " + weather);
	    System.out.println("Current Temperature (in Fahrenheit): " + temperature); 
	    System.out.println("Days traveled: " + days);
	    System.out.println("Miles Traveled: " + milesTraveled);
			
	    //pace depends on the number of oxen in the inventory
	    pat.pace = 1 + (.4 * countItem("oxen"));
	    if (countItem("oxen") == 0){pat.addHealth(-5); spouse.addHealth(-5); child1.addHealth(-5); child2.addHealth(-5); child3.addHealth(-5);}
	    //carrying stuff on ur own back is hard work
	    //pace also depends on weight
	    pat.pace -= (weight * .001);
	    //pace also depends on weather and temperature
	    if (temperature < 20) {pat.pace *= .5;} 
	    if (temperature < 45) {pat.pace *=.9;}
	    if (weather.equals("rainy")){pat.pace*=.8;}
	    if (temperature > 45) {pat.pace*=1.1;}
	    if (weather.equals("dry")){pat.pace*=1.1;}
	    if (weather.equals("windy")){pat.pace*=.9;}
	    if (pat.pace < .1){pat.pace = .1;}

	    if (temperature < 20) {
		for (Character bro: fam) {
		    bro.addHealth(-4);
		}
	    }

	    int probabilityOccuring = (int)(Math.random() * 100); 

	    if (probabilityOccuring < 50) {
		randomEvent();
	    }

	    for (Character bro : fam){
		if (bro.hasDisease){
		    bro.addHealth(-8);
		}
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
		if ((currentLandmark.indexOf("Town")!=-1) || (currentLandmark.indexOf("City")!=-1)) {
		    System.out.println("\t8: Visit the shop");
		}
		if((currentLandmark.indexOf("River")!=-1)) {
		    System.out.println("\t8: Cross the river!");
		}
			
		selection = Keyboard.readInt();

		//we will revolutionize the consumption of food products !!!
		if (selection == 1) {
		    continuee = true;
		    Continue();
		}

		if (selection == 2) {
		    days++;
		    System.out.println("You feel rested. Health has been increased, and a day has passed.");
		    rest();
		}

		if (selection == 3) {
		    System.out.println("You are " + (nextCheckpointMiles - milesTraveled) + " miles from " + nextCheckpoint + "."); 
		}

		if (selection == 4) {
		    printInventory();
		}

		if (selection == 5) {
		    for (Character bro : fam){
			System.out.println(bro.about());
		    }
		}

		if (selection == 6) {
		    System.out.println("Here is your current inventory:");
		    printInventory(); 
		    System.out.println("It weighs " + weight + ".\n");
		    System.out.println("What would you like to do?");
		    System.out.println("\t1: Use medicine\n" +
				       "\t2: Use wood\n" +
				       "\t3: Throw something out");
				
		    selection2 = Keyboard.readInt();

		    if (selection2 == 1) {
			int healthIncrease;

			Character member = pat;

			System.out.println("Here are the current stats of your party:");
			for (Character bro : fam){System.out.println(bro.about());}

			System.out.println("\nWho would you like to apply it to?");
			s = "";
			for (int i = 0; i < 5; i++){
			    s += "\t" + i + ":" + fam[i].name + "\n";
			}
			System.out.println(s);

			selection2 = Keyboard.readInt();

			for (int i = 0; i < 5; i++){
			    if (selection2 == i && fam[i].alive){
				member = fam[i];
			    }
			}
				
			System.out.println("What kind of medicine?");
			System.out.println("\t1: Standard medicine\n" +
					   "\t2: Good medicine\n" +
					   "\t3: Ultra medicine\n" +
					   "\t4: Superior medicine\n"); 
				
			selection2 = Keyboard.readInt();
			applyMedicine(selection2, member);
			selection2 = 1;
		    }
		    if (selection2 == 2) {
			if (haveItem("wood")) {
			    if (event.equals("BrokenWheel")) {
				event = "none";
				pat.pace = 1;
				removeItem("wood");
			    }
			    else {
				System.out.println("There's nothing to fix!");
			    }
			}
		    }
				
		    if (selection2 == 3) {
			System.out.println("What would you like to throw out?");
			System.out.println("\t1: Standard medicine\n" +
					   "\t2: Good medicine\n" +
					   "\t3: Ultra medicine\n" +
					   "\t4: Superior medicine\n" +
					   "\t5: Food \n" +
					   "\t6: Clothing\n" +
					   "\t7: Wood\n" +
					   "\t8: Your spouse\n" +
					   "\t9: A child\n");
			selection2 = Keyboard.readInt();
			if (selection2 < 8){
			    System.out.println("How many?");
			    selection3 = Keyboard.readInt();
			}
			if (selection2 == 1){for (int i = 0; i < selection3; i++){removeItem("standardMedicine");}}
			if (selection2 == 2){for (int i = 0; i < selection3; i++){removeItem("goodMedicine");}}
			if (selection2 == 3){for (int i = 0; i < selection3; i++){removeItem("ultraMedicine");}}
			if (selection2 == 4){for (int i = 0; i < selection3; i++){removeItem("superiorMedicine");}}
			if (selection2 == 5){for (int i = 0; i < selection3; i++){removeItem("food");}}
			if (selection2 == 6){for (int i = 0; i < selection3; i++){removeItem("clothing");}}
			if (selection2 == 7){for (int i = 0; i < selection3; i++){removeItem("wood");}}
			if (selection2 == 8){System.out.println("You're cruel..."); spouse.addHealth(-100);}
			if (selection2 == 9){System.out.println("You're cruel..."); 
			    if (child1.alive) {child1.addHealth(-100);}
			    else if (child2.alive) {child2.addHealth(-100);}
			    else if (child3.alive) {child3.addHealth(-100);}
			}
					
		    }
		}

		if (selection == 7) {
		    hunt();
		}
	
		if ((currentLandmark.indexOf("Town")!=-1) || (currentLandmark.indexOf("City")!=-1)) {
		    if (selection == 8) {
			shop();
		    }
		}
		if ((currentLandmark.indexOf("River"))!=-1) {
		    if (selection == 8) {
			crossRiver();
		    }
		}

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
		  
	    return true;
	}
	return false;
    }

	    
    public boolean rest(){
	days += 1;
	for (Character bro : fam){if(bro.alive){bro.addHealth(10);}}
	return true;
    }

    public void Continue() {
	milesTraveled += pat.pace * 10;
	currentLandmark = "";
	if (milesTraveled > nextCheckpointMiles) {
	    milesTraveled = nextCheckpointMiles;
	    System.out.println("You have arrived at " + nextCheckpoint + ".");
	    currentLandmark = nextCheckpoint;
	    updateNextCheckpoint();
	}
	else {
	    updateNextCheckpoint();
	}
	eat();
    }	    

    
    public String randomEvent() {
	event = "";
	
	int prob = ((int) (Math.random() * 5));
	Character member; 
	member = fam[prob];
	
	int probabilityEvent = (int)(Math.random() * 100);
	
	if (probabilityEvent != 0 && (days > 0)) {
		
	    if (probabilityEvent < 55 && weather == "rainy") {
		System.out.println("There is a thunderstorm!" + "\n" + "Your pace has been reduced to 1, you've lost 2 food items, and all members' health has been reduced by 5.");
		event = "Thunderstorm";
		pat.pace = 1;
		removeItem("food");
		removeItem("food");
		for (Character bro : fam){
		    bro.addHealth(-5);
		}
	    }

	    else if (probabilityEvent < 45) {
		event = "dysentery"; 
		System.out.println( member.name + " has dysentery! " + "\n" + member.name + "'s health has been reduced by 30. " + member.name + "'s health will keep reducing by 10 until medicine is received." ); 
		member.addHealth(-30);
		member.hasDisease = true;
		member.disease = "Dysentery";
	    }

	    else if (probabilityEvent < 40) {
		event = "cholera"; 
		System.out.println( member.name + " has cholera!" + "\n" + member.name + "'s health has been reduced by 30. " + member.name + "'s health will keep reducing by 10 until medicine is received." );
		member.addHealth(-30);
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
		for (Character bro : fam){
		    bro.addHealth(-7);
		}   
	    }

	    else if (probabilityEvent < 20) {
		System.out.println( member.name + " has Typhoid Fever! " + "\n" + member.name + "'s health has been reduced by 50. " + member.name + "'s health will keep reducing by 10 until medicine is received." );
		event = "TyphoidFever";
		member.hasDisease = true;
		member.disease = "Typhoid Fever";
		member.addHealth(-50); 
	    }

	    else if (probabilityEvent < 10) {
		event = "EagleSnatch"; 
	        System.out.println (member.name + " got snatched away by an eagle!" + "\n" + member.name + " has died."); 
		member.addHealth(-100);
	    }
	}

	else {
	    event = "none";
	}

	return event;
    }

    public double setTemperature() {
	if (month == 12 || month < 4) {
	    temperature = (Math.random() * 10);
	}

	if (month >= 4 && month < 6) {
	    temperature = (Math.random() * 10 + 20);
	}

	if (month >= 6 && month < 10) {
	    temperature = (Math.random() * 10 + 60);
	}

	if (month >= 10 && month < 12) {
	    temperature = (Math.random() * 10 + 25);
	}
	return temperature; 
    }

    //Sets the weather condition for the day
    //Algorithm: every __ days, ___ weather condition will occur 
    public String setWeather() {
	if ( (days % 3) == 0) {
	    weather = "windy";
	}
	
	if (days % 7 == 0) {
	    weather = "rainy";
	}

	if ( (days % 15) == 0) {
	    weather = "dry";
	}

	if ( (temperature < 45) ) {
	    weather = "cold";
	}

	if ( (temperature > 45) ) {
	    weather = "warm";
	}
	
	return weather; 
    }

    public void hunt() {
	boolean isFinished = false; 
	System.out.println("Welcome to hunting! Here's how the game will work:\n");
	System.out.println("You're going to have 10 chances to guess a random number picked by the computer between 1-100 inclusive.");
	System.out.println("If you get the number right with 5 or less guesses, then you will be rewarded with 7 food items.");
	System.out.println("If you get the number right with 7 or less guesses, then you will be rewarded with 5 food items.");
	System.out.println("If you get the number right with 8 or less guesses, then you will be rewarded with 3 food items.");
	System.out.println("If you don't guess the number, you will not receive any food.");
	System.out.println("Ready?");

	int firstNumber = 1;        //First number index (range)
	int lastNumber = 100;       //Last number index (range) 
	int number = (int)(Math.random()*100);  //Randomly selected number from 1-100
	int userNumber = -1;  //Number inputted by the user. Initial value is set to -1 in order to initiate the while loop 
	int numTries = 0;  // The number of tries it takes for the user to guess the correct number 
	
	while (numTries < 10 && (!isFinished)) {
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
		if (numTries <=5) {
		    System.out.println("Congrats, you received 7 food items.");
		    while (i < 7) {
			addItem("food");
			i++;
		    }
		}

		if (numTries > 5 && numTries <= 7) {
		    System.out.println("Congrats, you received 5 food items.");
		    while (i < 5) {
			addItem("food");
			i++;
		    }
		}

		if (numTries > 7 && numTries <= 8) {
		    System.out.println("Congrats, you received 3 food items.");
		    while (i < 3) {
			addItem("food");
			i++;
		    }
		}
		isFinished = true; 
	    }
	}	    

	if (numTries > 7) {
	    System.out.println("Sorry, you received no food items. Try again next time!");
	}
	
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

    public void updateWeight() {
	weight = 130;
	if (spouse.alive) {weight += 130;}
	if (child1.alive) {weight += 120;}
	if (child2.alive) {weight += 100;}
	if (child3.alive) {weight += 80;}
	for (Object item : inventory){
	    int weightitem = 0;
	    for (Object[] itemdata : items){
		if (item.equals(itemdata[0])){
		    weight += (Integer)itemdata[2];
		}
	    }
	}
    }

    public void eat() {
	for (int x = 0 ; x < numAlive ; x += 1) {
	    if (haveItem("food")) {
		//System.out.println("we got chipotle.");
		removeItem("food");
	    }
	    else {
		//System.out.println("oh no potato famine");
		if (fam[x].alive){
		    fam[x].addHealth(-20);
		    if (!fam[x].alive){
			System.out.println(fam[x].name + "'s life slips away . . .");}}
	    }
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

    public boolean addItem (String item) {
        if (inventory.size() < 50) {
	    if (haveItem(item)) {
		inventory.add(getFirstIndexOf(item) , item);
	    }
	    else {
		inventory.add(item);
	    }
	    updateWeight();
	    if (weight > cartCapacity){
		System.out.println("That's too heavy for your cart to carry! Your current capacity is " + cartCapacity + " pounds ");
		removeItem(item);
		return false;
	    }
	    return true;
	}
	else {
	    if(!isInventoryFull){System.out.println("Your inventory is full. You can only hold 30 items.");}
	    isInventoryFull = true; 
	}
	return false;
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
	
    private void applyMedicine(int selection2, Character member){
	if (selection2 == 1) {
	    if (haveItem("standardMedicine")) {
		member.addHealth(15);
		member.hasDisease = false;
		removeItem("standardMedicine");
	    }
	    else {
		System.out.println("You don't have that item!");
	    }
			
	}
	if (selection2 == 2) {
	    if (haveItem("goodMedicine")) {
		member.addHealth(25);
		member.hasDisease = false;
		removeItem("goodMedicine");
	    }
	    else {
		System.out.println("You don't have that item!");
	    }
	}
	if (selection2 == 3) {
	    if (haveItem("ultraMedicine")) {
		member.addHealth(75);
		member.hasDisease = false;
		removeItem("ultraMedicine");
	    }
	    else {
		System.out.println("You don't have that item!");
	    }
	} 
	if (selection2 == 4) {
	    if (haveItem("superiorMedicine")) {
		member.addHealth(120);
		member.hasDisease = false; 
		removeItem("ultraMedicine");
	    }
	    else {
		System.out.println("You don't have that item!");
	    }
	}
    }
        public void updateNextCheckpoint() {
	for (int x = 0 ; x < landmarks.length - 1 ; x += 1) {
	    if (milesTraveled >= nextCheckpointMiles) {
		nextCheckpointMiles = (int)landmarks[x + 1][1];
		nextCheckpoint = (String)landmarks[x + 1][0];
	    }
	}
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
