import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
[BOJ] 11725 트리의 부모 찾기
65176KB | 	464ms
풀이 : 인접리스트를 이용한 BFS로 풀이
 */
public class B11725_트리의부모찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Integer>[] adj = new List[N+1];
        for(int i = 0; i<= N; i++){
            adj[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        //루트노드부터 BFS
        int[] result = new int[N+1]; // result[i] : i번 노드의 부모 노드 번호
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int child : adj[cur]){
                if(!visited[child]){
                    visited[child] = true;
                    result[child] = cur;
                    q.add(child);
                }
            }
        }


        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= N; i++){
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);
    }
}
