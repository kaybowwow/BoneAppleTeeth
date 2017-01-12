public class Character{

    int health; //If no food: health decreases
    String name;

    public Character(int setHealth) {
	health = setHealth;
    }
    public String getName() {
	return name;
    }

    public int getHealth() {
	return health;
    }

    public boolean isAlive() {
	return (health > 0);
    } 
}
