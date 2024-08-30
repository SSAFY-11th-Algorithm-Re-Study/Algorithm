import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
[BOJ] 16506 CPU
	12356KB | 	76ms
풀이 : Integer.toString(10진수, 2) => 2진수로 변환
 */
public class B16506_CPU {
    static  Map<String, String> opcodeInfo = new HashMap<String, String>(){
        {
            put("ADD", "0000");
            put("SUB", "0001");
            put("MOV", "0010");
            put("AND", "0011");
            put("OR", "0100");
            put("NOT", "0101");
            put("MULT", "0110");
            put("LSFTL", "0111");
            put("LSFTR", "1000");
            put("ASFTR", "1001");
            put("RL", "1010");
            put("RR", "1011");
        }
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String opcode = st.nextToken();
            int rD = Integer.parseInt(st.nextToken());
            int rA = Integer.parseInt(st.nextToken());
            int rB = Integer.parseInt(st.nextToken());

            // 0 ~ 5 : opcode + 5번째 bit
            int fouthBit = 0;
            if(opcode.charAt(opcode.length()-1) == 'C'){
                sb.append(opcodeInfo.get(opcode.substring(0,opcode.length()-1))).append("10");
                fouthBit = 1;
            }
            else{
                sb.append(opcodeInfo.get(opcode)).append("00");
            }

            //6 ~ 8 : rd
            String rd = Integer.toString(rD, 2);
            if(rd.length() < 3){
                for(int j = 0; j < 3-rd.length(); j++){
                    sb.append("0");
                }
//                String digit = "0".repeat(3-rd.length());
//                sb.append(digit);
            }
            sb.append(rd);

            // 9 ~ 11 : rA
            // 사용하지 않을 경우 000 : MOV, NOT
            String ra = Integer.toString(rA, 2);
            if(ra.length() < 3){
                for(int j = 0; j < 3-ra.length(); j++){
                    sb.append("0");
                }
//                String digit = "0".repeat(3-ra.length());
//                sb.append(digit);
            }
            sb.append(ra);

            // 12 ~ 15
            // 4번 bit가 0일 경우, 12~14 : rB, 15번 bit는 0
            // 4번 bit가 1일 경우, 12 ~ 15 : 상수 #c
            String rb = Integer.toString(rB, 2);

            if(fouthBit == 0){
                if(rb.length() < 3){
                    for(int j = 0; j < 3-rb.length(); j++){
                        sb.append("0");
                    }
//                    String digit = "0".repeat(3-rb.length());
//                    sb.append(digit);
                }
                sb.append(rb).append(0);
            }else{
                if(rb.length() < 4){
                    for(int j = 0; j < 4-rb.length(); j++){
                        sb.append("0");
                    }
                    // java 11부터 repeat 지원
//                    String digit = "0".repeat(4-rb.length());
//                    sb.append(digit);
                }
                sb.append(rb);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

}
