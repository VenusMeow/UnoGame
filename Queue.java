/**
 * This is an generic array implementation of the data structure queue.
 * @author Venus Sun
 * email: venusyixinsun@brandeis.edu
 */
public class Queue<T> {
	private int rear; //pointer for the rear end of the queue
	private int front; //pointer for the front end of the queue
	private T[] queue; //array that stores the data input to the queue
	
	/**
	 * Constructor of a queue data structure using array of size input
	 * @param size the size of the array used to store values for the queue
	 */
	public Queue(int size) {
		queue = (T[]) new Object[size];
		this.rear = 0; //pointer for the rear end
		this.front = 0; //pointer for the front end
	}
	
	/**
	 * enqueue adds an element to the rear end of the queue
	 * @param data the generic type of data to be put in the queue
	 * running time: O(1)
	 */
	public void enqueue(T data) {
		queue[rear] = data;
		if (rear==queue.length) {
			rear = 0; //the queue wraps around and recycles spaces in the array to store data
		} else {
			rear += 1;
		}
	}
	
	/**
	 * dequeue removes an element from the front end of the queue
	 * @return the data stored in the queue which are being removed
	 * running time: O(1)
	 */
	public T dequeue() {
		T output = queue[front];
		if (front==queue.length) {
			front = 0; //the queue wraps around and recycles spaces in the array to store data
		} else {
			front += 1;
		}
		return output;
	}
	
	/**
	 * getSize return the amount of data stored in the queue
	 * @return the number count of data stored
	 * running time: O(1)
	 */
	public int getSize() {
		return Math.abs(this.rear-this.front); //the difference between rear and front yields how many elements are in the queue
	}
	
	/**
	 * isEmpty tests if a queue is empty
	 * @return true if the queue is empty and false if not
	 * running time: O(1)
	 */
	public boolean isEmpty() {
		return this.rear==this.front; //if rear and front indicate the same space, the queue is empty
	}
	
	/**
	 * isFull tests if a queue is full
	 * @return true if the queue is full and false if not
	 * running time: O(1)
	 */
	public boolean isFull() {
		if (queue.length==0) {
			return false; //if the queue is initialized with an empty array it can never be full
		} else {
			return this.getSize()==queue.length; //if the number of data stored equals the total length of the array the queue is full
		}	
	}
	
}
