import java.util.*;

class main {
   
   public static void main(String args[]) {
       int numStudents = 20;
       int numGroups = 5;
       int numHouses = 4;
       
       Vector<student> studentVector = new Vector<student>();
       Vector<student> houseOneVector = new Vector<student>();
       Vector<student> houseTwoVector = new Vector<student>();
       Vector<student> houseThreeVector = new Vector<student>();
       Vector<student> houseFourVector = new Vector<student>();
       Vector<Vector<student>> houseVector = new Vector<Vector<student>>(); 

       Object attendance = new Object();
    
       student[] totalStudents = new student[numStudents];
       teacher theTeacher = new teacher(1, studentVector, numStudents, attendance); 

       houseVector.add(houseOneVector);
       houseVector.add(houseTwoVector);
       houseVector.add(houseThreeVector);
       houseVector.add(houseFourVector);

       for(int i = 0;i < totalStudents.length; i++) {
	    totalStudents[i] = new student(i, studentVector, attendance, numStudents, houseVector);
	    totalStudents[i].start();
       }

       theTeacher.start();
       
       
       house[] houseAry = new house[numHouses];
       for(int i = 0;i < houseAry.length;i++) {
        houseAry[i] = new house(i, numGroups, houseVector);
        houseAry[i].start();
       }
   }
}
