package Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	/**
	 * 求i到j的最短路径。
	 * @param graph 图数组，graph[u][v]表示u到v的边权制，有边为非负数，无边为Integer.MAX_VALUE；
	 * @param i
	 * @param j
	 * @return
	 */
	public static int shortestPath(int[][] graph, int from, int to){
		int[] distance = graph[from];
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return distance[i1] - distance[i2];
			}
		});
		
		for(int i = 0;i < distance.length;i++){
			heap.add(i);
		}
		
		while(!heap.isEmpty()){
			int current = heap.poll();
			
			List<Integer> neighbourList = new ArrayList<>();
			int[] neighbourArray = graph[current];
			for(int i = 0;i < neighbourArray.length;i++){
				if(neighbourArray[i] != Integer.MAX_VALUE && i != current){
					neighbourList.add(i);
				}
			}
			
			for(int neighbourIndex : neighbourList){
				if(distance[neighbourIndex] > distance[current] + graph[current][neighbourIndex]){
					distance[neighbourIndex] = distance[current] + graph[current][neighbourIndex];
				}
			}
		}
		
		return distance[to];
	}
	
	public static void main(String[] args){
		int[][] graph = new int[][]{{0, 7, 9, Integer.MAX_VALUE, Integer.MAX_VALUE, 14},{7, 0, 10, 15, Integer.MAX_VALUE, Integer.MAX_VALUE},{9, 10, 0, 11, Integer.MAX_VALUE, 2},{Integer.MAX_VALUE, 15, 11, 0, 6, Integer.MAX_VALUE},{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0, 9},{14, Integer.MAX_VALUE, 2, Integer.MAX_VALUE, 9, 0}};
		System.out.println(shortestPath(graph, 3, 5));
	}
}
