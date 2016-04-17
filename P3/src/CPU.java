/**
 * Created by Martin on 17/04/2016.
 */
public class CPU {
    
    private Process activeProcess;
    private Queue cpuQueue;
    Statistics statistics;

    public CPU(Queue cpuQueue, Statistics statistics) {
        this.cpuQueue = cpuQueue;
        this.statistics = statistics;
    }

    public Process returnActiveProcess() {
        return activeProcess;
    }

    public boolean insertProcess(Process process) {
        if(cpuQueue.isEmpty()){
            activeProcess = process;
            return true;
        }
        cpuQueue.insert(process);
        return false;
    }


    /**
     * Sets a new active process
     * @return
     */
    public Process updateActive() {
        if(cpuQueue.isEmpty()){
            return null;
        }
        activeProcess = (Process)cpuQueue.getNext();
        return activeProcess;
    }
}
