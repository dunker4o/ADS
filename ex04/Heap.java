
public class Heap <E extends Comparable<E>> {
 
    private Object H[];   // contains the objects in the heap
    private int last;     // index of last element in heap
    private int capacity; // max number of elements in heap  

    public Heap(int n){
	capacity = n;
	H        = new Object[capacity+1];
	last     = 0;
    }
    //
    // create a heap with capacity n
    // NOTE: H is an array of objects
    //       must use casting when delivering the minimum
    //       See min() below.
    //       This must also be done in removeMin()
    //

    public Heap(){this(7);}
    //
    // by default, create a new heap with capacity 7
    //

    @SuppressWarnings("unchecked")
    private int compare(Object x,Object y){return ((E)x).compareTo((E)y);}
    
    public int size(){return last;}
    //
    // returns the number of elements in the heap
    //
    
    public boolean isEmpty(){return last==0;}
    //
    // is the heap empty?
    //
    
    @SuppressWarnings("unchecked")
    public E min() throws HeapException {
	if (isEmpty()) throw new HeapException("underflow");
	return (E) H[1];
    }
    //
    // returns element with smallest key, without removal
    // NOTE: must use casting to class (E)
    //
	
    
    public void insert(E e) throws HeapException {
		if(last == capacity) throw new HeapException("Heap overflow!");
		H[last+1] = e;
		last++;
		upHeapBubble(last);
    }		
    //
    // inserts e into the heap
    // throws exception if heap overflow
    //
    
    @SuppressWarnings("unchecked")
    public E removeMin() throws HeapException {
		if(isEmpty()) throw new HeapException("Heap underflow!");
		Object returnElement = H[1];
		if(last > 1){
			H[1] = H[last];
			last--;
			downHeapBubble(1);
		} else{
			H[1] = null;
			last--;
		}
		return (E) returnElement;
    }
    //
    // removes and returns smallest element of the heap
    // throws exception if heap is empty
    // NOTE: must cast result to class (E)
    //       see min() above
    //

    public String toString(){
		String s = "[";
		if(!isEmpty()){
			int i = 1;
			while(i != last){
				s = s + H[i] + ",";
				i++;
			}
			s = s + H[last];
		}
		s = s + "]";
	return s;
    }
    //
    // outputs the entries in H in the order H[1] to H[last]
    // in same style as used in ArrayQueue
    // 
    // 
	
	private boolean hasLeft(int pos){
		return pos*2 <= last;
	}
	//
	//	Checks if the hode has a left child
	//
	
	private boolean hasRight(int pos){
		return pos*2+1 <= last;
	}
	//
	// Checks if the node has a right child
	//
	
	private void swap(int d,int t){
		Object temp = H[d];
		H[d] = H[t];
		H[t] = temp;
	}
	//
	// Swaps two heap elements
	//
	
	private int getLeft(int pos){
		if(hasLeft(pos)) return pos*2;
		return -1;
	}
	//
	// Fetches the index of the left child. 
	//
	
	private int getRight(int pos){
		if(hasRight(pos)) return pos*2+1;
		return -2;
	}
	//
	//	Fetches the index of the right child.
	//
	
	private int getParent(int pos){
		return pos/2;
	}
	//
	//	Gets the parent index.
	//
	
	private int lesser(int f, int s){
		if(compare(H[f], H[s]) >= 0) return s;
		return f;
	}
	//
	// Compares two indexes and returns the one which contains a lesser element.
	//
	
	private void upHeapBubble(int i){
		int index = i;													//	Starts the sorting from the provided index
		while(index>1){													//	and continues until it reaches the root of the tree.
			if(compare(H[index], H[getParent(index)]) >= 0) return;		//	If the child node is greater than or equal to the parent then we need not swap and we can quit.
			swap(index, getParent(index));								//	Otherwise, swap them
			index = getParent(index);									//	and update the index.
		}
	}
	
	private void downHeapBubble(int i){
		int index = i;													// Starting index of the element
		int newIndex;													//	Position of the index after the update
		while (true){													//
			if(hasLeft(index) && hasRight(index)){						//	If the node has two childs, find the one which has a lesser element
				newIndex = lesser(getLeft(index), getRight(index));		//
			}else if(hasLeft(index)){									//	If it has only a right one, select that index
				newIndex = getLeft(index);								//
			} else return;												//	If it doesn't have any, return
			if(compare(H[index], H[newIndex]) < 0) return;				//	If the current node is less than the selected one, return
			swap(index, newIndex);										//	otherwise swap them and update the index
			index = newIndex;											//
		}
	}
}

