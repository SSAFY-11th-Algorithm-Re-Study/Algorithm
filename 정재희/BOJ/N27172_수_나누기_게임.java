package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * 인터넷 참고했습니다.
 * 에라토스테네스의 체
 * 1. 각 값과 인덱스를 바꾸기 (첫번째가 3이면 배열 중 3인덱스에 1넣기)
 * 2. 각 값의 배수 인덱스의 값티 0이 아니면 점수 증감 (배수인 애는 감소, 본인은 증가)
 */
public class N27172_수_나누기_게임 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()), X[] = new int[N], INF = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) INF = Math.max(INF, X[i] = Integer.parseInt(st.nextToken()));
		
		int[] P = new int[N+1], pos = new int[INF+1];
		for(int i = 0; i < N; i++) pos[X[i]] = i+1;

		for(int mod : X) for(int i = mod*2; i <= INF; i += mod) {
        	if (pos[i] != 0) {
            	P[pos[i]]--;
                P[pos[mod]]++;
            }
        }
		for(int i = 1; i <= N; i++) sb.append(P[i]).append(" ");
		System.out.print(sb);

	}

}
