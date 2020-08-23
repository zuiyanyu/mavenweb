package javaVMJDK7.虚拟机执行子系统.静态分派和动态分派;

/**
 * 动态分派：
 * 它和多态性的重写(Override) 有着很密切的关联。
 */
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        //TODO 1.现在的问题是：虚拟机是如何知道要调用哪个方法的？
        //TODO 显然这里不可能是再根据静态类型决定的，而是根据变量的实际类型来决定的。
        //TODO 2.虚拟机是如何根据实际类型来分派方法执行版本的呢？
        //TODO 这就要从invokevirtual指令的多态查找过程开始说起了：
        /**
         *  查找过程：
         * 1. 找到操作数栈顶的第一个元素所指向的对象的实际类型，记作C.
         * 2. 如果在类型C中找到与常量中的描述符和简单名称都相符合的方法：
         *     则进行访问权限校验：
         *     a. 如果访问权限校验通过，则返回这个方法的直接引用，查找过程结束。
         *     b. 如果访问权限校验不通过，则返回java.lang.IllegalAccessError异常。
         * 3. 如果在类型C中没找到符合的方法，那么按照继承关系从上往下依次对C的各个父类进行第2步的搜索和验证过程。
         *
         * 由于invodevirtual 指令执行的第一步就是在运行期确定接收者的实际类型，所以调用中的invokevirtual指令把常量池中的
         * 类方法符号引用解析到了不同的直接引用上，这个过程就是java语言中方法重写的本质。
         * 我笨包这种在运行期根据实际类型确定方法执行版本的分派过程称为 “动态分派”
         */
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();

        man = new Woman();
        man.sayHello();
    }
}
