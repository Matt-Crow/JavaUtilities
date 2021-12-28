/**
* @Author Matt Crow
* Use
*   javac Scheduler.java && java Scheduler
* to run
*/



import java.util.Scanner;



public class Scheduler {
    private int size;
    private final int capacity;

    /*
    Associative arrays mapping priorities to packet numbers
    */
    private final boolean[] priorities;
    private final int[] packetNumbers;



    private Scheduler(int capacity){
        this.capacity = capacity;
        size = 0;
        priorities = new boolean[capacity];
        packetNumbers = new int[capacity];
    }

    public final void run(int numPackets, String packets){
        String msg = validate(numPackets, packets);
        if(msg != null){
            System.out.println(msg); // invalid
        } else {
            for(int i = 0; i < numPackets; ++i){
                enqueue(packets.charAt(i) == 'H', i);
                // charAt(i) is either H or L at this point
            }
            sendPackets();
        }
    }

    private final String validate(int numPackets, String packets){
        if(numPackets <= 0){
            return "Wrong input: the number of packets must be greater than 0.";
        }
        if(packets.length() != numPackets){
            return "Wrong input: the number of packets is wrong.";
        }
        char c;
        for(int i = 0; i < numPackets; ++i){
            c = packets.charAt(i);
            if(c != 'L' && c != 'H'){
                return "Wrong input: the priority must be H or L.";
            }
        }
        return null;
    }

    private final void enqueue(boolean hasPriority, int packetNum){
        priorities[size] = hasPriority;
        packetNumbers[size] = packetNum;
        ++size;
        if(size == capacity){
            sendPackets();
        }
    }

    private final void sendPackets(){
        for(int i = 0; i < size; ++i){
            if(priorities[i]){ // send packets with high priority
                System.out.printf("%d ", packetNumbers[i]);
            }
        }
        for(int i = 0; i < size; ++i){
            if(!priorities[i]){ // send packets with low priority
                System.out.printf("%d ", packetNumbers[i]);
            }
        }
        size = 0;
    }

    public static void main(String[] args){
        Scheduler scheduler = new Scheduler(3);
        int n = 0;
        String packets = "";
        try (
            Scanner scanner = new Scanner(System.in);
        ) {
            n = scanner.nextInt();
            packets = scanner.next();
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        scheduler.run(n, packets);
    }
}
