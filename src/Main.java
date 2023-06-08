import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
//        Comparator<Integer> comparator = Comparator.naturalOrder();
//        var arr = Utils.initArray(15);
//        Utils.printArray(arr);
//        ParallelSelectionSort.sort(arr, 2, comparator);
//        Utils.printArray(arr);

//        int[] sizes = {50000, 100000, 150000, 250000, 500000, 1000000};; //50000, 100000, 150000, 250000, 500000, 1000000
//        for (var size: sizes) {
//            int[] partsArr = {1, 2, 3, 5, 8, 10};//size/50000, size/20000, size/10000, size/5000, size/2000, size/1000
//            for (var parts: partsArr) {
//                var arr = Utils.initArray(size);
//                execute(arr);
//                var arr2 = Utils.initArray(size);
//                executeParallel(arr2, parts);
//            }
//        }


        for(int i = 100000; i<=1000000; i+=50000){
            var parts = 2;
            var arr1 = Utils.initArray(i);
            var arr2 = Utils.initArray(i);
            execute(arr1);
            executeParallel(arr2, parts);
        }
    }

    public static void execute(Integer[] arr){
        Comparator<Integer> comparator = Comparator.naturalOrder();
        long start = System.currentTimeMillis();
        SelectionSort.sort(arr, comparator);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.print("Size: " + arr.length + "\tTime: " + timeElapsed + " ms");
    }
    public static void executeParallel(Integer[] arr, int parts){
        Comparator<Integer> comparator = Comparator.naturalOrder();
        long start = System.currentTimeMillis();
        ParallelSelectionSort.sort(arr, parts, comparator);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("\tParts: " + parts + "\tTime: " + timeElapsed + " ms");
    }
}
