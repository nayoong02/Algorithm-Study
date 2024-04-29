package Backtracking;

import java.util.*;
import java.io.*;

class Main_14500 {
    static int N, M;
    static int[][] matrix;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 종이의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 종이의 가로 크기
        matrix = new int[N][M];
        visited = new boolean[N][M];

        // 행렬 채우기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs
        for (int i = 0; i < N; i++ ) {
            for (int j = 0; j < M; j++) {
              visited[i][j] = true;
              dfs(i, j, matrix[i][j], 1);
              visited[i][j] = false;
            }
        }

        System.out.println(ans);
    }

    static void dfs(int x, int y, int tmp, int cnt) {
        if (cnt == 4) { // 테트로미노 4칸 다 채워졌을 때 합 최대값 갱신
            ans = Math.max(ans, tmp);
            return;
        }

        for (int d = 0; d < 4; d++) { // 4방향
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M) { // 범위 안
                if (!visited[nx][ny]) { // 방문 전
                    if (cnt == 2) { // ㅗ 탐색 위해 2번 갔을 때 거기서 다시 탐색
                        visited[nx][ny] = true;
                        dfs(x, y, tmp + matrix[nx][ny], cnt + 1);
                        visited[nx][ny] = false;
                    }

                    visited[nx][ny] = true;
                    dfs(nx, ny, tmp + matrix[nx][ny], cnt + 1);
                    visited[nx][ny] = false;
                }
            }
        }
    }
}
