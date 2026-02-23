package bt.learning;

public class StringProcessor implements MyProcessor<String> {
    @Override
    public String process(String element) {
        System.out.println(element);
        return element;
    }

    @Override
    public Tuple2<String, Integer> processAndCount(String element) {
        System.out.println(element);
        return Tuple2.of(element, element.length());
    }
}
