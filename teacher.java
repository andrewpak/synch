import java.util.*;

class teacher extends Thread {
    public static long time = System.currentTimeMillis();
    private int numStudents;
    private Vector<student> studentVector = new Vector<student>();
	private Object teachersObject;
    public void msg(String m) {
	System.out.println("[" + (System.currentTimeMillis() - time + "]"
				  + getName() + ": " + m));
    }

    public teacher(int id, Vector<student> s, int n, Object notifyTeacher) {
		setName("Teacher-" + id);
		studentVector = s;
		numStudents = n;
		teachersObject = notifyTeacher;
    }

    
    public void run() {
	try {
	    msg("waiting in homeroom");
		if(studentVector.size() < numStudents){
			synchronized(teachersObject){
				msg("Waiting for more students..");	
				teachersObject.wait();
			}
		}

		msg("waiting a bit to start lecture");
		Thread.sleep(3000);
		msg("lecturing..");
		Thread.sleep(5000);		
		msg("lecture over.");
		Thread.sleep(1000);

	    synchronized(studentVector) {
			msg("this is the vector size " + studentVector.size());
			// give the students candies
			for(int i = 0;i < studentVector.size();i++){
				Random myRandom = new Random();
				int candy = myRandom.nextInt(1, 5);
				studentVector.elementAt(i).setCandies(candy);
			}
			
			for(int i = 0;i < studentVector.size();i++){
				studentVector.elementAt(i).msg("Has " + studentVector.elementAt(i).getCandies() + " candies");
			}

			if(studentVector.size() == numStudents){
			    studentVector.notifyAll();
			}
			msg("terminating..");
	    }
	}
	catch (Exception e){
	    e.printStackTrace();
	}
    }
}
