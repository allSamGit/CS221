import java.util.ArrayList;
import java.util.Collections;

/**
 * A collection of methods that work with arrays of ints.
 * 
 * @author Saman Rastgar
 */
public class MethodsToAnalyze {

	/**
	 * Return index where value is found in array or -1 if not found.
	 * @param array ints where value may be found
	 * @param value int that may be in array
	 * @return index where value is found or -1 if not found
	 */
	private static long statementCounter;
	public static int find(int[] array, int value) {
		// it instantiates two values
		statementCounter+=2; 
		for (int i = 0; i < array.length; i++) {
			statementCounter++;  // one check in if condition
			
			if (array[i] == value) {
				return i;
			}
			statementCounter+=2; //check loop condition and increment
		}
		
		return -1;
	}

	/**
	 * Replace all occurrences of oldValue with newValue in array.
	 * @param array ints where oldValue may be found
	 * @param oldValue value to replace
	 * @param newValue new value
	 */
	public static void replaceAll(int[] array, int oldValue, int newValue) {
		statementCounter+=3;
		int index = find(array, oldValue);
		statementCounter+=2; //condition check for while loop and assignment
		while (index > -1) {
			array[index] = newValue;
			statementCounter++;
			index = find(array, oldValue);
		}
		
	}
	
	/**
	 * Take an int[] and reorganize it so they are in ascending order.
	 * @param array ints that need to be ordered 
	 */
	public static void order1(int[] array) {
		statementCounter=1;// array instantiation
		
		statementCounter+=1; //Initialization
		
        for (int next = 1; next < array.length; next++) {
        	statementCounter++; // outer loop iteration
            int value = array[next];
            int index = next;
            while (index > 0 && value < array[index - 1]) {
            	statementCounter+=4; //2 condition check and 1 assignments and 1 decrement in while
                array[index] = array[index - 1];
                index--;
               
            }
            statementCounter+=2; //last condition check for while loop 
            array[index] = value;
           
        }
        statementCounter+=5; //3 assignments in outer loop & one initialization and one condition & one increment
	}

	/**
	 * Take an int[] and reorganize it so they are in ascending order.
	 * @param array ints that need to be ordered 
	 */
	public static void order2(int[] array)
	{
		statementCounter=1; //array instantiation
		statementCounter+=1; //Initialization
		for (int index = 0; index < array.length-1; index++) {// 0 to n-1
			int min = index;
			statementCounter+=2; 
			 // for second loop condition and increment
			for (int scan = index+1; scan < array.length; scan++) { //i+1 to n
				statementCounter+=2;//condition if and comparison in it
				if (array[scan] < array[min]) {
					min = scan;
				}
			}
			swap(array, min, index);
		}
		
	}
	
	/**
	 * Take an int[] and reorganize it so they are in ascending order.
	 * @param array ints that need to be ordered 
	 */
	public static void order3(int[] array)
	{
		statementCounter=1;
		statementCounter+=2;//initialization and check condition
		
		for (int position = array.length - 1; position > 0; position--)
		{
			statementCounter+=2;
			for (int scan = 0; scan <= position - 1; scan++)
			{
				statementCounter+=3;//if condition and inner for condition check and increment
				
				if (array[scan] > array[scan+1])
					swap(array, scan, scan + 1);
				
			}
			statementCounter+=2; //outer for condition check and decrement
	}
}
	/**
	 * Swaps two ints in an array.
	 * @param array the array in which the elements are swapped
	 * @param index1 the index of the first element to be swapped
	 * @param index2 the index of the second element to be swapped
	 */
	private static void swap(int[] array, int index1, int index2)
	{
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		statementCounter+=3;  // we have 3 assignments in if 
	}

	
	public static int[] randomizedArray(int size) {
		ArrayList<Integer> aL = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			aL.add(i+1);
		}
		Collections.shuffle(aL);
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = aL.get(i);
		}
		return a;
	}
	
	public static Long getStatements()
	{
		return statementCounter;
	}
}