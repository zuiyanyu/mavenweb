package javaBase.泛型;

import javaBase.domain.Employee;
import javaBase.domain.Manager;
import javaBase.domain.Pair;
import org.junit.Test;

/**
 * TODO 直观地讲，带有超类型限定的通配符可以向泛型对象写入，带有子类型限定的通配符可以从泛型对象读取。
 */
public class Tong_pei_fu {

    /**
     * TODO *** 1. T: ? extends Employee   代表 T存储的是 所有Employee的子类类型(不包含Employee类型)。
     * 即对于T类，Employee类及其超类，都是T的超类。
     *TODO 这是设定泛型?参数的上限，给定的泛型类型只能是Employee的子类(不包含Employee类)
     *
     * 故，TODO 方法入参：? extends Employee  只能接收Employee的子类，但是又不确定是哪一个子类，也没有最小子类，所以无法接收入参
     *                      （Object是其最大父类，不能充当最小子类）
     *     TODO 方法出参：? extends Employee 的所有最小父类是Employee ，所以可以使用 Employee 类代替T，进行泛型擦除。
     * =====================================================================
     *TODO  public static void printBuddies(Pair<Employee> p)
     *TODO  {
     *TODO      Employee first = p.getFirst();
     *TODO      Employee second = p.getSecondO;
     *TODO      Systefn.out.println(first.getName() + " and " + second.getNameQ + " are buddies.");
     *TODO  }
     * TODO 不能将 Pair<Manager> 传递给这个方法，这一点很受限制.
     * TODO 解决的方法很简单：使用通配符类型： public static void printBuddies(Pair<? extends Eiployee> p)
     *      类型 Pair<Manager> 是 Pair<? extends Employee> 的子类型
     *      类型Pair<Employee> 也是 Pair<? extends Employee> 的子类型
     *      类型Pair<? extends Employee> 是 Pair的子类型 即 Pair<? extends Employee> 是Pair<Object>的子类
     *
     *      如果A是B的父类：
     *          对于C[? extend A]类型：
     *          1. C[? extend A] 是 C[B]的父类 ；
     *          2. 特殊的： C是 C[? extend A] 的父类，C也是C[B]的父类
     *
     *          对于C[? extend A]中的元素类型：
     *          3.  C[? extend A]  只能接收A类型的子类，但是代表子类型的？具体类型又无法具体确定下来。
     *                             只能确定这个类型的一个范围:无穷小，到A类型。
     *          4.  C[? extend A]  不能接收元素，或者只能接收null类型
     *          5.  C[? extend A]  中的元素只能被A类型接收，或是A父类接收。
     *
     *      //TODO Son extends Person
     *         List<? extends Son> list5 = new ArrayList<>();
     *         List<? super Son> list6 = new ArrayList<>();
     *         List<? super Person> list7 = new ArrayList<>();
     *         List<Person> list8 = new ArrayList<>();
     *         List<Object> list9 = new ArrayList<>() ;
     *         List list10 = new ArrayList() ;
     *
     *         list6 = list5 ; // 异常
     *         list7 = list5; // 异常
     *         list8 = list5; // 异常
     *         list9 = list5; // 异常
     *         list10 = list5 ; // ok
     *
     *
     *
     */
    @Test
    public void extendTest(){
        Manager ceo = new Manager(1);
        Manager cfo = new Manager(2);

        Pair<Manager> managerBuddies = new Pair(ceo, cfo) ;
        //只能访问不能设置：现在已经有办法区分安全的访问器方法和不安全的更改器方法了。
        Pair<? extends Employee> pair = managerBuddies ;

        //TODO ? extend 通配符的使用 ,可以使用返回值 ， 但不能为方法提供参数。
        Employee.printBuddies(managerBuddies);

        /*
          public ? extends Employee getFirst()
          将 getFirst 的返回值赋给一个 Employee 的引用完全合法。
         */
        Employee first = pair.getFirst();
        System.out.println("first = "+first);

        Employee employee = new Employee();
        /**
         * TODO public void setFirst(T)
         * TODO 变成了
         * TODO public void setFirst(? extends Employee) 这样将不可能调用 setFirst 方法。
         * TODO 编译器只知道需要某个 Employee 的子类型，但不知道具体是什么类型。它拒绝传递任何特定的类型。毕竟？不能用来匹配。
         *
         * TODO 使用 getFirst 就不存在这个问题
         */
        //pair.setFirst(employee);  //编译错误

        Pair<? extends Manager> pair2 = managerBuddies ;
        Manager first1 = pair2.getFirst();


    }

