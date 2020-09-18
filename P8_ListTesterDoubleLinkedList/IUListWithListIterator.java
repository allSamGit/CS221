import java.util.ListIterator;

/**
 *  Adds ListIterator support to IndexedUnorderedList
 *  
 * @author CS221
 * @param <T> object type to store in the list
 */
public interface IUListWithListIterator<T> extends IndexedUnsortedList<T> {
	/**
	 * Returns an ListIterator for the elements in this list. 
	 * @return reference to a ListIterator for the list
	 */
	public ListIterator<T> listIterator();

	/**
	 * Returns an ListIterator for the elements in this list,
	 * initialized in front of the element with the given index.
	 *
	 * @return reference to a ListIterator for the list
	 */
	public ListIterator<T> listIterator(int startingIndex);
}
