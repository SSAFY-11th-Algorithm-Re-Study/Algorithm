import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
[BOJ] 9470 Strahler 순서
	11672KB |	64ms
 */
public class B9470_Strahler순서 {
    static class Order{
        int num;
        int count;

        public Order(int num, int count){
            this.num = num;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            List<Integer>[] graph = new List[M+1];
            for(int i = 1; i <= M; i++){
                graph[i] = new ArrayList();
            }
            int[] dist = new int[M+1];  //i번 노드로 들어오는 간선수
            Order[] order = new Order[M+1]; //i번 노드의 순서
            //간선정보
            for(int i = 0; i < P; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                dist[b]++;
            }


            Queue<Integer> q = new ArrayDeque<>();
            //들어오는 간선 수가 0인 노드는 순서가 1
            for(int i = 1; i <= M; i++){
                if(dist[i] == 0){
                    order[i] = new Order(1, 1);
                    q.add(i);
                }else{
                    order[i] = new Order(0, 0);
                }
            }

            while(!q.isEmpty()){
                int curr = q.poll();

                for(int next : graph[curr]){
                    if(--dist[next] == 0) q.add(next);
                    if(order[next].num == order[curr].num){
                        order[next].count++;
                        if(order[next].count >= 2){
                            order[next].num += 1;
                            order[next].count = 0;
                        }
                    }
                    if(order[next].num < order[curr].num){
                        order[next].num = order[curr].num;
                        order[next].count = 1;
                    }
                }

            }

            sb.append(K).append(" ").append(order[M].num).append("\n");
        }

        System.out.println(sb);
    }
}
