import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
[BOJ] 9466 텀프로젝트
298128KB |	940ms
 */
public class B9466_텀프로젝트 {
    static int N;
    static int[] arr;
    static int answer;
    static boolean[] team;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i = 0; i < T; i++){
            N = Integer.parseInt(br.readLine());
            arr = new int[N+1];
            answer = 0;
            visited = new boolean[N+1];
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            team = new boolean[N+1];

            for(int j = 1; j < N+1; j++){
                if(!team[j]){
                    DFS(j);
                }
            }

            sb.append(N-answer).append('\n');
        }

        System.out.println(sb);
    }

    static void DFS(int n){
        if(visited[n]){
            team[n] = true;
            answer++;
        }
        else{
            visited[n] = true;
        }

        if(!team[arr[n]]){  //다음 학생
            DFS(arr[n]);
        }

        team[n] = true; //모든 작업이 끝나 더이상 사용 x
    }
}
