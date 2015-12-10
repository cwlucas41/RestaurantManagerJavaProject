package com.chriswlucas.restaurant;

import java.sql.Time;
import java.util.List;
import java.util.ListIterator;

class Job implements Comparable<Job>{
	/**
	 * Holds a list of items/tables that need to be produced,
	 * served, paid, or cleaned.
	 * @param inItems list of items to be produced or tables that need to be taken care of.
	 * @param aType type of job. 
	 * @param jobManager reference back to the containing class.
	 */
	public Job(List<Object>inItems, int aType, JobManager jobManager){
		this.jobManager = jobManager;
		this.items= (List<Object>)inItems;
		this.type=aType;
		this.current=new Time(0);
	}
	
	/**
	 * If the job is a producing job it needs to be changed over to a serving job.
	 * If the job is a collecting job(payment) it needs to be changed over to a bussing job.
	 */
	void markAsDone(){
		switch (type) {
		case 1: jobManager.assignServingJob(this); break; 
		case 3: jobManager.assignBussingJob(this); break; 
		case 4: this.jobManager.getPartyManager().getRestaurant().markTablesByNumberAs(this.jobManager.getPartyManager().getTableNumbers(), false); break;
		default: break;
		}
		
	}
	/**
	 * 
	 * @return a header string for output based on the type of job.
	 */
	private String getHeader(){
		switch (type) {
		case 1: return "Produce the following items:\n";
		case 2: return "Serve the following items:\n";
		case 3: return "Collect from these tables:\n";
		case 4: return "Buss the following tables:\n";
		default: return "Invalid Job type:\n";
		}
	}
	
	/**
	 * Displays the current list of items in the job.
	 */
	public String toString(){
		String temp;
		// make string here and print string in interface
		temp = getHeader();
	
		ListIterator<Object> iterator = items.listIterator();
		while(iterator.hasNext()){
			temp += "\t\t" + iterator.next().toString()+"\n";
		}
		return temp;
	}
	
	/**
	 * 
	 * @return the time this job was created.
	 */
	Time getCurrent(){
		return current;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public int compareTo(Job o) {
		return this.current.compareTo(o.current);
	}
	
	private JobManager jobManager;
	private Time current;
	private int type; //(1 is producing, 2 is serving, 3 is collecting, 4 is bussing)
	private List<Object>items;

}
