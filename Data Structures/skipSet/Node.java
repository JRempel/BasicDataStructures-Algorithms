package skipSet;

public class Node<T> {
    private Node<T> next;
    private Node<T> prev;
    private Node<T> child;
    private RootNode<T> rootNode;

    public Node<T> getChild() {
        return child;
    }

    public void setChild(Node<T> child) {
        this.child = child;
    }

    public Node(RootNode<T> root) {
        rootNode = root;
        next = null;
        prev = null;
        child = null;
    }

    public T getData() {
        return rootNode.getData();
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
        next.setPrev(this);
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void clear() {
        this.child = null;
        this.next = null;
        this.prev = null;
        this.rootNode = null;
    }

    public String toString() {
        return rootNode.getData().toString();
    }
}