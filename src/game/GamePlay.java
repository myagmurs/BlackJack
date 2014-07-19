package game;

import java.util.List;
import java.util.Scanner;

public class GamePlay {
		
	final private static int BLACKJACK = 21;
	
	private static Scanner scan = new Scanner(System.in);
	
	private static boolean gamePlayFlag = true;
	
	private static boolean playerFlag = true;
	
	private static boolean dealerFlag = false;
	
	final static int leastBet = 1;
	
	public static void main(String[] args) {		
		Game game = new Game();
		game.initGame();

		// initial distribution of cards
		initGamePlay(game);
		
		// game play scenario
		while(gamePlayFlag) {
			if(playerFlag) {
				turn(game.getDeckOfCards(), game.getGamePlayer());
				checkHand(game.getGamePlayer());
			} else {
				if(!dealerFlag) {
					relieveDealerHand(game.getGameDealer());
					dealerFlag = true;
				}
				turn(game.getDeckOfCards(), game.getGameDealer());
				checkHand(game.getGameDealer());
			}
			if(!gamePlayFlag) {
				openAllHands(game);
				askForReplay(game);
			}
		}		
	}

	private static void askForBets(Game game) {
		int bet;
		do {
			System.out.println(game.getGamePlayer().getName() + ", you have " + game.getGamePlayer().getChips() + " chips");
			System.out.println("How many chips you want to bet?");
			bet = scan.nextInt();
		} while(bet <= leastBet && bet <= game.getGamePlayer().getChips());
		game.getBet().setPlayerChips(bet);
		game.getGamePlayer().setChips(game.getGamePlayer().getChips()-bet);
	}

	private static void initGamePlay(Game game) {
		playerFlag = true;
		gamePlayFlag = true;
		dealerFlag = false;
		askForBets(game);
		turnInitial(game.getDeckOfCards(), game.getGamePlayers());
		turnInitial(game.getDeckOfCards(), game.getGamePlayers());
		checkHandsInitial(game);
	}
	
	private static void askForReplay(Game game) {
		if(game.getGamePlayer().getChips() < leastBet) {
			System.out.println(game.getGamePlayer().getName() + ", you don't have enough cheaps to replay.");
			System.out.println("Thanks for playing");
		} else {
			String replay;
			do {
				System.out.println(game.getGamePlayer().getName() + ", you have " + game.getGamePlayer().getChips() + " remaining.");
				System.out.println("Do you want to play again?(y/n)");
				replay = scan.next();
			} while(!"y".equals(replay) && !"n".equals(replay));
			if("y".equals(replay)) {
				game.resetGame();
				initGamePlay(game);
			} else {
				System.out.println("Thanks for playing");
			}
		}
	}
	
	private static void turnInitial(List<Card> deckOfCards, List<Gamer> gamePlayers) {
		for(Gamer gamer : gamePlayers) {
			if(!gamer.isStand() && gamer.isHit()) {
				gamer.getHand().add(deckOfCards.get(0));
				calculateHandSums(gamer);
				if(gamer.getHandSum1() > BLACKJACK && gamer.getHandSum2() > BLACKJACK) {
					gamer.setBusted(true);
					gamePlayFlag = false;
					break;
				}
				deckOfCards.remove(0);
			}
		}
	}
	
	private static void turn(List<Card> deckOfCards, Gamer gamer) {
		if(gamer.getHandSum1() == BLACKJACK || gamer.getHandSum2() == BLACKJACK) {
			if(gamer instanceof Player)
				playerFlag = false;
			else
				gamePlayFlag = false;
		} else {
			hitOrStand(gamer);
			if(!gamer.isStand()) {
				gamer.getHand().add(deckOfCards.get(0));
				calculateHandSums(gamer);
				if(gamer.getHandSum1() > BLACKJACK && gamer.getHandSum2() > BLACKJACK) {
					gamer.setBusted(true);
					gamePlayFlag = false;
				}
				deckOfCards.remove(0);
			} else if(gamer.isStand() && gamer instanceof Dealer) {
				gamePlayFlag = false;
			} else {
				playerFlag = false;
			}
		}
	}
	
	private static void checkHandsInitial(Game game) {
		if(game.getGamePlayer().getHandSum2() == BLACKJACK) {
			System.out.println(game.getGamePlayer().getName() + " has hit to Blackjack");
			if(game.getGameDealer().getHandSum2() == BLACKJACK) {
				System.out.println(game.getGamePlayer().getName() + " has hit to Blackjack too");
				openAllHands(game);
				System.out.println("The game finished with push");
				game.getPlayer().setChips(game.getPlayer().getChips() + game.getBet().getPlayerChips());
				askForReplay(game);
				return;
			}
			openAllHands(game);
			askForReplay(game);
		} else {
			System.out.println(game.getGamePlayer().getName() + " has hit to " + game.getGamePlayer().getHandSum1() + " or " + game.getGamePlayer().getHandSum2());
			System.out.println("The cards are: ");
			for(Card card : game.getGamePlayer().getHand()) {
				System.out.println(card.getSuit() + " " + card.getFace());
			}
			System.out.println("The Dealer has a " + game.getGameDealer().getHand().get(0).getSuit() + " " + 
										game.getGameDealer().getHand().get(0).getFace() + " and a hidden card");
		}	
	}
	
