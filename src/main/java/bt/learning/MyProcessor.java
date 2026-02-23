package bt.learning;

//@FunctionalInterface
public interface MyProcessor<T> {
    T process(T element);
    Tuple2<T, Integer> processAndCount(T element);
}
