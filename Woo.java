import cs1.Keyboard; 

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

        catch ( Exception e ) {}

	s = "\n What was your occupation before you moved? \n";

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

	    //System.out.println("\n" + pat.about(pat) + "\n"); 
	}

	catch (Exception e) {}
	

	/*
	pat = new Banker("JohnCena");
	System.out.println(pat.health);
	System.out.println(pat.name);
	System.out.println(pat.money);
	System.out.println(pat.pace);
	System.out.println(pat.ration);

	//testing methods of class Character
	System.out.println("\n\n" + pat.getName());
	System.out.println(pat.getHealth());
	System.out.println(pat.isAlive());

	//testing methods of class Player
	System.out.println(pat.getMoney());
	System.out.println(pat.changeRation(10));
	System.out.println(pat.changePace(3));
	
<<<<<<< HEAD
	*/ 
=======
	p1 = new Member("g dragon");
	System.out.println(p1.health);
	System.out.println(p1.name);
	System.out.println(p1.getHealth());
	System.out.println(p1.getName());
>>>>>>> 518ca1b0551b7ad7a6a7ba4c9a0fc68f8a471e28
    }
    public static void main (String[] args){
	Woo game = new Woo();
    }
}
