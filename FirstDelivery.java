package AOne;

import java.util.Date;
import java.util.Random;

/**
 * Class containing an algorithm to interpret the optimal transaction of a stock, 
 * given an array of changes to its value
 * @author Magnus Lutro Allison
 */
public class FirstDelivery {

    /**
     * Validates the input array of integers to find the optimal time for purchase and
     * sale of the stock. Utilizes nested for-loops to analyze the data, and output the
     * best possible transaction.
     *
     * @param stockChanges is the input array of integers
     * @return an integer array containing the day of the purchase, 
     *          followed by the day of the sale,
     *          and then the best possible profit return of the analyzed stock changes
     */
    private static int[] findOptimalTransaction(int[] stockChanges){
        
        int bestProfit = 0;                                         // assignment 1
        int buyDate = 0;                                            // assignment 1
        int sellDate = 0;                                           // assignment 1
        for (int i = 0; i < stockChanges.length-1; i++) {           // statement 1, statements: 2n
            int change = 0;                                         // assignment n
            for (int j = i + 1; j < stockChanges.length; j++) {     // statement n, statements: 2n^2
                change += stockChanges[j];                          // assignment n^2
                if(change > bestProfit){                            // if-check n^2
                    bestProfit = change;                            // possible assignment n^2
                    buyDate = i + 1;                                // possible assignment n^2
                    sellDate = j + 1;                               // possible assignment n^2
                }
            }
        }
        return new int[]{buyDate, sellDate, bestProfit};            // return 1
    }                                                  // 4n^2 (possibly 7n^2) + 4n + 6  |  Θ(n^2)


    /**
     * Only used to time the actions of the function.
     *
     * @param stockChanges is the input array of integers
     */
    private static void findOptimalTransactionByVoid(int[] stockChanges){

        int bestProfit = 0;
        int buyDate = 0;
        int sellDate = 0;
        for (int i = 0; i < stockChanges.length-1; i++) {
            int change = 0;
            for (int j = i + 1; j < stockChanges.length; j++) {
                change += stockChanges[j];
                if(change > bestProfit){
                    bestProfit = change;
                    buyDate = i + 1;
                    sellDate = j + 1;
                }
            }
        }
    }

    /**
     * Returns an array with random integers ranging from -10 to 10 (may contain both).
     * The array-size is determined by the arraySize parameter.
     *
     * @param arraySize is responsible for the length of the array which is returned
     * @return an integer array containing random integers (-10 to 10)
     */
    public static int[] generateArray(int arraySize){
        Random rnd = new Random();
        int[] currentArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            currentArray[i] = rnd.nextInt(20) - 10;
        }
        return currentArray;
    }

    /**
     * Records the execution time used by the {@link #findOptimalTransaction(int[])} method 
     * and returns a display of the result.
     *
     * @param currentArray is the integer array which is used by the
                            {@link #findOptimalTransaction(int[])} method
     * to analyze the optimal transaction of the stock
     * @return a double describing the execution time of the
     *         {@link #findOptimalTransaction(int[])} method
     */
    public static double getExecTime(int[] currentArray){

        Date start = new Date();
        int rounds = 0;
        double time;
        Date end;

        do {
            findOptimalTransactionByVoid(currentArray);
            end = new Date();
            ++rounds;
        } while (end.getTime()-start.getTime() < 1000);

        time = (double)(end.getTime()-start.getTime()) / rounds;

        return time;
    }


    /**
     * Main method that runs the {@link #getExecTime(int[])} method and prints the results.
     * @param args arguments for main
     */
    public static void main(String[] args) {

        int[] testData1 = generateArray(1000);
        int[] testData2 = generateArray(10000);
        int[] testData3 = generateArray(100000);
        int[] testData4 = generateArray(1000000);

        System.out.println("n = " + testData1.length + ", executed in: "
            + getExecTime(testData1) + " milliseconds");
        System.out.println("n = " + testData2.length + ", executed in: "
            + getExecTime(testData2) + " milliseconds");
        System.out.println("n = " + testData3.length + ", executed in: "
            + getExecTime(testData3) + " milliseconds");
        System.out.println("n = " + testData4.length + ", executed in: "
            + getExecTime(testData4) + " milliseconds");
    }


}
