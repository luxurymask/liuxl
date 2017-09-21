package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	static final int INFINITE = Integer.MAX_VALUE;

	/**
	 * 求i到j的最短路径。
	 * 
	 * @param graph
	 *            图数组，graph[u][v]表示u到v的边权制，有边为非负数，无边为Integer.MAX_VALUE；
	 * @param from
	 *            单源最短路径的源
	 * @param to
	 *            目的节点
	 * @return from到to的最短路径
	 */
	public static int shortestPathLength(int[][] graph, int from, int to) {
		int[] distance = Arrays.copyOf(graph[from], graph[from].length);
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return distance[i1] - distance[i2];
			}
		});

		for (int i = 0; i < distance.length; i++) {
			heap.add(i);
		}

		while (!heap.isEmpty()) {
			int current = heap.poll();

			List<Integer> neighbourList = new ArrayList<>();
			int[] neighbourArray = graph[current];
			for (int i = 0; i < neighbourArray.length; i++) {
				if (neighbourArray[i] != INFINITE && i != current) {
					neighbourList.add(i);
				}
			}

			int newDistance;
			for (int neighbourIndex : neighbourList) {
				if ((newDistance = distance[current] + graph[current][neighbourIndex]) < distance[neighbourIndex]) {
					distance[neighbourIndex] = newDistance;
				}
			}
		}

		return distance[to];
	}

	/**
	 * 打印最短路径。
	 * @param graph
	 * @param from
	 * @param to
	 */
	public static void shortestPath(int[][] graph, int from, int to) {
		int[] distance = graph[from];
		List<List<Integer>> list = new ArrayList<>();
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return distance[i1] - distance[i2];
			}
		});

		for (int i = 0; i < distance.length; i++) {
			heap.add(i);
			list.add(new ArrayList<>());
		}

		while (!heap.isEmpty()) {
			int current = heap.poll();
			list.get(current).add(current);
			if (current == to)
				break;

			List<Integer> neighbourList = new ArrayList<>();
			int[] neighbourArray = graph[current];
			for (int i = 0; i < neighbourArray.length; i++) {
				if (neighbourArray[i] != INFINITE && i != current) {
					neighbourList.add(i);
				}
			}

			int newDistance;
			for (int neighbourIndex : neighbourList) {
				if ((newDistance = distance[current] + graph[current][neighbourIndex]) < distance[neighbourIndex]) {
					distance[neighbourIndex] = newDistance;
					List<Integer> neighbour = list.get(neighbourIndex);
					neighbour.clear();
					neighbour.addAll(list.get(current));
				}
			}
		}
		List<Integer> path = list.get(to);
		for (int i = 0; i < path.size() - 1; i++) {
			System.out.print(path.get(i) + " - ");
		}
		System.out.println(path.get(path.size() - 1));
	}

	public static void main(String[] args) {
		int[][] graph = new int[][] { { 0, 7, 9, INFINITE, INFINITE, 14 }, { 7, 0, 10, 15, INFINITE, INFINITE },
				{ 9, 10, 0, 11, INFINITE, 2 }, { Integer.MAX_VALUE, 15, 11, 0, 6, Integer.MAX_VALUE },
				{ INFINITE, INFINITE, INFINITE, 6, 0, 9 }, { 14, INFINITE, 2, INFINITE, 9, 0 } };
		System.out.println(shortestPathLength(graph, 0, 3));
		shortestPath(graph, 0, 3);
	}
}
