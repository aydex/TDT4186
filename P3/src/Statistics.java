/**
 * This class contains a lot of public variables that can be updated
 * by other classes during a simulation, to collect information about
 * the run.
 */
public class Statistics
{
	/** The number of processes that have exited the system */
	public long nofCompletedProcesses = 0;
	/** The number of processes that have entered the system */
	public long nofCreatedProcesses = 0;
	/** The total time that all completed processes have spent waiting for memory */
	public long totalTimeSpentWaitingForMemory = 0;
	/** The time-weighted length of the memory queue, divide this number by the total time to get average queue length */
	public long memoryQueueLengthTime = 0;
	/** The largest memory queue length that has occured */
	public long memoryQueueLargestLength = 0;


    /** Variables not from skeleton-code */
    public long nofProcessSwitches = 0;
    public long nofProcessedIO = 0;
    public float avgThroughput = 0;
    public long totalProcessingTime = 0;
    public long fracOfCPUTimeProcessing = 0;
    public long totalWaitTime = 0;
    public long fracOfCPUTimeWaiting = 0;
    public long cpuQueueLargestLength = 0;
    public long ioQueueLargestLength = 0;
    public long totalInCPUQueue = 0;
    public long totalInIOQueue = 0;
    public long totalTimeSpentWaitingForCPU = 0;
    public long totalTimeSpentProcessing = 0;
    public long totalTimeSpentWaitingForIO = 0;
    public long totalTimeSpentInIO = 0;

    /**
	 * Prints out a report summarizing all collected data about the simulation.
	 * @param simulationLength	The number of milliseconds that the simulation covered.
	 */
	public void printReport(long simulationLength) {
		System.out.println();
		System.out.println("Simulation statistics:");
		System.out.println();
		System.out.println("Number of completed processes:                                "+nofCompletedProcesses);
		System.out.println("Number of created processes:                                  "+nofCreatedProcesses);
        System.out.println("Number of (forced) process switches:                          "+nofProcessSwitches);
        System.out.println("Number of processed I/O operations:                           "+nofProcessedIO);
        System.out.println("Average throughput (processes per second):                    "+avgThroughput);
        System.out.println();
        System.out.println("Total CPU time spent processing:                              "+totalProcessingTime);
        System.out.println("Fraction of CPU time spent processing:                        "+fracOfCPUTimeProcessing);
        System.out.println("Total CPU time spent waiting:                                 "+totalWaitTime);
        System.out.println("Fraction of CPU time spent waiting:                           "+fracOfCPUTimeWaiting);
        System.out.println();
		System.out.println("Largest occurring memory queue length:                         "+memoryQueueLargestLength);
		System.out.println("Average memory queue length:                                   "+(float)memoryQueueLengthTime/simulationLength);
        System.out.println("Largest occurring CPU queue length:                            "+cpuQueueLargestLength);
        System.out.println("Average CPU queue length:                                      "+(float)cpuQueueLargestLength/simulationLength);
        System.out.println("Largest occurring I/O queue length:                            "+ioQueueLargestLength);
        System.out.println("Average I/O queue length:                                      "+(float)ioQueueLargestLength/simulationLength);
		if(nofCompletedProcesses > 0) {
			System.out.println("Average # of times a process has been placed in memory queue: "+1);
            System.out.println("Average # of times a process has been placed in CPU queue: "+(float)totalInCPUQueue/nofCreatedProcesses);
            System.out.println("Average # of times a process has been placed in I/O queue: "+(float)totalInIOQueue/nofCreatedProcesses);
            System.out.println();
            System.out.println("Average time spent in system per process:                  "+
                    (totalTimeSpentWaitingForMemory+
                            totalTimeSpentWaitingForCPU+
                            totalTimeSpentProcessing+
                            totalTimeSpentWaitingForIO+
                            totalTimeSpentInIO) /
                    nofCompletedProcesses);
            System.out.println("Average time spent waiting for memory per process:            "+
				totalTimeSpentWaitingForMemory/nofCompletedProcesses+" ms");
            System.out.println("Average time spent waiting for CPU per process:            "+
                totalTimeSpentWaitingForCPU/nofCompletedProcesses);
            System.out.println("Average time spent processing per process:                 "+
                    totalTimeSpentProcessing/nofCompletedProcesses);
            System.out.println("Average time spent waiting for I/O per process:            "+
                    totalTimeSpentWaitingForIO/nofCompletedProcesses);
            System.out.println("Average time spent in I/O per process:                     "+
                    totalTimeSpentInIO/nofCompletedProcesses);
        }
	}
}
