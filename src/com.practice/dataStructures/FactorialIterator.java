package dataStructures;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class FactorialIterator implements Iterator<Integer>, Iterable<Integer> {

    private final Integer capacity;
    private Integer pos;
    private Integer fac;

    public FactorialIterator(Integer capacity) {
        this.capacity = capacity;
        this.pos = 1;
        this.fac = 1;
    }

    @Override
    public boolean hasNext() {
        return this.pos <= this.capacity;
    }

    @Override
    public Integer next() {
        if(!hasNext()) return 0;
        fac *= pos;
        pos++;
        return fac;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FactorialIterator(capacity);
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Objects.requireNonNull(action);
        Integer last = 0;
        for(Integer i : this){
            last = i;
        }
        action.accept(last);
    }
}
