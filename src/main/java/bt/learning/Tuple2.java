package bt.learning;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Tuple2<T1, T2> {
    public final T1 t1;
    public final T2 t2;

    private Tuple2(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
        Objects.requireNonNull(t1);
        Objects.requireNonNull(t2);
        return new Tuple2<>(t1, t2);
    }

    // operation on the 1st element
    public <R> Tuple2<R, T2> apply_1(Function<T1, R> function) {
        Objects.requireNonNull(function);
        return Tuple2.of(function.apply(t1), t2);
    }

    // operation on the 1st element
    public <R> Tuple2<T1, R> apply_2(Function<T2, R> function) {
        Objects.requireNonNull(function);
        return Tuple2.of(t1, function.apply(t2));
    }

    // apply to both elements 2 different functions (combine apply_1 & apply_2)
    public <R1, R2> Tuple2<R1, R2> applyFunctions(Function<T1, R1> function_1, Function<T2, R2> function_2) {
        Objects.requireNonNull(function_1);
        Objects.requireNonNull(function_2);
        return Tuple2.of(function_1.apply(t1), function_2.apply(t2));
    }

    // apply to both elements the sane function
    public <R1, R2> Tuple2<R1, R2> applyBoth(BiFunction<T1, T2, Tuple2<R1, R2>> biFunction) {
        Objects.requireNonNull(biFunction);
        return biFunction.apply(t1, t2);
    }

}
