package net.enot.dartasystem.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Енот on 01.05.2017.
 */
public class DIterator<T> {
    private Collection<T> collection;
    private Iterator<T> iterator;

    public DIterator(Collection<T> collection) {
        this.collection = collection;
        this.iterator = collection.iterator();
    }

    public T getNext() {
        if (!this.iterator.hasNext()) {
            this.iterator = this.collection.iterator();
        }
        return this.iterator.next();
    }
}