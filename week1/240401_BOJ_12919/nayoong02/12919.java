import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main_12919 {
    static String S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        if (dfs(T)) System.out.println(1);
        else System.out.println(0);
    }

    public static boolean dfs(String T) {
        // T 길이가 S랑 같아지면 확인 후 탐색 종료
        if (S.length() == T.length()) {
            if (S.equals(T)) return true;
            return false;
        }

        // 맨 마지막 글자가 'A' 이면 제거 후 다시 탐색
        if (T.charAt(T.length() - 1) == 'A') {
            if (dfs(T.substring(0, T.length() - 1))) {
                return true;
            }
        }

        // 첫글자가 'B' 이면 제거 후, 뒤 문자열 반전시킨 후 다시 탐색
        if (T.charAt(0) == 'B') {
            StringBuilder reverse = new StringBuilder();
            reverse.append(T.substring(1));

            if (dfs(reverse.reverse().toString())) {
                return true;
            }
        }

        return false;
    }

}
