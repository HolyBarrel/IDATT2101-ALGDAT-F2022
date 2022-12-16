package BTwo;

import java.util.Date;


/**
 * Class containing an algorithms to recursively compute exponentiation 
 * and to verify the algorithms with a main-method
 */
public class SecondDelivery {

    /**
     * Method to recursively calculate a base in a given exponent value
     * @param x is a double representing the base 
     * @param n is an integer representing the exponent
     * @return the recursive call-stack result of the computation
     */
    static double recursiveExp(double x, int n){
        if (n == 0) return 1;
        return x * recursiveExp(x, n-1);
    }

    /**
     * Method to recursively calculate a base in a given exponent value
     * by dividing the data used for each recursive step in half
     * @param x is a double representing the base 
     * @param n is an integer representing the exponent
     * @return the recursive call-stack result of the computation
     */
    static double recursiveExpEvenOdd(double x, int n){
        if(n == 0) return 1;
        if(n % 2 == 0){
            return recursiveExpEvenOdd(x*x, n/2);
        }else{
            return x * recursiveExpEvenOdd(x*x, (n-1)/2);
        }
    }

    /**
     * Returns the amount of time it takes to run a given algorithm. 
     * Takes input of:
     * 0 to run Algorithm 1, called recursiveExp()
     * 1 to run Algorithm 2, called recursiveExpEvenOdd()
     * any other int to record the time used by Math.pow()
     * @param methodType is the method type to record, is an integer value representation
     * @param x is a double representing the base 
     * @param n is an integer representing the exponent
     * @return double representing execution time
     */
    public static double getExecTime(int methodType, double x, int n){

        Date start = new Date();
        int rounds = 0;
        double time;
        Date end;

        switch (methodType) {
            case 0 -> {
                do {
                    recursiveExp(x, n);
                    end = new Date();
                    ++rounds;
                } while (end.getTime() - start.getTime() < 1000);
                time = (double) (end.getTime() - start.getTime()) / rounds;
            }
            case 1 -> {
                do {
                    recursiveExpEvenOdd(x, n);
                    end = new Date();
                    ++rounds;
                } while (end.getTime() - start.getTime() < 1000);
                time = (double) (end.getTime() - start.getTime()) / rounds;
            }
            default -> {
                do {
                    Math.pow(x, n);
                    end = new Date();
                    ++rounds;
                } while (end.getTime() - start.getTime() < 1000);
                time = (double) (end.getTime() - start.getTime()) / rounds;
            }
        }

        return time;
    }

    private static void printEquation(boolean evenOdd, double x, int n) {
        if(evenOdd) {
            System.out.println(x + "^" + n + " = " + recursiveExpEvenOdd(x, n));
        } else {
            System.out.println(x + "^" + n + " = " + recursiveExp(x, n));
        }
    }

    private static void printFunctionTimes(double x, int n) {
        String paramInfo = "x = %s, n = %d\n".formatted(x, n);
        System.out.println("______________\n");
        System.out.println(paramInfo + "Time(recursiveExp()): "
            + getExecTime(0, x, n));
        System.out.println(paramInfo + "Time(recursiveExpEvenOdd()): "
            + getExecTime(1, x, n));
        System.out.println(paramInfo + "Time(Math.pow()): "
            + getExecTime(2, x, n));
    }

    private static void printFunctionInterval(int base, int bound,
        double multiplier, double x) {
        for(int i = base; i <= bound; i *= multiplier) {
            printFunctionTimes(x, i);
            System.out.println("\n");
        }
    }



    /*
     * Main method to run the algorithms
     */

    public static void main(String[] args) {

        printEquation(false, 2, 12);
        printEquation(false, 3, 2);
        printEquation(false, 2.018, 4);

        System.out.println("______________");

        printEquation(true, 2, 12);
        printEquation(true, 3, 2);
        printEquation(true, 2.018, 4);

        printFunctionInterval(1000, 4000, 2, 3);

        System.out.println("______________\n");

        printFunctionInterval(1000, 4000, 2, 500);

    }
}
