package javaBase.domain;

public class ArrayAlg {
    /**
     * 泛型的使用测试
     * @param a
     * @param <T>
     * @return
     */
     public static <T extends Comparable> Pair<T> minmax(T[] a)
     {
            if (a == null || a.length == 0) return null ;
            T min = a[0];
            T max = a[0];
            for (int i = 1; i < a.length;i++)
            {
                 if (min.compareTo(a[i]) > 0) min = a[i];
                 if (max.compareTo(a[i]) < 0) max = a[i];
            }
            return new Pair(min, max);
      }
}
