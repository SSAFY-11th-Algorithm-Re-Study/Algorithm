package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 11,908KB 64ms
 * [문제 해설]
 * NxM 크기의 보드와 4개의 버튼으로 이루어진 게임
 * - 동전 이동하려는 칸이 벽이면 동전은 이동하지 않음
 * - 동전이 이동하려는 방향에 칸이 없으면 동전은 보드 바깥으로 떨어짐
 * - 그 외의 경우에는 이동하려는 방향으로 한 칸 이동
 * 	-> 동전이 있는 경우에도 한 칸 이동
 * 두 동전 중 하나만 보드에서 떨어뜨리기 위해 최소 몇 번 눌러야 하는 지
 * 
 * [입력]
 * 1. N, M (1<=N,M<=20)
 * 2. N개의 줄에는 보드의 상태
 * 		o: 동전
 * 		.: 빈 칸
 * 		#: 벽
 * 동전의 개수는 항상 2개
 * 
 * [출력]
 * 첫째 줄에 두 동전 중 하나만 보드에서 떨어뜨리기 위해 눌러야 하는 버튼의 최소 횟수
 * 10번보다 많이 눌러야 하면 -1 출력
 */
public class B16197_G4_두동전2 {
	 
    static int n, m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] board;
    static Coin[] coin;
    static boolean[][][][] visited;
 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
 
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        coin = new Coin[2];
        int coinIdx = 0;
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                char c = str.charAt(j);
                if(c == 'o') {
                    coin[coinIdx++] = new Coin(i, j); 
                }
                board[i][j] = c;
            }
        }
 
        visited = new boolean[n][m][n][m];
        System.out.println(bfs());
    }
 
    public static int bfs() {
        Queue<towCoins> q = new LinkedList<>();
        q.offer(new towCoins(coin[0].x, coin[0].y, coin[1].x, coin[1].y, 0));
        visited[coin[0].x][coin[0].y][coin[1].x][coin[1].y] = true;
 
        while(!q.isEmpty()) {
            towCoins current = q.poll();
 
            if(current.count >= 10) break;
 
            for(int i = 0; i < 4; i++) {
                int nx1 = current.x1 + dx[i];
                int ny1 = current.y1 + dy[i];
                int nx2 = current.x2 + dx[i];
                int ny2 = current.y2 + dy[i];
             
                if(!canMoveCoin(nx1, ny1)) { 
                    nx1 = current.x1;
                    ny1 = current.y1;
                }
                if(!canMoveCoin(nx2, ny2)) {
                    nx2 = current.x2;
                    ny2 = current.y2;
                }
 
                int flag = 0; 
                if(nx1 >= 0 && ny1 >= 0 && nx1 < n && ny1 < m) flag++;
                if(nx2 >= 0 && ny2 >= 0 && nx2 < n && ny2 < m) flag++;
 
                if(flag == 1) return current.count + 1;
                else if(flag == 2 && !visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2] = true;
                    q.offer(new towCoins(nx1, ny1, nx2, ny2, current.count + 1));
                }
            }
        }
        return -1;
    }
 
    public static boolean canMoveCoin(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < n && ny < m && board[nx][ny] == '#') {
            return false;
        }
        return true;
    }
 
    public static class towCoins { 
        int x1;
        int y1;
        int x2; 
        int y2;
        int count;
 
        public towCoins(int x1, int y1, int x2, int y2, int count) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.count = count;
        }
    }
 
    public static class Coin { 
        int x;
        int y;
 
        public Coin(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
