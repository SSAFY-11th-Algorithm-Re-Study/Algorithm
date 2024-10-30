package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 16,268kb 108ms
 * [문제 해석]
 * 두 수열이 주어졌을 때 모두의 부분 수열이 되는 수열 중 가장 긴 것
 * 
 * [입력]
 * 두 문자열
 * 
 * [출력]
 * 1. LCS 길이
 * 2. LCS 출력
 */
public class B9252_G4_LCS2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str1 = br.readLine();
		String str2 = br.readLine();
		int arr[][] = new int[str1.length()+1][str2.length()+1];
		int max = 0;
		
		for(int i=1; i<=str1.length(); i++){
			for(int j=1; j<=str2.length(); j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					arr[i][j] = arr[i-1][j-1] + 1;
					max = Math.max(max, arr[i][j]);
					continue;
				}
				
				arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
			}
		}
		
		char res[] = new char[max];
		int x = str1.length();
		int y = str2.length();
		
		for(int i=0; i<max; i++) {
			while(true) {
				if(arr[x][y] == arr[x-1][y]) {
					x--;
				}else if(arr[x][y] == arr[x][y-1]){
					y--;
				}else {
					x--;
					y--;
					res[i] = str1.charAt(x);
					break;
				}
			}
		}
		
		sb.append(max).append('\n');
		for(int i=max-1; i>=0; i--) {
			sb.append(res[i]);
		}
		System.out.println(sb);
	}

}
