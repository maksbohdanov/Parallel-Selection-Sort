import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class SortThread<T> extends Thread {
    private T[] arr;
    private final Comparator<? super T> comparator;
    private final int start;
    private final int end;
    private AtomicInteger minIndex;
    SortThread(T[] arr, Comparator<? super T> comparator, int start, int end, AtomicInteger minIndex) {
        this.arr = arr;
        this.comparator = comparator;
        this.start = start;
        this.end = end;
        this.minIndex = minIndex;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            if (comparator.compare(arr[i], arr[minIndex.get()]) < 0) {
                minIndex.set(i);
            }
        }
    }
}
