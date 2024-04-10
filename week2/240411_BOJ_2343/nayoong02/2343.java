import java.util.*;
import java.io.*;

class Main_2343 {
    static int N, M;
    static int[] lessons;
    static int left, right = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 강의 수
        M = Integer.parseInt(st.nextToken()); // 블루레이 수
        lessons = new int[N];

        // 강의 배열에 넣어주고, 강의 시간 최소값과 최대값 구하기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
            right += lessons[i];
            left = Math.max(left, lessons[i]);
        }

        binarySearch();
        System.out.println(left);
    }

    // 이분탐색
    public static void binarySearch() {
        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;
            int count = 1; // 최소 한 개의 블루레이는 사용

            for (int i = 0; i < N; i++) {
                if (sum + lessons[i] > mid) {
                    count++; // 새로운 블루레이 필요
                    sum = 0; // 현재 강의는 새 블루레이에 추가
                }
                sum += lessons[i];
            }

            if (count > M) { // 더 큰 블루레이가 필요한 경우
                left = mid + 1;
            } else { // 필요한 블루레이 수가 충분한 경우
                right = mid - 1;
            }
        }
    }
}
