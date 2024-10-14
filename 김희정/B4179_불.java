import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
[BOJ] 4179 불
	68672KB | 	456ms
풀이
- 지훈이와 불은 매 분마다 한칸씩 수평, 수직으로 이동
- 불은 4방향으로 확산
- 지훈이는 미로의 가장자리에 접한 공간에서 탈출 가능
- 불과 지훈이는 벽이 있는 공간은 통과x
 */

public class B4179_불 {
    static char[][] building;
    static Queue<Pair> person;
    static Queue<Pair> fire;

    static int[] dx={1,-1,0,0};
    static int[] dy={0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st= new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        building = new char[R][C];
        person = new ArrayDeque<>();
        fire = new ArrayDeque<>();

        for(int i=0;i<R;i++){
            String input = br.readLine();
            for(int j=0;j<C;j++){
                if(input.charAt(j)=='J'){
                    person.offer(new Pair(i,j,0));
                }
                if (input.charAt(j) == 'F') {
                    fire.offer(new Pair(i,j,0));
                }
                building[i][j] = input.charAt(j);
            }
        }
        System.out.println(escape(R,C));
    }
    static String escape(int h,int w){
        while (true){
            int f_size = fire.size();
            int p_size = person.size();

            if(p_size==0){
                return "IMPOSSIBLE";
            }
            while (f_size-->0){
                Pair fire_location = fire.poll();


                for(int i=0;i<4;i++){
                    int nx = fire_location.x+dx[i];
                    int ny = fire_location.y+dy[i];

                    if(nx>=0 && ny>=0 && nx<h && ny<w && (building[nx][ny]=='.' || building[nx][ny]=='V')){
                        fire.add(new Pair(nx,ny,0));
                        building[nx][ny]='F';
                    };
                }
            }

            while(p_size-->0){
                Pair person_location = person.poll();

                for(int i=0;i<4;i++){
                    int nx = person_location.x+dx[i];
                    int ny = person_location.y+dy[i];

                    if(nx<0 || ny<0 || nx>=h || ny>=w){
                        return Integer.toString(person_location.time+1);
                    }

                    if(nx>=0 && ny>=0 && nx<h && ny<w && building[nx][ny]=='.'){
                        person.add(new Pair(nx,ny,person_location.time+1));
                        building[nx][ny]='V';
                    };
                }
            }
        }
    }
    static class Pair{
        int x;
        int y;
        int time;
        public Pair(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
