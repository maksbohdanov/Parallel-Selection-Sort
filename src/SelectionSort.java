public class SelectionSort {
    public static void sort(int[] array){
        for(int i = 0; i < array.length; i++){
            var minIndex = i;
            for (int j = i+1; j < array.length; j++){
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            var tmp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = tmp;
        }
    }
}
