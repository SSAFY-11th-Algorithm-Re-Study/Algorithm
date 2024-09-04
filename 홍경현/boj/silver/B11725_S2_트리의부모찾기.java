package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * [문제 해석]
 * 트리의 루트를 1이라고 정했을 때 각 노드의 부모를 구하는 프로그램
 * 
 * [입력]
 * 1. 노드의 개수 2<=N<=100,000
 * 2. 트리상 연결된 두 정점
 * 
 * [출력]
 * 각 노드의 부모 노드 번호
 * 
 * [문제 해결 프로세스]
 * dfs/bfs로 부모 찾기
 * 
 * 최적화1) visit 배열 대신 parents 배열로 방문 노드 체크
 * 		  552ms -> 524ms
 * 최적화2) bfs 대신 dfs로 탐색하기 -> 대신 메모리는 증가함
 * 		  524ms -> 476ms 
 * 		  Queue를 사용하지 않기 때문에 빠른 것 같음
 */
public class B11725_S2_트리의부모찾기 {
	
	static List<Integer>[] graph;
	static int[] parents;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		graph = new List[N+1];
		for(int i=1; i<=N; i++)
			graph[i] = new ArrayList<Integer>();
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
//		bfs();
		dfs(0, 1);
		
		for(int i=2; i<=N; i++) {
			sb.append(parents[i]).append('\n');		
		}
		
		System.out.println(sb);
	}

	//71492KB 476ms
	private static void dfs(int idx, int n) {
		if(idx == N) return;
		for(int k : graph[n]) {
			if(parents[k] > 0) continue;
			parents[k] = n;
			dfs(idx+1, k);
		}
	}

	//65388KB 524ms
//	private static void bfs() {
//		Queue<Integer> queue = new ArrayDeque<Integer>();
//		queue.add(1);
//		parents[1] =1;
//		
//		while(!queue.isEmpty()) {
//			int n = queue.poll();
//			for(int k : graph[n]) {
//				if(parents[k] > 0) continue;
//				queue.offer(k);
//				parents[k] = n;
//			}
//		}
//	}
}
