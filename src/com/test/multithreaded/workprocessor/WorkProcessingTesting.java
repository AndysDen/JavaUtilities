package com.test.multithreaded.workprocessor;

import com.multithreaded.workprocessor.WorkProcesser;
import com.multithreaded.workprocessor.queuedatastructure.SharedDataStructure;
import com.multithreaded.workprocessor.workItem.WorkItemInterface;

import junit.framework.TestCase;

public class WorkProcessingTesting extends TestCase{
	SharedDataStructure sharedDataStructure;
	WorkProcesser workProcesser ;
	public void setUp(){
		sharedDataStructure = new SharedDataStructure();
		workProcesser = new WorkProcesser(20, sharedDataStructure);
	  	
	}
	
	public void testWorkProcessingFrameWork(){
		
		class WorkItem implements WorkItemInterface{
			
			private String message;
			private boolean hasFinished=false;
			public WorkItem(String message){
				this.message=message;
			}
			public void execute(){
				System.out.println("Executing the workStack"+message);
				hasFinished=true;
				
			}
			
			
		}
		WorkItem workItem[]= new WorkItem[10];
		for(int i=0;i<10;i++){
			workItem[i]= new WorkItem("This is WokItem :::::   "+i);
			System.out.println("WorkItem sucessfully added to queue"+sharedDataStructure.addItem(workItem[i]));
			
		}
		boolean processedAll=false;
		while(!processedAll){
			for(int i=0;i<10;i++){
				if(!workItem[i].hasFinished){
		      break;				
			}else{
				processedAll=false;
				}
			}
			
		}
		
		 
	}

}
