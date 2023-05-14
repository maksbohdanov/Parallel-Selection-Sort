import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelSelectionSort {
    public static int[] sort(int[] array, int parts){
        var arrays = split(array, parts);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new SelectionSortTask(arrays));

        int[] resultArray;
        resultArray = mergeArraysFromList(arrays);
        return resultArray;
    }

    public static List<int[]> split(int[] array, int partsCount)
    {
        var arrays = new ArrayList<int[]>();

        int elementsPerPart = array.length / partsCount;
        int counter = 0;

        for (int i = 0; i < partsCount; i++)
        {
            if (i < partsCount - 1)
                arrays.add(new int[elementsPerPart]);
            else
                arrays.add(new int[array.length - counter]);
            for (int j = 0; j < arrays.get(i).length; j++)
            {
                arrays.get(i)[j] = array[counter];
                counter++;
            }
        }
        return arrays;
    }
    public static int[] mergeArraysFromList(List<int[]> arrays) {
        int[] array = arrays.get(0);
        for (int i = 1; i < arrays.size(); i++) {
            array = merge(array, arrays.get(i));
        }

        return array;
    }
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int firstCount = 0;
        int secondCount = 0;
        for (int i = 0; i < result.length; i++) {
            if (left[firstCount] < right[secondCount]) {
                result[i] = left[firstCount];
                firstCount++;
            } else {
                result[i] = right[secondCount];
                secondCount++;
            }
            if (firstCount == left.length) {
                i++;
                return finishMerge(result, right, i, secondCount);
            }
            if (secondCount == right.length) {
                i++;
                return finishMerge(result, left, i, firstCount);
            }
        }
        return result;
    }

    private static int[] finishMerge(int[] result, int[] adit, int ind, int aditInd) {
        while (ind < result.length) {
            result[ind] = adit[aditInd];
            ind++;
            aditInd++;
        }
        return result;
    }
    private static class SelectionSortTask extends RecursiveAction {
        private final List<int[]> arrays;
        private int currentIndex;

        SelectionSortTask(List<int[]> arrays) {
            this(arrays, 0);
        }

        private SelectionSortTask(List<int[]> arrays, int currentIndex) {
            this.arrays = arrays;
            this.currentIndex = currentIndex;
        }

        @Override
        protected void compute() {
            if (currentIndex >= arrays.size()) {
                return;
            }

            int[] array = arrays.get(currentIndex);
            int minElementIndex = 0;

            for (int j = 0; j < array.length; j++) {
                minElementIndex = currentIndex;

                if (currentIndex == array.length) {
                    break;
                }

                for (int k = currentIndex; k < array.length; ++k) {
                    if (array[k] < array[minElementIndex]) {
                        minElementIndex = k;
                    }
                }

                if (minElementIndex != currentIndex) {
                    int temp = array[minElementIndex];
                    array[minElementIndex] = array[currentIndex];
                    array[currentIndex] = temp;
                }

                currentIndex++;
            }

            if (currentIndex < arrays.size()) {
                SelectionSortTask nextTask = new SelectionSortTask(arrays, currentIndex + 1);
                nextTask.fork();
                nextTask.join();
            }
        }
    }
}
