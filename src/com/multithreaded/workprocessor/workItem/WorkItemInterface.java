package com.multithreaded.workprocessor.workItem;
/*
 * This is a template reflecting what should be the structure of Item to be submitted
 * For any workItem to be processed by framework the workItem need to Implement this behavior.
 * 
 *  Any workItem will have 3 Status : 
 *  a. NotForProcessing. 
 *  b. InProcess
 *  c. FinishedProcessing
 *  
 * Below explains how this class is used. *************************************************** 
 * a. WorkItemInterface will be submitted to the dataStructure. 
 * b. Multiple threads would be checking for newly submitted task in dataStructure.
 * c. When a Thread process the task it changes it status to In processing. 
 * d. When a thread finished processing it changes its status to Finished processing.  
 * 
 * *******************************************************************************************
 */
public interface WorkItemInterface {
	/**
	 * Execute method will be called by the thread which picks up the workIem to process. 
	 * As the thread picks up the item for processing,it will check the status of the WorkItem. 
	 * 
	 *  
	 */
	public void execute();

}
