/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2q3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jma
 */
public class MST{
    private final int numOfVertices;
    private final int numOfEdges;
    private final ArrayList<Edge>[] adjList;
    private final int[][] graph;
    
    public MST(int n, int e){
        numOfVertices = n;
        numOfEdges = e;
        adjList = new ArrayList[n];
        graph = new int[n][n];
        for(int i = 0; i < n; i++){
            adjList[i] = new ArrayList<>();
        }
    }
    
    public class Edge{
        int a, b, weight;
        public Edge(int a, int b, int weight){
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }
    
    public void addEdgeWeight(int a, int b, int w){
        adjList[a].add(new Edge(a, b, w));
        adjList[b].add(new Edge(b, a, w));
    }

    int minKey(int key[], Boolean mstVertices[]){
        int min = Integer.MAX_VALUE;
        int indexOfMin = -1;
        for (int i = 0; i < numOfVertices; i++)
            if (mstVertices[i] == false && key[i] < min){
                min = key[i];
                indexOfMin = i;
            }
        return indexOfMin;
    }

    void primMST(int graph[][]){
        int parent[] = new int[numOfVertices];
        int key[] = new int[numOfVertices];
        Boolean mstVertices[] = new Boolean[numOfVertices];
        for (int i = 0; i < numOfVertices; i++){
            key[i] = Integer.MAX_VALUE;
            mstVertices[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;
        for (int i = 0; i < numOfVertices - 1; i++){
            int u = minKey(key, mstVertices);
            mstVertices[u] = true;
            for (int j = 0; j < numOfVertices; j++)
                if (graph[u][j] != 0 && mstVertices[j] == false && graph[u][j] < key[j]) {
                    parent[j] = u;
                    key[j] = graph[u][j];
                }
        }
        printSolution(parent, numOfVertices, graph);
    }
    
    void printSolution(int parent[], int n, int graph[][]){
        int Mst = 0;
        for (int i = 1; i < numOfVertices; i++){
            System.out.println("Edge " + parent[i] + "-" + i + " has a weight of " + graph[i][parent[i]]);
            Mst += graph[i][parent[i]];
        }
        System.out.println("MST = " + Mst);        
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Vertices, followed by Edges: ");
        int n = sc.nextInt();
        int e = sc.nextInt();
        MST m = new MST(n, e);
        for (int i = 0; i < e; i++){
            System.out.println("Add weighted edges (a, b, weight): ");
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            m.addEdgeWeight(a, b, w);
        }
        for (int i = 0; i < m.numOfVertices; i++){
            for (Edge j : m.adjList[i]){
                int a = j.b;
                int w = j.weight;
                m.graph[i][a] = w;
                m.graph[a][i] = w; 
            }
        }
        m.primMST(m.graph);
        
        
        //Hardcoded inputs, uncomment if need be
        //MST m = new MST(4, 5);
        //m.addEdgeWeight(0, 1, 10);
        //m.addEdgeWeight(0, 2, 6);
        //m.addEdgeWeight(0, 3, 5);
        //m.addEdgeWeight(1, 3, 15);
        //m.addEdgeWeight(2, 3, 4);
        
        //for (int i = 0; i < m.numOfVertices; i++){
        //    for (Edge j : m.adjList[i]){
        //        int a = j.b;
        //        int w = j.weight;
        //        m.graph[i][a] = w;
        //        m.graph[a][i] = w; 
        //   }
        // }
        // m.primMST(m.graph);
    }
}