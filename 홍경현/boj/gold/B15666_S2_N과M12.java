package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/* 15556KB 92ms
 * [문제 해석]
 * - N개의 자연수 중에서 M개를 고른 수열
 * - 같은 수를 여러 번 골라도 된다
 * - 고른 수열은 비내림차순이어야 한다
 * 	- 길이가 K인 수열 A가 A1<=A2<=...<=Ak를 만족하면 비 내림차순
 * 
 * [입력]
 * 1. N, M (1<=M, N<=8)
 * 2. N개의 수 (10,000 이하)
 * 
 * [출력]
 * 만족하는 수열 출력
 * 사전 순으로 증가하는 순서로 출력
 * 
 * [문제 해결 프로세스]
 * - 중복을 포함하는 중복 순열?
 */
public class B15666_S2_N과M12 {
	static int N, M;
	static int arr[], res[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		res = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		comb(0, 0);
		System.out.println(sb);
	}

	private static void comb(int idx, int n) {
		if(idx == M) {
			for(int i=0; i<M; i++)
				sb.append(res[i]+" ");
			sb.append('\n');
			return;
		}
		
		int before = -1;
		for(int i=n; i<N; i++) {
			int now = arr[i];
			if(before != now) {
				before = now;
				res[idx] = arr[i];
				comb(idx+1, i);
			}
		}
	}

}
