# CS3716 Group Project - Scheduling System

## Repository URL

You can find the repository at https://github.com/eeshangarg/CS3716Scheduler

## Course Details

* **Course Name:** Computer Science 3716 - Software Methodology
* **Instructor:** Dr. Adrian Fiech
* **Teaching Assistant:** Sipan Ye
* Memorial University of Newfoundland, St. John's, NL, Canada

## Group Details and Point Distribution

### Group 13

There are four members in our group. **Dr. Fiech is aware of this.**

* Eeshan Garg (eg3800@mun.ca)
* Sahil Anand (sa7437@mun.ca
* Hussein Parpia (hp5702@mun.ca)
* Victor Mutandwa (vtm806@mun.ca)

### Point Distribution

Since there are four members in our group, our point distribution
40 points total for 4 members. We all did equal amounts of work on
this project and met thrice a week to work together. The distribution
is as follows:

* Eeshan Garg - **10 points** - Eeshan supervised the entire project
  and made critical development decisions. Eeshan was responsible for
  object serialization, managing the Git repository and writing
  `AddSpaces.java`, `ReviewRequest.java` and, `Scheduler.java`.

* Sahil Anand - **10 points** - Sahil composed the initial GUI design.
  Sahil was also responsible for writing `Booking.java`, `ViewRequest.java`,
  and, `UserPanel.java` various improvements to the code base.

* Hussein Parpia - **10 points** - Hussein was responsible for writing the
  code for handling conflict. Hussein also wrote `Login.java`,
  `BookSpace.java`, and `Space.java`. He also focused on error handling.

* Victor Mutandwa - **10 points** - Victor was added later to our group
  but he was responsible for writing `Test.java`, `Interval.java`,
  and `AdminPanel.java` and wrote the Eclipse **Instructions** at the
  end of this document.

## Instructions

1. Clone the repository: `git clone https://github.com/eeshangarg/CS3716Scheduler`

2. Open Eclipse.

3. Click on **File** -> **New** -> **Java Project**

4. Uncheck the checkbox **Use Default Location**

5. Click on **Browse** and select the `CS3716Scheduler` repository you
   cloned in **Step 1**.

6. Click on **Finish**.

7. In Eclipse, right-click on the file **Test.java**, and then click on
   **Run As** -> **Java Application**

8. Enjoy our application! We hope you like it!

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
