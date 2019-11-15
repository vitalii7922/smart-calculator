import java.util.Arrays;
import java.util.Scanner;
/*
The first occurrence in subarray # Comprehension
Implement a method to search the index of the first occurrence of a given value in a range of indexes in a given array. The start index should be inclusive and the end index exclusive. If they are equal, suppose the element is not found.

If the value is not found, the method must return -1.

It's guaranteed that startIndex <= endIndex and the array are not empty.

In the test samples below, the first line represents an array of int's, the second line contains start and end indexes, and the third line has an element to search in the array.

Sample Input 1:

19 14 17 15 17
2 5
17
Sample Output 1:

2
Sample Input 2:

19 14 17 15 17
0 2
17
Sample Output 2:

-1
*/
public class Main {

    public static int searchInSubArray(int[] numbers, int startIndex, int endIndex, int value) {
        int index = -1;
        if (endIndex <= startIndex){
            return index;
        }
        for (int i = startIndex; i < endIndex; i++) {
            if(numbers[i] == value){
                index = i;
                return index;
            }
        }
        return -1;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        final String[] parts = scanner.nextLine().split("\\s+");
        final int startIndex = Integer.parseInt(parts[0]);
        final int endIndex = Integer.parseInt(parts[1]);
        final int k = Integer.parseInt(scanner.nextLine());
        System.out.println(searchInSubArray(numbers, startIndex, endIndex, k));
    }
}