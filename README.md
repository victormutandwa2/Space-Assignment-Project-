# CS3716 Group Project - Scheduling System

## Repository URL

You can find the repository at https://github.com/eeshangarg/CS3716Scheduler

## Course Details

* **Course Name:** Computer Science 3716 - Software Methodology
* **Instructor:** Dr. Adrian Fiech
* **Teaching Assistant:** Sipan Ye
* Memorial University of Newfoundland, St. John's, NL, Canada

## Description

Our program allows a Principal to set the schedule for various locations
within an institution. For example, the Principal can say that the Gym is
available from 1 pm to 7 pm on Monday, Wednesday, and Friday. A coach can
then make a booking for the Gym and specify a maximum of 5 time slot
preferences. For example, the coach can then make a booking where he is
okay with 1 pm to 2 pm on Monday **OR** 3 pm to 4 pm on Friday. Once the
coach submits the request, the Principal can then review it and accept
one slot that he thinks is okay, or reject the entire booking.

The coach can view all pending, accepted and rejected bookings. Also,
there is built-in conflict resolution in our program. If a time slot
is already booked, another coach cannot make a booking that conflicts
with that time slot. If the coach tries to make a booking that is
in conflict with a current accepted booking, the system will report an
error.

All the objects implement `Serializable` and the schedule is saved by
serializing the Schedule object and writing it to a file. The object
is reloaded from the file every time the program is started.

## Instructions

1. Clone the repository: `git clone https://github.com/eeshangarg/CS3716Scheduler`

2. Open Eclipse.

3. Click on **File** -> **New** -> **Java Project**

4. Uncheck the checkbox **Use Default Location**

5. Click on **Browse** and select the repository you cloned in **Step 1**.

6. Click on **Finish**.

7. In Eclipse, right-click on the file **Test.java**, and then click on
   **Run As** -> **Java Application**

8. Enjoy our application! We hope you like it!
