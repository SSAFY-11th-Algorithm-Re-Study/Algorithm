import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
[BOJ] 5639 이진검색트리
	35756KB | 	584ms
 */
public class B5639_이진검색트리 {

    static class Node{
        int key;
        Node left;
        Node right;

        public Node(int key){
            this.key = key;
        }

        public void insert(int key){
            if(key < this.key){
                if(left == null) left = new Node(key);
                else left.insert(key);
            }
            else{
                if(right == null) right = new Node(key);
                else right.insert(key);
            }
        }



    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        while(true){
            String s = br.readLine();
            if(s == null || s.equals("")) break;
            root.insert(Integer.parseInt(s));
        }
        postOrder(root);
    }

    static void postOrder(Node node){
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);

    }
}
