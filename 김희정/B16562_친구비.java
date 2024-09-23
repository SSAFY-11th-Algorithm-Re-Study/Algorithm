import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
[BOJ] 16562 친구비
19192KB | 176ms
풀이 : 1~10000(N) 완탐 => i가 속해 있는(이어져 있는) 그룹 안 제일 작은 거 BFS로 찾기
 */
public class B16562_친구비 {
    static boolean[] visited;
    static List<Integer>[] adjList;
    static int[] cash;
    static int k;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        answer = 0;
        cash = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            cash[i] = Integer.parseInt(st.nextToken());
        }

        adjList = new List[N+1];
        for(int i = 0; i < N+1; i++){
            adjList[i] = new ArrayList();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        visited = new boolean[N+1];
        for(int i = 1; i <= N; i++){
            if(visited[i]) continue;
            if(!BFS(i)) {
                System.out.println("Oh no");
                return;
            }
        }

        System.out.println(answer);

    }

    static boolean BFS(int i){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(i);
        visited[i] = true;
        int min = cash[i];

        while(!q.isEmpty()){
            int curr = q.poll();
            for(int next : adjList[curr]){
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
                min = Math.min(min, cash[next]);
            }
        }

        if(min > k){
            return false;
        }
        k -= min;
        answer += min;
        return true;
    }
}
