package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1922_네트워크연결_Main_김우희 {
	static int N, M; // 정점, 연결관계
	
	static ArrayList<Edge>[] list;
	
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
//		list = new List[M];
		parent = new int[N+1];
		rank = new int[N+1];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[i].add(new Edge(a, b, c));
		}
		
//		Arrays.sort(list);
		Arrays.sort(list);
		
		for (int i = 1; i < N+1; i++) {
			makeSet(i);
		}
		
		
		int result = 0;
		int cnt = 0;
		
		for (int i = 0; i < M; i++) {
			int a = findSet((int)list[i].get(0).from);
			int b = findSet((int)list[i].get(0).to);
			
			if(a != b) {
				union(a,b);
				result += list[i].get(0).weight;
				cnt++;
				if(cnt == N-1) {
					break;
				}
				
			}
		}
		System.out.println(result);

	}
	
	
	private static void union(int a, int b) {
		int px = findSet(a);
		int py = findSet(b);
		
		if(rank[px] > rank[py]) {
			parent[py] = px;
		} else {
			parent[px] = py;
			if(rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}
		
	private static int findSet(int x) {
		if(parent[x] == x) {
			return x;
		} else {
			parent[x] = findSet(parent[x]);
			return parent[x];
		}
	}


	static void makeSet(int x) {
		parent[x] = x;
	}
	
	static class Edge implements Comparable<Edge>{
		private int from;
		private int to;
		private int weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

	}

}




