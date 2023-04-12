package util;

@FunctionalInterface
public interface BiConsumer<T, V> {

    void accept(T t, V v);
}
