import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class B11725_트리의부모찾기 {
    static int N; // 노드의 개수
    static boolean[] isVisited; //방문여부 체크 배열
    static int[] parent; //부모 저장 배열
    static ArrayList<Integer> list[]; // 노드 저장 
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(reader.readLine());

        isVisited = new boolean[N+1];
        parent = new int[N+1];
        list = new ArrayList[N+1];

        for (int i=0; i<N+1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(reader.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        isVisited[1] = true;
        dfs(1);

        for (int i=2; i<N+1; i++) {
            sb.append(parent[i]).append("\n");
        }

        System.out.println(sb);

    }

    public static void dfs(int idx) {
        for (int i=0; i< list[idx].size(); i++) {
            int nextNode = list[idx].get(i);
            if (!isVisited[nextNode]) {
                isVisited[nextNode] = true;
                parent[nextNode] = idx;
                dfs(nextNode);
            }
        }
    }

    public static void dfs2(int idx) {
        for (int i : list[idx]) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                parent[i] = idx;
                dfs(i);
            }
        }
    }
}
