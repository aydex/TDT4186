import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {

	private Gui gui;

	//Variables for managing the queue
	protected int start;
	protected int end;
	private Customer[] queue;
	private int length;

	//Condition variables used to implement waiting on conditions in Barber and Doorman threads
	private final Lock lock = new ReentrantLock();
	public final Condition notFull = lock.newCondition();
	public final Condition notEmpty = lock.newCondition();


	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
		// Incomplete
		queue = new Customer[queueLength];
		start = 0;
		end = 0;
		this.gui = gui;
		this.length = queueLength;
	}

	/**
	 * Tests if the queue is empty.
	 * @return true if queue empty
	 */
	public boolean empty(){
		return (start == end) && queue[start] == null;
	}

	/**
	 * Tests if the queue is full
	 * @return true if queue full
	 */
	public boolean full(){
		return ((start == end) && queue[start] != null) || (start == 0 && end == length - 1);
	}

	/**
	 * Removes and returns the customer currently in the front of the queue
	 * @return Customer
	 */
	public Customer takeFirst(){
		Customer c = queue[start];
		queue[start] = null;
		start += 1;
		if(start >= length){
			start = 0;
		}
		return c;
	}

	/**
	 * Appends the given customer to the end of the queue
	 * @param c A new customer
	 */
	public void append(Customer c){
		queue[end] = c;
		end += 1;
		if(end >= length){
			end = 0;
		}
	}

}
