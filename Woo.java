public class Woo{
    private Player pat;

    public Woo() {
	newGame();
    }
    
    public void newGame() {
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
	
    }
    public static void main (String[] args){
	System.out.println("cool and good");
	Woo game = new Woo();
    }
}
