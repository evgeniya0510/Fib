import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int MAX_VALUE = 43;
        int MAX_CACHE_SIZE = 30;
        int NUMBER_REQUEST = 10000;

        Server server = new Server(MAX_CACHE_SIZE);
        Random randomGenerator = new Random();

        long startTime = 0;
        long globalTime1 = 0;
        long globalTime1End = 0;
        long globalTime2 = 0;
        long globalTime2End = 0;

        try(FileWriter writer = new FileWriter("withCache.txt", false)) {
            System.out.println("Calculate Fibonacci number with cache");
            globalTime1 = System.currentTimeMillis();
            for(int i = 0; i < NUMBER_REQUEST; i++) {
                startTime = System.nanoTime();
                server.getFibonacciNumber(randomGenerator.nextInt(MAX_VALUE));
                writer.write((System.nanoTime() - startTime) + "\n");
                System.out.println(i + 1 + " / " + NUMBER_REQUEST);
            }
            globalTime1End = System.currentTimeMillis() - globalTime1;
        } catch (IOException err) {
            System.out.println("Error: " + err);
        }

        try(FileWriter writer = new FileWriter("withoutCache.txt", false)) {
            System.out.println("Calculate Fibonacci number without cache");
            globalTime2 = System.currentTimeMillis();
            for(int i = 0; i < NUMBER_REQUEST; i++) {
                startTime = System.nanoTime();
                Fibonacci.fibonacci(randomGenerator.nextInt(MAX_VALUE));
                writer.write((System.nanoTime() - startTime) + "\n");
                System.out.println(i + 1 + " / " + NUMBER_REQUEST);
            }
            globalTime2End = System.currentTimeMillis() - globalTime2;
        } catch (IOException err) {
            System.out.println("Error: " + err);
        }

        System.out.println("\nTime:");
        System.out.println("\tWith cache: " + globalTime1End + " ms");
        System.out.println("\tWithout cache: " + globalTime2End + " ms");
    }
}
