package bt.learning;

@FunctionalInterface
public interface MyProcessor<T> {
    T process(T element);
}
