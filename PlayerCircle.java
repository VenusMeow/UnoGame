
public class PlayerCircle {
	private Player firstPlayer;
	private Player position;
	private int size=0;
	
	public void addToCircle(Player p) {
		if (size==0) {
			firstPlayer = p;
			p.setNextPlayer(p);
			p.setPrevPlayer(p);
		} else {
			position = firstPlayer;
			int count = 0;
			while (count<size&&position.getName().compareTo(p.getName())<0) {		
				position = position.getNextPlayer();
				count++;
			}
			position.getPrevPlayer().setNextPlayer(p);
			p.setPrevPlayer(position.getPrevPlayer());
			position.setPrevPlayer(p);
			p.setNextPlayer(position);
			if (count==0) {
				firstPlayer = p;
			}
		}	
		size+=1;
	}
	
	public void removePlayer(Player p) {
		position = firstPlayer;
		while (position!=p) {
			position = position.getNextPlayer();
		}
		position.getPrevPlayer().setNextPlayer(position.getNextPlayer());
		position.getNextPlayer().setPrevPlayer(position.getPrevPlayer());
		position.setNextPlayer(null);
		position.setPrevPlayer(null);
		size-=1;
	}
	
	public Player getFirstPlayer() {
		return firstPlayer;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		String presentation = "Circle: [";
		position = firstPlayer; 
		for (int i=0; i<this.size; i++) {
			presentation += position.getName(); 
			position = position.getNextPlayer();
			if (i<this.size-1) {
				presentation += ","; 
			}	
		}
		presentation += "]";
		return presentation;
	}
}
