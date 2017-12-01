package Scheduler;

import java.io.Serializable;
import java.util.LinkedList;

public class Interval implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<String> days;
	private String start;
	private String end;
	
	public Interval(String start, String end) {
		this.start = start;
		this.days = new LinkedList<>();
		this.end = end;	
	}
	
	public LinkedList<String> getDays() { return days; }
	public void setDays(LinkedList<String> days) { this.days = days; }
	public void addDay(String day) { this.days.add(day); }
	
	public String getStart() { return start; }
	public String getEnd() { return end; }

	public String toString() {
		String output = "";
		for (String day: this.days)
			if (day.equals("Thursday"))
				output = output + "R";
			else if (day.equals("Saturday"))
				output = output + "Sat";
			else if (day.equals("Sunday"))
				output = output + "Sun";
			else
			    output = output + day.substring(0, 1);

		return output + ", " + start + "-" + end;
	}
}
