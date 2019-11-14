import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int searchIndexOfLastOccurrence(int[] numbers, int value) {
        int index = -1;
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == value) {
                index = i;
                break;
            }
        }
        return index;
    }

    /* Do not change code below */
    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] numbers;
        final int k;
        if (scanner.hasNextInt()) {
            numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            k = Integer.parseInt(scanner.nextLine());
        } else {
            numbers = new int[0];
            k = 10;
        }
        System.out.println(searchIndexOfLastOccurrence(numbers, k));
    }
}