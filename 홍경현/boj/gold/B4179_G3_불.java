package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* 42816KB 396ms
 * [문제 해석]
 * 불은 매 분마다 한 칸씩 수평 또는 수직으로 이동
 * 불은 각 지점에서 네 방향으로 확산
 * 미로의 가장자리에 접한 공간에서 탈출 가능
 * 벽이 있는 공간 통과 X
 * 
 * [입력]
 * 공백으로 구분된 두 정수 R, C (1<=R,C<=1,000)
 * 
 * # : 벽
 * . : 지나갈 수 있는 공간
 * J : 지훈이의 미로 초기 위치
 * F : 불이 난 공간
 * 
 * [출력]
 * 지훈이가 미로를 탈출할 수 있는 경우 가장 빠른 탈출 시간
 * 불가능한 경우IMPOSSIBLE 출력
 * 
 * [문제 해결 프로세스]
 * 레벨별 BFS -> 레벨이 지날 때마다 불 확산 
 */
public class B4179_G3_불 {
	
	static char map[][];
	static boolean visited[][];
	static int R, C;
	static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
	static Node curNode;
	static Queue<Node> fire;
	
	static class Node{
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		fire = new ArrayDeque<>();
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
				
				if(map[i][j] == 'J') {
					curNode = new Node(i, j);
					map[i][j] = '.';
				}
				else if(map[i][j] == 'F') fire.add(new Node(i, j));
			}
		}
		
		int res = bfs();
		System.out.println(res == -1 ? "IMPOSSIBLE" : res);
	}

	//레벨별 BFS -> 최단 거리 구하기
	private static int bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(curNode);
		visited[curNode.r][curNode.c] = true;
		int res = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			res++;
			//사람의 이동과 불의 이동은 동시에 이루어짐, 불이 이동하는 곳으로 사람은 갈 수 없음
			moveFire(); 
			
			for(int i=0; i<size; i++) {
				Node node = q.poll();
				int r = node.r;
				int c = node.c;
				
				for(int j=0; j<4; j++) {
					int nr = r+dr[j];
					int nc = c+dc[j];
					
					if(!check(nr, nc)) return res;
					if(map[nr][nc] != '.' || visited[nr][nc]) continue;
					
					q.add(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		
		return -1;
	}

	private static void moveFire() {
		int size = fire.size();
		for(int i=0; i<size; i++) {
			Node node = fire.poll();
			int r = node.r;
			int c = node.c;
			for(int j=0; j<4; j++) {
				int nr = r+dr[j];
				int nc = c+dc[j];
				
				if(!check(nr, nc) || map[nr][nc] != '.' || visited[nr][nc]) continue;
				
				fire.add(new Node(nr, nc));
				map[nr][nc] = 'F';
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}

}
