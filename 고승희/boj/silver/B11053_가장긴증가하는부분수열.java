import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11053_가장긴증가하는부분수열 {
    static int N;
    static int[] dp;
    static int[] seq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        // // 반복문
        // for (int i=0; i<N; i++) {
        //     dp[i] = 1;

        //     for (int j=0; j<i; j++) {
        //         if (seq[j] < seq[i] && dp[i] < dp[j] + 1) {
        //             dp[i] = dp[j] + 1;
        //         }
        //     }
        // }

        // int max = 0;
        // for (int i=0; i<N; i++) {
        //     max = Math.max(max, dp[i]);
        //     // System.out.println("i: " + i + "dp: " + dp[i] + ", max: " + max);
        // }

        // System.out.println(max);

        for (int i=0; i<N; i++) {
            LIS(i);
        }
       
        int max = dp[0];
        for (int i=0; i<N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    static int LIS(int N) {
        if (dp[N] == 0) {
            dp[N] = 1;
      
            for (int i=N-1; i>=0; i--) {
                if (seq[i] < seq[N]) {
                   dp[N] = Math.max(dp[N], LIS(i) + 1);
                }
            } 
        
        }
        return dp[N];
    }
}
