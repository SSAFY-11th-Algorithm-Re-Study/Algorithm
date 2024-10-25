import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
[BOJ] 14658 하늘에서 별똥별이 빗발친다
	12912KB |	136ms
풀이
- 트램펄린 영역에 최대한 많은 별똥별이 있게 해야 함
- 별 기준으로 탐색
    - 별이 트램펄린의 좌상단, 우상단, 좌하단, 우하단인 경우 모든 경우 탐색 x
    -> 별이 꼭짓점이 아닌 경계에 위치한 경우에도 최댓값이 존재할 수 있기 때문
=> 별의 모든 x 좌표, y좌표를 뽑아 이 좌표가 이루는(즉, 별이 경계에 있게) 사각형의 좌상단을 기준으로 트램펄린 펼치기
    - 별의 경계가 별의 최댓값을 찾을 수 있는 경우 중 가장 최악의 경우이기 때문
 */

public class B14658_하늘에서별똥별이빗발친다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 구역의 가로길이
        int M = Integer.parseInt(st.nextToken());   // 구역의 세로길이
        int L = Integer.parseInt(st.nextToken());   // 트램펄린의 한 변의 길이
        int K = Integer.parseInt(st.nextToken());   // 별똥별 수4

        List<int[]> star = new ArrayList<>();
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int[] coord = new int[2];
            coord[0] = Integer.parseInt(st.nextToken());
            coord[1] = Integer.parseInt(st.nextToken());
            star.add(coord);
        }

        //로직
        int maxStar = 0;
        for(int i = 0; i < K; i++){
            for(int j = 0; j < K; j++){
                int x = star.get(i)[0]; //모든 별의 x,y 뽑기
                int y = star.get(j)[1];
                //별 순환
                int countStar = 0;
                for(int[] coord : star){
                    int curX = coord[0];
                    int curY = coord[1];
                    if(x <= curX && curX <= x+L && y <= curY && curY <= y+L){
                        countStar++;
                    }
                }
                maxStar = Math.max(maxStar, countStar);
            }
        }
        System.out.println(K - maxStar);
    }
}
