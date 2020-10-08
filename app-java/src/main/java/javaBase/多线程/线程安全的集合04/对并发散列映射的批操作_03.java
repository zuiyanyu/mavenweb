package javaBase.多线程.线程安全的集合04;

import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO 1. Java SE 8 为并发散列映射提供了批操作，即使有其他线程在处理映射，这些操作也能安全地执行
 * TODO 2. 批操作会遍历映射，处理遍历过程中找到的元素。无须冻结当前映射的快照。
 * TODO 3. 除非你恰好知道批操作运行时映射不会被修改， 否则就要把结果看作是映射状态的一个近似。
 * TODO 4. 有 3 种不同的操作：
 *          •搜索（search)
 *               为每个键或值提供一个函数，直到函数生成一个非 null 的结果。然后搜索终止，返回这个函数的结果。
 *          •归约（reduce)
 *               组合所有键或值， 这里要使用所提供的一个累加函数。
 *          •forEach
 *                为所有键或值提供一个函数。
 * TODO 5. 每个操作都有 4 个版本：
 *          •operationKeys: 处理键。
 *          •operatioriValues: 处理值。
 *          •operation: 处理键和值。
 *          •operatioriEntries: 处理 Map.Entry 对象。
 *
 *TODO 6. 对于上述各个操作， 需要指定一个参数化阈值（parallelism threshold)。
 *      TODO 如果映射包含的元素多于这个阈值， 就会并行完成批操作。
 *      TODO 如果希望批操作在一个线程中运行， 可以使用阈值 Long.MAX_VALUE。
 *      TODO 如果希望用尽可能多的线程运行批操作，可以使用阈值 1。
 *
 * TODO 7.下面首先来看 search 方法。有以下版本：
 *      U searchKeys(long threshold,  Function<? super K, ? extends U> f)
 *      U searchVaiues(long threshold,Function<? super V, ? extends U> f)
 *      U search(long threshold, BiFunction<? super K, ? super V,? extends U> f)
 *      U searchEntries(long threshold, Function<Map.Entry<K, V>, ? extends U> f)
 *
 *      例如， 假设我们希望找出第一个出现次数超过 1000 次的单词。需要搜索键和值：
 *      String result = map.search(threshold, (k, v) -> v > 1000 ? k : null);
 *      result 会设置为第一个匹配的单词，如果搜索函数对所有输人都返回 null, 则 返 回 null。
 *
 *TODO 8. forEach方法有两种形式。
 * TODO 8.1 第一个只为各个映射条目提供一个消费者函数， 例如：
 * map.forEach(threshold,
 *              (k, v) -> System.out.println(k + " -> " + v));
 * TODO 8.2 第二种形式还有一个转换器函数， 这个函数要先提供， 其结果会传递到消费者：
 * map.forEach(threshold,
 *                  (k, v)-> k + " -> " + v，// Transformer
 *                  System.out::println); // Consumer
 *TODO 8.3 转换器可以用作为一个过滤器。 只要转换器返回 null, 这个值就会被悄无声息地跳过。
 * 例如，下面只打印有大值的条目：
 * map.forEach(threshold,
 *       (k, v) -> v > 1000 ? k + "-> " + v : null , // Filter and transformer
 *       System.out::println); // The nulls are not passed to the consumer
 *
 * TODO 9. reduce 操作用一个累加函数组合其输入。
 * 例如，可以如下计算所有值的总和：
 * Long sum = map.reduceValues(threshold, Long::sum);
 *
 * TODO 9.1 与 forEach 类似，也可以提供一个转换器函数。
 * 可以如下计算最长的键的长度：
 * Integer maxlength = map.reduceKeys(  threshold,
 *                                      String::length, // Transformer
 *                                      Integer::max); // Accumulator
 *TODO 9.2 转换器可以作为一个过滤器，通过返回 null 来排除不想要的输入。null 这个值就会被悄无声息地跳过。
 * 在这里，我们要统计多少个条目的值 > 1000:
 * Long count = map. reduceValues(threshold,
 *                                  v -> v > 1000 ? 1L : null ,
 *                                  Long::sum) ;
 *
 *TODO 10 .注：如果映射为空， 或者所有条目都被过滤掉， reduce 操作会返回 null。如果只有一个元素， 则返回其转换结果， 不会应用累加器。
 *TODO 11 . 对于 int、 long 和 double 输出还有相应的特殊化操作， 分别有后缀 Tolnt、 ToLong 和ToDouble
 * 需要把输入转换为一个基本类型值，并指定一个默认值和一个累加器函数。映射为空时返回默认值。
 * long sum = map.reduceValuesToLong(threshold,
 *              Long::longValue, // Transformer to primitive type
 *              0, // Default value for empty map
 *              Long::sum); // Primitive type accumulator
 *
 *
 *
 *
 */
public class 对并发散列映射的批操作_03 {
    public static void main(String[] args) {
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>() ;
        map.put("zhangsan",2);
        map.put("lisi",4);
        map.put("wangwu",4);
        map.put("zhaoliu",6);

        int parallelismThreshold = map.size();
        String search = map.search(parallelismThreshold, (k, v) -> v > 3 ? k + "->" + v : null);
        System.out.println("search: "+search);

        map.forEach(parallelismThreshold, (k, v) -> v > 3 ? k + "->" + v : null , System.out::println);

        Integer integer = map.reduceValues(parallelismThreshold, Integer::sum);
        System.out.println("Integer::sum:"+integer);

        Integer integer1 = map.reduceValues(parallelismThreshold, v -> v, Integer::sum);
        System.out.println("map.reduceValues:"+integer1);

    }
}
