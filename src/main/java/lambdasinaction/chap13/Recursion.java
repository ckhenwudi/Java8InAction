package lambdasinaction.chap13;

import java.util.stream.LongStream;


public class Recursion {

    public static void main(String[] args) {
        System.out.println(factorialIterative(5));
        System.out.println(factorialRecursive(5));
        System.out.println(factorialStreams(5));
        System.out.println(factorialTailRecursive(5));
    }

    /**
     * 阶乘
     * @param n
     * @return
     */
    public static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r*=i;
        }
        return r;
    }

    /**
     * 阶乘-递归
     * @param n
     * @return
     */
    public static long factorialRecursive(long n) {
        return n == 1 ? 1 : n*factorialRecursive(n-1);
    }

    /**
     * 阶乘-stream
     * @param n
     * @return
     */
    public static long factorialStreams(long n){
        return LongStream.rangeClosed(1, n)
                         .reduce(1, (long a, long b) -> a * b);
    }

    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    /**
     * 1):n
     * 2):n*(n-1)
     * 3):n*(n-1)*(n-2)
     * 4):n*(n-1)*(n-2)*(n-3)
     * @param acc
     * @param n
     * @return
     */
    public static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n-1);
    }
}
