package Scheduler;

import java.io.Serializable;
import java.util.LinkedList;

public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;
	private String user;
	private LinkedList<Interval> slots = new LinkedList<>();
	private Space associatedSpace = null;
	private Interval approved = null;
	
	public Booking(String user, Space space) {
		this.user = user;
		this.associatedSpace = space;
	}
	
	public String getUser() { return user; }
	public Space getAssociatedSpace() { return associatedSpace; }
	
	public LinkedList<Interval> getSlots() { return slots; }
	public void setSlots(LinkedList<Interval> slots) { this.slots = slots; }
	
	public boolean isApproved() { return approved != null; }
	public void setApproved(Interval approved) { this.approved = approved; }
	public Interval getApproved() { return this.approved; }
	
	public String toString() {
		String user = "";
		if (this.user != null) 
			user = this.user;
		else
			user = "User";
		
		return user + ", " + slots.size() + " slots, " + associatedSpace.getLocation();
	}
}
