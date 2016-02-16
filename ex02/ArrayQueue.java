import java.util.*;
import java.io.*;

public class ArrayQueue<E> {

    public static final int CAPACITY = 10;  // default queue capacity
    private E[] Q;                          // E array used to implement the queue  
    private int n;                          // actual capacity of queue
    private int front;                      // index for the top of queue
    private int rear;                       // rear of the queue
    private int size;                       // size of the queue
    
	
    public ArrayQueue(){this(CAPACITY);}

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity){
	n = capacity;
	Q = (E[]) new Object [n]; 
	front = rear = size = 0;
    }
    //
    // NOTE: java does not allow creation of array with parametrised type!
    //
	
    public int size(){return size;}
    //
    // Implemented
    //
	 
    public boolean isEmpty(){return size==0;}
    //
    // Implemented
    //

    public E front() throws ArrayQueueException {return Q[front];}
    //
    // Implemented
    //
	
    public void enqueue(E element) throws ArrayQueueException {
		if(size==n) throw new ArrayQueueException("Array overflow!");
		Q[rear] = element;
		rear = (rear+1)%n;
		size++;
	}
    //
    // Implemented
    //

    
    public E dequeue() throws ArrayQueueException {
		if(isEmpty()) throw new ArrayQueueException("Array underflow!");
		E dequeuedElement = Q[front];
		front = (front+1)%n;
		size--;
		
		return dequeuedElement;
	}
    //
    // Implemented
    //
    
    public String toString(){
		String s = "[";
		int i = front;
		while(i<(front+size)){
			s = s + Q[i%n];
			if(i!= front+size-1) s = s + ",";
			i++;
		}
		return s+"]";
	}
    //
    // Implemented
    //
    //
    // NOTE: if the queue contains 1,2,3 then return "[1,2,3]"
    //       if the queue contains 1 then return "[1]"
    //       if the queue is empty return "[]"
    //
}
	
