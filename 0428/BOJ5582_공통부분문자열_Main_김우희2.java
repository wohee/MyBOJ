package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ5582_공통부분문자열_Main_김우희2 {
	static int[][] dp;
	
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String first = br.readLine();
		String second = br.readLine();
		
//		if(first.length() > second.length()) {
//			String temp = first;
//			first = second;
//			second = temp;
//		}

		
		dp = new int[first.length()+1][second.length()+1];
		
		
		for (int i = 1; i <= first.length(); i++) {
			for (int j = 1; j <= second.length(); j++) {
				if(first.charAt(i-1) == second.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				
				} 
				
				result = Math.max(result, dp[i][j]);
			}
		}

		
		System.out.println(result);
		
	} // end of main

} // end of class
