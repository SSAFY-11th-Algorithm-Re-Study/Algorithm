/**
 *  1. 10진수 => 2진수 변환 
    - String binary = Integer.toBinaryString(temp);

    2. 반복문 돌 때 조건에 변하는 값이 들어가면 안되므로 int n에 따로 저장해주기

    3. StringBuilder 사용해서 출력하는 시간복잡도 줄이기 (input은 BufferedReader, StringTokenizer 사용)

    4. 다른 함수 정의할 때 public static String getString() 이런 식으로 표현함.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CPU {
    static String[][] arr;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(reader.readLine());
        arr = new String[N][4];

       for (int i=0; i<N; i++) {
        String string = reader.readLine();
        arr[i] = string.split(" ");
       }

       for (int i=0; i<N; i++) {
        String opcode = getOpcode(arr[i][0]);
        sb.append(opcode);
        if (opcode.charAt(4) == '0') {
            sb.append("0").append(getBinary(arr[i][1], 3)).append(getBinary(arr[i][2], 3)).append(getBinary(arr[i][3], 3)).append( "0");
        } else {
            sb.append("0").append(getBinary(arr[i][1], 3)).append(getBinary(arr[i][2], 3)).append(getBinary(arr[i][3], 4));
        }
        sb.append("\n");
       }

       System.out.println(sb);
    }

    public static String getOpcode(String str) {
        switch (str) {
            case "ADD": return "00000";
            case "ADDC": return "00001";
            case "SUB": return "00010";
            case "SUBC": return "00011";
            case "MOV": return "00100";
            case "MOVC": return "00101";
            case "AND": return "00110";
            case "ANDC": return "00111";
            case "OR": return "01000";
            case "ORC": return "01001";
            case "NOT": return "01010";
            case "MULT": return "01100";
            case "MULTC": return "01101";
            case "LSFTL": return "01110";
            case "LSFTLC": return "01111";
            case "LSFTR": return "10000";
            case "LSFTRC": return "10001";
            case "ASFTR": return "10010";
            case "ASFTRC": return "10011";
            case "RL": return "10100";
            case "RLC": return "10101";
            case "RR": return "10110";
            case "RRC": return "10111";
            default: return "00000";
        }
    }

    public static String getBinary(String op, int num) {
        int temp = Integer.parseInt(op);
        String binary = Integer.toBinaryString(temp);

        if (binary.length() < num) {
            int n = num - binary.length();
            for (int i=0; i<n; i++) {
                binary = "0" + binary;
            }
        } 
        return binary;
    }
}