package dataStructures;

public class ReverseStringRecursive {
    public static void main(String[] args) {
        String inputString = "This is string reversal using recursion";
        printReverse(inputString.toCharArray());
    }

    public static void printReverse(char[] str){
        reverse(0, str);
    }
    private static void reverse(int idx, char[] str) {
        if(str == null || idx >= str.length){
            return;
        }
        reverse(idx + 1, str);
        System.out.print(str[idx]);
    }
}
