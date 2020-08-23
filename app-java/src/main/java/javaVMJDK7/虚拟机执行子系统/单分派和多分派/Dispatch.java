package javaVMJDK7.虚拟机执行子系统.单分派和多分派;

/**
 * 单分派,多分派演示
 * 1.方法的接收者和方法的参数统称为方法的宗量。  定义来源《java 与模式》一书
 * 2.根据分派基于多少种宗量，可以将分派分为 单分派 和 多分派 两种。
 * 3.单分派是根据一个宗量对目标方法进行选择。
 * 4.多分派是根据多于一个宗量对目标方法进行选择。
 *
 */
public class Dispatch {
    static class QQ{}
    static class _360{}

    public static class Father{
        public void hardChoice( QQ arg){
            System.out.println( "father choose qq");
        }
        public void hardChoice( _360 arg){
            System.out.println( "father choose 360");
        }}
    public static class Son extends Father{
        public void hardChoice( QQ arg){
            System.out.println( "son choose qq");
        }
        public void hardChoice( _360 arg){
            System.out.println( "son choose 360");
        }}
    public static void main( String[]args){

        Father father=new Father( );
        Father son=new Son( );

        //TODO main函数调用了两次hardChoice()方法
        //TODO 1.我们来看看编译阶段编译器的选择过程，也就是静态分派的过程：
        /**
         * 这时候，选择目标方法的依据有两点：
         * a. 静态类型是Father 还是Son .
         * b. 方法参数是QQ 还是 _360
         *
         * 最终产物是产生了两条invokevirtual 指令，两条指令的参数分别为 常量池中指向
         * Father.hardChoice(360) 和 Father.hardChoice(QQ) 方法的符号引用。
         *
         * 因为是根据两个总量进行的选择，所以java语言的静态分派属于多分派类型。
         */

        //TODO 2. 再看看运行阶段虚拟机的选择，也就是动态分派的过程。
        /**
         * 在执行son.hardChoice(new QQ()) 代码的时候，更准确说，是执行这句话对应的 invokevirtual 指令时，
         * 由于编译期已经确定目标方法的签名必须为hardChoice(QQ),虚拟机此时不关心传递过来 QQ的实际类型，只关心QQ这个静态类型，
         * 故：唯一可以影响虚拟机的因素只有此方法的接受者的实际类型是Father 还是Son。
         *
         * 因为只有一个总量作为选择依据，所以java语言的动态分派属于单分派类型。
         */

        //TODO 3. 根据上述论证结果，我们可以总结一句：今天(直到Java 1.8)的java语言是一门静态多分派，动态单分派的语言。

        father.hardChoice( new _360( ));
        son.hardChoice( new QQ( ));
    }}



















