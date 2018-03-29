import java.util.Scanner;

public class UnoGame {
	static Scanner input = new Scanner(System.in);
	
	static int numPlayer = 0;
	static Player newPlayer = new Player(null);
	static PlayerCircle circle = new PlayerCircle();
	static Queue<Player> waiting = new Queue<>(1000);
	
	static boolean continueGame = true;
	static int gameCount=0;
	static Player loser = new Player(null);
	
	static Player thisPlayer = new Player(null);
	
	
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Uno game!");
		initiate();
		while (continueGame) {
			playOneGame();
		}		
		System.out.printf("%d games have been played. See you next time!%n", gameCount);
		
	}
	
	
	public static void initiate() {
	
		System.out.println("How many players do you have?");
		numPlayer = input.nextInt();
		waiting = new Queue<>(numPlayer);
				
		for (int i=0;i<numPlayer;i++) {
			System.out.printf("Enter the name for player #%d: ",i+1);
			String name = input.next();
			newPlayer = new Player(name);
			if (i<5) {
				circle.addToCircle(newPlayer);
			} else {
				waiting.enqueue(newPlayer);
			}
		}
	}
	
	public static void playOneGame() {
		if (numPlayer>5 && gameCount>0) {
			circle.removePlayer(loser);
			waiting.enqueue(loser);
			newPlayer = waiting.dequeue();
			circle.addToCircle(newPlayer);	
		}
		UnoDeck game = new UnoDeck();
		
		thisPlayer = circle.getFirstPlayer();
		
		for(int i=0;i<circle.getSize();i++) {
			for (int j=0;j<7;j++) {
				thisPlayer.addToHand(game.drawCard());
			}
			//System.out.printf("#%d %s %n",i+1,thisPlayer);
			thisPlayer = thisPlayer.getNextPlayer();
		}
		
		System.out.println("Ready? Start!");
		int moveCount = 0;
		boolean isReversed = false;
		boolean changed = false;
		boolean isSkipped = false;
		boolean isDrawn2 = false;	
		boolean isDrawn4 = false;
		
		game.discardCard(game.drawCard());
		
		Player playPlayer = circle.getFirstPlayer();
		int direction = 1;
		
		while(!playPlayer.winner()) {
	
			System.out.printf("Last drawn card is: %s%n",game.getLastDiscarded());
			
			if (game.getLastDiscarded().isSpecial()) {
				if (game.getLastDiscarded().isDrawTwo()&&isDrawn2==false) {
					for (int i=0;i<2;i++) {
						playPlayer.addToHand(game.drawCard());
					}
					System.out.printf("2 cards have been drawn to %s%n",playPlayer.getName());
					isDrawn2 = true;
				} else if (game.getLastDiscarded().isWildDrawFour()&&isDrawn4==false){
					for (int i=0;i<4;i++) {
						playPlayer.addToHand(game.drawCard());
					} 
					System.out.printf("4 cards have been drawn to %s%n",playPlayer.getName());
					isDrawn4 = true;
				} else if (game.getLastDiscarded().isSkip()&&isSkipped==false) {
					if (direction>0) {
						playPlayer = playPlayer.getNextPlayer();
					} else {
						playPlayer = playPlayer.getPrevPlayer();
					}
					System.out.printf("Please change to %s%n",playPlayer.getName());
					isSkipped = true;
				} else if (game.getLastDiscarded().isReverse()&&isReversed==false) {
					if (direction>0) {
						playPlayer = playPlayer.getPrevPlayer().getPrevPlayer();
					} else {
						playPlayer = playPlayer.getNextPlayer().getNextPlayer();
					}
					direction = (-1)*direction;
					System.out.printf("Please change to %s%n",playPlayer.getName());
					isReversed = true;
				} 
			} 
			
			if (changed==true) {
				isReversed = false;
				changed = false;
				isSkipped = false;
				isDrawn2 = false;	
				isDrawn4 = false;
			}
			
			String avaCards = "";
			int index = 0;
			SinglyLinkedNode<UnoCard> currentCard = playPlayer.hand.getHead();
			while (currentCard!=null) {
				index+=1;
				UnoCard cardValue = currentCard.getData();
				if (cardValue.canBePlacedOn(game.getLastDiscarded())) {
					avaCards += ("["+index+"] "+cardValue+", ");
				}
				currentCard = currentCard.getNext();
			}
			
			if (avaCards!="") {
				if (game.getLastDiscarded().isSpecial()) {
					changed = true;
				} 
				System.out.printf("%s, you have these cards avaliable for discard: %n",playPlayer.getName());
				System.out.println(avaCards+"which one would you like to discard? Enter the index before the card: ");
				int draw = input.nextInt();
				game.discardCard(playPlayer.removeFromHand(draw));
			} else {
				playPlayer.addToHand(game.drawCard());
				System.out.printf("1 card has been drawn to %s%n",playPlayer.getName());
			}	
			
			moveCount++;
			
			if (playPlayer.winner()) {
				gameCount++;
				break;	
			}
			
			System.out.printf("%s, you have %d cards left!%n",playPlayer.getName(),playPlayer.hand.size());
			
			if (direction>0) {
				playPlayer = playPlayer.getNextPlayer();
			} else {
				playPlayer = playPlayer.getPrevPlayer();
			}	
			System.out.printf("Please change to %s%n%n",playPlayer.getName());
		}
		
		int max = -1;
		thisPlayer = circle.getFirstPlayer();
		for (int i=0;i<circle.getSize();i++) {
			if (thisPlayer.hand.size()>max) {
				max = thisPlayer.hand.size();
				loser = thisPlayer;
			}
			thisPlayer = thisPlayer.getNextPlayer();
		}
		
		int circles = (int)Math.floor(moveCount/(circle.getSize()));
		System.out.printf("Winner is %s.%n",playPlayer.getName());
		System.out.printf("Loser is %s, who has %d cards left.%n", loser.getName(),loser.hand.size());
		System.out.printf("The game has gone through the circle for %d times.%n", circles);
		
		System.out.println("Continue to next game? (true or false)");
		continueGame = input.nextBoolean();
	}
	

	
	
}
