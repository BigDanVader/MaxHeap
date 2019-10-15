package MaxHeapPackage;
/*
 * file: MaxHeapInterface.java
 * author: D. Luoma
 * class: CS 241.01 - Data Structures and Algorithms II
 * 
 * assignment: Program 2
 * date last modified: 2/4/18
 * 
 * purpose: This is the interface for the MaxHeap java class.
 */

public interface MaxHeapInterface<T extends Comparable<? super T>>
{
	public void add(T newEntry);
	public T removeMax();
	public T getMax();
	public boolean isEmpty();
	public int getSize();
	public void clear();
}
