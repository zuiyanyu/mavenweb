package javaBase.集合.位集;

import java.util.BitSet;

/**
 * Java 平台的 BitSet 类用于存放一个位序列（它不是数学上的集，称为位向量或位数组更
 * 为合适)。如果需要高效地存储位序列（例如，标志）就可以使用位集。由于位集将位包装在
 * 字节里， 所以，使用位集要比使用 Boolean 对象的 ArrayList 更加高效。
 * BitSet 类提供了一个便于读取、设置或清除各个位的接口。使用这个接口可以避免屏蔽
 * 和其他麻烦的位操作。如果将这些位存储在 int 或丨ong 变量中就必须进行这些繁琐的操作。
 * 例如，对于一个名为 bucketOfBits 的 BitSet,
 * bucketOfBits.get(i)
 * 如果第 i 位处于“ 开” 状态，就返回 true; 否则返回 false。同样地，
 * bucketOfBits.set(i)
 * 将第 i 位置为“ 开” 状态。最后，
 * bucketOfBits.cl ear⑴
 * 将第 i 位置为“ 关” 状态。
 *
 * java.util.BitSet 1.0
 *
 * • BitSet( int initialCapacity)
 * 创建一个位集。
 * • int length( )
 * 返回位集的“ 逻辑长度”， 即 1 加上位集的最高设置位的索引。
 * • boolean get(int bit )
 * 获得一个位。
 * • void set(int bit )
 * 设置一个位。
 * • void clear(int bit )
 * 清除一个位。
 * • void and( BitSet set )
 * 这个位集与另一个位集进行逻辑“ AND”。
 * • void or(BitSet set )
 * 这个位集与另一个位集进行逻辑“ OR”。
 * • void xor(BitSet set )
 * 这个位集与另一个位集进行逻辑“ X0R”。
 * • void andNot(BitSet set )
 * 清除这个位集中对应另一个位集中设置的所有位。
 * 作为位集应用的一个示例，这里给出一个采用“ Eratosthenes 筛子” 算法查找素数的实
 * 现（素数是指只能被 1 和本身整除的数， 例如 2、 3 或 5,“ Eratosthenes 筛子” 算法是最早发
 * 现的用来枚举这些基本数字的方法之一。) 这并不是一种查找素数的最好方法，但是由于某
 * 种原因，它已经成为测试编译程序性能的一种流行的基准。（这也不是一种最好的基准测试方
 * 法，它主要用于测试位操作。）
 * 在此，将尊重这个传统，并给出实现。其程序将计算 2 - 2 000 000 之间的所有素数（一
 * 共有 148 933 个素数，或许不打算把它们全部打印出来吧。)
 * 这里并不想深人程序的细节， 关键是要遍历一个拥有 200 万个位的位集。首先将所有的
 * 位置为“ 开” 状态，然后，将已知素数的倍数所对应的位都置为“ 关” 状态。经过这个操作
 * 保留下来的位对应的就是素数。
 *
 */
public class Sieve {
    public static void main(String[] args) {
        int n = 2000000 ;
        BitSet bitSet = new BitSet(n);
        int count = 0 ;

        long startTime = System.currentTimeMillis();
        //开关都设置为 true
        for (int i = 0; i < n; i++) {
            bitSet.set(i);
        }
        //根据已知素数的倍数所在的位都进行关闭
        int i = 2;
        for ( ;i * i< n; i++) {
            if(bitSet.get(i)){
                //每个素数进来，数据量增加1
                count ++ ;
                //关闭i 的 2倍，3倍，4倍... 等所在位
                int k = 2 * i ;
                while(k<=n){
                    bitSet.clear(k) ;
                    k += i ;
                }
            }
        }
        for(;i<=n;i++){
            if(bitSet.get(i)){
                count ++ ;
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("count = "+ count);
        System.out.println("endTime - startTime = " + (endTime - startTime));
    }
}
