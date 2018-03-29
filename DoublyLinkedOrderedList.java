/**
 * This is a generic implementation of the data structure doubly linked ordered list.
 * @author Venus Sun
 * email: venusyixinsun@brandeis.edu
 */
public class DoublyLinkedOrderedList<T extends Comparable<T>> {
	private DoublyLinkedNode<T> head; //pointer for the head of the list
	private DoublyLinkedNode<T> position; //pointer for some current position in the list
	private DoublyLinkedNode<T> temp; //pointer for some position in the list, used for convenience
	private DoublyLinkedNode<T> newNode; //pointer for a newNode to be added in the list
	private int size; //the size of the list
	
	/**
	 * constructor of a new list
	 */
	public DoublyLinkedOrderedList() {
		this.head = new DoublyLinkedNode<T>(null); //initializes the head which holds no data
		head.setPrevious(null); //initializes the head to point NIL on the previous direction
		this.size = 0; //initializes the list as 0 size
	}

	/**
	 * getHead return the first element in the list
	 * @return the first element in the list or null if it's an empty list
	 * running time: O(1)
	 */
	public DoublyLinkedNode<T> getHead() {
		return this.head.getNext(); //head is not the first element in the list, head points to the first element in the list
	}
	
	/**
	 * insert inserts an element at the specific point where the element should be put since this is an ordered list
	 * what "ordered" means: e.g. for a numeric value, "ordered" means from small to big; for a word, "ordered" means from a to z as regards to the first character
	 * @param data the element to be inserted
	 * running time: O(n) whereas n indicates the size of the list
	 */
	public void insert(T data) {
		newNode = new DoublyLinkedNode<T>(data); //construct a new node for the data
		position = head.getNext(); //starts from the beginning of the list
		temp = head; //keeps track of the previous node 
		
		if (position==null) {
			head.setNext(newNode);
			newNode.setPrevious(head);
			//if the list is empty in the previous version, simply add the data as the first element in the list
		} else {
			while (position!=null&&position.getData().compareTo(data)<0) {
				//traverse the list and find the largest element smaller than the new data
				temp = position; //the largest element smaller than the new data
				position = position.getNext(); //the smallest element bigger or equal to the new data	
			}
			newNode.setNext(temp.getNext()); //set the "next" pointer of the new node to the next element
			if (newNode.getNext()!=null) {
				newNode.getNext().setPrevious(newNode);
				//if the new node is not added at the end, set the next code's "previous" pointer to the new node
			}
			temp.setNext(newNode); //set the previous node's "next" pointer to the new node
			newNode.setPrevious(temp); //set the new node's "previous" pointer to the previous node
		}
		size += 1; //increment the size of the list
	}
	
	/**
	 * delete removes a specific element from the list and returns it
	 * null is returned and no changes is done to the list if the element is not in the list
	 * @param data the specific element to be removed
	 * @return the specific element being removed
	 * running time: O(n) whereas n indicates the size of the list
	 */
	public DoublyLinkedNode<T> delete(T data){
		position = head.getNext(); //start from the first element in the list
		while (position!=null && position.getData()!=data) {
			position = position.getNext();
			//traverse the list and try to find the element to be removed
		}
		if (position!=null) {
			//only remove the element when it's found
			//if it's not found, position would become null in the last while loop while traversing
			//thus a double check on position ensures it only remove when the element is found
			if (position.getPrev()!=head) {
				position.getPrev().setNext(position.getNext());
				//if the found element is not the first element of the list, remove it by setting the previous node points to the next node
			} else {
				head.setNext(position.getNext());
				//if the found element is the first element of the list, remove it by setting the head to the next node
			}
			if (position.getNext()!=null) {
				position.getNext().setPrevious(position.getPrev());
				//if the found element is not the last element of the list, also set the next node points to the previous node
			}
			size -= 1; //decrement the size;
		}
		return position;
	}
	
	/**
	 * getSize returns the size of the list
	 * @return the number of elements in the list
	 * running time: O(1) 
	 */
	public int getSize() {
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
