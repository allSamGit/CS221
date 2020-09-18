import java.util.ConcurrentModificationException;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * This programs helps to implement data from a sequence (different nodes). 
 * The nodes reference and two ways direction is really important
 * 
 * @author Saman Rastgar
 *
 * @param <T>
 */
public class IUDoubleLinkedList<T> implements IUListWithListIterator<T> {
	private DLLNode<T> head;
	private DLLNode<T> tail;
	private int size;
	private int modCount;

	/**
	 * Creating an empty list
	 */
	public IUDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
		modCount = 0;
	}
	/**
	 * Return String
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");

		DLLNode<T> currentNode = head;
		while (currentNode != null) {
			str.append(currentNode.getElement());
			if (currentNode != tail) {
				str.append(", ");
			}
			currentNode = currentNode.getNext();
		}
		str.append("]");
		return str.toString(); //toString
	}

	@Override
	public T removeFirst() {
		if (isEmpty()) { // checking if there is no node
		//	throw new IllegalStateException(" ");
			throw new NoSuchElementException();
		}
		T element1 = head.getElement();
		if (head == tail) { // one node
			head = tail = null; //setting to null
		} else { // more than one node
			head = head.getNext(); // setting head to next node
			head.setPrevious(null); // previous node to null
		}
		size--;
		modCount++;
		return element1; // first element
	}

	@Override
	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		T element2 = null;
		if (head == tail) { //one node
			element2 = head.getElement();
			head = tail = null; //setting to null
		} else { //checking if there is more node
			DLLNode<T> currentNode = head;
			element2 = tail.getElement();
			while (currentNode.getNext() != tail) { //looping until found the last node or tail
				currentNode = currentNode.getNext();
			}
			tail = currentNode;
			tail.setNext(null);// if found it then setting it to null
		}
		size--;
		modCount++;
		return element2; //last element
	}

	@Override
	public T remove(T element) {

		DLLNode<T> currentNode = head;
		boolean foundElement = false;
		while ((currentNode != null) && (foundElement == false)) {// checking the node existence and element is not found
			if (currentNode.getElement().equals(element)) { 
				foundElement = true; //element found
			} else {
				currentNode = currentNode.getNext(); //moving to find the element in next node
			}
			
		}
		if (!foundElement) { //element not found
			throw new NoSuchElementException();
		}
		T returnElement = currentNode.getElement(); //saving the element
		if (head == tail) { //checking size one
			head = tail = null;
		} else if (currentNode == tail) { //currentNode is at tail
			currentNode.getPrevious().setNext(null); //moving to previous node and setting nextNode(original tail to null)
			tail = currentNode.getPrevious();//currentNode to previousNode(node before original tail)
		} else if (currentNode == head) { 
//			head = currentNode.getNext(); //moving currentNode to Next Node 
//			currentNode.setPrevious(null);// setting previous Node (original head) to null
			currentNode.getNext().setPrevious(null);
			head=currentNode.getNext();
		} else { //more nodes
			currentNode.getPrevious().setNext(currentNode.getNext());
			currentNode.getNext().setPrevious(currentNode.getPrevious());
		}
		
		
		
		size--;
		modCount++;
		return returnElement;
	}  //removed element
	@Override
	public T first() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.getElement(); //first element
	}

	@Override
	public T last() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return tail.getElement(); //last element
	}

	@Override
	public boolean contains(T target) {
		DLLNode<T> currentNode = head;
		if (head == null) {
			return false;
		}
		while (!(currentNode.getElement().equals(target)) && (currentNode.getNext() != null)) { //checking target not found and node is not null
			currentNode = currentNode.getNext();
		}
		if (currentNode.getElement().equals(target)) { //found the target
			return true;
		}
		return false; // not found the target
	}

	@Override
	public boolean isEmpty() {
		return size == 0; // no node
	}

	@Override
	public int size() {
		return size; // number of Nodes
	}

	@Override
	public void add(int index, T element) {
		if ((index < 0) || (index > size)) {
			throw new IndexOutOfBoundsException(" ");
		}
		DLLNode<T> newNode = new DLLNode<T>(element); 
		if (isEmpty()) { 
			head = tail = newNode;
		} else {
			DLLNode<T> currentNode = head;
			for (int i = 0; i < index; i++) { // index looping
				currentNode = currentNode.getNext();
			}
			if (currentNode == head) { //
				newNode.setNext(head);
				head.setPrevious(newNode);
				head = newNode;
			} else if (currentNode == null) { //checking the size = currentNode, node after tail (which is empty)
				tail.setNext(newNode);
				newNode.setPrevious(tail);
				tail = newNode;
			} else { // Index in the middle of two nodes
				newNode.setNext(currentNode); 
				newNode.setPrevious(currentNode.getPrevious());
				currentNode.getPrevious().setNext(newNode);
				currentNode.setPrevious(newNode);	
			}
		}
		size++;
		modCount++;
	}

	@Override
	public void set(int index, T element) {
		if ((index < 0) || (index >= size)) {
			throw new IndexOutOfBoundsException(" ");
		}
		DLLNode<T> currentNode = head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		currentNode.setElement(element);// setting the element in index or even overiding
		modCount++;
	}
	@Override
	public void add(T element) {
		addToRear(element); // calling addToRear method
	}
	@Override
	public T get(int index) {
		if ((index < 0) || (index >= size)) {
			throw new IndexOutOfBoundsException(" ");
		}
		DLLNode<T> currentNode = head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		currentNode.getElement();
		return currentNode.getElement(); // returning the get element
	}
	@Override
	public int indexOf(T element) {
		DLLNode<T> currentNode = head;
		for (int index = 0; index < size; index++) {
			if (!(currentNode.getElement().equals(element))) {
				currentNode = currentNode.getNext();
			} else {
				return index; // returning the index
			}
		}
		return -1; //element not found in any nodes (invalid index)
	}
	@Override
	public T remove(int index) {
		if ((index < 0) || (index >= size())) {
			throw new IndexOutOfBoundsException(" ");
		}
		DLLNode<T> currentNode = head;
		T returnValue = null; // saving the elements
		if (size() == 1) {
			returnValue = head.getElement();
			head = tail = null;
		} else {
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.getNext();
			}
			returnValue = currentNode.getElement();
			if (currentNode == tail) {
				currentNode.getPrevious().setNext(null); // removing element at tail by setting the tail to null
				tail = currentNode.getPrevious();
			} else if (currentNode == head) {
				currentNode = currentNode.getNext();// new current Node
				currentNode.setPrevious(null); // setting previous to null
				head = currentNode;
			} else { // index between two nodes
				currentNode.getPrevious().setNext(currentNode.getNext());
				currentNode.getNext().setPrevious(currentNode.getPrevious());
			}
		}
		size--;
		modCount++;
		return returnValue; // returning the removed elements
	}
	@Override
	public void addToFront(T element) {
		DLLNode<T> newNode = new DLLNode<>(element); //newNode
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			head.setPrevious(newNode);
			newNode.setNext(head);
			head = newNode;
		}
		size++;
		modCount++;
	}
	@Override
	public void addToRear(T element) {
		DLLNode<T> newNode = new DLLNode<>(element); //newNode
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
		modCount++;
	}
	@Override
	public void addAfter(T element, T target) { 
		DLLNode<T> newNode = new DLLNode<T>(element);// newNode
		DLLNode<T> currentNode = head;
		while (currentNode != null) { //
			if (currentNode.getElement().equals(target)) {
				if (currentNode == tail) {
					newNode.setPrevious(currentNode);
					currentNode.setNext(newNode);
					tail = newNode;
				} else { //target in middle of the nodes
					newNode.setNext(currentNode.getNext());
					newNode.setPrevious(currentNode);
					currentNode.getNext().setPrevious(newNode);
					currentNode.setNext(newNode);
				}
				size++;
				modCount++;

				return; //exit the function
			} 
			else {
				currentNode = currentNode.getNext();
			}
		}
		throw new NoSuchElementException();
	}
	@Override
	public ListIterator<T> listIterator() {
		return new DLLIterator();
	}
	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		if(startingIndex<0 || startingIndex > size())
		{
			throw new IndexOutOfBoundsException();
		}
		return new DLLIterator(startingIndex);
	}
	@Override
	public Iterator<T> iterator() {
		return new DLLIterator();
	}
	/**
	 * Implements all necessary method from LisIterators for DoubleLinkedList
	 * @author Saman Rastgar
	 *
	 */
	private class DLLIterator implements ListIterator<T> {
		private DLLNode<T> nextNode;
		private DLLNode<T> lastReturnedNode;
		private int nextIndex;
		private int iterModCount;
		boolean remove = false;
		/**
		 * 
		 */
		public DLLIterator() { //constructor 
			this(0);
		}
		/**
		 * Constructor for DoubleLinkedList
		 * @param startingIndex
		 */
		public DLLIterator(int startingIndex) {
			nextNode = head;
			lastReturnedNode = null;
			nextIndex = 0;
			iterModCount = modCount;

			for (int i = 0; i < startingIndex; i++) {
				nextNode = nextNode.getNext();
				nextIndex++;
			}
		}
		@Override
		public boolean hasNext() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException(" ");
			}
			return (nextNode != null); 
		}
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException(" ");
			}
			T returnValue = nextNode.getElement(); //saving before removing
			lastReturnedNode = nextNode;
			nextNode = nextNode.getNext();
			nextIndex++;
			remove = true;
			return returnValue; 
		}
		@Override
		public void remove() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException(" ");
			}
			if (lastReturnedNode == null) { //next and previous not in use
				throw new IllegalStateException();
			}
			if (nextNode == lastReturnedNode) { //when previous was ran before
				nextNode = nextNode.getNext();
			} else {
				nextIndex--;
			}
			if (lastReturnedNode == head) { //when next previous gives first Node
				head = head.getNext();
				if(head!=null){
					head.setPrevious(null);
				}
			} else {
				lastReturnedNode.getPrevious().setNext(lastReturnedNode.getNext());
			}
			if (lastReturnedNode == tail) {//when next previous gives last Node
				tail = tail.getPrevious();
				if(tail!=null){
					tail.setNext(null);
				}
			} else {
				lastReturnedNode.getNext().setPrevious(lastReturnedNode.getPrevious());
			}
			if (!remove) {
				throw new IllegalStateException();
			}
			size--;
			modCount++;
			iterModCount++;
			remove = false;
			lastReturnedNode = null; 
		}
		@Override
		public boolean hasPrevious() {
			if (iterModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return (nextIndex > 0); //checking nextIndex size more than one
		}
		@Override
		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if(nextNode == null){
				nextNode = tail;
			}else{
				nextNode = nextNode.getPrevious();
			}

			nextIndex--;
			lastReturnedNode = nextNode;
			remove = true;
			return nextNode.getElement(); // returning element from the next node
		}
		@Override
		public int nextIndex() {
			if (modCount != iterModCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex;
		}
		@Override
		public int previousIndex() {
			if (modCount != iterModCount) {
				throw new ConcurrentModificationException();
			}
			return nextIndex - 1;
		}
		@Override
		public void set(T e) {
			if (modCount != iterModCount) {
				throw new ConcurrentModificationException();
			}
			if (!remove) {
				throw new IllegalStateException();
			}
			lastReturnedNode.setElement(e);
			modCount++;
			iterModCount = modCount;
		}
		@Override
		public void add(T e) {
			if (modCount != iterModCount) {
				throw new ConcurrentModificationException();
			}
			try {
				DLLNode<T> temp = new DLLNode<T>(e); // newDLLNode
				if (size() == 0) {
					head = tail = temp;
					nextNode = head.getNext();
				} else if (nextNode == null) { //when the pointer reaches the end of the list
					tail.setNext(temp);
					temp.setPrevious(tail);
					tail = temp;
				} else if (nextNode.equals(head)) { //when the pointer start of the list
					temp.setNext(nextNode);
					nextNode.setPrevious(temp);
					nextNode = temp.getNext();
					head = temp;
				} else {
					temp.setNext(nextNode);
					nextNode.getPrevious().setNext(temp);
					temp.setPrevious(nextNode.getPrevious());
					nextNode.setPrevious(temp);
				}
				nextIndex++;
				remove = false;
				size++;
				modCount++;
				iterModCount = modCount;
			}catch (Exception exception) {
				throw new IllegalArgumentException(exception.getMessage());
			}
		}
	}
	/**
	 * Node to insert in DoubleLinked
	 * @author Saman Rastgar
	 *
	 * @param <T>
	 */
	private class DLLNode<T> {
		private DLLNode<T> next; // reference to next node
		private DLLNode<T> previous; // reference to previous node
		private T element; // reference to object stored in node

		/**
		 * Constructor - with given element
		 * 
		 * @param element
		 *            - object of type T
		 */
		public DLLNode(T element) {
			setElement(element);
			setNext(null);
		}
		/**
		 * Returns reference to next node
		 * 
		 * @return - ref to DLLNode<T> object
		 */
		public DLLNode<T> getNext() {
			return next;
		}
		/**
		 * Returns reference to previous node
		 * 
		 * @return - ref to DLLNode<T> object
		 */
		public DLLNode<T> getPrevious() {
			return previous;
		}
		/**
		 * Assign reference to next node
		 * 
		 * @param next
		 *            - ref to Node<T> object
		 */
		public void setNext(DLLNode<T> next) {
			this.next = next;
		}
		/**
		 * Assign reference to previous node
		 * 
		 * @param next
		 *            - ref to Node<T> object
		 */
		public void setPrevious(DLLNode<T> previous) {
			this.previous = previous;
		}

		/**
		 * Returns reference to node stored in node
		 * 
		 * @return - ref to object of type T
		 */
		public T getElement() {
			return element;
		}
		/**
		 * Sets reference to element stored at node
		 * 
		 * @param element
		 *            - ref to object of type T
		 */
		public void setElement(T element) {
			this.element = element;
		}
	}
}
