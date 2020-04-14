package ru.otus.ovodkov.hw02;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * Собственная реализация ArrayList
 *
 * @author Ovodkov Sergey on 14.04.2020
 */
public class DIYArrayList<T> implements List<T>, RandomAccess, Serializable {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = new Object[0];
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = new Object[0];

    Object[] elementData;
    private int size;

    /**
     * Конструктор поумолчанию.
     */
    public DIYArrayList() {
        elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    /**
     * Конструктор с заданой размерностью
     *
     * @param initialCapacity Размер коллекции
     */
    public DIYArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity != 0) {
            throw new IllegalArgumentException("Неверный развер: " + initialCapacity);
        } else {
            this.elementData = EMPTY_ELEMENT_DATA;
        }
    }

    /**
     * Добавление элемента в коллекцию
     *
     * @param element Добавляемый элемент
     * @return Возвращает {@code true} если элемент успешно добавлен
     */
    @Override
    public boolean add(T element) {
        if (this.size == this.elementData.length) {
            this.elementData = this.grow();
        }
        this.elementData[this.size] = element;
        this.size += 1;
        return true;
    }

    /**
     * Получение элемента по указаному индексу
     *
     * @param index Индекс возвращаемого элемента
     * @return Элемент в указанной позиции
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, this.size);
        return (T) this.elementData[index];
    }

    /**
     * Заменяет элемент в указанной позиции коллекции на указанный элемент
     *
     * @param index   Позиция заменяемого элемента
     * @param element Новый элемент
     * @return Элемент ранее находившейся в указанной позиции
     */
    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, this.size);
        T oldValue = (T) this.elementData[index];
        this.elementData[index] = element;
        return oldValue;
    }

    /**
     * Получить количество элементов в коллекции
     *
     * @return Количество элементов в коллекции
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Получить итератор списка элементов коллекции
     *
     * @return Итератор списка элементов коллекции
     */
    @Override
    public ListIterator<T> listIterator() {
        return new DIYArrayList.ListItr(0);
    }

    /**
     * Возвращает массив содержащий все элементы коллекции
     *
     * @return Массив из элементов коллекции
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elementData, this.size);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    private Object[] grow(int minCapacity) {
        return this.elementData = Arrays.copyOf(this.elementData, this.newCapacity(minCapacity));
    }

    private Object[] grow() {
        return this.grow(this.size + 1);
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (this.elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
                return Math.max(10, minCapacity);
            } else if (minCapacity < 0) {
                throw new OutOfMemoryError();
            } else {
                return minCapacity;
            }
        } else {
            return newCapacity - 2147483639 <= 0 ? newCapacity : hugeCapacity(minCapacity);
        }
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        } else {
            return minCapacity > 2147483639 ? 2147483647 : 2147483639;
        }
    }

    static <T> T elementAt(Object[] es, int index) {
        return (T) es[index];
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int i, T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int i, int i1) {
        throw new UnsupportedOperationException();
    }

    private class ListItr extends DIYArrayList<T>.Itr implements ListIterator<T> {
        ListItr(int index) {
            super();
            this.cursor = index;
        }

        public boolean hasPrevious() {
            return this.cursor != 0;
        }

        public int nextIndex() {
            return this.cursor;
        }

        public int previousIndex() {
            return this.cursor - 1;
        }

        public T previous() {
            int i = this.cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            } else {
                Object[] elementData = DIYArrayList.this.elementData;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = i;
                    return (T) elementData[this.lastRet = i];
                }
            }
        }

        public void set(T e) {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            } else {
                try {
                    DIYArrayList.this.set(this.lastRet, e);
                } catch (IndexOutOfBoundsException var3) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        public void add(T e) {
            try {
                int i = this.cursor;
                DIYArrayList.this.add(i, e);
                this.cursor = i + 1;
                this.lastRet = -1;
            } catch (IndexOutOfBoundsException var3) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class Itr implements Iterator<T> {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return this.cursor != DIYArrayList.this.size;
        }

        public T next() {
            int i = this.cursor;
            if (i >= DIYArrayList.this.size) {
                throw new NoSuchElementException();
            } else {
                Object[] elementData = DIYArrayList.this.elementData;
                if (i >= elementData.length) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = i + 1;
                    return (T) elementData[this.lastRet = i];
                }
            }
        }

        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            } else {
                try {
                    DIYArrayList.this.remove(this.lastRet);
                    this.cursor = this.lastRet;
                    this.lastRet = -1;
                } catch (IndexOutOfBoundsException var2) {
                    throw new ConcurrentModificationException();
                }
            }
        }

        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            int size = DIYArrayList.this.size;
            int i = this.cursor;
            if (i < size) {
                Object[] es = DIYArrayList.this.elementData;
                if (i >= es.length) {
                    throw new ConcurrentModificationException();
                }

                while (i < size) {
                    action.accept(DIYArrayList.elementAt(es, i));
                    ++i;
                }

                this.cursor = i;
                this.lastRet = i - 1;
            }

        }
    }
}
