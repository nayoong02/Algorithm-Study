import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main_1759 {
    static int L, C;
    static char[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        L = Integer.parseInt(line.split(" ")[0]);
        C = Integer.parseInt(line.split(" ")[1]);

        arr = new char[C];
        visited = new boolean[C];

        // 알파벳 배열 넣기
        String line2 = br.readLine().replaceAll(" ", "");
        for (int i = 0; i < C; i++) {
            arr[i] = line2.charAt(i);
        }

        Arrays.sort(arr); // 오름차순 정렬

        comb(0, 0);
    }

    // 조합 구하기 
    public static void comb(int start, int len) {
        if (len == L) { // 검증 시작
            StringBuffer sb = new StringBuffer();
            int c = 0; // 자음 개수
            int v = 0; // 모음 개수

            for (int i = 0; i < C; i++) {
                if (visited[i]) { // 방문했으면
                    char ch = arr[i];
                    sb.append(ch);

                    if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') { // 모음이면
                        v++;
                    } else { // 자음이면
                        c++;
                    }
                }
            }
            // 모음 1개 이상, 자음 2개 이상이면 출력 
            if (v >= 1 && c >= 2) {
                System.out.println(sb);
            }
        }
        // 백트래킹
        else {
            for (int i = start; i < C; i++) {
                visited[i] = true;
                comb(i + 1, len + 1);
                visited[i] = false;
            }
        }
    }
}
