package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 15700KB 160ms
 * 
 * [문제 해석]
 * 최종 : 16-bit CPU 설계하고 Verilog 언어로 구현하는 것
 * 어셈블리어 : 기계어 - 일대일 대응
 * 입출력은 항상 명령어 단위
 * "opcode rD rA rB" 또는 "opcode rD rA #C"의 형태
 * 레지스터 rA와 rB에 있는 두 수 또는 레지스터 rA에 있는 수와 상수 #C를 opcode에 해당하는 연산을 수행하고
 * 그 결괏값을 레지스터 rD에 저장하는 명령어. rA는 opcord에 따라 사용하지 않을 수도 있음.
 * 어셈블러는 opcode, rD, rA, rB, #C를 각 bit의 자리에 맞게 2진수 0과 1로 이루어진 16-bit 기계어 코드로 번역
 * 
 * bit마다 자리의 의미
 * 0~4: CPU가 수행해야 할 연산을 나타내는 opcode. 만약 4번 bit가 0일 경우 레지스터 rB를, 1일 경우 상수 #C를 사용
 * 5: 사용하지 않는 bit이며, 항상 0이다
 * 6~8: 결괏값을 저장하는 레지스터 rD의 번호
 * 9~11: 연산에 사용되는 레지스터 rA의 번호. 사용하지 않을 경우 000.
 * 12~15: 만약 4번 bit가 0일 경우 12~14번 bit는 연산에 사용되는 레지스터 rB의 번호이며, 15번 bit는 항상 0.
 * 만약 4번 bit가 1일 경우 12~15번 bit는 상수 #C
 * 
 * 직접 설계한 16-bit CPU의 명령어 구조 표를 보고 어셈블리어 코드가 주어졌을 때 이를 기계어 코드로 번역하는 어셈블러 만들기
 * 
 * [입력]
 * 1. 명령어으 개수 1<=N<=500
 * 2. "opcode rD rA rB" 또는 "opcode rD rA #C"의 형태
 * 		opcode는 항상 대문자. 정수 0 <= rD, rA, rB <= 7는 레지스터 번호
 *      사용하는 레지스터 번호는 1부터 7까지, 사용하지 않을 경우에만 0
 *      정수 #C (0<=#C<=15)는 상수를 의미
 *      
 * [출력]
 * N개의 각 줄에 어셈블리어 코드를 기계어 코드로 번역하려 출력
 */
public class B16506_S5_CPU {
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String opcode = st.nextToken();
			int bit4 = changeOpcode(opcode);
			int rD = Integer.parseInt(st.nextToken());
			sb.append(String.format("%03d", Integer.parseInt(Integer.toBinaryString(rD))));
			int rA = Integer.parseInt(st.nextToken());
			sb.append(String.format("%03d", Integer.parseInt(Integer.toBinaryString(rA))));
			if(bit4 == 0) {
				int rB = Integer.parseInt(st.nextToken());
				sb.append(String.format("%03d", Integer.parseInt(Integer.toBinaryString(rB))))
				.append('0');
			}else {
				int C = Integer.parseInt(st.nextToken());
				sb.append(String.format("%04d", Integer.parseInt(Integer.toBinaryString(C))));
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

	private static int changeOpcode(String opcode) {
		String str = "";
		if(opcode.equals("ADD")) str = "00000";
		else if(opcode.equals("ADDC")) str = "00001";
		else if(opcode.equals("SUB")) str = "00010";
		else if(opcode.equals("SUBC")) str = "00011";
		else if(opcode.equals("MOV")) str = "00100";
		else if(opcode.equals("MOVC")) str = "00101";
		else if(opcode.equals("AND")) str = "00110";
		else if(opcode.equals("ANDC")) str = "00111";
		else if(opcode.equals("OR")) str = "01000";
		else if(opcode.equals("ORC")) str = "01001";
		else if(opcode.equals("NOT")) str = "01010";
		else if(opcode.equals("MULT")) str = "01100";
		else if(opcode.equals("MULTC")) str = "01101";
		else if(opcode.equals("LSFTL")) str = "01110";
		else if(opcode.equals("LSFTLC")) str = "01111";
		else if(opcode.equals("LSFTR")) str = "10000";
		else if(opcode.equals("LSFTRC")) str = "10001";
		else if(opcode.equals("ASFTR")) str = "10010";
		else if(opcode.equals("ASFTRC")) str = "10011";
		else if(opcode.equals("RL")) str = "10100";
		else if(opcode.equals("RLC")) str = "10101";
		else if(opcode.equals("RR")) str = "10110";
		else if(opcode.equals("RRC")) str = "10111";
		sb.append(str).append('0');
		
		if(str.charAt(4) == '1') return 1;
		return 0;
	}

}
