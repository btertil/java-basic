package bt.learning;

@FunctionalInterface
public interface MyProcessor<T> {
    public T process(T element);
}
