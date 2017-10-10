package com.multithreaded.workprocessor.queuedatastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.multithreaded.workprocessor.workItem.WorkItemInterface;
/**
 * This class is responsible for following actions : 
 * a. Initialization of dataStructure 
 * b. Provision correct synchronization mechanism to make sure that access to shared location is seralized access.
 * c. Allowing adding task and removing task from dataStructure in Seralized manner to make sure data is consistent.   
 * @author anandsoni
 *
 */
public class SharedDataStructure {


    public List < WorkItemInterface > workToBeProcessed;
    private final int MAX_SIZE = 50;
    
    /**
     * This method is responsible for added WorkItem to the DataStructure.Once task is submitted,it calls  
     * NotifyAll , to make sure that all threads waiting on this datatStructure try pick the submitted task.
     * If MAX_SIZE is reached and more task are submitted then it returns false to the calling thread to 
     * allow the submitting thread to action accordingly.
     * 
     * @param workItem
     * @return
     */

    public boolean addItem(WorkItemInterface workItem) {

        synchronized(workToBeProcessed) {
        	System.out.println("Found a request to add value in dataStructure");
            if (workToBeProcessed.size() <= MAX_SIZE) {
            	
                workToBeProcessed.add(workItem);
                workToBeProcessed.notifyAll();
                System.out.println("Added in dataSture and Notified all waiting thread");
            }else {
            	System.out.println("DataStructre is full, cannot add more element to it");
            	return false;
            }
        }
        return true;
    }

    public SharedDataStructure() {
        workToBeProcessed = new ArrayList < WorkItemInterface > ();
    }
    /**
     * This method will returned  the workItem removed  to this dataStructure. 
     * If there are no more Items to be removed then this dataStructue will return null , what should be the next action 
     * should be decided by the thread calling to pickup task.  
     * @return
     */

    public WorkItemInterface removeItem() {

        synchronized(workToBeProcessed) {
            Iterator < WorkItemInterface > itr = workToBeProcessed.iterator();
            if (itr.hasNext()) {
                WorkItemInterface workItemInterface= itr.next();
                workToBeProcessed.remove(workItemInterface);
                return workItemInterface;
            } else {
            	System.out.println("No Item to be processed,hence returning null");
                return null;
            }

        }
    }

}