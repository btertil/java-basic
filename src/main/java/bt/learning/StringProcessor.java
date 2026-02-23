package bt.learning;

public class StringProcessor implements MyProcessor<String> {
    @Override
    public String process(String element) {
        System.out.println(element);
        return element;
    }
}
