import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelSelectionSort {
    public static <T> void sort(T[] arr, int numThreads, Comparator<? super T> comparator) {
        int n = arr.length;
        int elementsPerThread = (int) Math.ceil((double) n / numThreads);

        for (int i = 0; i < n - 1; i++) {
            AtomicInteger minIndex = new AtomicInteger(i);
            Thread[] threads = new SortThread[numThreads];
            for (int j = 0; j < numThreads; j++) {
                int start = j * elementsPerThread + i;
                int end = Math.min(start + elementsPerThread, n);
                threads[j] = new SortThread<>(arr, comparator, start, end, minIndex);
                threads[j].start();
            }
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            swap(arr, i, minIndex.get());
        }
    }


    private static <T> void swap(T[] arr, int i, int j) {
        var temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
