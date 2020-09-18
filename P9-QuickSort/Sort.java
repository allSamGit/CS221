
import java.util.Comparator;


import java.util.Iterator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Quicksort algorithm.
 *
 * @author CS221
 * 
 * @author Saman Rastgar
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new IUDoubleLinkedList<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param listT pivot=list.last();
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		quicksort(list);
		
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		quicksort(list, c);

	}
	
	/**
	 * Quicksort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void quicksort(IndexedUnsortedList<T> list) {
		
		// TODO: Implement recursive quicksort algorithm
		
		//check if size of list is equal to 1 or 0 which means we don't need to sort anything
		
		if (list.size() <= 1) {
			return ;
		}
		
		IndexedUnsortedList<T> leftList= newList();
		IndexedUnsortedList<T> rightList= newList();
		
		//
		
		T pivot = list.removeLast();
		Iterator<T> itr = list.iterator();

		while (itr.hasNext()) {

			T comparedElem = itr.next();//iterating over each element
			itr.remove();
			
			if (comparedElem.compareTo(pivot) > 0) {
				
				rightList.add(comparedElem);
			} else
				leftList.add(comparedElem);
		}
		
		//recursion function
		
		quicksort(leftList);
		quicksort(rightList);
		
		////////Reconstruct my List/////////
		
		//Constructing left list
		
		int Size=leftList.size();
		for(int i=0;i<Size;i++)
		{
			list.add(leftList.removeFirst());
		}
		
		//adding pivot
		
		list.add(pivot);
		
		//Constructing right list
		
		int rightSize=rightList.size();
		
		for(int i=0;i<rightSize;i++)
		{
			list.add(rightList.removeFirst());
		}
	
	}
	
	public static <T> void swap(IndexedUnsortedList<T> list, int i, T pivot) {
		
		  T tmp = pivot;
		  pivot=list.get(i);
		  list.set(i,tmp);
	}
		
	/**
	 * Quicksort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void quicksort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		
		// TODO: Implement recursive quicksort algorithm using Comparator
		
		if (list.size() <= 1) {
			return ;
		}
		//WrappedDLL<T> leftList = new WrappedDLL<T>();
		//WrappedDLL<T> rightList = new WrappedDLL<T>();
		IndexedUnsortedList<T> leftList= newList();
		IndexedUnsortedList<T> rightList= newList();
		

		
		T pivot = list.removeLast();
		Iterator<T> itr = list.iterator();

		while (itr.hasNext()) {

			T comparedElem = itr.next();
		//	list.remove(comparedElem);
			itr.remove();
			
			if (c.compare(comparedElem, pivot)>0) {
				
				rightList.add(comparedElem);
			} else
				leftList.add(comparedElem);
		}
		
		//recursion function
		
		quicksort(leftList,c);
		quicksort(rightList,c);
		
		////////Reconstruct my List/////////
		
		//Constructing left list
		int Size=leftList.size();
		for(int i=0;i<Size;i++)
		{
			list.add(leftList.removeFirst());
		}
		
		//adding pivot
		list.add(pivot);
		//Constructing right list
		
		int rightSize=rightList.size();
		for(int i=0;i<rightSize;i++)
		{
			list.add(rightList.removeFirst());
		}
	
	}
}