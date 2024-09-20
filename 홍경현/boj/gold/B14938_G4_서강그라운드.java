package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 15840KB 108ms
 * [문제 해석]
 * 각 지역은 일정한 길이(1<=l<=15)의 길로 다른 지역과 연결 (양방 통행)
 * 낙하한 지역을 중심으로 거리가 수색범위 m (1<=m<=15) 이내의 모든 아이템 습득 가능
 * 얻을 수 있는 아이템의 최대 개수
 * 
 * [입력]
 * 1. 지역의 개수 1<=n<=100 수색범위 1<=m<=15 길의 개수 1<=r<=100
 * 2. 아이템 수들 1<=t<=30
 * 3. 지역 번호 a, b, 길의 길이 l (1<=l<=15)
 * 
 * [출력]
 * 최대 아이템 개수
 * 
 * [문제 해결 프로세스]
 * 1번부터 n번까지 반복문 돌면서 최대 개수 갱신
 * 
 * 1) dfs 돌면서 인접한 지역이 m 이하면 아이템 획득
 * 	1-1) 인접한 지역의 인접한 지역까지 dfs
 * 	=> 길이가 m 보다 크면 continue 
 */
public class B14938_G4_서강그라운드 {
	
	static int M, max, cur;
	static List<Node>[] list;
	static boolean visit[];
	static int[] item;

	static class Node{
		int n, l;

		public Node(int n, int t) {
			super();
			this.n = n;
			this.l = t;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		item = new int[N+1];
		list = new List[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, l));
			list[b].add(new Node(a, l));
		}

		for(int i=1; i<=N; i++) {
			visit = new boolean[N+1];
			visit[i] = true;
			cur = item[i];
			dfs(0, i);
			max = Math.max(max, cur);
		}
		
		System.out.println(max);
	}

	private static void dfs(int curL, int a) {
		for(Node node : list[a]) {
			int n = node.n;
			int l = node.l;
			if(curL+l > M) continue;
			if(!visit[n]) cur += item[n];
			visit[n] = true;
			dfs(curL + l, n);
		}
	}

}
