package javaBase.集合.Iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TODO 1. Iterator 接口包含 4 个方法：
 *      public interface Iterator<E>
 *      {
 *            E next();
 *            boolean hasNext();
 *            void remove();
 *            default void forEachRemaining(Consumer<? super E> action);
 *      }
 *
 *TODO 2.通过反复调用 next 方法，可以逐个访问集合中的每个元素。但是，如果到达了集合的末尾，next 方法将抛出一个 NoSuchElementException。
 *TODO 3.因此，需要在调用 next 之前调用 hasNext方法，来判断是否还有剩余元素存在。
 *TODO   Iterator 接口的 remove 方法将会删除上次调用 next 方法时返回的元素。
 * （在大多数情况下，在决定删除某个元素之前应该先看一下这个元素是很具有实际意义的。）
 *TODO   对 next 方法和 remove 方法的调用具有互相依赖性。如果调用 remove 之前没有调用next 将是不合法的
 *
 *      Collection<String> c = . . .;
 *      Iterator<String> iter = c.iterator()；
 *      while (iter.hasNextO)
 *      {
 *          String element = iter.next0；
 *          do something with element
 *      }
 * 用“foreach” 循环可以更加简练地表示同样的循环操作：
 *      for (String element : c)
 *      {
 *           do something with element
 *      }
 *  编译器简单地将“ foreach” 循环翻译为带有迭代器的循环。
 * TODO 4.for each” 循环可以与任何实现了 Iterable 接口的对象一起工作
 * TODO 5.Collection 接口扩展了 Iterable 接口。因此， 对于标准类库中的任何集合都可以使用“ for each” 循环。
 *
 * TODO 6.在 Java SE 8中，甚至不用写循环。可以调用 forEachRemaining 方法并提供一 lambda表达式（它会处理一个元素）。
 * TODO       将对迭代器的每一个元素调用这个 lambda 表达式，直到再没有元素为止。
 * TODO       iterator.forEachRemaining(element -> do something with element);
 *
 *
 * TODO Iterator 接口的 next 和 hasNext 方法与 Enumeration 接口的nextElement 和 hasMoreElements 方法的作用一样。
 * TODO Java 集合类库的设计者可以选择使用Enumeration 接口。但是，他们不喜欢这个接口累赘的方法名， 于是引入了具有较短方法名的新接口。
 *
 *
 * 为了避免对链表完成随机访问操作，Java SE 1.4 引入了一个标记接口 RandomAccess。
 * 这个接口不包含任何方法， 不过可以用它来测试一个特定的集合是否支持高效的随机访问：
 * if (c instanceof RandomAccess)
 * {
 * use random access algorithm
 * }
 * else{
 * use sequential access algorithm
 * }
 * Set 接口等同于 Collection 接口，不过其方法的行为有更严谨的定义。
 */
public class IteratorDesc {

    @Test
    public void forEachRemainingTest(){
        List<String> list = new ArrayList<>();
        list.add("zhangsan1");
        list.add("zhangsan2");
        list.add("zhangsan3");

        Iterator<String> iterator = list.iterator();
        iterator.forEachRemaining(System.out::println);

        list.forEach(System.out::println);
    }
}
