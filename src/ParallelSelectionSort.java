import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelSelectionSort {
    public static <T> void sort(T[] arr, int numThreads, Comparator<? super T> comparator) {
        int n = arr.length;
        int elementsPerThread = (int) Math.ceil((double) n / numThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < n - 1; i++) {
            AtomicInteger minIndex = new AtomicInteger(i);
            for (int j = 0; j < numThreads; j++) {
                int start = j * elementsPerThread + i;
                int end = Math.min(start + elementsPerThread, n);

                executor.execute(new SortTask<T>(arr, comparator, start, end, minIndex));
            }
            swap(arr, i, minIndex.get());
        }
        executor.shutdown();
    }
    private static <T> void swap(T[] arr, int i, int j) {
        var temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
