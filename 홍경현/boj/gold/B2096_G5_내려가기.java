package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 54,572KB 312ms
 * [문제 해석]
 * N 줄에 0 이상 9 이하의 숫자가 세 개씩
 * 첫줄->마지막 줄 내려가기 게임
 * 바로 아래의 수 또는 바로 아래의 수와 붙어있는 수로만 이동 가능
 * 
 * [입력]
 * 1. 1<=N<=100,000
 * 2. 숫자 3개
 * 
 * [출력]
 * 최대점수 최소점수
 * 
 * [문제 해결 프로세스]
 * min[][] = new min[N][3];
 * min[i][0] = Math.min(min[i-1][0], min[i-1][1]) + arr[i][0]
 * min[i][1] = Math.min(min[i-1][0], Math.min(min[i-1][1], min[i-1][2]) + arr[i][0]
 * min[i][2] = Math.min(min[i-1][1], min[i-1][2]) + arr[i][2]
 * 
 */
public class B2096_G5_내려가기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[][] minDp = new int[N][3];
		int[][] maxDp = new int[N][3];
		
		for(int i=0; i<3; i++) {
			minDp[0][i] = arr[0][i];
			maxDp[0][i] = arr[0][i];
		}

		for(int i=1; i<N; i++) {
			//min
			minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1]) + arr[i][0];
			minDp[i][1] = Math.min(minDp[i-1][0], Math.min(minDp[i-1][1], minDp[i-1][2])) + arr[i][1];
			minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2]) + arr[i][2];
			
			//max
			maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1]) + arr[i][0];
			maxDp[i][1] = Math.max(maxDp[i-1][0], Math.max(maxDp[i-1][1], maxDp[i-1][2])) + arr[i][1];
			maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2]) + arr[i][2];
		}
		
		int min = Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2]));
		int max = Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]));
		
		System.out.println(max+" "+min);
	}

}