    /**
     * TODO ***  T: ? super ClassType 代表T是 ClassType类的超类类型，ClassType类 及其子类都是T的子类。
     * (不含ClassType类型，所以 set的时候，可以set ClassType 这个类型,因为也是超类T 的子类) 。
     * 即T超类
     * TODO 这是设定了泛型?的下限 , 给出的泛型类型必须 只能是 ClassType 类或其子类
     *
     * TODO 2.通配符的超类型限定 ：  ? super ClassType   比如 ： ? super   Manager
     * TODO 这个通配符限制为 Manager 的所有超类型。
     * TODO  可以为方法提供参数， 但不能使用返回值(非Object的，确定类型的返回值)。
     *      TODO 提供参数：即 ? super  Manager  接收 Manager以及Manager的子类，作为参数
     *       （T是Manager的超类，肯定可以自动接收所有Manager的子类）
     *      TODO 返回值  ：即使用 Manager 或者 Manager的超类来接收 ? super  Manager的值
     *      （T是Manager的超类，肯定只能用最大的超类Object类接收 其他Manager的超类。）
     *=====================================================================
     * 例如， Pair<? super Manager> 有方法
     *         public void setFirst(? super Manager)
     *         public ? super Manager getFirst()
     * 这不是真正的 Java 语法，但是可以看出编译器知道什么。编译器无法知道 setFirst 方法
     * 的具体类型， 因此调用这个方法时不能接受类型为 Employee 或 Object 的参数。 只能传递 Manager 类型的对象
     * ，或者某个子类型 （如 Executive) 对象。
     * 另外， 如果调用 getFirst, 不能保证返回对象的类型。只能把它赋给一个 Object
     */
    @Test
    public void superTest(){
        Manager ceo = new Manager(1);
        Manager cfo = new Manager(2);

        Pair<Employee> managerBuddies = new Pair(ceo, cfo) ;
        Pair<? super Employee> pair = managerBuddies ;

        //TODO  getFirst, 不能保证返回对象的类型。只能把它赋给一个 Object
        /**
         * ? super Manager getFirst() 只知道需要某个 Manager的超类来接收返回值，但是不知道具体的哪个超类，
         * 所以只能由 Object来接收
         */
        Object first = pair.getFirst();
        System.out.println(first);

        //TODO 可以确定的是，可以接收Employee类型，而 Manager 可以向上转型为 Employee类型，
        // TODO 所以 子类Manager 也就是Employee类型，所以 Employee的子类型可以传递。
        pair.setFirst(ceo);

        Employee employee = new Employee();
        pair.setFirst(employee);
         first = pair.getFirst();
        System.out.println(first);

        Pair<? super Manager> supperManager = managerBuddies ;
        /**
         * TODO 只能确定 可以接收 Manager的父类，但是不确定接收哪一个父类，它拒绝传递任何特定的父类型，所以无法接收 Employee 类型。
         */
        //supperManager.setFirst(employee);

    }

    /**
     * TODO 3.无限定通配符
     * 还可以使用无限定的通配符， 例如，Pair<?>。初看起来，这好像与原始的 Pair 类型一样。
     * 实际上， 有很大的不同。类型 Pair<?> 有以下方法：
     *       ? getFirst()
     *       void setFirst(?)
     * TODO getFirst 的返回值只能赋给一个 Object。
     * TODO setFirst 方法不能被调用， 甚至不能用 Object 调用。
     * Pair<?> 和 Pair 本质的不同在于： 可以用任意 Object 对象调用原始 Pair 类的 setObject方法。
     *
     * TODO 可以调用 setFirst(null)
     * 为什么要使用这样脆弱的类型？ 它对于许多简单的操作非常有用。例如，下面这个方法
     * 将用来测试一个 pair 是否包含一个 mill 引用，它不需要实际的类型。
     * TODO public static boolean hasNulls(Pair<?> p)
     *      {
     *         return p.getFirstO = null || p.getSecondO = null;
     *      }
     * 通过将 hasNulls 转换成泛型方法，可以避免使用通配符类型：
     * public static <T> boolean hasNulls(Pair<T> p)
     * 但是，带有通配符的版本可读性更强
     */
    public void swap(){
        Manager ceo = new Manager(1);
        Manager cfo = new Manager(2);

        Pair<Employee> managerBuddies = new Pair(ceo, cfo) ;

        // ---------------------------------
        Pair<?> p1 = managerBuddies ;
        Object first = p1.getFirst();



    }

    /**
     * TODO 4. 通配符捕获
     * 通配符捕获只有在有许多限制的情况下才是合法的。编译器必须能够确信通配符表达的
     * 是单个、 确定的类型。 例如， ArrayList<Pair<T>> 中的 T 永远不能捕获 ArrayList<Pair<?»
     * 中的通配符。数组列表可以保存两个 Pair<?>， 分别针对？的不同类型
     *
     * 实例
     * TODO 编写一个交换成对元素的方法：
     * @param p
     */
    public void swap(Pair<?> p){
        /** TODO 1.
         * TODO 通配符不是类型变量， 因此， 不能在编写代码中使用“ ？” 作为一种类型。
         * TODO 也就是说， 下述代码是非法的：
         */
        // ? t = p.getFirst(); // Error
        // p .setFirst (p.getSecond()) ;
        // p.setSecond(t) ;

        //TODO 2.这是一个问题， 因为在交换的时候必须临时保存第一个元素。

        /**
         * TODO 3.幸运的是， 这个问题有一个有趣的解决方案。我们可以写一个辅助方法 swapHelper
         * 现在可以由 swap 调用 swapHelper:
         */
        //通配符捕获
        swapHelp(p);
    }

    /**
     * swapHelper 是一个泛型方法，它具有固定的 Pair<?> 类型的参数
     *
     * 在这种情况下，swapHelper 方法的参数 T 捕获通配符。它不知道是哪种类型的通配符， 但是，
     * 这是一个明确的类型，并且 <T>swapHelper 的定义只有在 T 指出类型时才有明确的含义。
     * @param p
     * @param <T>
     */
    public  <T> void swapHelp(Pair<T> p){
         T t = p.getFirst();
         p .setFirst (p.getSecond()) ;
         p.setSecond(t) ;

    }

}
