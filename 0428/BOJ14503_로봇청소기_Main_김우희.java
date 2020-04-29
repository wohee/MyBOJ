package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 0 : 북(위), 1 : 동(오른쪽), 2 : 남(아래), 3 : 서(왼쪽)

public class BOJ14503_로봇청소기_Main_김우희 {
	static int N,M;
	static int r,c,d;
	static int result;
	
	static int[][] arr;
	static boolean[][] visited;
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	
	static int[] rx = {1, 0, -1, 0};
	static int[] ry = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		Robot robot = new Robot(r, c, d); // 로봇 시작위치
		
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Robot> queue = new LinkedList<>();
		queue.add(new Robot(r, c, d));

		int rotation = 0;
		
ex:		while(!queue.isEmpty()) {
			Robot rr = queue.poll();
			
			if(arr[rr.x][rr.y] == 0 && !visited[rr.x][rr.y]) {
				visited[rr.x][rr.y] = true;
				result++;
			}		
			
			
			int newX = rr.x+dx[rr.dir];
			int newY = rr.y+dy[rr.dir];
			
			if(rotation == 4) {
				if(arr[rr.x+rx[rr.dir]][rr.y+ry[rr.dir]] == 1) { // 뒤쪽이 벽인경우
					break ex;
				} else { // 뒤쪽이 벽이 아닌경우
					queue.add(new Robot(rr.x+rx[rr.dir], rr.y+ry[rr.dir], rr.dir));
					rotation = 0;
					continue;
				}
			}
	

			// 왼쪽 아직 청소하지 않은 공간 
			if(!visited[newX][newY] && arr[newX][newY] == 0) {
				if(rr.dir == 0) {
					rr.dir = 3;
				} else {
					rr.dir = rr.dir-1;
				}
				queue.add(new Robot(newX, newY, rr.dir));
				rotation = 0;
				continue;
			}
			
			
			// 왼쪽 청소할 공간이 없다
			if(visited[newX][newY] || arr[newX][newY] == 1) {
				if(rr.dir == 0) {
					rr.dir = 3;
				} else {
					rr.dir = rr.dir-1;
				}
				rotation++;
				queue.add(new Robot(rr.x, rr.y, rr.dir));
			}
			
		
		} // end of while 
		
		System.out.println(result);

	} // end of main


	public static class Robot{
		private int x;
		private int y;
		private int dir;
		
		public Robot(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}

}
