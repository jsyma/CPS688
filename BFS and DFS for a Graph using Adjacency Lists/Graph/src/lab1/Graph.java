/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1;

import java.util.*;
import java.util.Scanner;
/**
 *
 * @author jma
 */
public class Graph {
    private final int Vertex;
    private final int Edges;
    private final ArrayList<Integer>[] adjList;
    
    public Graph(int v, int e) {
        Vertex = v;
        Edges = e;
        adjList = new ArrayList[v];
        for (int i = 0; i < v; i++) { //Initialize the Adjacency List
            adjList[i] = new ArrayList<>(); 
        }
    }
    
    //Adds edge between vertex a and b
    public void addEdge(int a, int b){
        adjList[a].add(b);
        adjList[b].add(a);
    }
    
    //Returns number of edges adjacent to vertex a
    public int degreeVertex(int a) {
        return adjList[a].size();
    }
    
    //Print adjacent vertices of vertex a
     public void printAdjVertices(int a) {
        System.out.println("Adjacent vertices of vertex " + a + ": " + adjList[a]);
    }
     
     public void BFS(int s) {
        boolean[] visited = new boolean[Vertex];
        LinkedList<Integer> queue = new LinkedList<>();
       
        queue.add(s); //Starting vertex added to top of queue
        visited[s] = true; //Starting vertex already visited 

        while (!queue.isEmpty()) {
            s = queue.poll(); //If queue is not empty, removes and retrieves the head of the queue 
            System.out.print(s + " ");

            for (int i = 0; i < adjList[s].size(); i++){
                int x = adjList[s].get(i); //Retrieve first item in adjList
                if (!visited[x]) {
                    visited[x] = true;
                    queue.add(x);
                }
            }     
        }
    }
    
     public void DFS(int s) {
        boolean[] visited = new boolean[Vertex];
        Stack<Integer> stack = new Stack<>();
        
        stack.push(s);
        
        while(!stack.empty()){
            s = stack.pop(); //While stack is not empty, the top of the stack is popped and removed
           
            for (int i =0; i < adjList[s].size(); i++){
                int x = adjList[s].get(i); //Retrieve first itme in adjList
                if(!visited[x]){
                    stack.push(x); //if not visited push into the stack
                }
            }
             if(visited[s] == false){
                System.out.print(s + " ");
                visited[s] = true;
            }
        }
    }
     
     public static void main(String[] args){
        //Read inputs from user following the format of the manual's sample input
        Scanner  sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices: ");
        int x = sc.nextInt();
        System.out.println("Enter the number of Edges: ");
        int e = sc.nextInt();
        Graph g = new Graph(x, e);
        for (int i = 0; i < e; i++){
            System.out.println("Add edges below (a first, then b): ");
            int a = sc.nextInt();
            int b = sc.nextInt();
            g.addEdge(a, b);
        }
        System.out.println("\nEnter the node to find the degree: ");
        int y = sc.nextInt();
        System.out.println("The degree of vertex " + y +" is: " + g.degreeVertex(y));
        
        System.out.println("\nEnter the node to find the adjacent vertices: ");
        int adj = sc.nextInt();
        g.printAdjVertices(adj);
        
        System.out.println("\nEnter the starting node for BFS: ");
        int b = sc.nextInt();
        System.out.println("BFS is: ");
        g.BFS(b);
        
        System.out.println("\nEnter the starting node for DFS: ");
        int d = sc.nextInt();
        System.out.println("DFS is: ");
        g.DFS(d);
        
        //Hardcoded inputs, uncomment if need be
        
        //g.addEdge(0, 1);
        //g.addEdge(0,3);
        //g.addEdge(1,2);
        //g.addEdge(2,4);
        //g.addEdge(3,4);
        //g.addEdge(3,5);
        //System.out.println("Degree of vertex 0 is: " + g.degreeVertex(0));
        //g.printAdjVertices(0);
        //System.out.println("BFS of vertex 0: ");
        //g.BFS(0); 
        //System.out.println("\nDFS of vertex 0: ");
        //g.DFS(0);        
     }
}


