package org.iterator;

import java.util.Iterator;
import java.util.List;

public final class Box implements Iterable<String> {

    private final List<String> list1 = List.of("1", "2", "3");
    private final List<String> list2 = List.of("One", "Two", "Three");
    private final List<String> list3 = List.of("Один", "Два", "Три");
    private final List<String> list4 = List.of("Uno", "Dos", "Tre");

    public Iterator<String> iterator() {
        return new BoxIterator(this);
    }

    private static class BoxIterator implements Iterator<String> {

        private final Box box;
        private int currentListIndex;
        private Iterator<String> currentListIterator;

        public BoxIterator(Box box) {
            this.box = box;
            this.currentListIndex = 0;
            this.currentListIterator = box.list1.iterator();
        }

        @Override
        public boolean hasNext() {
            if (currentListIterator.hasNext()) {
                return true;
            } else {
                currentListIndex++;
                if (currentListIndex < 4) {
                    switch (currentListIndex) {
                        case 1:
                            currentListIterator = box.list2.iterator();
                            break;
                        case 2:
                            currentListIterator = box.list3.iterator();
                            break;
                        case 3:
                            currentListIterator = box.list4.iterator();
                            break;
                        default:
                            break;
                    }
                    return true;
                } else {
                    return false;
                }
            }
        }

        @Override
        public String next() {
            return currentListIterator.next();
        }
    }
}
