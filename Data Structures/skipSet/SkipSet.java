package skipSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SkipSet<T extends Comparable<T>> implements Iterable {
    private ArrayList<Node<T>> structure;
    private RootNode<T> base;
    private int size;
    private int probability = 50;

    public SkipSet() {
        structure = new ArrayList<>();
        base = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (base == null);
    }

    public int getSize() {
        return size;
    }

    public boolean add(T item) {
        if (!contains(item)) {
            int height = determineHeight();
            RootNode<T> rootNode = new RootNode<>(item);
            insertRoot(rootNode);
            Node<T> lastInserted= null;

            for (int i = 1; i < height; i++) {
                try {
                    Node<T> currentNode = structure.get(i);
                    Node<T> currentInsert = new Node<>(rootNode);
                    currentInsert.setChild(lastInserted);
                    insertNode(currentInsert, currentNode);
                    lastInserted = currentInsert;
                } catch (IndexOutOfBoundsException e) {
                    Node<T> currentInsert = new Node<>(rootNode);
                    structure.set(i, currentInsert);
                    lastInserted = currentInsert;
                }
            }
            return true;
        }
        return false;
    }

    public boolean remove(T item) {
        if (!contains(item)) {
            return false;
        } else {
            // Naive remove -> create findFirstOccurrence() later & vertical traversal...
            for (int i = 0; i < structure.size(); i++) {
                Node<T> currentNode = structure.get(i);
                while (currentNode.getData() != item){
                    currentNode = currentNode.getNext();
                }
                if (currentNode != null) {
                    if (currentNode.getNext() == null && currentNode.getPrev() == null) {
                        currentNode.clear();
                    }
                    if (currentNode.getPrev() == null) {
                        currentNode.getNext().setPrev(null);
                        currentNode.clear();
                    } else {
                        currentNode.getPrev().setNext(null);
                        currentNode.clear();
                    }
                }
            }
            RootNode<T> currentRoot = base;
            while(currentRoot.getData() != item) {
                currentRoot = currentRoot.getNext();
            }
            if (currentRoot.getNext() == null && currentRoot.getPrev() == null) {
                currentRoot.getNext().setPrev(currentRoot.getPrev());
                currentRoot.getPrev().setNext(currentRoot.getNext());
            }
        }
        size--;
        return true;
    }

    public boolean contains(T item) {
        if (size == 0) {
            // CASE: Root-list empty.
            return false;
        } else if (structure.size() == 0){
            // CASE: Structure empty, but root-list not empty.
            return rootSearch(item);
        } else {
            // CASE: Size of both root-list and structure are both > 0.
            Node<T> current = structure.get(structure.size() - 1);
            while (true) {
                if (item.compareTo(current.getData()) == 0) {
                    // CASE: Current node contains item.
                    return true;
                }
                if (current.getNext() == null && current.getPrev() == null ) {
                    // CASE: Current node is the only one on this level.
                    if (current.getChild() == null) {
                        // CASE: Current not is only one on level, and on base level.
                        return rootSearch(item);
                    }
                    current = current.getChild();
                } else if (item.compareTo(current.getData()) > 1) {
                    // CASE: Item is greater than current node.
                    if (current.getNext() != null) {
                        current = current.getNext();
                    } else {
                        if (current.getChild() == null) {
                            // CASE: is at the end of the Base level
                            return rootSearch(item);
                        }
                        current = current.getChild();
                    }
                } else if (item.compareTo(current.getData()) < 1) {
                    // CASE: Item is less than current node.
                    if (current.getPrev() != null) {
                        current = current.getPrev();
                    } else {
                        if (current.getChild() == null) {
                            // CASE: is at the beginning of the Base level
                            return rootSearch(item);
                        }
                        current = current.getChild();
                    }
                }
            }
        }
    }

    private boolean rootSearch(T item) {
        RootNode<T> currentNode = base;
        while (item.compareTo(currentNode.getData()) > 1 && currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        if (currentNode.getData() == item) {
            return true;
        }
        return false;
    }

    public void clear() {
        size = 0;
        base = null;
        structure = null;
    }

    private int determineHeight() {
        int height = 1;
        while (new Random().nextInt(100 + 1) > probability) {
            height++;
        }
        return height;
    }

    private void insertRoot(RootNode<T> node) {
        if (base == null) {
            base = node;
        } else {
            RootNode<T> currentNode = base;
            while (node.getData().compareTo(currentNode.getData()) > 1 && currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            if (currentNode.getPrev() == null) {
                currentNode.setPrev(node);
            } else if (currentNode.getNext() == null) {
                currentNode.setNext(node);
            } else {
                currentNode.getNext().setPrev(node);
                currentNode.setNext(node);
            }
        }
        size++;
    }

    private void insertNode(Node<T> node, Node<T> currentNode) {
        while (node.getData().compareTo(currentNode.getData()) > 1 && currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        if (currentNode.getPrev() == null) {
            currentNode.setPrev(node);
        } else if (currentNode.getNext() == null) {
            currentNode.setNext(node);
        } else {
            currentNode.getNext().setPrev(node);
            currentNode.setNext(node);
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private RootNode<T> current = base;

            @Override
            public boolean hasNext() {
                return (current != null && current.getNext() != null);
            }

            @Override
            public T next() {
                current = current.getNext();
                return current.getData();
            }
        };
    }
}
