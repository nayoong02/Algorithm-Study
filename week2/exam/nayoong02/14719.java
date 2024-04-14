package Implementation;

import java.util.*;
import java.io.*;

class Main_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로 길이
        int M = Integer.parseInt(st.nextToken()); // 가로 길이

        // 현재 고인 빗물 높이 (3 0 1 4)
        int[] tmpRain = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            tmpRain[i] = Integer.parseInt(st.nextToken());
        }

        // 왼 -> 오 빗물 높이 최대값 갱신 (3 3 3 4)
        int[] ltor = tmpRain.clone();
        for (int i = 1; i < M; i++) {
            ltor[i] = Math.max(ltor[i - 1], ltor[i]);
        }

        // 오 -> 왼 빗물 높이 최대값 갱신 (4 4 4 4)
        int[] rtol = tmpRain.clone();
        for (int i = M - 1; i >= 1; i--) {
            rtol[i - 1] = Math.max(rtol[i], rtol[i - 1]);
        }

        // 왼 -> 오 / 오 -> 왼 중 최소 높이 구하고 현재 빗물 높이 차 구하기 -> (3 3 3 4 - 3 0 1 4)
        int ans = 0;
        for (int i = 0; i < M; i++) {
            int min = Math.min(ltor[i], rtol[i]);
            ans += min - tmpRain[i];
        }

        System.out.println(ans);
    }
}
