import java.util.Vector;
import java.util.*;

class student extends Thread {
    private static long time = System.currentTimeMillis();
    private Vector<student> studentVector = new Vector<student>(); // Object for students to block on
	private Vector<Vector<student>> houseVector = new Vector<Vector<student>>();
	private int candies = 0;
	private int groupNumber = 0;
	private int numStudents;
	private int groupOneTotal = 0; 
	private int groupTwoTotal = 0;
	private int groupThreeTotal = 0;
	private int groupFourTotal = 0;
	private Object attendance; // Object to notify the teacher when  all students arrive
    public void msg(String m) {
	System.out.println("[" + (System.currentTimeMillis() - time + "]"
				  + getName() + ": " + m));
    }

    public student(int id, Vector<student> s, Object notifyTeacher, int n, Vector<Vector<student>> houseVect) {
		studentVector = s;
		numStudents = n;
		setName("Student-" + id);
		attendance = notifyTeacher;
		houseVector = houseVect;
    }

	public void setCandies(int amount) {
		this.candies += amount;
	}

	public int getCandies() {
		return this.candies;
	}

    public void run() {
	try {
		msg("arrived at school");
		synchronized (studentVector) {
			studentVector.addElement(this);
			// if all students arrived, let the teacher know they can start teaching
			if(studentVector.size() == numStudents){
				synchronized(attendance){
					msg("Notifying teacher, all students arrived");
					attendance.notify();
				}
			}
			msg("waiting for teacher");
			studentVector.wait();
	    }

		Random randGroup = new Random();
		groupNumber = randGroup.nextInt(1, 5);	 // give students a group
		msg("group number " + groupNumber);
		msg("taking break...");

		if (groupNumber == 1) {
			msg("blocking on houseVector at n - 1: " + groupNumber);
			houseVector.get(groupNumber - 1).add(this);
			synchronized (houseVector.get(groupNumber - 1)) {
				houseVector.get(groupNumber - 1).wait();
			}
		}
 
		else if (groupNumber == 2) {
			msg("blocking on houseVector at n - 1: " + groupNumber);
			houseVector.get(groupNumber - 1).add(this);
			synchronized (houseVector.get(groupNumber - 1)) {
				houseVector.get(groupNumber - 1).wait();
			}

		}

		else if (groupNumber == 3) {
			msg("blocking on houseVector at n - 1: " + groupNumber);
			houseVector.get(groupNumber - 1).add(this);
			synchronized (houseVector.get(groupNumber - 1)) {
				houseVector.get(groupNumber - 1).wait();
			}

		}

		else if (groupNumber == 4) {
			msg("blocking on houseVector at n - 1: " + groupNumber);
			houseVector.get(groupNumber - 1).add(this);
			synchronized (houseVector.get(groupNumber - 1)) {
				houseVector.get(groupNumber - 1).wait();
			}

		}

		msg("I made it here..this is my group number: " + this.groupNumber);
		msg("I have " + this.getCandies() + " candies");
		if (groupNumber == 1) {
			groupOneTotal += this.getCandies();
			msg("terminating... current " + groupNumber + " candy total: " + groupOneTotal );
		}
		else if (groupNumber == 2){
			groupTwoTotal += this.getCandies();
			msg("terminating... current " + groupNumber + " candy total: " + groupTwoTotal );
		}
		else if (groupNumber == 3){
			groupThreeTotal += this.getCandies();
			msg("terminating... current " + groupNumber + " candy total: " + groupThreeTotal );
		}
		else if (groupNumber == 4){
			groupFourTotal += this.getCandies();
			msg("terminating... current " + groupNumber + " candy total: " + groupFourTotal );
		}


	}

	catch (Exception e) {
	    e.printStackTrace();
	}
	
    }
}
