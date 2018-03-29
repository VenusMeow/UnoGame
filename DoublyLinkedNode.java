/**
 * This is a generic implementation of the node used for the data structure doubly linked ordered list.
 * @author Venus Sun
 * email: venusyixinsun@brandeis.edu
 */
public class DoublyLinkedNode<T extends Comparable<T>> {
	private T data; //field whereas the key(data) for the node is stored
	private DoublyLinkedNode<T> next; //pointer for the next node when in a list
	private DoublyLinkedNode<T> prev; //pointer for the previous node when in a list
	
	/**
	 * constructor that creates a node object which stores some data as key
	 * @param data the data to be stored in the node
	 */
	public DoublyLinkedNode(T data) {
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
	public void setNext(DoublyLinkedNode<T> nextNode) {
		this.next = nextNode;
	}
	
	/**
	 * setPrevious sets the "previous" pointer of a node to the previous node in a list 
	 * @param prev the previous node in the list to be pointed by
	 * running time: O(1)
	 */
	public void setPrevious(DoublyLinkedNode<T> prevNode) {
		this.prev = prevNode;
	}
	
	/**
	 * getNext returns the next node in a list whereas pointed by the "next" pointer
	 * @return the next node in the list pointed by the pointer
	 * running time: O(1)
	 */
	public DoublyLinkedNode<T> getNext(){
		return this.next;
	}
	
	/**
	 * getPrev returns the previous node in a list whereas pointed by the "previous" pointer
	 * @return the previous node in the list pointed by the pointer
	 * running time: O(1)
	 */
	public DoublyLinkedNode<T> getPrev(){
		return this.prev;
	}
	
	/**
	 * toString prints out the data stored in the node as a String
	 * @return the String representation of the data stored 
	 * running time: O(1)
	 */
	public String toString() {
		return (String) this.data;
	}
}

