package BOJ;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// 밸먼 포드 알고리즘
// 인터넷 참고하였습니다!
public class N11657_타임머신 {
    static class Edge {
        int st;
        int end;
        int cost;

        public Edge(int s, int e, int c) {
            this.st = s;
            this.end = e;
            this.cost = c;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); 
        int m = Integer.parseInt(st.nextToken()); 
        
        Edge[] graph = new Edge[m + 1];
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[i] = new Edge(a, b, w);
        }

        dist[1] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Edge now_edge = graph[j];
                if (dist[now_edge.st] != Integer.MAX_VALUE &&
                        dist[now_edge.end] > dist[now_edge.st] + now_edge.cost) {
                    dist[now_edge.end] = dist[now_edge.st] + now_edge.cost; // update
                }
            }
        }
        
        boolean cycle = false;
        for(int i = 0 ; i < m; i++){
            Edge now_edge = graph[i];
            if (dist[now_edge.st] != Integer.MAX_VALUE &&
                    dist[now_edge.end] > dist[now_edge.st] + now_edge.cost) {
                cycle = true;
            }
        }
        if(cycle){
            System.out.println(-1);
        } else {
            for(int i = 2; i <= n; i++){
                if(dist[i]==Integer.MAX_VALUE)
                    System.out.println(-1);

               	else
                    System.out.println(dist[i]);

            }
        }
    }


}