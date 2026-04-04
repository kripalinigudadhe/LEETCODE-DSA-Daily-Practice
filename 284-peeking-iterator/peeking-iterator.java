import java.util.Iterator;

class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private Integer nextElement; // stores next element

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        // initialize nextElement
        if (iterator.hasNext()) {
            nextElement = iterator.next();
        }
    }

    // Returns the next element without moving pointer
    public Integer peek() {
        return nextElement;
    }

    @Override
    public Integer next() {
        Integer result = nextElement;

        // move forward
        if (iterator.hasNext()) {
            nextElement = iterator.next();
        } else {
            nextElement = null;
        }

        return result;
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }
}