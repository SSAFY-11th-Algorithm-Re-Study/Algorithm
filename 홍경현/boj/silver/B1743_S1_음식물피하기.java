package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 13612KB 92MS
 * [문제 해석]
 * 떨어진 음식물 중에 제일 큰 음식물 피해 가기
 * 
 * [입력]
 * 1. 세로 1<=N<=100, 가로 1<=M<=100, 쓰레기 개수 K(1<=K<=NxM)
 * 2. 음식물이 떨어진 좌표 (r,c)
 * 
 * [출력]
 * 가장 큰 음식물의 크기
 * 
 * [문제 해결 프로세스]
 * 전체 구간을 돌면서 탐색
 */
public class B1743_S1_음식물피하기 {
	static int N, M, K;
	static int max, cur;
	static boolean[][] map, visit;
	static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[N+1][M+1];
		visit = new boolean[N+1][M+1];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = true;
		}
		
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(!map[i][j] || visit[i][j]) continue;
				dfs(i, j);
				max = Math.max(max, cur);
				cur = 0;
			}
		}
		
		System.out.println(max);
	}

	private static void dfs(int r, int c) {
		for(int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(!check(nr, nc) || !map[nr][nc] || visit[nr][nc]) continue;
			visit[nr][nc] = true;
			cur++;
			dfs(nr, nc);
		}
	}

	private static boolean check(int r, int c) {
		return r>=1 && c>=1 && r<=N && c<=M;
	}

}
