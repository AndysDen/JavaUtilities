package com.multithreaded.workprocessor;

import com.multithreaded.workprocessor.queuedatastructure.SharedDataStructure;
import com.multithreaded.workprocessor.workItem.WorkItemInterface;
/**
 * This class is core of processing ,Following are features of this class : 
 * a.Configurable length of ArrayOfThread at the runtime. 
 * b. Inner class which implements Runnable interface,this class is responsible checking the centralized 
 *    dataStructre and pick up the Item as they arrived. 
 * c. SharedDataStrure,this is the shared resource  between threads.   
 * 
 * Following is the responsibility of this class : 
 * a. Initalize the pool of threads responsible for processing the task. 
 * b. If there is no task left for processing then thread will wait on dataStruce for the new task to be submitted
 * c. While initilizing the threads I have purposefully added a delay of three seconds.  [If you dont need you could remove it]
 * 
 * @author anandsoni
 *
 */

public class WorkProcesser {

	Thread arrThread[];
	SharedDataStructure sharedDataStructure;
	
	public WorkProcesser(int numberOfThread,SharedDataStructure sharedDataStructure){
		arrThread = new Thread[numberOfThread];
		this.sharedDataStructure=sharedDataStructure;
		 initalizeThreads();
	}

    private void initalizeThreads() {
    	try {
		for(int i=0;i< arrThread.length;i++){
			arrThread[i]= new Thread(new processorThread(sharedDataStructure));
			arrThread[i].start();
				Thread.currentThread().sleep(3000);
				
			} 
    	}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	class processorThread implements Runnable {

        SharedDataStructure dataStructure;

        public processorThread(SharedDataStructure queue) {
            dataStructure = queue;
        }

        public void run() {
            try {
            	System.out.println("Started The Thread"+Thread.currentThread().getName());
                while (true) {
                    WorkItemInterface workItemInterface = dataStructure.removeItem();
                    if (workItemInterface == null) {
                        System.out.println(Thread.currentThread().getName() + "  ::: Waiting for more workItem");
                        synchronized (dataStructure.workToBeProcessed) {
                        	dataStructure.workToBeProcessed.wait();	
						}
                        
                    } else {
                        workItemInterface.execute();
                    }
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //dataStructure

    }
}