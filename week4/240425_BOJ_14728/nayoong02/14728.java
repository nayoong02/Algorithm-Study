package DynamicProgramming;

import java.util.*;
import java.io.*;

class Main_14728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 단원 개수
        int T = Integer.parseInt(st.nextToken()); // 총 공부 가능 시간

        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken()); // 단원 별 예상 공부 시간
            int S = Integer.parseInt(st.nextToken()); // 단원 문제의 배점

            nodes[i] = new Node(K, S);
        }

        int[][] dp = new int[N + 1][T + 1];
        for (int i = 1; i <= N; i++) { // 단원
            for (int j = 1; j <= T; j++) { // 현재 시간
                if (nodes[i].time <= j) { // 현재 시간보디 공부 시간 적으면 -> 현재 단원 공부할 수 O
                    // 현재 시간(j)까지 i-1단원까지의 점수 vs 현재 시간에서 아직 i단원 공부 전에서의(j- i단원 공부 시간) 최대값 + 현재 단원 점수
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nodes[i].time] + nodes[i].score);
                } else { // 현재 공부할 수 X -> 이전 i-1단원까지의 최대 점수 리턴
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][T]);
    }

    static class Node {
        int time;
        int score;

        public Node(int time, int score) {
            this.time = time;
            this.score = score;
        }
    }
}
