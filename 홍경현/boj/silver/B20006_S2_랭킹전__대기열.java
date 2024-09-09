package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/* 11868KB 72ms
 * [문제 해석]
 * 1. 플레이어가 입장을 신청하였을 때 매칭이 가능한 방이 없다면 새로운 방 생성 후 입장
 * 		- 해당 방에는 처음 입장한 플레이어 레벨 기준 -10부터 +10까지 입장 가능
 * 2. 입장 가능한 방이 있다면 입장시킨 후 방의 정원이 모두 찰 때까지 대기
 * 		- 이때 입장 가능한 방이 여러 개라면 먼저 생성된 방에 입장
 * 3. 방의 정원이 모두 차면 게임을 시작
 * 
 * 플레이어의 수 p, 플레이어의 닉네임 n, 플레이어의 레벨 l, 방 한 개의 정원 m
 * 최종적으로 만들어진 방의 상태와 입장 플레이어를 출력하는 프로그램
 * 
 * [입력]
 * 1. 플레이어의 수 1<=p<=300, 방의 정원 1<=m<=300
 * 2. 플레이어의 레벨 1<=ㅣ<=500, 닉네임 n
 * 
 * [출력]
 * 모든 생성된 방에 대해서 게임의 시작 유무와 방에 들어있는 플레이어들의 레벨과 아이디 출력
 * 시작 유무와 플레이어의 정보들을 줄 바꿈으로 구분되며 레벨과 아이디는 한 줄에서 공백으로 구분
 * 방은 생성된 순서대로 출력
 * 닉네임이 사전순으로 앞서는 플레이어부터 출력
 * 시작되었으면 started! 대기 중이면 Waiting!
 * 
 * [문제 해결 프로세스]
 * list로 방 생성 및 플레이어 관리
 * 반복문 돌며 해당하는 곳에 입장 및 생성 
 */
public class B20006_S2_랭킹전__대기열 {
	
	static class Node implements Comparable<Node>{
		int l;
		String n;
		
		public Node(int l, String n) {
			super();
			this.l = l;
			this.n = n;
		}

		@Override
		public int compareTo(Node o) {
			return this.n.compareTo(o.n);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int p = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		List<Integer> roomList = new ArrayList<>();
		List<Node>[] memberList = new List[p];
		
		
		for(int i=0; i<p; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			String n = st.nextToken();
			
			boolean check = false;
			
			//현재 웨이팅 중인 방에서 들어갈 수 있는 방 탐색
			for(int k=0; k<roomList.size(); k++) {
				int level = roomList.get(k);
				if(level-10 <= l && level+10 >= l) {
					if(memberList[k].size()<m) {
						memberList[k].add(new Node(l, n));
						check = true;
						break;
					}
				}
			}
			
			//없으면 방 생성
			if(!check) {
				roomList.add(l);
				memberList[roomList.size()-1] = new ArrayList<>();
				memberList[roomList.size()-1].add(new Node(l, n));
			}
		}
		
		for(int i=0; i<roomList.size(); i++) {
			if(memberList[i].size() == m) {
				sb.append("Started!").append('\n');
			}else {
				sb.append("Waiting!").append('\n');
			}
			
			Collections.sort(memberList[i]);
			
			for(Node node : memberList[i]) {
				sb.append(node.l).append(" ").append(node.n).append('\n');
			}
		}
		
		System.out.println(sb);
		
	}

}
