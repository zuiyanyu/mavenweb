package javaBase.多线程.线程安全的集合04;

/**
 * TODO CopyOnWriteArrayList 和 CopyOnWriteArraySet 是线程安全的集合,其中所有的修改线程对底层数组进行复制。
 * TODO 如果在集合上进行迭代的线程数超过修改线程数， 这样的安排是很有用的。
 *
 * TODO  当构建一个迭代器的时候， 它包含一个对当前数组的引用。
 * TODO  如果数组后来被修改了，迭代器仍然引用旧数组， 但是，集合的数组已经被替换了。
 *
 * TODO 因而，旧的迭代器拥有一致的（可能过时的）视图，访问它无须任何同步开销。
 *
 * TODO 写时拷贝源码：
 * // 写操作  ：写的时候拷贝一份数组，完成操作后替换旧数组
 * TODO blic E set(int index, E element) {
 * TODO       final ReentrantLock lock = this.lock;
 * TODO       lock.lock();
 * TODO       try {
 * TODO           Object[] elements = getArray();
 * TODO           E oldValue = get(elements, index);
 *
 * TODO           if (oldValue != element) {
 * TODO               int len = elements.length;
 * TODO               Object[] newElements = Arrays.copyOf(elements, len);
 * TODO               newElements[index] = element;
 * TODO               setArray(newElements);
 * TODO           } else {
 * TODO               // Not quite a no-op; ensures volatile write semantics
 * TODO               setArray(elements);
 * TODO           }
 * TODO           return oldValue;
 * TODO       } finally {
 * TODO           lock.unlock();
 * TODO       }
 * TODO   }
 *
 *    final Object[] getArray() {
 *         return array;
 *     }
 *
 * //读操作,会复制一份当前数组的引用进行读取数组
 *TODO   private E get(Object[] a, int index) {
 *TODO      return (E) a[index];
 *TODO   }
 *TODO   public E get(int index) {
 *TODO        return get(getArray(), index);
 *TODO    }
 *
 *
 *
 */
public class 写时拷贝的数组_05 {

}
