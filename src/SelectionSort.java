import java.util.Comparator;
public class SelectionSort {
    public static <T> void sort(T[] array, Comparator<? super T> comparator){
        for(int i = 0; i < array.length; i++){
            var minIndex = i;
            for (int j = i+1; j < array.length; j++){
                if (comparator.compare(array[i], array[minIndex]) < 0)
                    minIndex = j;
            }
            var tmp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = tmp;
        }
    }
}
