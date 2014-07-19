package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
	
	final private int initialPlayerChips = 100;
	
	private Dealer dealer;
	
	private Player player;
	
	private Bet bet;
	
	private List<Gamer> gamePlayers = new ArrayList<Gamer>();
	
	private List<Card> deckOfCards = new ArrayList<Card>();
	
	public Game() {
		
	}
	
	public void initGame() {
		this.dealer = new Dealer("Dealer", true, false, false);
		this.player = new Player("Player", true, false, false);
		this.player.setChips(initialPlayerChips);
		this.gamePlayers.add(dealer);
		this.gamePlayers.add(player);

		// creation of deck
		Deck deck = new Deck();
		this.deckOfCards = new ArrayList<Card>(Arrays.asList(deck.createDeck()));
		deck.shuffleDeck(this.deckOfCards);
		
		//initialization of bet
		this.bet = new Bet();
	}
	
	public void resetGame() {
		for(Gamer gamer : this.gamePlayers) {
			gamer.setHandSum1(0);
			gamer.setHandSum2(0);
			gamer.setHit(true);
			gamer.setStand(false);
			gamer.setBusted(false);
			gamer.setHand(new ArrayList<Card>());
		}

		// creation of deck
		Deck deck = new Deck();
		this.deckOfCards = new ArrayList<Card>(Arrays.asList(deck.createDeck()));
		deck.shuffleDeck(this.deckOfCards);
		this.setBet(new Bet());
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Gamer> getGamePlayers() {
		return gamePlayers;
	}

	public void setGamePlayers(List<Gamer> gamePlayers) {
		this.gamePlayers = gamePlayers;
	}

	public List<Card> getDeckOfCards() {
		return deckOfCards;
	}

	public void setDeckOfCards(List<Card> deckOfCards) {
		this.deckOfCards = deckOfCards;
	}
	
	public Player getGamePlayer() {
		return (Player) this.gamePlayers.get(1);
	}
	
	public Dealer getGameDealer() {
		return (Dealer) this.gamePlayers.get(0);
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

}
