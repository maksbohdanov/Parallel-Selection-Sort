public class Main {
    public static void main(String[] args) {
//       var arr = Utils.initArray(15);
//        Utils.printArray(arr);
//        SelectionSort.sort(arr);
//        var res = ParallelSelectionSort.sort(arr, 2);
//        Utils.printArray(res);

//        int[] sizes = {1000000}; //50000, 100000, 150000, 250000, 500000, 1000000
//        for (var size: sizes) {
//            int[] partsArr = {size/10000, size/1000, size/500, size/200, size/100};//, size/20
//            for (var parts: partsArr) {
//                var arr = Utils.initArray(size);
//                executeParallel(arr, parts);
//            }
//            //execute(arr);
//        }

        for(int i = 100000; i<=1000000; i+=50000){
            var parts = i/1000;
            var arr1 = Utils.initArray(i);
            var arr2 = Utils.initArray(i);
            execute(arr1);
            executeParallel(arr2, parts);
        }

    }

    public static void execute(int[] arr){
        long start = System.currentTimeMillis();
        SelectionSort.sort(arr);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Size: " + arr.length + "\tTime: " + timeElapsed + " ms");
    }
    public static void executeParallel(int[] arr, int parts){
        long start = System.currentTimeMillis();
        ParallelSelectionSort.sort(arr, parts);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Size: " + arr.length + "\tParts: " + parts + "\tTime: " + timeElapsed + " ms");
    }
}
