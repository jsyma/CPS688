/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2q2;

import java.util.*;
/**
 *
 * @author jma
 */
public class GraphCycleCheck{
    private final int Vertex;
    private final ArrayList<Integer>[] adjList;
    private final int Edges;
    public GraphCycleCheck(int v, int e){
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
    
    //Check for back edges in a graph to determine if there is a cycle
    public boolean DFSCheck(int v, boolean visited[], int[] parent){
        visited[v] = true;
        for (int i : adjList[v]) {
            if (visited[i]) {
                if (i != parent[v]) {
                    return true;
                }
            } 
            else {
                parent[i] = v;
                if (DFSCheck(i, visited, parent)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCyclic(){
        boolean[] visited = new boolean[Vertex];
        int[] parent = new int[Vertex];
        
        for (int i = 0; i < Vertex; i++) {
            if (!visited[i]) {
                if (DFSCheck(i, visited, parent)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String args[]){ 
        Scanner  sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices: ");
        int x = sc.nextInt();
        System.out.println("Enter the number of Edges: ");
        int e = sc.nextInt();
        GraphCycleCheck g = new GraphCycleCheck(x, e);
        for (int i = 0; i < e; i++){
            System.out.println("Add edges below (a first, then b): ");
            int a = sc.nextInt();
            int b = sc.nextInt();
            g.addEdge(a, b);
        }
        if(g.isCyclic())
            System.out.println("No - Graph has a Cycle");
        else
            System.out.println("Yes - Graph doesn't have a Cycle");
        
        //Hardcoded inputs, uncomment if need be
        
        //GraphCycleCheck g = new GraphCycleCheck(6, 6);
        //g.addEdge(1, 0);
        //g.addEdge(0, 2);
        //g.addEdge(2, 0);
        //g.addEdge(0, 3);
        //g.addEdge(3, 4);
        //if (g.isCyclic())
        //    System.out.println("No - Graph has a Cycle");
        //else
        //    System.out.println("Yes - Graph doesn't have a Cycle");
        
    }
}
