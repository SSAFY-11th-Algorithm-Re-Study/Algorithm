package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 22328KB 192ms
 * [문제 해석]
 * 10,000 이하 자연수로 이루어진 N짜리 수열
 * 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중
 * 가장 짧은 것
 * 
 * [입력]
 * 1. 10<=N<=100,000, 0<S<=100,000,000
 * 2. 수열 10,000 이하의 자연수
 * 
 * [출력]
 * 최소 길이, 불가능하면 0
 * 
 * [문제 해결 프로세스]
 * 배열 0부터 시작해서 for문 돌기
 * -> 다음 배열값을 더했을 때 S보다 크면 시작한 부분을 S보다 작아질 때까지 빼기
 * -> S보다 작으면 다시 for문 돌기
 */
public class B1806_G4_부분합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = 1000000;
		int start = 0, sum = 0, cur = 0;
		
		for(int i=0; i<N; i++) {
			sum += arr[i];
			cur++;
			if(sum<S) continue;
			while(sum>=S) {
				sum -= arr[start++];
				cur--;
			}
			res = Math.min(res, cur+1);
		}
		
		System.out.println(res==1000000?0:res);
	}

}
