import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;


public class B20006_랭킹전대기열 {
    static int p, m;
    public static class Player implements Comparable<Player> {
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
     
        st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Player>> rooms = new ArrayList<>();
        
        for (int i=0; i<p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            if (rooms.size() == 0) {
                rooms.add(new ArrayList<>());
                rooms.get(0).add(new Player(level, name));
                continue;
            }

            boolean flag = false; //유저가 방을 배정받으면 true
            for (int j=0; j<rooms.size(); j++) {
                if (rooms.get(j).size() == m) continue;

                int referenceLevel = rooms.get(j).get(0).level;
                if (referenceLevel-10 <= level && level <= referenceLevel +10) {
                    flag = true;
                    rooms.get(j).add(new Player(level, name));
                    break;
                }
            }

            if (!flag) {
                rooms.add(new ArrayList<>());
                rooms.get(rooms.size()-1).add(new Player(level, name));
            }
        }

        for (ArrayList<Player> room : rooms) {
            if (room.size() == m) System.out.println("Started!");
            else System.out.println("Waiting!");

            Collections.sort(room);
            for (Player player : room) System.out.println(player.level + " " + player.name);
        }

    }    
}
