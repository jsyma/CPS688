/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2q4;

import java.util.Scanner;
/**
 *
 * @author jma
 */
public class CandyShop{

    public static void main(String[] args){  
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of candies present in the shop: ");
        int N = sc.nextInt();
        int[] sentimentalValue = new int[N + 1];
        int[] weight = new int[N + 1];
        
        System.out.println("Enter the sentimental value of the candy: ");
        for (int i = 0; i < N; i++){
            sentimentalValue[i] = sc.nextInt();
        }
        
        System.out.println("Enter the weight of each candy: ");
        for (int i = 0; i < N; i++){
            weight[i] = sc.nextInt();
        }
        
        System.out.println("Enter the maximum weight that can be carried by the bag: ");
        int W = sc.nextInt();
       
        int[][] dp = new int[N+1][W+1];
        for (int i = 0; i <= N; i++){
            for (int j = 0; j <= W; j++){
                if (i == 0 || j == 0){
                    dp[i][j] = 0;
                } 
                else if (weight[i] <= j){
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + sentimentalValue[i]);
                } 
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println("The highest sentimental aggregated value is: " + dp[N][W]);
    }
}
