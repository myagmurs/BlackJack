package game;

public class Player extends Gamer {

	public Player(String name) {
		super(name);
	}
	
	public Player(String name, boolean hit, boolean stand, boolean busted) {
		super(name, hit, stand, busted);
	}
	
}
