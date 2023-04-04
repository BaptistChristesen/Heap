import java.util.ArrayList;

/**
 * Write a description of class b here.
 *
 * @author  (your name here)
 * @version (today's date)
 */
public class Heap<T extends Comparable<T>> implements PriorityQueue<T> {

    private ArrayList<T> heapList;

    public Heap() {
        heapList = new ArrayList<T>();
    }

    public T add(T item) {
        heapList.add(item);
        int hole = heapList.size() - 1;
        reheapUp(hole);
        return item;
    }

    public T remove() throws PQUnderflowException {
        if (isEmpty()) {
            throw new PQUnderflowException();
        }
        T maxItem = heapList.get(0);
        T lastItem = heapList.remove(heapList.size() - 1);
        if (heapList.size() > 0) {
            heapList.set(0, lastItem);
            reheapDown(0);
        }
        return maxItem;
    }

    public boolean isEmpty() {
        return heapList.isEmpty();
    }

    private void reheapUp(int hole) {
        T item = heapList.get(hole);
        int parent = (hole - 1) / 2;
        while (hole > 0 && item.compareTo(heapList.get(parent)) > 0) {
            heapList.set(hole, heapList.get(parent));
            hole = parent;
            parent = (hole - 1) / 2;
        }
        heapList.set(hole, item);
    }
    public void clear() {
        heapList.clear();
    }
    public T peek() throws PQUnderflowException {
        if (isEmpty()) {
            throw new PQUnderflowException();
        }
        return heapList.get(0);
    }
    public boolean empty() {
        return heapList.isEmpty();
    }
    public int size() {
        return heapList.size();
    }
    private void reheapDown(int hole) {
        T item = heapList.get(hole);
        int child = 2 * hole + 1;
        while (child < heapList.size()) {
            if (child < heapList.size() - 1 && heapList.get(child + 1).compareTo(heapList.get(child)) > 0) {
                child++;
            }
            if (heapList.get(child).compareTo(item) > 0) {
                heapList.set(hole, heapList.get(child));
                hole = child;
                child = 2 * hole + 1;
            } else {
                break;
            }
        }
        heapList.set(hole, item);
    }
}

class PQUnderflowException extends Exception {
    public PQUnderflowException() {
        super("Priority queue is empty.");
    }
}
