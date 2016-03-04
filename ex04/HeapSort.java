import java.util.*;
import java.io.*;

public class HeapSort {
    
    public static void main(String[] args) throws IOException {
	Scanner sc = new Scanner(new File(args[0]));
	ArrayList<String> data = new ArrayList<String>();
	while (sc.hasNext()) data.add(sc.next());
	bubbleUp(data);
	//for (String str : data) System.out.println(str);
	for (int k = 0; k < data.size()-1; k++) System.out.println(data.get(k));
    }
	
	private static void bubbleUp(ArrayList<String> toSort){
		int index = toSort.size()-1;
		while(index>0){
			int parent = (index-1)/2;
			if(toSort.get(index).compareTo(toSort.get(parent)) >= 0) return;
			swap(toSort, index, parent);
			index = parent;
		}
	}
	
	private static void swap(ArrayList<String> list, int f, int s){
		String temp = list.get(f);
		list.set(f, list.get(s));
		list.set(s, temp);
	}
}
//
// takes a filename from the command line
// and outputs that file sorted, one word per line
//
