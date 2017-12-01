package Scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
	public static Scheduler scheduler = readObjectFromFile();
	public static String[] times = new String[] {
			"1 PM", "2 PM", "3 PM",
			"4 PM", "5 PM", "6 PM",
			"7 PM", "8 PM", "9 PM", 
			"10 PM", "11 PM", "12 PM",
			"1 AM", "2 AM", "3 AM",
			"4 AM", "5 AM", "6 AM",
			"7 AM", "8 AM", "9 AM",
			"10 AM","11 AM", "12 AM"
	};
	
	public static String[] getTimes(String start, String end) {
		int startingIndex = 0;
		int endingIndex = times.length - 1;
		for (int i = 0; i < times.length; i++) {
			if (times[i].equals(start))
				startingIndex = i;
			if (times[i].equals(end))
				endingIndex = i;
		}
		
		int subtimeLength = endingIndex-startingIndex + 1;
		String[] subtimes = new String[subtimeLength];
		for (int i = startingIndex, j = 0; i <= endingIndex && j < subtimeLength; i++, j++) {
			subtimes[j] = times[i];
		}
		
		return subtimes;
	}

	public static void main(String args[]) {
		Login.main(null);
	}
	
	public static void writeObjectToFile(Scheduler scheduler) {
		try {
		   FileOutputStream myFileOutputStream = new FileOutputStream(
				   new File("data.txt"));
		   ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
		   myObjectOutputStream.writeObject(Test.scheduler);
		   myObjectOutputStream.close();
		}
		catch (Exception e) { System.out.println(e.toString()); }
	}
	
	public static Scheduler readObjectFromFile() {
		try {
		    FileInputStream myFileInputStream = new FileInputStream(
		    		new File("data.txt"));
		    ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
		    Scheduler schedulerObj = (Scheduler) myObjectInputStream.readObject(); 
		    myObjectInputStream.close();
		    return schedulerObj;
		}
		catch (Exception e) { 
			System.out.println(e.toString());
			return new Scheduler();
		}		
	}
}
