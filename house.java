import java.util.*;
public class house extends Thread {
    private boolean[] groupsFinished;
    private static long time = System.currentTimeMillis();

    private Vector<Vector<student>> houseVector;
   public house(int id, int groups,  Vector<Vector<student>> houseVect) {
    groupsFinished = new boolean[groups];
    setName("House-" + id);
    houseVector = houseVect;
   }

   public void msg(String m) {
	System.out.println("[" + (System.currentTimeMillis() - time + "]"
				  + getName() + ": " + m));
    }

   public void run() {
    try {

        for(int i = 0;i < groupsFinished.length;i++){
            groupsFinished[i] = false;
        }

        Thread.sleep(10000);
        msg("this is a house..");
        for(int i = 0;i < houseVector.size();i++){
            synchronized(houseVector.get(i)){
                for(int j = 0;j < houseVector.get(i).size();j++){
                    Random rdmCandies = new Random();
                    int rdm = rdmCandies.nextInt(1,10 );
                    msg("about to give "  + rdm +  " candies.");
                    houseVector.get(i).get(j).setCandies(rdm);
                    Thread.sleep(rdm * 250);
                    msg("about to notify student on house " + i);
                    houseVector.get(i).notify();
                }
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
   }
}
