import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
[BOJ] 14938 서강그라운드
12084KB |	80ms
풀이
- 모든 지역을 탐색 => 최대 아이템 개수 업데이트
- 아이템 탐색 시, 특정 지역에 갈 수 있는 최단 거리 구하기
=> 가중치가 있는 최단 거리 => 다익스트라(시작 정점에서 다른 모든 정점으로의 최단 거리)
 */
public class B14938_서강그라운드 {
    static class Node implements Comparable<Node>{
        int idx; //다음 노드의 인덱스
        int cost;

        public Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int n;
    static int m;
    static List<Node>[] graph;
    static int[] item;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //지역의 개수
        m = Integer.parseInt(st.nextToken()); //수색범위
        int r = Integer.parseInt(st.nextToken()); //길의 개수

        item = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            item[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        answer = 0;

        //다익스트라로 최단 거리 구하기
        for(int i = 1; i <= n; i++){
            Dijkstra(i);
        }

        System.out.println(answer);
    }

    static void Dijkstra(int start){
        boolean[] check = new boolean[n+1];
        int[] dist = new int[n+1];  //start에서 i번째 정점까지의 최소 거리
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            int curr = pq.poll().idx;

            if(check[curr]) continue;
            check[curr] = true;

            for(Node next : graph[curr]){
                if(dist[next.idx] > dist[curr] + next.cost){
                    dist[next.idx] = dist[curr] + next.cost;

                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        //start정점에서 최소거리인 지점들 구하기
        int sum = 0;
        for(int i = 1; i <= n; i++){
            // 수색 범위 내에 있는 item들만 더하기
            if(dist[i] <= m) sum += item[i];
        }

        answer = Math.max(sum, answer);
    }
}
