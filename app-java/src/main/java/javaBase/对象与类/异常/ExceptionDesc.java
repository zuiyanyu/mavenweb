package javaBase.对象与类.异常;

/**
 * TODO 1.异常有两种类型： 未检查异常和已检查异常。
 *   1.2 对于已检查异常， 编译器将会检查是否提供了处理器。 Class.forName 方法就是一个抛出已检查异常的例子
 *   1.1 然而， 有很多常见的异常， 例如， 访问 null 引用， 都属于未检查异常。
 *    Throwable   -> Error , Exception
 *    Exception   -> IOException ,RuntimeException
 *
 *   TODO RuntimeException：由程序错误导致的异常属于 RuntimeException ;
 *   TODO                   如果出现 RuntimeException 异常， 那么就一定是你的问题” 是一条相当有道理的规则。
 *   TODO IOException： 而程序本身没有问题， 但由于像 I/O 错误这类问题导致的异常属于其他异常
 *
 *   TODO Java 语 言 规 范 将 派 生 于 Error 类 或 RuntimeException 类的所有异常称为非受查( unchecked ) 异常，
 *   TODO 所有其他的异常称为受查（checked) 异常。
 *
 *
 *    派生于 RuntimeException 的异常包含下面几种情况：
 *       •错误的类型转换。
 *       •数组访问越界 i
 *       •访问 null 指针
 *       不是派生于 RuntimeException 的异常包括：
 *       •试图在文件尾部后面读取数据。
 *       •试图打开一个不存在的文件。
 *       •试图根据给定的字符串查找 Class 对象， 而这个字符串表示的类并不存在,，
 *
 */
public class ExceptionDesc {
}
