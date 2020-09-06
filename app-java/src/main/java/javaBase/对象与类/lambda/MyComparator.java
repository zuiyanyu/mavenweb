package javaBase.对象与类.lambda;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface MyComparator<T> extends Comparator<T> {
    int compare(T o1, T o2);
    boolean equals(Object obj);
    default Comparator<T> reversed() {
        return Collections.reverseOrder(this);
    }
    default Comparator<T> thenComparing(Comparator<? super T> other) {
        Objects.requireNonNull(other);
        return (Comparator<T> & Serializable) (c1, c2) -> {
            int res = compare(c1, c2);
            return (res != 0) ? res : other.compare(c1, c2);
        };
    }
    default <U> Comparator<T> thenComparing(
            Function<? super T, ? extends U> keyExtractor,
            Comparator<? super U> keyComparator)
    {
        return thenComparing(comparing(keyExtractor, keyComparator));
    }

    @Override
    default <U extends Comparable<? super U>> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor) {
        return null;
    }

    public static <T, U> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor,
            Comparator<? super U> keyComparator)
    {
        Objects.requireNonNull(keyExtractor);
        Objects.requireNonNull(keyComparator);
        return (Comparator<T> & Serializable)
                (c1, c2) -> keyComparator.compare(keyExtractor.apply(c1),
                        keyExtractor.apply(c2));
    }
    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable)
                (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }


}
