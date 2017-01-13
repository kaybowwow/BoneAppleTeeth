import cs1.Keyboard;
import java.util.ArrayList;

public class Woo{
    
    private Player pat;
    private Member p1;

    public Woo() {
	newGame();
    }
    
    public void newGame() {
	String s;
	String name = "";
	int selection; 

	s= "Welcome to the Oregon Trail! Who are you?";
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
		pat = new Farmer(name);
		
	    }

	    if (selection == 2) {
		pat = new Carpenter(name);
	
	    }

	    if (selection == 3) {
		pat = new Banker(name);
		
	    }

	    if (selection == 4) {
		pat = new Immigrant(name);
	    }   
	}

	catch (Exception e) {
	    pat = new Farmer(name);
	    System.out.println("Unexpected input received. Default settings are used: you are a farmer."); 
	}

	//printing initial stats:
	System.out.println("Name: " + pat.name);
	System.out.println("Health: " + pat.health);
	System.out.println("Money: " + pat.money);

	System.out.println("\n Before you head on the road, would you like to buy anything?");

	System.out.println("\n"+ "\t1: Yes" + "\t2: No");

	try {
	    selection = Keyboard.readInt();

	    if (selection == 1) {
		shop();
	    }

	    // NOTE: it should be something like this:
	    /* else {
	       playTurn() }
	    */

	    System.out.println(pat.inventory);
	    System.out.println(pat.money); 
	}

	catch (Exception e) {}

	

    }

    public ArrayList<String> shop() {
	int minusMoney = 0;
	int selection;
	int selection2 = 0;
	String item = ""; 
	
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
	    
	    for (int i = selection2; i > 0; i--) {
		pat.inventory.add(item);
		System.out.println(pat.inventory); 
	    }
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

	//Subtracting money 
	pat.money -= minusMoney;
	
	    
	//Adding item(s) to inventory:
	for (int i = selection2; i > 0; i--) {
	    pat.inventory.add(item);
	    System.out.println(pat.inventory); 
	}

	   
	System.out.println(pat.money); 
	
	

	
	return pat.inventory;
    }

    
	    
    
       

    
    public static void main (String[] args){
	Woo game = new Woo();
    }
}
