package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ2146_다리만들기_Main_김우희2 {
	static int N;
	static int isLandIdx;
	
	static int[][] map;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int MinDist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬번호 매기기
		isLandIdx = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1) {
					bfs(i,j);
					isLandIdx++;
				}
			}
		}
		
		//  각섬에서 다른섬까지의 최단 거리 찾아보기
		MinDist = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 1) {
					makeBridBFS(i,j);
				}
			}
		}

		System.out.println(MinDist);

	} // end of main

	private static void makeBridBFS(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(i, j, map[i][j], 0));
		
		boolean[][] visited = new boolean[N][N];
		visited[i][j] = true;

		while(!q.isEmpty()) { 
			Point front = q.poll();
			
			if(front.d >= MinDist) {
				break;
			} 
			
			for (int d = 0; d < dx.length; d++) {
				int newX = front.row + dx[d];
				int newY = front.col + dy[d];
				
				if(isIn(newX, newY) && !visited[newX][newY]) {
					visited[newX][newY] = true;
					
					if(map[newX][newY] == front.idx) { // 이 섬의 내륙지방
						continue;
					} else if(map[newX][newY] == 0) { // 바다 -> 다리연결
						q.add(new Point(newX, newY, front.idx, front.d+1));
					} else if (map[newX][newY] != front.idx) { // 다른 섬
						MinDist = Math.min(MinDist, front.d);
						return;
					}
				}
				
			}
		}
		
	}

	private static void bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(i, j, isLandIdx));
		// 방문 처리
		map[i][j] = isLandIdx;
		
		while(!q.isEmpty()) { 
			Point front = q.poll();
			
			for (int d = 0; d < dx.length; d++) {
				int newX = front.row + dx[d];
				int newY = front.col + dy[d];
				
				if(isIn(newX, newY) && map[newX][newY] == 1) {
					map[newX][newY] = isLandIdx;
					q.add(new Point(newX, newY, isLandIdx));
				}
				
			}
		}
	}
	
	private static boolean isIn(int row, int col) {
		return row >= 0 && col >= 0 && row < N && col < N;
	}

	
	static class Point{
		int row;
		int col;
		int idx;
		int d; // bfs의 depth
		
		public Point(int row, int col, int idx) {
			super();
			this.row = row;
			this.col = col;
			this.idx = idx;

		}
		public Point(int row, int col, int idx, int d) {
			this(row, col, idx);
			this.d = d;
		}

	}

} // end of class
