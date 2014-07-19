package game;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
	
	private String name;
	
	private List<Card> hand = new ArrayList<Card>();
	
	private int handSum1;
	
	private int handSum2;
	
	private boolean hit;
	
	private boolean stand;
	
	private boolean busted; 
	
	public Gamer() {
		
	}
	
	public Gamer(String name) {
		this.name = name;		
	}
	
	public Gamer(String name, boolean hit, boolean stand, boolean busted) {
		this.name = name;
		this.hit = hit;
		this.stand = stand;
		this.busted = busted;
	}
	
	public Gamer(String name, ArrayList<Card> hand, int handSum1, int handSum2) {
		super();
		this.name = name;
		this.hand = hand;
		this.handSum1 = handSum1;
		this.handSum2 = handSum2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public int getHandSum1() {
		return handSum1;
	}

	public void setHandSum1(int handSum1) {
		this.handSum1 = handSum1;
	}
	
	public int getHandSum2() {
		return handSum2;
	}

	public void setHandSum2(int handSum2) {
		this.handSum2 = handSum2;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean isStand() {
		return stand;
	}

	public void setStand(boolean stand) {
		this.stand = stand;
	}

	public boolean isBusted() {
		return busted;
	}

	public void setBusted(boolean busted) {
		this.busted = busted;
	}
}
