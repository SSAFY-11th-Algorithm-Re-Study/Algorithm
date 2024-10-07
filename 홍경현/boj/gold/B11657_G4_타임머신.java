package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 17032KB 232ms	
 * [문제 해석]
 * N개의 도시
 * 한 도시에서 출발하여 다른 도시에 도착하는 버스 M개
 * A 시작 도시 B 도착 도시 C 버스 이동 시간
 * C = 0: 순간 이동
 * C < 0: 타임머신으로 시간 되돌아감
 * 
 * [입력]
 * 1. 1<=N<500, 1<=M<=6000
 * 2. A B C 
 * 
 * [출력]
 * 1번 도시 -> K 도시로 가는 과정에서 시간을 무한히 오래 전으로 돌릴 수 있으면 -1
 * 그렇지 않다면 N-1개 줄에 걸쳐 1번 도시에서 ...N번 도시로 가는 가장 빠른 시간 출력
 * 가는 경로가 없으면 -1
 */
public class B11657_G4_타임머신 {

	static int N, M;
	static long distance[];
	static int graph[][];
	static long INF = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[M][3];
		distance = new long[N+1];
		for(int i=1; i<=N; i++) {
			distance[i] = INF;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
		}
		

		for(int i=1; i<=N; i++) {
			distance[1] = 0;
			for(int j=0; j<M; j++) {
				int cur = graph[j][0];
				int next = graph[j][1];
				int cost = graph[j][2];
				
				if(distance[cur] != INF && distance[next] > distance[cur] + cost) {
					distance[next] = distance[cur] + cost;
					
					if(i == N) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		
		for(int i=2; i<=N; i++) {
			System.out.println(distance[i] == INF ? -1 : distance[i]);
		}
	}

}
