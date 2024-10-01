package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 32956KB 272ms
public class B2342_G3_DanceDanceRevolution {

	public static void main(String[] args) throws IOException {
		int MAX_VALUE = 1000000;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		List<Integer> list = new ArrayList<>();
		list.add(0);
		while(true) {
			int n = Integer.parseInt(st.nextToken());
			if(n==0) break;
			list.add(n);
		}
		
		int[][][] dp = new int[5][5][list.size()];
		for(int l=0; l<5; l++) {
			for(int r=0; r<5; r++) {
				Arrays.fill(dp[l][r], MAX_VALUE);
			}
		}
		
		if(list.size()==0) {
			System.out.println(0);
			return;
		}
		
		dp[0][0][0] = 0;
				
		for(int idx=1; idx<list.size(); idx++) {
			int n = list.get(idx);
			for(int l=0; l<5; l++) {
				for(int r=0; r<5; r++) {
					dp[l][n][idx] = Math.min(dp[l][n][idx], dp[l][r][idx-1] + moveCost(r, n));
					dp[n][r][idx] = Math.min(dp[n][r][idx], dp[l][r][idx-1] + moveCost(l, n));
				}
			}
		}
		
		int result = MAX_VALUE;
		
		int n = list.get(list.size()-1);
		for(int i=0; i<5; i++) {
			result = Math.min(result, dp[n][i][list.size()-1]);
			result = Math.min(result, dp[i][n][list.size()-1]);
		}
		
		System.out.println(result);
	}

	private static int moveCost(int l, int n) {
		if(l == 0) return 2;
		if(l == n) return 1;
		if(Math.abs(l-n) == 2) return 4;
		return 3;
	}

}
