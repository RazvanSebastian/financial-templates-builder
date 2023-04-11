package util;

@FunctionalInterface
public interface BiSupplier<T, V> {

    void apply(T t, V v);
}
