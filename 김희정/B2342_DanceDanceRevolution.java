import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
[BOJ] 2342 Dance Dance Revolution
	43380KB |	416ms
풀이 : n번째 지시사항은 n-1번의 결과에 영향을 받음 => DP
- DP[i][j][k] : k번째 지시 사항에서 왼쪽발은 i, 오른쪽 발은 j인 비용의 최솟값
- Top-Down 형식으로 풀이
 */
public class B2342_DanceDanceRevolution {

    static int[] move;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        move = new int[line.length-1];
        for(int i = 0; i < line.length - 1; i++){
            move[i] = Integer.parseInt(line[i]);
        }

        dp = new int[5][5][line.length];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(solve(0,0,0));

    }

    static int solve(int left, int right, int cnt){
        if(cnt == move.length) return 0;

        if(dp[left][right][cnt] != -1) return dp[left][right][cnt];

        // Math.min(왼쪽발을 움직이는 경우, 오른쪽 발을 움직이는 경우)
        dp[left][right][cnt] = Math.min(solve(move[cnt], right, cnt+1) + power(left, move[cnt]),
                solve(left, move[cnt], cnt+1) + power(right, move[cnt]));
        return dp[left][right][cnt];
    }



    // pos : 현재 위치
    // des : 이동할 위치
    static int power(int pos, int des){
        int num = Math.abs(pos - des);
        if(pos == 0) return 2;   // 중앙에 있던 발이 다른 지점으로 움직일 때, 2의 힘을 사용하게 된다.
        if(num == 0) return 1;  // 같은 지점을 한번 더 누른다면, 그때는 1의 힘을 사용하게 된다.
        if(num == 1 || num == 3) return 3; // 다른 지점에서 인접한 지점으로 움직일 때는 3의 힘을 사용하게 된다.
        return 4; // 반대편으로 움직일때는 4의 힘을 사용하게 된다.
    }

}
