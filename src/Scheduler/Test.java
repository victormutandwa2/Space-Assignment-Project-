package Scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {
	public static Scheduler scheduler = readObjectFromFile();
	public static String[] militaryTimes = new String[] {
			"06:00", "07:00", "08:00",
			"09:00", "10:00", "11:00",
			"12:00", "13:00", "14:00",
			"15:00", "16:00", "17:00",
			"18:00", "19:00", "20:00",
			"21:00", "22:00", "23:00",
			"00:00", "01:00", "02:00",
			"03:00", "04:00", "05:00"
	};
	public static String[] times = new String[] {
			"6 AM", "7 AM", "8 AM",
			"9 AM", "10 AM", "11 AM",
			"12 PM", "1 PM", "2 PM",
			"3 PM", "4 PM", "5 PM",
			"6 PM", "7 PM", "8 PM",
			"9 PM", "10 PM", "11 PM",
			"12 AM", "1 AM", "2 AM",
			"3 AM", "4 AM", "5 AM"
	};
	
	public static String convertToMilitaryTime(String time) {
		String militaryTime = "";
		for (int i = 0; i < times.length; i++) {
			if (times[i].equals(time)) {
				militaryTime = militaryTimes[i];
				break;
			}
		}
		return militaryTime;
	}

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
		catch (Exception e) { return new Scheduler(); }
	}
}
