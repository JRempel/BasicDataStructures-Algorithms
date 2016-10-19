package doublyLinkedList;

import java.util.HashSet;

public class LinkedList<T> {
	private int size;
	private Node<T> front;
	private Node<T> end;

	public LinkedList() {
		size = 0;
		front = null;
		end = null;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public Node<T> getFront() {
		return front;
	}

	public Node<T> getEnd() {
		return end;
	}

	// adds an item to the end of the list
	public void add(T data) {
		Node<T> node = new Node<T>(data);
		if (size() == 0) {
			front = node;
			end = node;
		} else {
			node.setPrev(end);
			end.setNext(node);
			end = node;
		}
		size++;
	}

	// adds an item to the front of the list
	public void addToFront(T data) {
		Node<T> node = new Node<T>(data);
		if (size() == 0) {
			front = node;
			end = node;
		} else {
			node.setNext(front);
			front.setPrev(node);
			front = node;
		}
		size++;
	}

	// inserts an item after the specified index
	public void insertAfter(T data, int pos) throws IndexOutOfBoundsException {
		if (pos >= size || pos < 0)
			throw new IndexOutOfBoundsException();
		else {
			Node<T> node = new Node<T>(data);
			Node<T> curr = getFront();
			for (int i = 0; i < pos; i++) {
				curr = curr.getNext();
			}
			node.setNext(curr.getNext());
			curr.setNext(node);
			if (node.getNext() == null)
				end = node;
		}
		size++;
	}

	public void removeFront() {
		if (size() == 0)
			return;
		if (size() == 1) {
			front = null;
			end = null;
		} else {
			front = front.getNext();
			front.setPrev(null);
		}
		size--;
	}

	public void removeEnd() {
		if (size() == 0)
			return;
		if (size() == 1) {
			front = null;
			end = null;
		} else
			end = end.getPrev();
		end.setNext(null);
		size--;
	}

	// removes an item at the given index
	public void remove(int pos) throws IndexOutOfBoundsException {
		if (pos >= size || pos < 0)
			throw new IndexOutOfBoundsException();
		else {
			if (pos == 0) {
				removeFront();
				return;
			}
			if (pos == size() - 1) {
				removeEnd();
				return;
			}
			Node<T> curr = getFront();
			for (int i = 0; i < pos - 1; i++) {
				curr = curr.getNext();
			}
			curr.setNext(curr.getNext().getNext());
			curr.getNext().setPrev(curr);
		}
		size--;
	}

	private void removeNode(Node<T> node) {
		if (node.getPrev() == null)
			removeFront();
		if (node.getNext() == null)
			removeEnd();
		else {
			node.getPrev().setNext(node.getNext());
			node.getNext().setPrev(node.getPrev());
		}
	}

	// gets the node at the given index
	private Node<T> get(int pos) throws IndexOutOfBoundsException {
		if (pos >= size || pos < 0)
			throw new IndexOutOfBoundsException();
		else {
			int i = 0;
			Node<T> curr = getFront();
			while (i < size) {
				if (i == pos)
					return curr;
				curr = curr.getNext();
				i++;
			}
			return null;
		}
	}

	// sets the data at the given index
	public void set(T data, int pos) throws IndexOutOfBoundsException {
		if (pos >= size || pos < 0)
			throw new IndexOutOfBoundsException();
		else {
			get(pos).setData(data);
		}
	}

	public void swap(int pos1, int pos2) throws IndexOutOfBoundsException {
		if (pos1 >= size || pos1 < 0 || pos2 >= size || pos2 < 0)
			throw new IndexOutOfBoundsException();
		else {
			Node<T> node1 = get(pos1);
			Node<T> node2 = get(pos2);
			T data = node1.getData();
			node1.setData(node2.getData());
			node2.setData(data);
		}
	}

	// reverses the list by swapping data items
	public void reverse() {
		if (isEmpty())
			return;
		Node<T> front = getFront();
		Node<T> back = getEnd();
		int counter = 0;
		int max = size() / 2;
		T tempData;
		while (counter < max) {
			tempData = front.getData();
			front.setData(back.getData());
			back.setData(tempData);
			front = front.getNext();
			back = back.getNext();
			counter++;
		}
	}

	// remove duplicate data items from the list
	public void removeDuplicates() {
		if (isEmpty())
			return;
		HashSet<T> items = new HashSet<T>();
		items.add(front.getData());
		Node<T> curr = front.getNext();
		while (curr != null) {
			if (!items.contains(curr.getData())) {
				items.add(curr.getData());
				curr = curr.getNext();
			} else {
				if (curr.getNext() == null)
					removeEnd();
				else {
					curr = curr.getNext();
					removeNode(curr.getPrev());
				}
			}
		}
	}

	public String toString() {
		String toReturn = "";
		Node<T> curr = getFront();
		while (curr != null) {
			toReturn += curr.getData().toString() + " -> ";
			curr = curr.getNext();
		}
		if (isEmpty())
			toReturn += "List is empty.";
		return toReturn;
	}
}
