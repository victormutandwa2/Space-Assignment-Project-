package Scheduler;

import java.io.Serializable;
import java.util.LinkedList;

public class Space implements Serializable {
	private static final long serialVersionUID = 1L;
	private String location;
	private Interval time;
	private String semester;
	private String user = null;
	private LinkedList<Interval> blocked = new LinkedList<>();
	
	public Space(String location, Interval time, String semester) {
		this.location = location;
		this.time = time;
		this.semester = semester;
	}
	
	public String getLocation() { return location; }
	public Interval getTime() { return time; }
	public String getSemester() { return this.semester; }
	
	public void setUser(String u) { this.user = u; }
	public String getUser() { return this.user; }
	
	public LinkedList<Interval> getBlocked() { return this.blocked; }
	public void addBlocked(Interval i) { this.blocked.add(i); }
	
	public String toString() {
		return location + ": " + semester + ", " + time.toString();
	}
}
