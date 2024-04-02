import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main_16120 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < word.length(); i++) {
            char tmp = word.charAt(i);

            // 'P' 면 스택에 일단 push
            if (tmp == 'P') {
                stack.push(tmp);
            } else { // 'A'일 때
                // 현재 스택 안에 'P' 2개 이상 들어있고, 그 다음 글자가 'P' 이면 안에 스택에서 'P' 2개 pop
                if (stack.size() >= 2 && i < word.length() - 1 && word.charAt(i + 1) == 'P') {
                    stack.pop();
                    stack.pop();
                }
                // 그 외 경우이면 PPAP 될 수 X
                else {
                    System.out.println("NP");
                    return;
                }
            }
        }

        if (stack.size() == 1) System.out.println("PPAP"); // 'P' 하나 남아있음.
        else System.out.println("NP");
    }
}
