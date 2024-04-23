import java.io.*;
import java.util.*;

class Main_2565 {
    static int N;
    static int[][] arr;
    static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 전깃줄 개수
        arr = new int[N][2];
        dp = new Integer[N];

        // 전깃줄 연결
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // A 전봇대 기준 오름차순 정렬
        Arrays.sort(arr, ((o1, o2) -> o1[0] - o2[0]));

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(recur(i), max);
        }

        System.out.println(N - max);
    }

    // 최장 증가 부분 수열
    static int recur(int n) {
        // 아직 탐색 전 위치라면
        if (dp[n] == null) {
            dp[n] = 1;

            for (int i = n + 1; i < N; i++) {
                if (arr[n][1] < arr[i][1]) {
                    dp[n] = Math.max(dp[n], recur(i) + 1);
                }
            }
        }

        return dp[n];
    }
}
