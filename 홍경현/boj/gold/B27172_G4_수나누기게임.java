package gold;

import java.io.BufferedReader;
import java.io.IOException;import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 46168KB 364ms
 * [문제 해석]
 * - 게임을 시작하기 전 각 플레이어는 1~1,000,000 사이의 수가 적힌 서로 다른 카드를 잘 섞은 뒤 한 장씩 나눠 가짐
 * - 매 턴마다 플레이어는 다른 플레이어와 한 번씩 결투
 * - 결투는 서로의 카드를 보여줌
 * 		-> 플레이어의 카드에 적힌 수로 다른 플레이어의 카드에 적힌 수를 나눴을 때 나머지가 0이면 승리
 * 		-> 플레이어의 카드에 적힌 수가 다른 플레이어의 카드에 적힌 수로 나누어 떨어지면 패배
 * 		-> 둘다 아니면 무승부
 * - 승리한 플레이어는 1점을 획득하고,  패배한 플레이어는 1점 잃음
 * - 본인을 제외한 모든 플레이어와 정확히 한 번씩 결투하면 게임 종료
 * - 게임 종료된 후의 모든 플레이어의 점수
 * 
 * [입력]
 * 1. 플레이어의 수 N 2<=N<=100,000
 * 2. 카드에 적힌 수
 * 
 * [출력]
 * 각 플레이어의 점수
 * 
 * [문제 해결 프로세스]
 * 모든 조합의 경우의 수 계산하여 배열에 점수 => 시간초과
 * 에라토스테네스의 체
 */
public class B27172_G4_수나누기게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		int[] scores = new int[N+1];
		int max = 0;
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		int[] rev = new int[max+1];
		for(int i=0; i<N; i++) {
			rev[arr[i]] = i+1;
		}
		
		for(int n : arr) {
			for(int i=n*2; i<=max; i+=n) {
				if(rev[i] != 0) {
					scores[rev[i]]--;
					scores[rev[n]]++;
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			sb.append(scores[i]+" ");
		}
		
		System.out.println(sb);
	}

}
