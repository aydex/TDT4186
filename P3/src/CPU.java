/**
 * Created by Martin on 17/04/2016.
 */
public class CPU {
    
    private Process activeProcess;

    public Queue getCpuQueue() {
        return cpuQueue;
    }

    private Queue cpuQueue;
    Statistics statistics;

    public CPU(Queue cpuQueue, Statistics statistics) {
        this.cpuQueue = cpuQueue;
        this.statistics = statistics;
    }

    public void largestQueueLength() {
        if (cpuQueue.getQueueLength() > statistics.cpuQueueLargestLength) {
            statistics.cpuQueueLargestLength = cpuQueue.getQueueLength();
        }
    }

    public Process returnActiveProcess() {
        return activeProcess;
    }

    public boolean insertProcess(Process process, long clock, Gui gui) {
        if(cpuQueue.isEmpty() && activeProcess==null){
            activeProcess = process;
            gui.setCpuActive(activeProcess);
            return true;
        }
        cpuQueue.insert(process);
        statistics.totalInCPUQueue ++;
        process.addedCpuQueue(clock);
        return false;
    }


    /**
     * Sets a new active process
     * @return
     */
    public Process updateActive(long clock, Gui gui) {
        if(cpuQueue.isEmpty()){
            activeProcess = null;
            return null;
        }
        //Should it be removeNext or getNext? (Odd)
        activeProcess = (Process)cpuQueue.removeNext();
        activeProcess.leftCpuQueue(clock);
        gui.setCpuActive(activeProcess);
        return activeProcess;
    }
}
