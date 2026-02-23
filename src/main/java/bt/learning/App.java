package bt.learning;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


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
        Tuple2<String, Integer> stringCountTuple = stringProcessor.processAndCount("DDD");
        int lengthOfProcessedString = stringCountTuple.t2;
        System.out.println("The length of lengthOfProcessedString is: " + lengthOfProcessedString);

        //stringCountTuple.apply_2(element -> element * 8);

        System.out.println(stringCountTuple.apply_2(element -> element * 8).t2);

        var integerProcessor = new IntegerProcessor();
        Tuple2<Integer, Integer> integerCountTuple = integerProcessor.processAndCount(12345);
        System.out.println(integerCountTuple.t2);

        System.out.println(integerProcessor.processAndCount(12345).apply_2(element -> element * 8).t2);


        // streams: will process elements in a single thread, one after another
        List<String> stringList2 = Arrays.asList("raz", "dwa", "trzy", "cztery", "pięć");
        stringList2.stream()
                .filter(element -> element.length() > 3)
                .map(element -> element.toUpperCase())
                .forEach(System.out::println);

        // parallel streams: will use shared thread pool (ForkJoinPool.commonPool()) to process elements in parallel, potentially improving performance for large collections and computationally intensive operations
        System.out.println("\n--- Parallel Stream ---");
        stringList2.parallelStream()
                .filter(element -> element.length() > 3)
                .map(element -> element.toUpperCase())
                .forEach(System.out::println);


        // concurrent processing with ExecutorService: full control over thread management, allowing you to submit tasks and retrieve results asynchronously, which is useful for more complex scenarios where you need to manage task execution and handle results or exceptions in a more granular way.
        System.out.println("\n--- Concurrent processing with ExecutorService ---");
        // Create a thread pool with a fixed number of threads (e.g., 2)
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Callable<String>> tasks = new ArrayList<>();
        for (String item : stringList2) {
            tasks.add(() -> {
                System.out.println("Processing '" + item + "' in thread: " + Thread.currentThread().getName());
                // Simulate some work by sleeping
                Thread.sleep(100);
                return item.toUpperCase();
            });
        }

        try {
            // Submit all tasks and wait for them to complete
            List<Future<String>> futures = executorService.invokeAll(tasks);

            System.out.println("All tasks submitted. Retrieving results...");

            for (Future<String> future : futures) {
                try {
                    // future.get() is a blocking call. It waits for the task to finish.
                    String result = future.get();
                    System.out.println("Retrieved result: " + result);
                } catch (ExecutionException e) {
                    // This exception is thrown if the task threw an exception
                    System.err.println("Task failed with an exception: " + e.getCause());
                }
            }
        } catch (InterruptedException e) {
            // This exception is thrown if the current thread is interrupted while waiting for invokeAll
            System.err.println("The main thread was interrupted.");
            // It's a good practice to re-interrupt the thread
            Thread.currentThread().interrupt();
        } finally {
            // It's crucial to shut down the executor service to release resources.
            // shutdown() will allow currently running tasks to finish.
            executorService.shutdown();
      System.out.println("ExecutorService shutdown initiated.");
        }
    }
}
