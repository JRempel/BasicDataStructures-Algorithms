package doublyLinkedList;

public class Node <T> {
	private T data;
	private Node<T> next;
	private Node<T> prev;
	
	public Node() {
		
	}
	
	public Node(T data) {
		this.data = data;
		next = null;
		prev = null;
	}
	
	public T getData() {
		return data;
	}
	
	public Node<T> getNext() {
		return next;
	}
	
	public Node<T> getPrev() {
		return prev;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public void setPrev(Node<T> end) {
		this.prev = end;
	}
	
}