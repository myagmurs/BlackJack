package game;

public class Dealer extends Gamer {

	public Dealer(String name) {
		super(name);
	}
	
	public Dealer(String name, boolean hit, boolean stand, boolean busted) {
		super(name, hit, stand, busted);
	}

}
