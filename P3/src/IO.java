/**
 * Created by Martin on 17/04/2016.
 */
public class IO {


    /** The queue of processes waiting for IO */
    private Queue ioQueue;
    /** A reference to the statistics collector */
    private Statistics statistics;

    private Process activeProcess;

    /**
     * Creates a new IO device with the given parameters.
     * @param ioQueue	The io queue to be used.
     * @param statistics	A reference to the statistics collector.
     */
    public IO(Queue ioQueue, Statistics statistics) {
        this.ioQueue = ioQueue;
        this.statistics = statistics;
    }



    /**
     * Adds a process to the io queue. If queue is empty, activate the process.
     * @param p
     * @return if process is now active
     */
    public boolean insertProcess(Process p, long clock, Gui gui) {
        if(ioQueue.isEmpty() && activeProcess==null){
            activeProcess = p;
            gui.setIoActive(activeProcess);
            return true;
        }
        else{
            ioQueue.insert(p);
            p.addedIoQueue(clock);
            return false;
        }

    }



    /**
     * This method is called when a discrete amount of time has passed.
     * @param timePassed	The amount of time that has passed since the last call to this method.
     */
    public void timePassed(long timePassed) {
        statistics.memoryQueueLengthTime += ioQueue.getQueueLength()*timePassed;
        if (ioQueue.getQueueLength() > statistics.ioQueueLargestLength) {
            statistics.ioQueueLargestLength = ioQueue.getQueueLength();
        }
    }

    /**
     * Returns the current active process, changes to a new process if the queue is not empty.
     * @return an array with the activeProcess, and the new active process (may be null)
     */
    public Process[] returnActiveProcess(long clock, Gui gui) {
        Process returnProcess = activeProcess;
        returnProcess.leftIo(clock);
        activeProcess = null;
        if(!ioQueue.isEmpty()){
            activeProcess = (Process)ioQueue.removeNext();
            activeProcess.leftIoQueue(clock);
            gui.setIoActive(activeProcess);
        }
        Process[] returnArray = {returnProcess,activeProcess};
        return returnArray;
    }
}
