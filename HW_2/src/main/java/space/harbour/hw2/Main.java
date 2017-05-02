package space.harbour.hw2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // run with options -Xmx64m -Xms64m
        intSizeTest();
        cleanMemory();
        refSizeTest();
        cleanMemory();
        stringSizeTest();
    }

    private static void cleanMemory() throws InterruptedException {
        System.out.println("\nGC started working, free memory: " + Runtime.getRuntime().freeMemory());
        System.gc();
        Thread.sleep(10);
        System.out.println("GC ended working, free memory: " + Runtime.getRuntime().freeMemory() + "\n");
    }

    private static void intSizeTest() {
        long beforeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory before: " + beforeMemory);

        int N = 10_000_000;
        int[] arr = new int[N];

        long afterMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory after: " + afterMemory);
        System.out.println("approximate int size: " + ((double) (beforeMemory - afterMemory) / (double) N));
    }

    private static void refSizeTest() {
        long beforeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory before': " + beforeMemory);

        int N = 10_000_000;
        Object[] arr = new Object[N];

        long afterMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory after': " + afterMemory);
        System.out.println("approximate ref size: " + ((double) (beforeMemory - afterMemory) / (double) N));
    }

    private static void stringSizeTest() {
        long beforeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory before: " + beforeMemory);

        int N = 500_000;
        String[] arr = new String[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = new String(new char[0]);
        }

        long afterMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory after: " + afterMemory);
        System.out.println("approximate (String without string pool + ref) size: " + ((double) (beforeMemory - afterMemory) / (double) N));
    }
}
