import java.util.Random;

public class Utils {
    public static int[] initArray(int length){
        var array = new int[length];

        Random random = new Random();
        for (var i = 0; i < length; i++ ) {
            //array[i] = random.nextInt();
            array[i] = random.nextInt(20);
        }
        return array;
    }

    public static void printArray(int[] array){
        for (var item: array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
