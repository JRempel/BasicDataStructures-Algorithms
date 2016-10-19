package skipSet;

public class RootNode<T> {
    private T data;
    private RootNode<T> next;
    private RootNode<T> prev;

    public RootNode() {
        data = null;
        next = null;
        prev = null;
    }

    public RootNode(T data) {
        this.data = data;
        next = null;
        prev = null;
    }

    public T getData() {
        return data;
    }

    public RootNode<T> getNext() {
        return next;
    }

    public RootNode<T> getPrev() {
        return prev;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(RootNode<T> next) {
        this.next = next;
        next.setPrev(this);
    }

    public void setPrev(RootNode<T> prev) {
        this.prev = prev;
        prev.setNext(this);
    }

    public String toString() {
        return data.toString();
    }
}