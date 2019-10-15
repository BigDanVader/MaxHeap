package MaxHeapPackage;
/*
 * file: UserFile.java
 * author: D. Luoma
 * class: CS 241.01 - Data Structures and Algorithms II
 * 
 * assignment: Program 2
 * date last modified: 1/21/18
 * 
 * purpose: This program creates MaxHeap structure for the user to use for testing the average
 * number of insertions for both the linear series of insertions as well as the optimal method.
 */
import java.util.Random;
import java.util.Scanner;

public class UserFile
{
	private static int linearSwaps = 0;
	private static int optimalSwaps = 0;
	private static MaxHeap<Integer> linHeap;
	private static MaxHeap<Integer> optHeap;
	private static final int SIZE = 100;
	private static final int MAX_RANGE = 500;
	private static final int ITERATIONS = 20;
	
	/*
	 * method:arrayFiller
	 * purpose: Fills the given array with a linear series of integers.
	 */
	private static void arrayFiller(Integer[] array, int size)
	{
		for (int i = 0; i < size; i++)
			array[i] = (i + 1);
	}
	
	/*
	 * method:arrayFiller
	 * purpose: Fills the given array with an assortment of randomly-generated integers within
	 * the given range.
	 */
	private static void arrayFiller(Integer[] array, int size, int max)
	{
		Random rand = new Random();
		
		for (int i = 0; i < size; i++)
		{
			Integer newInt = rand.nextInt(max) + 1;
			boolean duplicateFound = false;
			
			for (int j = 0; j <= i; j++)
			{
				if (array[j] == newInt)
					duplicateFound = true;
			}
			
			if(!duplicateFound)
				array[i]=newInt;
			else
				i--;
		}
	}
	
	/*
	 * method:testMethod
	 * purpose: Creates two MaxHeap objects. Fills one using a linear series of insertions, then 
	 * fills the other using the optimal method. The method then saves the number of swaps
	 * performed by each MaxHeap object.
	 */
	private static void testMethod(Integer[] tempArray)
	{
		linHeap = new MaxHeap<Integer>(SIZE);
		
		for (int j = 0; j < SIZE; j++)
			linHeap.add(tempArray[j]);

		linearSwaps += linHeap.getSwapTotal();
		
		optHeap = new MaxHeap<Integer>(tempArray);
		optimalSwaps += optHeap.getSwapTotal();
	}
	
	/*
	 * method:menuMessage
	 * purpose: Prints the options available to the user.
	 */
	private static void menuMessage() 
	{
		System.out.println("Welcome!\nPlease select how to test the program:");
		System.out.println("(1) 20 sets of 100 randomly generated integers.");
		System.out.println("(2) Fixed integer values 1-100.");
		System.out.println("Enter choice: ");	
	}
	
	/*
	 * method:errorMessage
	 * purpose: Prints an entry error message to the user.
	 */
	private static void errorMessage() 
	{
		System.out.println("Invalid entry. Please try again.");
	}
	
	public static void main(String[] args) 
	{
		boolean done = false;
		
		while (!done)
		{
			menuMessage();
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();
			
			if (choice == 1)
			{
				for (int i = 0; i < ITERATIONS; i++)
				{
					Integer[] tempArray = new Integer[SIZE];
					arrayFiller(tempArray, SIZE, MAX_RANGE);
					
					testMethod(tempArray);
					
					linHeap.clear();
					optHeap.clear();
				}
				
				System.out.println("Average swaps of series of insertions: " + (linearSwaps / ITERATIONS));
				System.out.println("Average swaps for optimal method: " + (optimalSwaps / ITERATIONS));
				
				done = true;
				in.close();
			}
			else if (choice == 2)
			{
				Integer[] tempArray = new Integer[SIZE];
				arrayFiller(tempArray, SIZE);
				
				testMethod(tempArray);
				
				System.out.print("Heap built using series of insertions: ");
				for (int i = 1; i <= 10; i++)
					System.out.print(linHeap.getIndex(i) + " ");
				System.out.println(".");
				
				System.out.println("Number of swaps: " + linHeap.getSwapTotal());
				for (int i = 0; i < 10; i++)
					linHeap.removeMax();
				
				System.out.print("Heap after 10 removals: ");
				for (int i = 1; i <= 10; i++)
					System.out.print(linHeap.getIndex(i) + " ");
				System.out.println(".");
				
				System.out.print("Heap built using optimal method: ");
				for (int i = 1; i <= 10; i++)
					System.out.print(optHeap.getIndex(i) + " ");
				System.out.println(".");
				
				System.out.println("Number of swaps: " + optHeap.getSwapTotal());
				for (int i = 0; i < 10; i++)
					optHeap.removeMax();
				
				System.out.print("Heap after 10 removals: ");
				for (int i = 1; i <= 10; i++)
					System.out.print(optHeap.getIndex(i) + " ");
				System.out.println(".");

				done = true;
				in.close();
			}
			else
			{
				errorMessage();
			}
		}
	}
}
