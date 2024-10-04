import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
[BOJ] 1238 파티
	86564KB |	576ms
풀이 : 가중치 있는 최단 거리 => 다익스트라
n번 노드 -> X 까지의 최단 거리 + X -> n번 노드까지의 최단 거리
 */
public class B1238_파티 {

    static class Node implements Comparable<Node>{
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static List<Node>[] graph;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));  //단방향
        }

        // 다익스트라로 최단 거리 구하기
        int answer = 0; //최대 소요 시간
        for(int i = 1; i <= N; i++){
            if(i == X) continue;
            int sum1 = dijkstra(i, X);
            int sum2 = dijkstra(X, i);
            answer = Math.max(answer, sum1 + sum2);
        }

        System.out.println(answer);

    }

    static int dijkstra(int start, int end){
        boolean[] check = new boolean[N+1];
        int[] dist = new int[N+1];      //start에서 i번째 node까지의 최소 거리
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();

            if(check[curr.idx]) continue;
            check[curr.idx] = true;

            for(Node next : graph[curr.idx]){
                if(dist[next.idx] > dist[curr.idx] + next.cost){
                    dist[next.idx] = dist[curr.idx] + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        return dist[end];

    }
}
