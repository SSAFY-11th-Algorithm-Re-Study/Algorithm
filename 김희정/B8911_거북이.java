import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[BOJ] 8911 거북이
22472KB | 	288ms
 */
public class B8911_거북이 {

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            int minX = 0, minY = 0, maxX = 0, maxY = 0, dir = 0, curX = 0, curY = 0;
            String command = br.readLine();
            for(int j = 0; j < command.length(); j++){
                char c = command.charAt(j);
                if(c=='F') {
                    curX = curX+dx[dir];
                    curY = curY+dy[dir];
                }else if(c=='B') {
                    curX = curX-dx[dir];
                    curY = curY-dy[dir];
                }else if(c=='L') {
                    if(dir==0) dir=3;
                    else dir--;
                }else if(c=='R') {
                    if(dir==3) dir=0;
                    else dir++;
                }

                minX = Math.min(minX, curX);
                minY = Math.min(minY, curY);
                maxX = Math.max(maxX, curX);
                maxY = Math.max(maxY, curY);
            }
            sb.append((Math.abs(minX) + Math.abs(maxX)) * (Math.abs(minY)+Math.abs(maxY))+"\n");
        }
        System.out.println(sb);
    }

}
