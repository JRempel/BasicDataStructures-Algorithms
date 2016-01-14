package doublyLinkedList;

public class LinkedList<T extends Node<T>> {
	private int size;
	private Node<T> front;
	private Node<T> end;

	public LinkedList() {
		size = 0;
	}

	public int getSize() {
		return size;
	}
	
	public Node<T> getFront() {
		return front;
	}
	
	public Node<T> getEnd() {
		return end;
	}

	public void addToEnd(Node<T> node) {
		if (getSize() == 0) {
			front = node;
			end = node;
		} else {
			node.setPrev(end);
			end.setNext(node);
			end = node;
		}
		size++;
	}

	public void addToFront(Node<T> node) {
		if (getSize() == 0) {
			front = node;
			end = node;
		} else {
			node.setNext(front);
			front.setPrev(node);
			front = node;
		}
		size++;
	}
	
	public void insertAfter(Node<T> node, int pos) {
		if (pos >= size)
			return;
		else {
			Node<T> curr = getFront();
			for (int i = 0; i < size; i++) {
				curr = curr.getNext();
			}
			node.setNext(curr.getNext());
			curr.setNext(node);
			if (node.getNext() == null)
				end = node;
			size++;
		}
	}
	
	public void removeFront() {
		if (getSize() == 0)
			return;
		else
			front = front.getNext();
		size--;
	}
	
	public void removeEnd() {
		if (getSize() == 0)
			return;
		else
			end = end.getPrev();
		size--;
	}
	
	public void remove(int pos) {
		
	}
}
