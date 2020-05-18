/* *****************************************************************************
 *  countame: Lucas Henrique Braga Martins
 *  Date: 17/05/2020
 *  Description: Randomized
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
        count = 0;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++)
            copy[i] = s[i];
        s = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {

        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (count == s.length) resize(2 * s.length);
        if (count > 2) {
            int randon = StdRandom.uniform(count);
            Item temp = s[randon];
            s[randon] = item;
            s[count++] = temp;
        }
        else s[count++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (count == 0) throw new NoSuchElementException();
        if (count > 0 && count == s.length / 4) resize(s.length / 2);
        return s[--count];
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (count == 0) throw new NoSuchElementException();
        int random = StdRandom.uniform(count);
        return s[random];
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = count;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[--i];
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rdq = new RandomizedQueue<>();
        rdq.enqueue(1);
        rdq.enqueue(2);
        rdq.enqueue(3);
        rdq.enqueue(4);
        rdq.enqueue(5);
        rdq.enqueue(6);

        System.out.println(rdq.sample());
        System.out.println(rdq.sample());
        System.out.println(rdq.sample());

        System.out.println(rdq.dequeue());
        System.out.println(rdq.dequeue());
        System.out.println(rdq.dequeue());
        System.out.println(rdq.dequeue());
    }

}
