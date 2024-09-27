import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
[BOJ] 2096 내려가기
58696KB |	368ms
풀이
- 표 : N x 3
- 내려갈 수 있는 조건 : 표의 바로 아래 수 or 바로 아래 수 옆에 붙어 있는 수
- 최대 점수, 최소 점수 구하기
 */
public class B2096_내려가기 {

    static int[][] map;
    static int N;
    static int min;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][3];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int[][][] dp = new int[N][3][2];
        dp[0][0][0] = map[0][0];
        dp[0][0][1] = map[0][0];
        dp[0][1][0] = map[0][1];
        dp[0][1][1] = map[0][1];
        dp[0][2][0] = map[0][2];
        dp[0][2][1] = map[0][2];

        for(int i = 1; i < N; i++){
            //최대
            dp[i][0][0] = Math.max(dp[i-1][0][0], dp[i-1][1][0]) + map[i][0];
            dp[i][1][0] = Math.max(dp[i-1][0][0], Math.max(dp[i-1][1][0], dp[i-1][2][0])) + map[i][1];
            dp[i][2][0] = Math.max(dp[i-1][1][0], dp[i-1][2][0]) + map[i][2];


            //최소
            dp[i][0][1] = Math.min(dp[i-1][0][1], dp[i-1][1][1]) + map[i][0];
            dp[i][1][1] = Math.min(dp[i-1][0][1], Math.min(dp[i-1][1][1], dp[i-1][2][1])) + map[i][1];
            dp[i][2][1] = Math.min(dp[i-1][1][1], dp[i-1][2][1]) + map[i][2];

        }

        min = Integer.MAX_VALUE;
        max = 0;
        for(int i = 0; i < 3; i++){
            max = Math.max(max, dp[N-1][i][0]);
            min = Math.min(min, dp[N-1][i][1]);
        }
        System.out.println(max + " " + min);

    }

}
