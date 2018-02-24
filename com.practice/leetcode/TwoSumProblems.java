package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Given an array of random elements find the
 * indexes that sum up and matches the search key
 */
public class TwoSumProblems {

    /**
     * Returns indexes of the elements added to get
     * the search key
     *
     * <P>This approach uses O(1) time complexity using
     * Map. ie., time and space complexity of O(n)</P>
     *
     * @param a
     * @param sKey
     * @return
     */
    public static int[] findSumIndexes(int[] a, int sKey){
        if(a==null || a.length<1){
            throw new IllegalArgumentException("Container cannot be empty");
        }
        Map<Integer, Integer> map = new HashMap<>(a.length);
        for(int i=0; i<a.length; i++){
            int x = a[i];
            if(map.containsKey(sKey-x)){
                return new int[]{map.get(sKey-x)+1, i+1};
            }
            map.put(x,i);
        }
        throw new IllegalArgumentException("No two sum solution") ;
    }

    public static void printElements(int[] a){
        for(int i : a){
            System.out.print(i + "\t");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter any skey : %d");
        int sKey = input.nextInt();
        int[] solution = findSumIndexes(IntStream.of(1,2,3,4,5,6,7).toArray(), sKey);
        printElements(solution);
    }
}
