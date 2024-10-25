package gold;
import java.util.*;
import java.io.*;

/* 12568KB 120ms
 * [문제 해석]
 * L*L 크기의 트램펄린으로 별똥별 튕기기
 * 모서리에 부딪혀도 튕겨나감
 * 
 * [입력]
 * 1. N M L K
 * 	1<=N, M<=500,000 1<=L<=100,000 1<=K<=100
 * 	N: 가로 길이, M: 세로길이, L: 한 변의 길이, K: 별똥별의 수
 * 2. 별똥별 위치 x, y
 * 
 * [출력]
 * 지구에 부딪히는 별똥별의 개수
 */
public class B14658_G3_하늘에서별똥별이빗발친다 {
    static int N, M, L, K;
    static List<int[]> stars;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stars = new ArrayList<>();
        for(int i =0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new int[]{x, y});
        }
        int res = Integer.MIN_VALUE;
        for(int[] s1: stars){
            for(int[] s2: stars){
                res = Math.max(res, boundStar(s1[0], s2[1]));
            }
        }
        System.out.println(K-res);
    }

    private static int boundStar(int i, int j) {
        int res = 0;
        for(int[] s:stars){
            if(i<=s[0]&&s[0]<=i+L && j<=s[1]&&s[1]<=j+L ) res++;
        }
        return res;
    }
}