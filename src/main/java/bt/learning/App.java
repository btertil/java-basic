package bt.learning;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();
        stringList.add("raz");
        stringList.add("dwa");

        stringList.forEach(System.out::println);
        stringList.forEach( (element) -> System.out.println(element));

        //stringList( String element -> System.out::println);

        List<String> myList2 = Arrays.asList("jeszcze raz", "jeszcze dwa");

        for (String element : myList2) {
            System.out.println(element);
        }

        // printer generic helper
        Printer<Integer> intPrinter = new Printer<Integer>();
        Printer<String> strPrinter = new Printer<String>();

        intPrinter.print(19);
        strPrinter.print("abc");

        var stringProcessor = new StringProcessor();
        int lengthOfProcessedString = stringProcessor.processAndCount("DDD").t2;
        System.out.println("The length of lengthOfProcessedString is: " + lengthOfProcessedString);

    }
}
