import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
[BOJ] 1743 음식물 피하기
	13184KB | 	96ms
 */
public class B1743_음식물피하기 {
    static int N;
    static int M;

    static boolean[][] visited;
    static int[][] map;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K;
        answer = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }

        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= M; j++){
                if(map[i][j] == 0) continue;
                if(visited[i][j]) continue;
                BFS(i,j);
            }
        }

        System.out.println(answer);
    }

    static void BFS(int i, int j){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{i,j});
        visited[i][j] = true;
        int size = 1;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            for(int d = 0; d < 4; d++){
                int x = curr[0] + dx[d];
                int y = curr[1] + dy[d];
                if(x < 0 || x > N || y < 0 || y > M || visited[x][y]) continue;

                if(map[x][y] == 1){
                    size++;
                    visited[x][y] = true;
                    q.add(new int[]{x,y});
                }
            }
        }
        answer = Math.max(answer, size);

    }
}
