public class Woo{
    private Player pat;
    private Member p1;

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
	
	p1 = new Member("g dragon");
	System.out.println(p1.health);
	System.out.println(p1.name);
	System.out.println(p1.getHealth());
	System.out.println(p1.getName());
    }
    public static void main (String[] args){
	System.out.println("cool and good");
	Woo game = new Woo();
    }
}
