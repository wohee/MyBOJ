package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ5582_공통부분문자열_Main_김우희 {
	static char[] first;
	static char[] second;
	
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			
			if(i == 0) {
				first = new char[line.length()];
				for (int j = 0; j < line.length(); j++) {
					first[j] = line.charAt(j);
				}
			} else {
				second = new char[line.length()];
				for (int j = 0; j < line.length(); j++) {
					second[j] = line.charAt(j);
				}
			}

		}

		
		if(first.length < second.length) { // 기준 second
			for (int i = 0; i < first.length; i++) {
				for (int j = 0; j < second.length; j++) {
					int num = 0;
					if(first[i] == second[j]) {
						int a = i;
						int b = j;
						while(a != first.length && b != second.length && first[a] == second[b]) {
							num++;
							a++;
							b++;
						}
						if(result < num) {
							result = num;
						}
					}
					
				}
				
			}

		} else {
			for (int i = 0; i < second.length; i++) {
				for (int j = 0; j < first.length; j++) {
					int num = 0;
					if(first[j] == second[i]) {
						int a = i;
						int b = j;
						while(a != second.length && b != first.length && first[b] == second[a]) {
							num++;
							a++;
							b++;
						}
						if(result < num) {
							result = num;
						}
					}
					
				}
				
			}
			
		}
		
		System.out.println(result);
		
	} // end of main

} // end of class
