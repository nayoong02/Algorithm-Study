import java.io.*;
import java.util.*;

class Main_2512 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 지방의 수
        int[] arr = new int[N];

        int left = 0, right = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 각 지방의 얘산 요청
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);
        }

        int M = Integer.parseInt(br.readLine()); // 총 예산
        while (left <= right) {
            int mid = (left + right) / 2;
            long budget = 0;

            for (int i = 0; i < N; i++) {
                // 최대 예산 넘으면
                if (arr[i] > mid) budget += mid;
                // 안 넘으면
                else budget += arr[i];
            }

            // 최대 예산보다 작으면 예산 높이기
            if (budget <= M) left = mid + 1;
            // 최대 예산보다 크면
            else right = mid - 1;
        }

        System.out.println(right);
    }
}
