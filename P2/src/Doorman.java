/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread{

	private CustomerQueue queue;
	private Gui gui;

	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) {
		this.queue = queue;
		this.gui = gui;
	}

	/**
	 * Start the doorman as a separate thread.
	 */
	public void startThread() {
		this.start();
	}

	/**
	 * Stop the doorman thread.
	 */
	public void stopThread() {
		Thread.currentThread().stop();
	}

	/**
	 * Run() function of Doorman thread. Creates new customers and appends them to the customer queue as long as
	 * the queue is not full. Notifies all threads waiting on notEmpty after adding a customer.
	 */
	@Override
	public void run() {
		try{
			while(true){
				//Synchronized on the condition variable notFull in queue. If queue is full wait until notified
				//through notFull variable. If queue not full append new customer to queue.
				synchronized (queue.notFull){
					while(queue.full()){
						queue.notFull.wait();
					}
					Customer c = new Customer();
					gui.fillLoungeChair(queue.end, c);
					queue.append(c);
				}
				//Notify all threads waiting on the notEmpty condition variable in queue.
				synchronized (queue.notEmpty){
					queue.notEmpty.notifyAll();
				}
				//Sleep before attempting to add another customer.
				Thread.sleep(Globals.doormanSleep);
			}
		} catch (InterruptedException e) {
			System.out.println("Error" + e);
		}
	}
}
