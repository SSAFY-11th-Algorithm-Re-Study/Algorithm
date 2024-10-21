package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 300772kb 936ms
 * [문제 해석]
 * 프로젝트를 함께하고 싶은 학생 선택
 * 자기 자신 선택 가능
 * 
 * [입력]
 * 1. TC 
 * 2. 학생의 수 2<=n<=100,000
 * 3. 선택된 학생들의 번호
 * 
 * [출력]
 * 프로젝트 팀에 속하지 못한 학생들의 수
 * 
 */
public class B9466_G3_텀프로젝트 {
	static int N, res;
	static int[] arr;
	static boolean[] check, done;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			res = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1];
			check = new boolean[N+1];
			done = new boolean[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<=N; i++) {
				if(done[i]) continue;
				dfs(i);
			}
			
			sb.append(N-res).append('\n');
		}
		
		System.out.println(sb);
	}

	private static void dfs(int i) {
		if(check[i]) {
			done[i] = true;
			res++;
		}
		check[i] = true;
		
		if(!done[arr[i]]){
            dfs(arr[i]);
        }

        check[i] = false;
        done[i] = true;
	}

}
