import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
[BOJ] 11657 타임머신
	16944KB |	208ms
풀이 : 음의 가중치에서의 최단 거리 => 벨만포드 알고리즘
- 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 -1 출력 => 음수 사이클 존재 여부
- 최솟값이 500*600*-10000 = -30억 이므로 dist배열 long으로 해야함.
 */
public class B11657_타임머신 {
    static class Edge{
        int s;
        int e;
        int cost;

        public Edge(int s, int e, int cost){
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge> graph = new ArrayList<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.add(new Edge(s,e,cost));
        }


        //벨만 포드 알고리즘
        long[] dist = new long[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;    //1번 정점에서 시작

        // 정점의 갯수만큼 반복
        for(int i = 0; i < N; i++){
            // 간선의 갯수만큼 반복
            for(int j = 0; j < M; j++){
                Edge edge = graph.get(j);

                //현재 간선의 들어오는 정점에 대해 비교
                if(dist[edge.s] != Integer.MAX_VALUE && dist[edge.e] > dist[edge.s] + edge.cost){
                    dist[edge.e] = dist[edge.s] + edge.cost;
                }
            }
        }


        //음수 사이클 확인
        for(int i = 0; i < M; i++){
            Edge edge = graph.get(i);

            if(dist[edge.s] != Integer.MAX_VALUE && dist[edge.e] > dist[edge.s] + edge.cost){
                System.out.println(-1);
                return;
            }
        }

        //출력
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i < N+1; i++){
            if(dist[i] == Integer.MAX_VALUE)
                sb.append(-1).append("\n");
            else
                sb.append(dist[i]).append("\n");
        }

        System.out.println(sb);

    }

}
