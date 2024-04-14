import java.io.*;
import java.util.*;

class Main_17484 {
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 행렬 세로 길이
        int M = Integer.parseInt(st.nextToken()); // 행렬 가로 길이
        int[][] matrix = new int[N][M]; // 지구 -> 달 행렬

        // 행렬에 연료 값 할당
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 테이블 초기화
        int[][][] dp = new int[N][M][3];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, INF);
            }
        }

        // 초기값 설정
        for (int j = 0; j < M; j++) {
            dp[0][j][0] = matrix[0][j];
            dp[0][j][1] = matrix[0][j];
            dp[0][j][2] = matrix[0][j];
        }

        // DP로 최소 연료 사용량 계산
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j > 0) { // 왼쪽에서 오는 경우
                    dp[i][j][0] = Math.min(dp[i][j][0], matrix[i][j] + Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]));
                }
                // 바로 위에서 오는 경우
                dp[i][j][1] = Math.min(dp[i][j][1], matrix[i][j] + Math.min(dp[i-1][j][0], dp[i-1][j][2]));
                if (j < M - 1) { // 오른쪽에서 오는 경우
                    dp[i][j][2] = Math.min(dp[i][j][2], matrix[i][j] + Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]));
                }
            }
        }

        // 최소 연료 합 계산
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < 3; k++) {
                ans = Math.min(ans, dp[N-1][j][k]);
            }
        }

        System.out.println(ans);
    }
}
