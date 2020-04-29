package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11066_파일합치기_Main_김우희 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		
		for (int testCase = 0; testCase < t; testCase++) {
			int k = Integer.parseInt(br.readLine());
			int[] sum = new int[k+1];
					
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= k; i++) {
				int num = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + num;
			} // 입력 & 합 누적 배열에 저장하기
			
			int[][] dp = new int[k+1][k+1];
			
			for (int i = 1; i < k; i++) { // 파일의 길이
				for (int x = 1; x+i <= k; x++) { // 시작 파일
					int y = x+i; // 끝 위치

					dp[x][y] = Integer.MAX_VALUE;
					
					for (int w = x; w < y; w++) { // 나누는 챕터부분
						dp[x][y] = Math.min(dp[x][y], dp[x][w]+dp[w+1][y]+sum[y]-sum[x-1]);
						
					}
				}
			}
			
			System.out.println(dp[1][k]);
			
		}
	}

}
  