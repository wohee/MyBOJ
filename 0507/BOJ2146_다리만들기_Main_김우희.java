package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ2146_다리만들기_Main_김우희 {
	static int N;
	static int isLandIdx;
	
	static int[][] map;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int result = Integer.MAX_VALUE;
	
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
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 1) {
					makeBridBFS(i,j);
				}
			}
		}

		System.out.println(result);
	} // end of main

	private static void makeBridBFS(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];

		q.add(new Point(i,j,0));
		visited[i][j] = true;

		while(!q.isEmpty()) { 
			Point front = q.poll();
			
			for (int d = 0; d < dx.length; d++) {
				int newX = front.row + dx[d];
				int newY = front.col + dy[d];
				
				if(isIn(newX, newY) && !visited[newX][newY]) {
					if(map[newX][newY] == 0) {
						q.add(new Point(newX, newY, front.cnt+1));
						visited[newX][newY] = true;
					} else if(map[newX][newY] == map[i][j]) {
						continue;
					} else if (map[newX][newY] != map[i][j]) {
						if(result > front.cnt) {
							result = front.cnt;
						}
					}
				}
				
			}
		}
		
	}

	private static void bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(i, j, 0));
		map[i][j] = isLandIdx;
		
		while(!q.isEmpty()) { 
			Point front = q.poll();
			
			for (int d = 0; d < dx.length; d++) {
				int newX = front.row + dx[d];
				int newY = front.col + dy[d];
				
				if(isIn(newX, newY) && map[newX][newY] == 1) {
					map[newX][newY] = isLandIdx;
					q.add(new Point(newX, newY, 0));
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
		int cnt;
		
		public Point(int row, int col, int cnt) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}

	}

} // end of class
