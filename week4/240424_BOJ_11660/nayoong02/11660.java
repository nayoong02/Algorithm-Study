import java.util.*;
import java.io.*;

class Main_11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 표의 크기
        int M = Integer.parseInt(st.nextToken()); // 합 구해야하는 횟수
        int[][] arr = new int[N + 1][N + 1]; // 원래 값 담을 배열
        int[][] dp = new int[N + 1][N + 1]; // 누적합 담을 배열

        // 입력 받은 값 arr 배열에 담기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 누적합 dp 배열에 담기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + arr[i][j] - dp[i - 1][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        // 시작 정점 -> 끝 정점 누적합
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            sb.append(dp[ex][ey] - dp[ex][sy - 1] - dp[sx - 1][ey] + dp[sx - 1][sy - 1]).append("\n");
        }

        System.out.println(sb);

    }
}
