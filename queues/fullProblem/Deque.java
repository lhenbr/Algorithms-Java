/* *****************************************************************************
 *  Name: Lucas Henrique Braga Martins
 *  Date:17/05/2020
 *  Description: A class to implementate a double-ended queue or deque
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int counter;


    private class Node {
        Item item;
        Node next, prev;
    }


    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        counter = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return counter == 0;
    }

    // return the number of items on the deque
    public int size() {
        return counter;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            first.prev = null;
            oldFirst.prev = first;
        }
        counter++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            oldLast.next = last;
            last.next = null;
        }

        counter++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (counter == 0) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        counter--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (counter == 0) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.prev;
        counter--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return (current != null);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more objects to iterate through");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deq = new Deque<String>();
        deq.addFirst("A");
        deq.addLast("Z");
        System.out.println(deq.isEmpty());
        System.out.println(deq.size());

        System.out.println(deq.removeFirst());
        System.out.println(deq.removeLast());
        System.out.println(deq.isEmpty());

    }

}

