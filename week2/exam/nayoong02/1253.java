import java.util.*;
import java.io.*;

class Main_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 수의 개수
        int[] arr = new int[N];

        if (N == 1) { // 숫자 1개면 바로 출력
            System.out.println(1);
            System.exit(0);
        }

        // 배열에 숫자 할당
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 투포인터 위해 오름차순 정렬

        int ans = 0;
        for (int i = 0; i < N; i++) {
            int l = 0;
            int r = N - 1;

            while (true) {
                // 현재 위치 (i)에 있는 경우
                if (l == i) l++;
                else if (r == i) r--;

                if (l >= r) break; // 찾을 수 X

                if (arr[l] + arr[r] > arr[i]) r--;
                else if (arr[l] + arr[r] < arr[i]) l++;
                else { // GOOD
                   ans++;
                   break;
                }
            }
        }

        System.out.println(ans);

    }
}
