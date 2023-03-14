/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3q1;

import java.util.Scanner;

/**
 *
 * @author jma
 */
public class CutRod {
    
    public static int cutRod(int[] p, int n){
        int[] r = new int[n + 1]; //Let r[0..n] be a new array
        r[0] = 0; //Set revenue of rod length 0 to be 0
        for (int j = 1; j <= n; j++){
            int q = 0; //initialize maxRevenue as 0
            //Try all possible cuts of size j <= i and choose the one that gives max revenue
            for (int i = 1; i <= j; i++){  
                q = Math.max(q, p[i] + r[j-i]);
            }
            r[j] = q; //Stores the max revenue for a rod of length i
        } 
        return r[n]; //Returns max revenue for a rod of length n
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the length of the rod: ");
        int n = sc.nextInt();
        int[] p = new int[n + 1];
        System.out.println("Input the prices for different rod lengths: ");
        for(int i = 1; i <= n; i++){
            p[i] = sc.nextInt();
        }
        int maxRevenue = cutRod(p, n);
        System.out.println(maxRevenue);
        
    }
}
