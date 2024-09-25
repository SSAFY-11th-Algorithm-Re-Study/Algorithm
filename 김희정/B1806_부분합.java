import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
[BOJ] 1806 부분합
22628KB |	192ms
 */
public class B1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;
        int total = 0;
        while(start <= N && end <= N){
            if(total >= S && min > end - start) min = end - start;
            if(total < S) total += arr[end++];
            else total -= arr[start++];
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println("0");
        }
        else{
            System.out.println(min);
        }

    }
}
