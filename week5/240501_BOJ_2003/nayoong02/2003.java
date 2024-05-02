import java.util.*;
import java.io.*;

class Main_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열 개수
        int M = Integer.parseInt(st.nextToken()); // 합이 될 타겟 숫자
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int l = 0, r = 0;
        int sum = 0;

        // 투포인터 누적합 구하기
        while (true) {
            if (sum >= M) { // 합이 M 이상이면 arr[l] 빼주고 l++
                sum -= arr[l++];
            } else if (r >= N) { // 종료조건
                break;
            } else { // 합이 M보다 작으면 arr[r] 더해주고 r++
                sum += arr[r++];
            }

            if (sum == M) { // 합이 M과 일치하면 카운트 증가
                ans++;
            }
        }

        System.out.println(ans);
    }
}
