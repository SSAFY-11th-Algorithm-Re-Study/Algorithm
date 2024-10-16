package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 11660KB 68ms
 * [문제 해석]
 * Strahler 순서
 * - 강의 근원인 노드의 순서는 1
 * - 그 노드로 들어오는 강의 순서 중 가장 큰 값이 i라고 할 때
 * 		i인 강이 1개면 i, 2개 이상이면 i+1
 * 
 * 노드 M은 항상 바다와 만나는 노드
 * 
 * [입력]
 * 1. T (1<=T<=1000)
 * 2. 테스트 케이스 K 노드의 수 M 간선의 수 P 
 * 3. A B (A->B)
 * 
 * [출력]
 * 테스트 케이스 번호와 Strahler 순서
 * 
 * [문제 해결 프로세스]
 * 방향 그래프, 역방향 그래프
 * 역방향 그래프에서 자식이 0이면 순서 1
 * 
 * 1인 노드 Queue에 담기 -> 역방향으로 확인
 * -> 순서를 알 수 없는 노드면 그 노드의 역방향을 확인
 */
public class B9470_G3_Strahler순서 {
	static int M, P;
	static int[] strahler;
	static List<Integer>[] graph, revGraph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			strahler = new int[M+1];
			graph = new List[M+1];
			revGraph = new List[M+1];
			
			for(int i=1; i<=M; i++) {
				graph[i] = new ArrayList<Integer>();
				revGraph[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<P; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
				revGraph[b].add(a);
			}
			
			for(int i=1; i<=M; i++) {
				checkStrahler(i);
			}
			
			sb.append(K).append(" ").append(strahler[M]).append('\n');
		}
		
		System.out.println(sb);
	}

	private static void checkStrahler(int number) {
		int i = 0;
		int count = 0;
		for(int n : revGraph[number]) {
			if(strahler[n] == 0) checkStrahler(n);
			if(i == strahler[n]) count ++;
			else if (i < strahler[n]) {
				count = 1;
				i = strahler[n];
			}
		}
		
		if(count == 0) strahler[number] = 1;
		else if(count == 1) strahler[number] = i;
		else strahler[number] = i+1;
	}

}
