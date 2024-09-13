package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 40860KB 304ms
 * [문제 해석]
 * 2차원 평면 위 움직일 수 있는 거북이 로봇
 * - F: 한 눈금 앞으로
 * - B: 한 눈굼 뒤로
 * - L: 왼쪽으로 90도 회전
 * - R: 오른쪽으로 90도 회전
 * 
 * L과 R 명령 시 이동 x -> 방향만 바꿈
 * 컨트롤 프로그램으로 거북이가 이동한 영역 계산
 * 거북이는 항상 x축과 y축에 평행한 방향으로 이동
 * 지나간 영역을 모두 포함할 수 있는 가장 작은 직사각형의 넓이
 * 
 * [입력]
 * 1. T
 * 2. 컨트롤 프로그램
 * 
 * [출력]
 * 가장 작은 직사각형의 넓이
 * 
 * [문제 해결 프로세스]
 * 최대 x, 최소 x, 최대 y, 최소 y 구하기
 */
public class B8911_S3_거북이 {

	static class Point{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			Point min = new Point(0, 0);
			Point max = new Point(0, 0);
			Point cur = new Point(0, 0);
			int dir = 0;
			
			String str = br.readLine();
			for(int i=0; i<str.length(); i++) {
				char c = str.charAt(i);
				
				if(c == 'F') {
					cur.x += dx[dir];
					cur.y += dy[dir];
					max.x = max.x>cur.x ? max.x:cur.x;
					max.y = max.y>cur.y ? max.y:cur.y;
					min.x = min.x<cur.x ? min.x:cur.x;
					min.y = min.y<cur.y ? min.y:cur.y;
				}else if(c == 'B') {
					cur.x -= dx[dir];
					cur.y -= dy[dir];
					max.x = max.x>cur.x ? max.x:cur.x;
					max.y = max.y>cur.y ? max.y:cur.y;
					min.x = min.x<cur.x ? min.x:cur.x;
					min.y = min.y<cur.y ? min.y:cur.y;
				}else if(c == 'L') {
					dir = (dir+3)%4;
				}else {
					dir = (dir+1)%4;
				}
			}
			
			sb.append((max.x-min.x) * (max.y-min.y)).append('\n');
		}
		System.out.println(sb);
	}

}
