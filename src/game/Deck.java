package game;

import java.util.Collections;
import java.util.List;

public class Deck {
	
	final private Suit[] suits = {Suit.Spades, Suit.Hearts, Suit.Diamonds, Suit.Clubs};
	
	final private Face[] faces = {Face.Ace, Face.Two, Face.Three, Face.Four, Face.Five,
								  Face.Six, Face.Seven, Face.Eight, Face.Nine, Face.Ten,
								  Face.Jack, Face.Queen, Face.King};
	
	public Deck() {
		
	}
	
	public Card[] createDeck() {
		Card[] deckOfCards = new Card[52];
		int i = 0;
		for(Suit suit : suits) {
			for(Face face : faces) {
				deckOfCards[i] = new Card(suit, face);
				i++;
			}
		}		
		return deckOfCards;		
	}
	
	public List<Card> shuffleDeck(List<Card> deckOfCards) {
		Collections.shuffle(deckOfCards);
		return deckOfCards;
	}
}
