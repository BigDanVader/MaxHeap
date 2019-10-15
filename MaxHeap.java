package MaxHeapPackage;
/*
 * file: MaxHeap.java
 * author: D. Luoma
 * class: CS 241.01 - Data Structures and Algorithms II
 * 
 * assignment: Program 2
 * date last modified: 2/4/18
 * 
 * purpose: This program creates a max heap. It provides methods for construction using linear 
 * insertion as well as using the optimal method.
 */

public final class MaxHeap<T extends Comparable<? super T>>
{
	private T[] heap;
	private int lastIndex;
	private int swapTotal;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 25;
	
	public MaxHeap()
	{
		this(DEFAULT_CAPACITY);
	}
	
	/*
	 * method:MaxHeap
	 * purpose: Constructor used for linear insertion creation of MaxHeap
	 */
	public MaxHeap(int initialCapacity)
	{
		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		swapTotal = 0;
		capacity = initialCapacity;
	}
	
	/*
	 * method:MaxHeap
	 * purpose: Constructor used for optimal creation of MaxHeap.
	 * Imports an array and organizes it using the reheap() method.
	 */
	public MaxHeap (T[] entries)
	{
		this(entries.length);
		
		for (int index = 0; index < entries.length; index++)
		{
			heap[index + 1] = entries[index];
			lastIndex++;
		}
		
		for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
			reheap(rootIndex);
	}
	
	/*
	 * method:add
	 * purpose: Adds a new entry to the MaxHeap object. Used for linear insertion.
	 */
	public void add(T newEntry)
	{
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		
		while (parentIndex > 0 && newEntry.compareTo(heap[parentIndex]) > 0)
		{
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
			swapTotal++;
		}
		
		heap[newIndex] = newEntry;
		lastIndex++;
		ensureCapacity();
	}
	
	/*
	 * method:removeMax
	 * purpose: Removes the root of the MaxHeap object, then calls reheap() to properly organize
	 * the remaining heap.
	 */
	public T removeMax()
	{
		T root = null;
		
		if(!isEmpty())
		{
			root = heap[1];
			heap[1]= heap[lastIndex];
			lastIndex--;
			reheap(1);
		}
		
		return root;
	}
	
	/*
	 * method:getMax
	 * purpose: returns the data stored in the root of the MaxHeap object.
	 */
	public T getMax()
	{
		T root = null;
		if (!isEmpty())
			root = heap[1];
		return root;
	}
	
	/*
	 * method:getIndex
	 * purpose: returns the data stored in the given index of the MaxHeap object.
	 */
	public T getIndex(int index)
	{
		return heap[index];
	}
	
	/*
	 * method:isEmpty
	 * purpose: returns a bool signifying if the MaxHeap object is angry.
	 */
	public boolean isEmpty()
	{
		return lastIndex < 1;
	}
	
	/*
	 * method:getSize
	 * purpose: returns the size of the MaxHeap object.
	 */
	public int getSize()
	{
		return lastIndex;
	}
	
	/*
	 * method:getSwapTotal
	 * purpose: returns the number of swaps performed while sorting the MaxHeap object.
	 */
	public int getSwapTotal()
	{
		return swapTotal;
	}
	
	/*
	 * method:clear
	 * purpose: Sets all entries in the MaxHeap object to null and sets lastIndex to zero.
	 */
	public void clear()
	{
		while (lastIndex >= 1)
		{
			heap[lastIndex] = null;
			lastIndex--;
		}
		lastIndex = 0;
	}
	
	/*
	 * method:ensureCapacity
	 * purpose: If MaxHeap object is full, doubles the size of the MaxHeap object.
	 */
	private void ensureCapacity()
	{
		if (lastIndex == capacity)
		{
			@SuppressWarnings("unchecked")
			T[] tempHeap = (T[]) new Comparable[(capacity * 2) + 1];
			
			for (int i = 0; i <= capacity; i++)
				tempHeap[i] = heap[i];
			
			heap = tempHeap;
			capacity *= 2;
		}
	}
	
	/*
	 * method:reheap
	 * purpose: Sorts the MaxHeap object using the optimal method.
	 */
	private void reheap(int rootIndex)
	{
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		
		while (!done && (leftChildIndex <= lastIndex))
		{
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
			
			if ( (rightChildIndex <= lastIndex) && 
					heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
			{
				largerChildIndex = rightChildIndex;
			}
			
			if (orphan.compareTo(heap[largerChildIndex]) < 0)
			{
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;
				swapTotal++;
			}
			else
				done = true;
		}
		heap[rootIndex] = orphan;
	}
}
