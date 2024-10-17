import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
[BOJ] 1005 ACM Craft
256176KB |	968ms
문제해석
- 매 게임마다 건물 짓는 순서가 다르다
- 모든 건물은 각각 건설을 시작해 완성될 때까지 Delay가 존재
=> 특정건물을 가장 빨리 지을 때까지 걸리는 최소시간 구하기
풀이
- 위상 정렬로 각 건물을 순서대로 탐색
- 들어오는 노드들 중 가장 큰 값 저장 => buildCost
- buildCost[next] = Math.max(현재 노드까지의 총 건설 시간 + 다음 노드의 건설시간, 다음노드의 총 건설 시간)
 */
public class B1005_ACMCraft {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   //건물 갯수
            int K = Integer.parseInt(st.nextToken());   //건설 순서 규칙 갯수
            st = new StringTokenizer(br.readLine());
            int[] delay = new int[N+1];  //건설 시간
            List<Integer>[] graph = new List[N+1];
            for(int j = 1; j <= N; j++){
                delay[j] = Integer.parseInt(st.nextToken());
                graph[j] = new ArrayList<>();
            }

            int[] dist = new int[N+1];
            for(int j = 0; j < K; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                dist[b]++;
            }
            int target = Integer.parseInt(br.readLine());

            //로직
            int[] buildCost = new int[N+1]; // i번 건물을 짓는데 가장 오래걸리는 시간
            Queue<Integer> q = new ArrayDeque<>();
            for(int b = 1; b <= N; b++){
                if(dist[b] == 0){
                    buildCost[b] = delay[b];
                    q.add(b);
                }
            }
            while(!q.isEmpty()){
               int curr = q.poll();

               for(int next : graph[curr]){
                   buildCost[next] = Math.max(buildCost[next], buildCost[curr] + delay[next]);
                   if(--dist[next] == 0) q.add(next);
               }
            }

            sb.append(buildCost[target]).append("\n");
        }

        System.out.println(sb);
    }
}
