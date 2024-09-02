import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[][] arr;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        arr = new String[N][4];
       for (int i=0; i<N; i++) {
        String string = reader.readLine();
        arr[i] = string.split(" ");
       }

       for (int i=0; i<N; i++) {
        String ans = "";
        String opcode = getOpcode(arr[i][0]);
        ans += opcode;
        if (opcode.charAt(4) == 0) {
            ans += "0" + getOperand(arr[i][1])+getOperand(arr[i][2])+getOperand(arr[i][3]);
        } else {
            ans += "0" + getOperand(arr[i][1])+getOperand(arr[i][2])+getOperand(arr[i][3]) + "0";
        }

        System.out.println(ans);
       }
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

    public static String getOperand(String op) {
        int temp = Integer.parseInt(op);
        String binary = Integer.toBinaryString(temp);

        if (binary.length() < 3) {
           
            for (int i=0; i<3-binary.length(); i++) {
                binary = "0" + binary;
            }
        } 
        return binary;
    }
}