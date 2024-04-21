import java.io.*;
import java.util.*;

class Main_2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] max = new int[10001];
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken()); // p
            arr[i][1] = Integer.parseInt(st.nextToken()); // d
        }

        // p 기준 내림차순
        Arrays.sort(arr, (o1, o2) -> o2[0] - o1[0]);

        // 각 날짜까지 받을 수 있는 최대 d 기록
        for (int i = 0; i < N; i++) {
            int cost = arr[i][0];
            int day = arr[i][1];

            // d까지 받을 수 있는 p 최대값 갱신
            for (int j = day; j >= 1; j--) {
                if (max[j] < cost) {
                    max[j] = cost;
                    break;
                }
            }
        }

        // 최대 p 모두 더하기
        int ans = 0;
        for (int i = 0; i < max.length; i++) {
            ans += max[i];
        }

        System.out.println(ans);
    }
}