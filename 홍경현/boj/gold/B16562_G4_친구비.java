package gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 16928KB 136ms
 * [문제 해석]
 * 학생 i에게 Ai 돈을 주면 1달간 친구가 됨
 * 친구의 친구는 친구
 * 
 * 총 k원의 돈으로 가장 적은 비용으로 모든 사람과 친구가 되는 방법
 * 
 * [입력]
 * 1. 학생 수 N (1<=N<=10,000) 친구 관계 수 M (0<=M<=10,000) 돈 K (1<=K<=10,000,000)
 * 2. 각각의 학생이 원하는 친구비 A (1<=A<=10,000)
 * 3. 학생 v 학생 w
 * 
 * [출력]
 * 모든 학생을 친구로 만들 수 있는 최소 비용
 * 사귈 수 없으면 Oh no
 */
public class B16562_G4_친구비 {
	static int[] cost;
    static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        cost = new int[N+1];
        parent = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) cost[i] = Integer.parseInt(st.nextToken());
        for(int i=0;i<=N;i++) parent[i] = i;

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a > b) union(b,a);
            else union(a,b);
        }

        long cnt = 0;
        boolean[] check = new boolean[N+1];
        for(int i=1;i<=N;i++){
            int idx = find(i);

            if(!check[idx]){
                cnt += cost[idx];
                check[idx] = true;
            }

            check[i] = true;
        }

        if(cnt > k) System.out.println("Oh no");
        else System.out.println(cnt);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(cost[a] > cost[b]) parent[a] = b;
        else parent[b] = a;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }

}
