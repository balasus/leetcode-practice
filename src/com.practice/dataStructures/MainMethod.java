package dataStructures;

public class MainMethod {
    public static void main(String[] args) {
        FactorialIterator factorialUsingIterator = new FactorialIterator(5);
        factorialUsingIterator.forEach(System.out::println);
    }
}
