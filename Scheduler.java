package Scheduler;
import java.io.Serializable;
import java.util.LinkedList;

public class Scheduler implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<Booking> acceptedBookings = new LinkedList<>();
	private LinkedList<Space> availableSpaces = new LinkedList<>();
	private LinkedList<Booking> pendingBookings = new LinkedList<>();
	private LinkedList<Booking> rejectedBookings = new LinkedList<>();
	private LinkedList<String> users = new LinkedList<>();

	public LinkedList<Booking> getAcceptedBookings() { return acceptedBookings; }
	public LinkedList<Space> getAvailableSpaces() { return availableSpaces; }
	public LinkedList<Booking> getRejectedBookings() { return rejectedBookings; }
	public LinkedList<Booking> getPendingBookings() { return pendingBookings; }
	
	public LinkedList<String> getUsers() { return users; }
	public void setUsers(LinkedList<String> users) { this.users = users; }
	public boolean userExists(String user) {
		for (String existingUser: users) {
			if (existingUser.equals(user))
				return true;
		}
		return false;
	}
}
