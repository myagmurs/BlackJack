package game;

public class Player extends Gamer {

	private int chips;
	
	public Player(String name) {
		super(name);
	}
	
	public Player(String name, boolean hit, boolean stand, boolean busted) {
		super(name, hit, stand, busted);
	}

	public int getChips() {
		return chips;
	}

	public void setChips(int chips) {
		this.chips = chips;
	}
	
}
