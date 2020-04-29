package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


// 1 ->
// 2 <-
// 3 ^
//   |
//
// 4 |
//   V



public class BOJ17837_새로운게임2_Main_김우희 {
	static int N,K;
	static int[][] map;
	static ArrayList<Integer>[][] arr;
	static Horse[] horses;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		arr = new ArrayList[N][N]; // 말의 위치 및 순서를 위해
		horses = new Horse[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = new ArrayList<Integer>();
			}	
		}

		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			horses[i] = new Horse(x-1, y-1, dir-1);
			arr[x-1][y-1].add(i);

		}
		
		int turn = 0;
		
ex:		while(turn <= 1000) {
			
			for (int i = 0; i < K; i++) {
				int newX = horses[i].x + dx[horses[i].dir];
				int newY = horses[i].y + dy[horses[i].dir];

				// 범위를 벗어나는 경우
				if(newX < 0 || newY < 0 || newX >= N || newY >= N || map[newX][newY] == 2) {
				
					if(horses[i].dir == 0 || horses[i].dir == 1) { 	// 왼쪽 오른쪽
						horses[i].dir = horses[i].dir == 0? 1:0;
						
					} else if (horses[i].dir == 2 || horses[i].dir == 3) { 	// 위 아래
						horses[i].dir = horses[i].dir == 2? 3:2;
						
					}
					
					newX = horses[i].x + dx[horses[i].dir];
					newY = horses[i].y + dy[horses[i].dir];
					
					if(newX < 0 || newY < 0 || newX >= N || newY >= N || map[newX][newY] == 2) {
						continue;
					}

				}

				// 위치 파악
				int size = arr[horses[i].x][horses[i].y].size();
				
				int index = arr[horses[i].x][horses[i].y].indexOf(i);
				
				int hx = horses[i].x;
				int hy = horses[i].y;
				
				if(map[newX][newY] == 0) {
					for (int j = index; j < size; j++) {
						int temp = arr[hx][hy].get(index);
						
						arr[newX][newY].add(temp); // 새로운 위치에 번호 추가
						arr[hx][hy].remove(index);
						
						horses[temp].x = newX;
						horses[temp].y = newY;

					}
					if(arr[newX][newY].size() >= 4) {
						turn++;
						break ex;
					}
					
				} else if(map[newX][newY] == 1) { // 빨간색
					for (int j = size-1; j >= index; j--) {
						int temp = arr[hx][hy].get(j);
						
						arr[newX][newY].add(temp);
						arr[hx][hy].remove(j);
						
						horses[temp].x = newX;
						horses[temp].y = newY;
					}
					if(arr[newX][newY].size() >= 4) {
						turn++;
						break ex;
					}

				}
				
			}
			
			turn++;
		} // end of while
		
		
		if(turn == 0 || turn > 1000) {
			System.out.println("-1");
		} else {
			System.out.println(turn);
		}

		
	} // end of main
	

	public static class Horse {
		int x;
		int y;
		int dir;
		
		public Horse(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}
} // end of class
