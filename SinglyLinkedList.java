/**
 * This is a generic implementation of the data structure singly linked list.
 * @author Venus Sun
 * email: venusyixinsun@brandeis.edu
 */
public class SinglyLinkedList<T> {
	private SinglyLinkedNode<T> head; //pointer for the head of the list
	private SinglyLinkedNode<T> tail; //pointer for the last element of the list 
	private SinglyLinkedNode<T> position; //pointer for some current position in the list
	private SinglyLinkedNode<T> prevNode; //pointer to the previous node of some current position in the list
	private SinglyLinkedNode<T> newNode; //pointer for a newNode to be added in the list
	private int size; //the size of the list
	
	/**
	 * constructor of a new list
	 */
	public SinglyLinkedList() {
		this.head = new SinglyLinkedNode<T>(null); //initializes the head which holds no data
		this.tail = new SinglyLinkedNode<T>(null); //initializes the tail which holds no data
		this.tail.setNext(null); //initializes the tail to point NIL
		this.size = 0; //initializes the list as 0 size
	}
	
	/**
	 * getHead return the first element in the list
	 * @return the first element in the list or null if it's an empty list
	 * running time: O(1)
	 */
	public SinglyLinkedNode<T> getHead() {
		return this.head.getNext(); //head is not the first element in the list, head points to the first element in the list
	}
	
	/**
	 * regularInsert inserts an element at the rear end of the list
	 * @param data the element to be inserted
	 * running time: O(1)
	 */
	public void regularInsert(T data) {
		newNode = new SinglyLinkedNode<T>(data); //construct a new node for the data
		if (this.size==0) {
			this.head.setNext(newNode);	//if the list is empty, the new node becomes the first element in the list	
		} 
		newNode.setNext(null); //since we are adding on the rear end, the added node should points NIL
		this.tail.setNext(newNode); //set the last element in the previous version of the list to point to the new node
		this.tail = newNode; //set the added new node to be the last element
		this.size += 1; //increment the size of the list
	}
	
	/**
	 * randomInsert inserts an element at any point in the list, equally likely 
	 * @param data the element to be inserted
	 * running time: O(n) whereas n indicates the size of the list
	 */
	public void randomInsert(T data) {
		newNode = new SinglyLinkedNode<T>(data); //construct a new node for the data
		int random = (int) Math.floor(Math.random() * (this.size+1)); 
		//generate a random position to be inserted
		//this will result in an integer from 0 to the size of the current version of the list
		for (int i=0; i<=random; i++) {	
			if (i==0) {
				position = head;	//if the random number yields 0, the new node will be added at the front
			} else {
				position = position.getNext(); //traverse to the position indicated by random
			}
		}
		newNode.setNext(position.getNext()); 
		position.setNext(newNode); //insert the new node
		if (random==this.size) {
			this.tail = newNode; //if the new node is added at the end, update the pointer which keeps track of last element
		}
		this.size += 1; //increment size
	}
	
	/**
	 * remove removes a specific element from the list, nothing happens if the element is not in the list
	 * @param data the specific element to be removed
	 * running time: O(n) whereas n indicates the size of the list
	 */
	public void remove(T data) {
		position = head.getNext(); //start from the first element in the list
		prevNode = head; //keep track of the previous node
		while (position!=null && position.getData()!=data) { 
			prevNode = position;
			position = position.getNext();
			//traverse the list and try to find the element to be removed
		}
		if (position!=null) {
			prevNode.setNext(position.getNext());
			size -= 1;
			//only remove the element when it's found
			//if it's not found, position would become null in the last while loop while traversing
			//thus a double check on position ensures it only remove when the element is found
		} 
	}
	
	/**
	 * size returns the size of the list
	 * @return the number of elements in the list
	 * running time: O(1) 
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * toString prints out a String presentation of the list in the form: "List: [a,b,c,...]"
	 * @return the String representation of the list 
	 * running time: O(n) whereas n indicates the size of the list
	 */
	public String toString() {
		String presentation = "List: [";
		position = head; //starts from the head
		for (int i=0; i<this.size; i++) {
			position = position.getNext();
			presentation += position.getData(); //traverse and adds each element to the string presentation
			if (i<this.size-1) {
				presentation += ","; //the last element will not need to be followed by a comma
			}	
		}
		presentation += "]";
		return presentation;
	}
}
