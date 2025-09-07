package com.yangcq.dsa.lovedsa.s1.class02_arraylist;

/**
 * 动态数组，扩容结构
 *
 * @author yangcq6
 */
public class ArrayList<E> {

    private E[] data;
    private int size;

    private static final int DEFAULT_CAPACITY = 5;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        // 对象数组，数组存在堆空间中，每个元素存一个内容的地址
        // 地址大小是固定的，不同于各种对象的属性大小累加，导致存储空间长度不一致
        data = (E[]) new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int indexOf(E ele) {
        // 当前结构可以存储null值，特判
        if (ele == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (ele.equals(data[i])) return i;
            }
        }
        return -1;
    }

    public boolean contains(E ele) {
        return indexOf(ele) >= 0;
    }

    /**
     * 实现为逻辑上的清空，将size=0，后续的get,set等访问操作时
     * 无法获取到数据即可。语义上满足
     * 针对基本类型的数据，后续的add等操作，从0位置开始覆盖即可
     * 针对引用类型的数据，需要将data数组的元素清空，避免内存泄漏，让gc回收不被引用的对象
     */
    public void clear() {
        // gc
        for (int i = 0; i < size; i++) data[i] = null;
        size = 0;
    }

    private String outOfBoundsMessage(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMessage(index));
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMessage(index));
        }
    }

    public E get(int index) {
        rangeCheck(index);
        return data[index];
    }

    public E set(int index, E ele) {
        rangeCheck(index);
        E oldEle = data[index];
        data[index] = ele;
        return oldEle;
    }

    public void add(E ele) {
        // this.data[size++] = ele;
        this.add(size, ele);
    }

    private void ensureCapacity(int capacity) {
        int oldCap = data.length;
        // 当前data长度刚好够用，不进行扩容操作
        if (oldCap >= capacity) return;

        int newCap = oldCap + (oldCap >> 1);
        E[] newData = (E[]) new Object[newCap];
        for (int i = 0; i < size; i++) newData[i] = data[i];
        data = newData;
        System.out.printf("ensureCapacity: %d --> %d\n", oldCap, newCap);
    }

    public void add(int index, E ele) {
        rangeCheckForAdd(index);

        // 确保当前data[]长度够用
        ensureCapacity(size + 1);

        // 将index及其之后的元素后移，空出index位置，赋值为ele
        // [index+1,size]
        for (int i = size; i > index; i--) data[i] = data[i - 1];
        data[index] = ele;
        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldEle = data[index];
        // 将index后边的元素都前移
        // [index+1, size-1]
        for (int i = index + 1; i < size; i++) data[i - 1] = data[i];
        // 针对基本数据类型，原来的最后一个元素不处理
        // 针对引用数据类型，最后一个元素清空，需要gc
        size--;
        data[size] = null;
        return oldEle;
    }

    public void remote(E ele) {
        remove(indexOf(ele));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0) sb.append(", ");
            sb.append(data[i]);
            // if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
