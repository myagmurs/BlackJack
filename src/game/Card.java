package game;

public class Card {
	
	private Suit suit;
	
	private Face face;
	
	public Card() {
		
	}
	
	public Card(Suit suit, Face face) {
		this.suit = suit;
		this.face = face;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Face getFace() {
		return face;
	}

	public void setFace(Face face) {
		this.face = face;
	}
	
	public int getCardValue() {
		int value = 0;
		switch(this.getFace()) {
			case Ace: value=1;
				  	  break;
			case Two: value=2;
		  		  	  break;
			case Three: value=3;
			  	  		break;
			case Four: value=4;
			  	  	   break;
			case Five: value=5;
			  	  	   break;
			case Six: value=6;
			  	  	  break;
			case Seven: value=7;
			  	  		break;	
			case Eight: value=8;
			  	  		break;
			case Nine: value=9;
			  	  	   break;
			case Ten: value=10;
			  	  	  break;
			case Jack: value=10;
			  	  	   break;
			case Queen: value=10;
			  			break;
			case King: value=10;
			  		   break;
			default : value = 0;
					  break;
		}
		return value;
	}
}
