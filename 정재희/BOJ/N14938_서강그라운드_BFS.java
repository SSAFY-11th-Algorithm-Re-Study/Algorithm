package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 21,828KB | 172ms
 */
public class N14938_서강그라운드_BFS {
	static class Node {
		int cur;
		int dis;
		public Node(int cur, int dis) {
			super();
			this.cur = cur;
			this.dis = dis;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int[] items = new int[n+1];
		int[][] adjmat = new int[n+1][n+1];
		boolean[] isVisited;
		
		int totalSum = 0;
		int maxCnt = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
			totalSum += items[i];
			for(int j=1; j<= n; j++)
				adjmat[i][j] = -1;
		}
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjmat[s][e] = w;
			adjmat[e][s] = w;
		}
		
		ArrayDeque<Node> queue = new ArrayDeque();
		
		for(int i=1;i<=n;i++) {
			int sum = items[i];
			isVisited = new boolean[n+1];
			isVisited[i] = true;
			queue.offer(new Node(i, 0));
			while(!queue.isEmpty()) {
				Node node = queue.poll();
				for (int j = 1; j <=n; j++) {
					if(adjmat[node.cur][j] == -1 || node.dis+adjmat[node.cur][j] > m) continue;
					
					if(!isVisited[j]) {
						isVisited[j] = true;
						sum += items[j];
					}
					queue.offer(new Node(j, node.dis+adjmat[node.cur][j]));
				}
			}
			
			maxCnt = Math.max(maxCnt, sum);
			if(maxCnt == totalSum) break;
		}
		
		System.out.println(maxCnt);
		
	}

}
