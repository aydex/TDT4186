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

    public boolean insertProcess(Process process, long clock, Gui gui) {
        if(cpuQueue.isEmpty() && activeProcess==null){
            activeProcess = process;
            gui.setCpuActive(activeProcess);
            return true;
        }
        cpuQueue.insert(process);
        process.addedCpuQueue(clock);
        return false;
    }


    /**
     * Sets a new active process
     * @return
     */
    public Process updateActive(long clock, Gui gui) {
        if(cpuQueue.isEmpty()){
            return null;
        }
        //Should it be removeNext or getNext? (Odd)
        activeProcess = (Process)cpuQueue.removeNext();
        activeProcess.leftCpuQueue(clock);
        gui.setCpuActive(activeProcess);
        return activeProcess;
    }
}
