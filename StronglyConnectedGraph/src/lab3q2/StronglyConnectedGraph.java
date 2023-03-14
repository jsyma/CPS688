/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab3q2;

import java.util.*;
/**
 *
 * @author jma
 */

public class StronglyConnectedGraph{
    
    private int n; //number of vertices/antennas 
    private int e; //number of edges/cables 
    private List<List<Integer>> graph; 
    
    public StronglyConnectedGraph(int n, int e){
        this.n = n;
        this.e = e;
        this.graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int a, int b){
        graph.get(a).add(b);
    }
    
    private void DFS(int a, boolean[] visited){
        visited[a] = true;
        for (int b : graph.get(a)){
            if (!visited[b]) {
                DFS(b, visited);
            }
        }
    }
    
    public boolean isStronglyConnected(){
        boolean[] visited = new boolean[n];
        DFS(0, visited); //Perform DFS starting from vertex 0
        for (boolean v : visited){ //Check if all vertices were visited in DFS
            if (!v) {
                return false;
            }
        }
        StronglyConnectedGraph reverseGraph = getReverseGraph();
        Arrays.fill(visited, false); //Reset the visited array
        reverseGraph.DFS(0, visited);
        for (boolean v : visited){
            if (!v){
                return false;
            }
        }
        return true; //If all vertices were visited during both DFS's, graph is strongly connected
    }
    
    private StronglyConnectedGraph getReverseGraph(){
        StronglyConnectedGraph reverseGraph = new StronglyConnectedGraph(n, e);
        for (int a = 0; a < n; a++){
            for (int b : graph.get(a)){ //Iterates through all neighbours of the current vertex
                reverseGraph.addEdge(b, a);
            }
        }
        return reverseGraph;
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of antennas followed by the number of cables: ");
        int n = sc.nextInt();
        int e = sc.nextInt();
        StronglyConnectedGraph graph = new StronglyConnectedGraph(n, e);
        System.out.println("Add the antennas that are connected by a cable: ");
        for (int i = 0; i < e; i++){   
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.addEdge(a, b); 
        }
        if (graph.isStronglyConnected()){
            System.out.println("\nyes");
        } 
        else{
            System.out.println("\nno");
        }
        //Hardcoded inputs, uncomment if need be
        /*int n = 4;
        int e = 4;
        StronglyConnectedGraph graph = new StronglyConnectedGraph(n , e);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        if (graph.isStronglyConnected()){
            System.out.println("yes");
        } 
        else{
            System.out.println("no");
        }
        */
    }
}
