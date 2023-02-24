/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2q5;


/**
 *
 * @author jma
 */

import java.util.*;

public class LIS {
       
    public static int getLISLength(int n, int[] arr) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static List<Integer> findLIS(int[] arrA, int[] arrB) {
        int la = arrA.length;
        int lb = arrB.length;
        int[][] dp = new int[la + 1][lb + 1];
        List<Integer> LIS = new ArrayList<>();

        for (int i = 1; i <= la; i++) {
            for (int j = 1; j <= lb; j++) {
                if (arrA[i - 1] == arrB[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        while (la > 0 && lb > 0) {
            if (arrA[la - 1] == arrB[lb - 1]) {
                LIS.add(arrA[la - 1]);
                la--;
                lb--;
            } else if (dp[la - 1][lb] >= dp[la][lb - 1]) {
                la--;
            } else {
                lb--;
            }
        }
        Collections.reverse(LIS);
        return LIS;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the array length: ");
        int n = sc.nextInt();
        int[] arrA = new int[n];
        
        System.out.println("Enter the values in the array: ");
        for(int i = 0; i < arrA.length; i++){

            int x = sc.nextInt();
            arrA[i] = x;
        }
        int[] arrB = Arrays.stream(arrA).distinct().sorted().toArray();
        int LISLength = getLISLength(n, arrA);
        List<Integer> LIS = findLIS(arrA, arrB);

        System.out.println("LIS = " + LISLength);
        System.out.println("LIS is : " + LIS);
        
        //Hardcoded inputs, uncomment if need be
        //int[] arrA = {10, 22, 9, 33, 21, 50, 41, 60};
        //int[] arrB = Arrays.stream(arrA).distinct().sorted().toArray();
        //int LISLength = getLISLength(n, arrA);
        //List<Integer> LIS = findLIS(arrA, arrB);

        //System.out.println("LIS = " + LISLength);
        //System.out.println("LIS is: " + LIS);
    }
}


