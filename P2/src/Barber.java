/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread {

	private CustomerQueue queue;
	private Gui gui;
	private int pos;

	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	public Barber(CustomerQueue queue, Gui gui, int pos) {
		this.queue = queue;
		this.gui = gui;
		this.pos = pos;
	}

	/**
	 * Start the barber as a separate thread.
	 */
	public void startThread() {
		this.start();
	}

	/**
	 * Stop the barber thread.
	 */
	public void stopThread() {
		Thread.currentThread().stop();
	}

	/**
	 * Run() function of Barber thread. Takes customers from the customer queue as long as the queue is not empty.
	 * If the queue is empty suspend thread until notified. After taking a customer notify the Doorman thread.
	 * Also calls various GUI operations.
	 */
	@Override
	public void run() {
		try{
			while(true){
				gui.barberIsAwake(pos);
				//Synchronized on the condition variable notEmpty in queue. If queue is empty wait until notified
				//through notEmpty variable. If queue not empty take first customer from queue.
				synchronized (queue.notEmpty){
					while(queue.empty()){
						queue.notEmpty.wait();
					}
					gui.emptyLoungeChair(queue.start);
					Customer c = queue.takeFirst();
					gui.fillBarberChair(pos, c);
				}
				//Notify a thread (Doorman) waiting on the condition variable notFull.
				synchronized (queue.notFull){
					queue.notFull.notify();
				}
				//Perform barber job and sleep before attempting to take new customer.
				Thread.sleep(Globals.barberWork);
				gui.emptyBarberChair(pos);
				gui.barberIsSleeping(pos);
				Thread.sleep(Globals.barberSleep);
			}
		}
		catch (InterruptedException e){
			System.out.println("Error" + e);
		}
	}
}

