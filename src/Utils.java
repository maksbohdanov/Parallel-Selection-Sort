import java.util.Random;

public class Utils {
    public static Integer[] initArray(int length){
        var array = new Integer[length];

        Random random = new Random();
        for (var i = 0; i < length; i++ ) {
            array[i] = random.nextInt();
            //array[i] = random.nextInt(50);
        }
        return array;
    }

    public static void printArray(Integer[] array){
        for (var item: array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
