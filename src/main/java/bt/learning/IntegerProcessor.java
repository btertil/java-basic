package bt.learning;

public class IntegerProcessor implements MyProcessor<Integer> {
    @Override
    public Integer process(Integer element) {
        System.out.println(element);
        return element;
    }

    @Override
    public Tuple2<Integer, Integer> processAndCount(Integer element) {
        System.out.println(element);
        return Tuple2.of(element, String.valueOf(element).length());
    }

}
