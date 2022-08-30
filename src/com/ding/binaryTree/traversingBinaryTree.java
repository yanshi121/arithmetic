package com.ding.binaryTree;


import com.sun.javafx.geom.Edge;

import java.util.LinkedList;

public class traversingBinaryTree {
    public static void dfs(Graph graph, int start, boolean[] visited){
        System.out.println(graph.vertexes[start].data);
        visited[start] = true;
        for (int index : graph.adj[start]){
            if (!visited[index]){
                dfs(graph, index, visited);
            }
        }
    }
    public static void bfs(Graph graph, int start, boolean[] visited, LinkedList<Integer> queue){
        queue.offer(start);
        while (!queue.isEmpty()){
            int front = queue.poll();
            if (visited[front]){
                continue;
            }
            System.out.println(graph.vertexes[front].data);
            visited[front] = true;
            for (int index : graph.adj[front]){
                queue.offer(index);
            }
        }
    }
    public static class Vertex{
        int data;
        Vertex(int data){
            this.data = data;
        }
    }
    public static class Graph{
        private int size;
        private Vertex[] vertexes;
        private LinkedList<Integer>[] adj;
        Graph(int size){
            this.size = size;
            vertexes = new Vertex[size];
            adj = new LinkedList[size];
            for (int i = 0; i < size; i++){
                vertexes[i] = new Vertex(i);
                adj[i] = new LinkedList();
            }
        }
    }
//    public static int[] dijkstra(Graph graph, int startIndex){
//        int size = graph.vertexes.length;
//        int[] distances = new int[size];
//        boolean[] access = new boolean[size];
//        for (int i = 1; i < size; i++){
//            distances[i] = Integer.MAX_VALUE;
//        }
//        access[0] = true;
//        List<Edge> edgesFromStart = graph.adj[startIndex];
//        for (Edge edge : edgesFromStart){
//            distances[edge.index] = edge.weight;
//        }
//        for (int i = 1; i < size; i++){
//            int minDistanceFromStart = Integer.MIN_VALUE;
//            int minDistanceIndex = -1;
//            for (int j = 1; j < size; j++){
//                if (!access[j] && (distances[j] < minDistanceFromStart)){
//                    minDistanceFromStart = distances[j];
//                    minDistanceIndex = j;
//                }
//            }
//            if (minDistanceIndex == -1){
//                break;
//            }
//            access[minDistanceIndex] = true;
//            for (Edge edge : graph.adj[minDistanceIndex]){
//                if (access[edge.index]){
//                    continue;
//                }
//                int weight = edge.weight;
//                int preDistance = distances[edge.index];
//                if ((weight != Integer.MAX_VALUE) && ((minDistanceFromStart + weight) < preDistance)){
//                    distances[edge.index] = minDistanceFromStart + weight;
//                }
//            }
//        }
//        return prevs;
//    }
//    public static int[] dijkstraV2(Graph graph, int startIndex){
//        int size = graph.vertexes.length;
//        int[] distances = new int[size];
//        int[] prevs = new int[size];
//        boolean[] access = new boolean[size];
//        for (int i = 0; i < size; i++){
//            distances[i] = Integer.MAX_VALUE;
//        }
//        access[0] = true;
//        List<Edge> edgesFromStart = graph.adj[startIndex];
//        for (Edge edge : edgesFromStart){
//            distances[edge.index] = edge.weight;
//            prevs[edge.index] = 0;
//        }
//        for (int i = 1; i < size; i++){
//            int minDistanceFromStart = Integer.MIN_VALUE;
//            int minDistanceIndex = -1;
//            for (int j = 1; j < size; j++){
//                if (!access[j] && (distances[j] < minDistanceFromStart)){
//                    minDistanceFromStart = distances[j];
//                    minDistanceIndex = j;
//                }
//            }
//            if (minDistanceIndex == -1){
//                break;
//            }
//            access[minDistanceIndex] = true;
//            for (Edge edge : graph.adj[minDistanceIndex]){
//                if (access[edge.index]){
//                    continue;
//                }
//                int weight = edge.weight;
//                int preDistance = distances[edge.index];
//                if ((weight != Integer.MAX_VALUE) && ((minDistanceFromStart + weight) < preDistance)){
//                    distances[edge.index] = minDistanceFromStart + weight;
//                    prevs[edge.index] = minDistanceIndex
//                }
//            }
//        }
//        return distances;
//    }
    final static int INF = Integer.MAX_VALUE;
    public static void floyd(int[][] matrix){
        for (int k = 0; k < matrix.length; k++){
            for (int i = 0; i < matrix.length; i++){
                for (int j = 0; j < matrix.length; j++){
                    if ((matrix[i][j] == INF) || (matrix[k][j] == INF)){
                        continue;
                    }
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        System.out.println("最短路径 \n");
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                System.out.printf("%3d  ", matrix[i][j]);
            }
            System.out.println("\n");
        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.adj[0].add(1);
        graph.adj[0].add(2);
        graph.adj[0].add(3);
        graph.adj[1].add(0);
        graph.adj[1].add(3);
        graph.adj[1].add(4);
        graph.adj[2].add(0);
        graph.adj[3].add(0);
        graph.adj[3].add(1);
        graph.adj[3].add(4);
        graph.adj[3].add(5);
        graph.adj[4].add(1);
        graph.adj[4].add(3);
        graph.adj[4].add(5);
        graph.adj[5].add(3);
        graph.adj[5].add(4);
        System.out.println("深度优先遍历");
        dfs(graph, 0, new boolean[graph.size]);
        System.out.println("广度优先遍历");
        bfs(graph, 0, new boolean[graph.size], new LinkedList<Integer>());
        int[][] matrix = {
                {0, 5, 2, INF, INF, INF, INF},
                {5, 0, INF, 1, 6, INF, INF},
                {2, INF, 0, 6, INF, 8, INF},
                {INF, 1, 6, 0, 1, 2, INF},
                {INF, 6, INF, 1, 0, INF, 7},
                {INF, INF, 8, 2, INF, 0, 3},
                {INF, INF, INF, INF, 7, 3, 0}
        };
        floyd(matrix);
    }
}