	private static void checkHand(Gamer gamer) {
		System.out.println(gamer.getName() + ": Sum1->" + gamer.getHandSum1() + " Sum2->" + gamer.getHandSum2());
	}
	
	private static void openAllHands(Game game) {
		for(Gamer gamer : game.getGamePlayers()) {
			if(gamer.isBusted()) {
				System.out.println(gamer.getName() +  " is busted.");
				Gamer winner = game.getGamePlayers().get(1-game.getGamePlayers().indexOf(gamer));
				System.out.println("The winner is " + winner.getName());
				if(winner instanceof Player) {
					((Player) winner).setChips(((Player) winner).getChips()+game.getBet().getPlayerChips()*2);
					System.out.println(winner.getName() + ", you won " + game.getBet().getPlayerChips() + " chips.");
				} else {
					System.out.println(gamer.getName() + ", you lost " + game.getBet().getPlayerChips() + " chips.");
				}
				return;
			}
		}
		int hand0 = openHand(game.getGameDealer());
		int hand1 = openHand(game.getGamePlayer());
		if(hand0 == hand1) {
			System.out.println("Both Dealer and Player hit the same value, no winner");
			game.getPlayer().setChips(game.getPlayer().getChips() + game.getBet().getPlayerChips());
		} else if(hand0 > hand1) {
			System.out.println("The winner is: " + game.getGameDealer().getName());
			System.out.println(game.getGamePlayer().getName() + ", you lost " + game.getBet().getPlayerChips() + " chips.");
		} else {
			System.out.println("The winner is: " + game.getGamePlayer().getName());
			System.out.println(game.getGamePlayer().getName() + ", you won " + game.getBet().getPlayerChips() + " chips.");
		}
	}
	
	private static int openHand(Gamer gamer) {
		if(gamer.getHandSum1() == gamer.getHandSum2()) {
			System.out.println(gamer.getName() + ": " + gamer.getHandSum1());
			return gamer.getHandSum1();
		} else {
			if(Math.abs(21-gamer.getHandSum1()) < Math.abs(21-gamer.getHandSum2())) {
				System.out.println(gamer.getName() + ": " + gamer.getHandSum1());
				return gamer.getHandSum1();
			} else {
				System.out.println(gamer.getName() + ": " + gamer.getHandSum2());
				return gamer.getHandSum2();
			}
		}
	}
	
	private static void calculateHandSums(Gamer gamer) {
		gamer.setHandSum1(gamer.getHandSum1()+gamer.getHand().get(gamer.getHand().size()-1).getCardValue());
		if(gamer.getHand().get(gamer.getHand().size()-1).getFace().equals(Face.Ace)) {
			gamer.setHandSum2(gamer.getHandSum2()+gamer.getHand().get(gamer.getHand().size()-1).getCardValue()+10);
		} else {
			gamer.setHandSum2(gamer.getHandSum2()+gamer.getHand().get(gamer.getHand().size()-1).getCardValue());
		}
	}
	
	private static void relieveDealerHand(Dealer gameDealer) {
		System.out.println(gameDealer.getName() + " has hit to " + gameDealer.getHandSum1() + " or " + gameDealer.getHandSum2());
		System.out.println("The cards are: "); 
		for(Card card : gameDealer.getHand()) {
			System.out.println(card.getSuit() + " " + card.getFace());
		}
	}
	
	private static void hitOrStand(Gamer gamer) {
		String message;
		if(gamer instanceof Dealer)
			if(gamer.getHandSum1()<17 && gamer.getHandSum2()<17) {
				System.out.println("Dealer has to hit.");
				gamer.setHit(true);
				gamer.setStand(false);
				return;
			}
			else {
				message = "Dealer, will you hit or stand?(h/s)";
			}
		else 
			message = "Player, will you hit or stand?(h/s)";
		String answer;
		do {
			System.out.println(message);
			answer = scan.next();
		} while(!"h".equals(answer) && !"s".equals(answer));		
		if("s".equals(answer)) {
			gamer.setHit(false);
			gamer.setStand(true);
		} else {
			gamer.setHit(true);
			gamer.setStand(false);
		}
	}
}
