package ss13_SortAlgorithm.Demo;

public class PerformanceTest {

    public static void main(String[] args) {
        getTime(100000);
        getTime(1000000);
        getTime(10000000);
        getTime(100000000);
    }

    private static void getTime(long n) {
        long start = System.currentTimeMillis();
        long k = 0;
        for (int i = 0; i < n; i++) {
            k = k + 5;
        }
        long end = System.currentTimeMillis();
        System.out.println("Excution time for n = " + n + " is " + (end - start) + " milliseconds ");
    }
}
