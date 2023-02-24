/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2q1;

import java.util.Scanner;

/**
 *
 * @author jma
 */
public class NQueens{
    
    private final int N;
    private final int [][] board;
    
    public NQueens(int N){
        this.N = N;
        board = new int[N][N];
    }

    private boolean noClash(int row, int col){
        int i, j;

        for (i = 0; i < col; i++){
            if (board[row][i] == 1){
                return false;
            }
        }
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--){
            if (board[i][j] == 1){
                return false;
            }
        }
        for (i = row, j = col; j >= 0 && i < N; i++, j--){
            if (board[i][j] == 1){
                return false;
            }
        }
        return true;
    }

    private boolean isValid(int col){ 
        if (col >= N){
            return true;
        }
        for (int i = 0; i < N; i++){
            if (noClash(i, col)){
                board[i][col] = 1;
                if (isValid(col + 1) == true){
                    return true;
                }
                board[i][col] = 0;
            }
        }
        return false;
    }
    
    public void solve(){
        if (isValid(0) == false){
            System.out.print("Solution does not exist \n");
        }
        printSolution();
    }
    
    private void printSolution(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the board size/matrix N: ");
        int N = sc.nextInt();
        NQueens queen = new NQueens(N);
        queen.solve();
        
        //Hardcoded inputs, uncomment if need be  
        //NQueens queen = new NQueens(4);
        //queen.solve();
    }   
}
