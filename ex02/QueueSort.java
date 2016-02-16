import java.util.*;
import java.io.*;

public class QueueSort<E extends Comparable<E>> {

    private ArrayQueue<ArrayQueue<E>> Q;
    public static final int CAPACITY = 10;  // default queue capacity
    private int n;    
    private boolean trace;
	
    public QueueSort(){this(CAPACITY);}
	
    public QueueSort(int capacity){
	n = capacity;
	Q = new ArrayQueue<ArrayQueue<E>>(n);
    }

    private ArrayQueue<E> merge(ArrayQueue<E> q1,ArrayQueue<E> q2) throws ArrayQueueException {
		
		ArrayQueue<E> sortedQ = new ArrayQueue<E>(q1.size()+q2.size());
		//While both queues have elements in them
		while(!q1.isEmpty() && !q2.isEmpty()){
			// If the front of the first queue comes before the front of the second queue
			// add it to the front, else the other way round
			if(q1.front().compareTo(q2.front()) <= 0){
				sortedQ.enqueue(q1.dequeue());
			} else sortedQ.enqueue(q2.dequeue());
		}
		// Add the remaining elements of each queue
		while(!q1.isEmpty()) sortedQ.enqueue(q1.dequeue());
		while(!q2.isEmpty()) sortedQ.enqueue(q2.dequeue());
		
		return sortedQ;
    }
	//	
    // Implemented
    // Take two sorted queues and merge them to produce a third
    // sorted queue
    //

    public void sort(){
		while(Q.size()>=2){			
			ArrayQueue<E> sQ = merge(Q.dequeue(), Q.dequeue());	
			Q.enqueue(sQ);
		}
	}
    //
	// Implemented
    // given a queue Q of queues
    // (1) if Q is of size 1 deliver the first queue in Q
    // (2) if Q is of size 2 or more 
    //     - get the first and second queues off Q
    //     - merge these two queues to create a third queue
    //     - add the third queue to the queue
    //     - go back to (1)
    //

    public void add(E element){
		ArrayQueue<E> tempQ = new ArrayQueue<E>(1);
		tempQ.enqueue(element);
		Q.enqueue(tempQ);
	}
    //
    // Implemented
    // create an ArrayQueue<E> that contains the element
    // enqueue it onto Q
    //
    
    public String toString(){return Q.toString();}

    public void trace(){trace = !trace;}

    public static void main(String[] args) throws IOException {
	Scanner sc = new Scanner(new File(args[0]));
	ArrayList<String> data = new ArrayList<String>();
	while (sc.hasNext()) data.add(sc.next());
	int n = data.size();
	QueueSort<String> QS = new QueueSort<String>(n);
	for (String s : data) QS.add(s);
	if (args.length > 1) QS.trace();
	QS.sort();
	System.out.println(QS);
    }
}
