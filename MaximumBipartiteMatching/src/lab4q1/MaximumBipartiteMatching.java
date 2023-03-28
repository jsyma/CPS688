/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4q1;
import java.util.*;
/**
 *
 * @author Jonathan Ma
 */
public class MaximumBipartiteMatching{
    
    public static int maximumFlow(int[][] graph, int source, int sink){
        int[] parent = new int[graph.length];
        int maxFlow = 0;
        while (BFS(graph, source, sink, parent)){
            int flow = Integer.MAX_VALUE;
            //Subtract flow from forward edges and adding it to backwards edges
            for (int i = sink; i != source; i = parent[i]){
                int j = parent[i];
                flow = Math.min(flow, graph[j][i]);
                graph[j][i] -= flow;
                graph[i][j] += flow;
            }
            //Add flow to the maxFlow 
            maxFlow += flow;
        }
        return maxFlow;
    }

    public static boolean BFS(int[][] graph, int source, int sink, int[] parent){
        boolean[] visited = new boolean[graph.length];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;
        while (!queue.isEmpty()) {
            int x = queue.poll(); //If queue is not empty, removes and retrieves the head of the queue 
            for (int i = 0; i < graph.length; i++){
                if (!visited[i] && graph[x][i] > 0){
                    queue.add(i);
                    parent[i] = x;
                    visited[i] = true;
                }
            }
        }
        return visited[sink];
    }

    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the N dimension in an N by M Matrix");
        int n = scanner.nextInt();
        System.out.println("Input the M dimension in an N by M Matrix");
        int m = scanner.nextInt();
        int [][] graph = new int[n][m];
        System.out.println("Input the Matrix below: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                graph[i][j] = scanner.nextInt();
            }
        }
        
        /*
        int[][] graph =  {{0, 1, 1, 0, 0, 0},
                         {0, 0, 0, 0, 0, 0},
                         {1, 0, 0, 1, 0, 0},
                         {0, 0, 1, 0, 0, 0},
                         {0, 0, 1, 1, 0, 0},
                         {0, 0, 0, 0, 0, 1}};*/
        
        int source = graph.length;
        int sink = graph.length + 1;
        int[][] flowNetwork = new int[graph.length + 2][graph.length + 2]; //adding the source and sink vertices
        //Initializes the flowNetwork with same values as graph
        for (int i = 0; i < graph.length; i++){
            System.arraycopy(graph[i], 0, flowNetwork[i], 0, graph[i].length);
            //for (int j = 0; j < graph[i].length; j++){
            //    flowNetwork[i][j] = graph[i][j];
            //}
        }
        //Adding a source vertex to the flowNetwork
        for (int i = 0; i < graph.length; i++){
            flowNetwork[source][i] = 1; //Setting capacity of edges between the source and all nodes in the flowNetwork
            flowNetwork[i][sink] = 1; //Setting capacity of edges between all nodes in the flowNetwork and the sink
        }
        for (int i = 0; i < graph.length; i++){
            boolean matched = false;
            for (int j = 0; j < graph[i].length; j++){
                if (graph[i][j] != 0){
                    matched = true;
                    break;
                }
            }
            if (!matched){
                flowNetwork[source][i] = 0; //No job available == set to 0 so no applicant can match
            }
        }
        System.out.println("The maximum number of applicants matching for the jobs is " + maximumFlow(flowNetwork, source, sink));
    }
}