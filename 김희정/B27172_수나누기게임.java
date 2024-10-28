import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
[BOJ] 27172 수 나누기 게임
34012KB |	316ms
풀이 : 에라토스테네스의 체의 원리 이용
- 1~N까지의 수 중 소수를 찾는다고 하면 1제외, 2의 배수 제외, 3의 배수 제외 .. 로 구함
=> 각 카드 번호의 배수들이 존재하는지 확인한다
- 존재 여부는 미리 인덱싱한 배열 이용
 */
public class B27172_수나누기게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int INF = 1000000;
        int[] card = new int[N+1];
        int[] pos = new int[INF+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            card[i] = Integer.parseInt(st.nextToken());
            pos[card[i]] = i;
        }
        int[] score = new int[N+1];
        for(int i = 1; i <= N; i++){
            for(int j = card[i] * 2; j <= INF; j += card[i]){   //num의 배수 확인
                if(pos[j] >= 1){
                    score[pos[card[i]]]++;
                    score[pos[j]]--;
                }
            }
        }

        //출력
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            sb.append(score[i]).append(' ');
        }

        System.out.println(sb);
    }
}
