package java基础.对象与类.lambda;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * TODO ==================== 第三部分：lambda 中的构造器引用 =====================
 *构造器引用与方法引用很类似，只不过方法名为 new。例如，Person::new 是 Person 构造
 * 器的一个引用。哪一个构造器呢？ 这取决于上下文。假设你有一个字符串列表。可以把它转
 * 换为一个 Person 对象数组，为此要在各个字符串上调用构造器，调用如下：
 * ArrayList<String> names = . . .;
 * Stream<Person> stream = names.stream().map(Person::new);
 * List<Person> people = stream.col1ect(Col1ectors.toList());
 *
 * 我们将在卷 II 的第 1 章讨论 stream、 map 和 collect 方法的详细内容。就现在来说， 重
 * 点是 map 方法会为各个列表元素调用 Person(String) 构造器。 如果有多个 Person 构造器， 编
 * 译器会选择有一个 String 参数的构造器， 因为它从上下文推导出这是在对一个字符串调用构
 * 造器。
 * 可以用数组类型建立构造器引用。例如， int[]::new 是一个构造器引用，它有一个参数：
 * 即数组的长度。这等价于 lambda 表达式 x-> new int[x]c
 * TODO Java 有一个限制，无法构造泛型类型 T 的数组。数组构造器引用对于克服这个限制很有用。
 * TODO 表达式 new T[n] 会产生错误，因为这会改为 new Object[n]
 * 对于开发类库的人来说，这是一个问题。
 * 例如，假设我们需要一个 Person 对象数组。Stream 接口有一个 toArray 方法可以返回 Object 数组：
 * TODO Object[] people = stream.toArray()；
 * 不过，这并不让人满意。用户希望得到一个 Person 引用数组，而不是 Object 引用数组。
 *
 * 流库利用构造器引用解决了这个问题。可以把 Person[]::new 传入 toArray 方法：
 * TODO Person[] people = stream.toArray(Person[]::new):
 * toArray方法调用这个构造器来得到一个正确类型的数组。然后填充这个数组并返回。
 *
 *
 *
 */
public class Lambda_构造器引用 {
    public static void main(String[] args) {
         ArrayList<String> names =new ArrayList<>();
        names.add("张三");
        names.add("李四");

//        names.stream().map(x-> { return new Person(x);} );
        Stream<PersonAL> personALStream = names.stream().map(PersonAL::new);// Person必须提供一个有参的构造器

        for (String name : names) {
            System.out.println(name);
        }

        //TODO 流只能被一条分支使用，使用完毕有就不能再被使用了。因为使用完毕有流就自动关闭了。
        /*TODO 1.使用流打印信息*/
//        personALStream.forEach(System.out::println);

        /*TODO 2.流中的元素提取到 List集合中*/
//        List<PersonAL> collect = personALStream.collect(Collectors.toList( ));
//        for (PersonAL personAL : collect) {
//            System.out.println(personAL);
//        }

         /*TODO 转换为数组: 但是元素为Object类型*/
//        Object[] objects = personALStream.toArray();

        /*TODO 3.转换为数组：数组元素为指定的类型 */
        PersonAL[] personALS = personALStream.toArray(PersonAL[]::new);
        for (PersonAL personAL : personALS) {
            System.out.println("personAL.name = "+personAL.name);
        }
    }

}
class PersonAL{
     String name ;

    public PersonAL(){}

    //必须提供的
    public PersonAL(String name ){
        this.name = name ;
    }

    @Override
    public String toString() {
        return "PersonAL{" +
                "name='" + name + '\'' +
                '}';
    }
}
class PersonBL{
    String name ;
    public PersonBL(PersonAL person){
        System.out.println("PersonBL");
        this.name = person.name +"_BL";
    }
}