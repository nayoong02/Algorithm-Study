import java.util.*;
import java.io.*;

class Sol_92344 {
    static int[][] sums;

    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        sums = new int[N + 1][M + 1];

        for (int[] s : skill) {
            int type = s[0]; // 공격 or 회복
            int r1 = s[1]; // 시작 y
            int c1 = s[2]; // 시작 x
            int r2 = s[3]; // 끝 y
            int c2 = s[4]; // 끝 x
            int degree = (s[0] == 1 ? -1 : 1) * s[5]; // 공격이면 빼주고, 회복이면 더해줌.

            // 네 꼭지점
            sums[r1][c1] += degree;
            sums[r1][c2 + 1] += (degree * -1);
            sums[r2 + 1][c1] += (degree * -1);
            sums[r2 + 1][c2 + 1] += degree;
        }

        // 위 <-> 아래
        for (int j = 0; j < M + 1; j++) {
            for (int i = 0; i < N; i++) {
                sums[i + 1][j] += sums[i][j];
            }
        }


        // 왼 <-> 오
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < M; j++) {
                sums[i][j + 1] += sums[i][j];
            }
        }

        // 파괴되지 않은 건물 구하기
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (sums[i][j] + board[i][j] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
