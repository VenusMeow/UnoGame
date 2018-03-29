public class Player {
	private String name;
	private Player nextPlayer=null;
	private Player prevPlayer=null;
	SinglyLinkedList<UnoCard> hand; 
	private SinglyLinkedNode<UnoCard> position;
	
	public Player(String name){
		this.name = name;
		hand = new SinglyLinkedList<>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addToHand(UnoCard c){
		this.hand.regularInsert(c);
	}
	
	public UnoCard removeFromHand(int index){
		position = this.hand.getHead();
		for (int i=1;i<index;i++) {
			position = position.getNext();
		}
		this.hand.remove(position.getData());
		return position.getData();
	}
	
	

	public boolean winner(){
		boolean result = false;
		if (this.hand.size()==0) {
			result = true;
		}
		return result;
	}

	Player getNextPlayer() {
		return nextPlayer;
	}

	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	public Player getPrevPlayer() {
		return prevPlayer;
	}

	public void setPrevPlayer(Player prevPlayer) {
		this.prevPlayer = prevPlayer;
	}

	public String toString() {
		return "Player " + name + " has these cards: " + hand;
	}
	
	
}
