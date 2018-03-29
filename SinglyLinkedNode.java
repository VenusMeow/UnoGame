/**
 * This is a generic implementation of the node used for the data structure singly linked list.
 * @author Venus Sun
 * email: venusyixinsun@brandeis.edu
 */
public class SinglyLinkedNode<T> {
	private T data; //field whereas the key(data) for the node is stored
	private SinglyLinkedNode<T> next; //pointer for the next node when in a list
	
	/**
	 * constructor that creates a node object which stores some data as key
	 * @param data the data to be stored in the node
	 */
	public SinglyLinkedNode(T data) {
		this.data = data;
	}
	
	/**
	 * getData returns the key stored in the node
	 * @return the data stored in the node
	 * running time: O(1)
	 */
	public T getData() {
		return this.data;
	}
	
	/**
	 * setNext sets the "next" pointer of a node to the next node in a list 
	 * @param next the next node in the list to be pointed by
	 * running time: O(1)
	 */
	public void setNext(SinglyLinkedNode<T> next) {
		this.next = next;
	}
	
	/**
	 * getNext returns the next node in a list whereas pointed by the "next" pointer
	 * @return the next node in the list pointed by the pointer
	 * running time: O(1)
	 */
	public SinglyLinkedNode<T> getNext() {
		return this.next;
	}
	
	/**
	 * toString prints out the data stored in the node as a String
	 * @return the String representation of the data stored 
	 * running time: O(1)
	 */
	public String toString() {
		return (String)this.data;
	}
}
