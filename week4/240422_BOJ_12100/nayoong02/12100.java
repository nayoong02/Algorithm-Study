import java.io.*;
import java.util.*;

class Main_12100 {
    static int N;
    static int[][] map;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 보드의 크기
        map = new int[N][N];
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                Integer.parseInt(st.nextToken());
            }
        }

        game(0);
        System.out.println(ans);
    }

    // 5번 이동하기
    public static void game(int cnt) {
        if (cnt == 5) { // 5번 이동하면 map에서 최대값 찾기
            findMax();
            return;
        }

        int[][] copy = new int[N][N]; // 배열 복사해두기
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }

        // 4방향
        for (int i = 0; i < 4; i++) {
            move(i);
            game(cnt + 1);

            // 원래 상태로 복원
            for (int j = 0; j < N; j++) {
                map[j] = copy[j].clone();
            }
        }
    }

    // 방향에 따라 이동하기
    public static void move(int dir) {
        switch (dir) {
            case 0: // 위로 몰기
                for (int i = 0; i < N; i++) { // 열 순회
                    int idx = 0; // 현재 블록이 이동할 위치
                    int block = 0; // 이전에 처리한 블록 값

                    for (int j = 0; j < N; j++) { // 행 순회
                        if (map[j][i] != 0) { // 0 아니면 이동 필요
                            if (block == map[j][i]) { // block과 현재 블록 같다면
                                map[idx - 1][i] = block * 2; // 이전 위치 블록 값을 2배로
                                block = 0;
                                map[j][i] = 0; // 현재 위치는 비우기
                            }
                        } else { // 현재 블록을 idx 위치로 옮기고 idx 증가
                            block = map[j][i];
                            map[j][i] = 0;
                            map[idx][i] = block;
                            idx++;
                        }
                    }
                }
                break;
            case 1: // 아래쪽으로 몰기
                for (int i = 0; i < N; i++) { // 열 순회
                    int idx = N - 1; // 현재 블록이 이동할 위치
                    int block = 0; // 이전에 처리한 블록 값

                    for (int j = N - 1; j >= 0; j--) { // 행 역순회
                        if (map[j][i] != 0) { // 0 아니면 이동 필요
                            if (block == map[j][i]) { // block과 현재 블록 같다면
                                map[idx + 1][i] = block * 2; // 이전 위치 블록 값을 2배로
                                block = 0;
                                map[j][i] = 0; // 현재 위치는 비우기
                            }
                        } else { // 현재 블록을 idx 위치로 옮기고 idx 증가
                            block = map[j][i];
                            map[j][i] = 0;
                            map[idx][i] = block;
                            idx--;
                        }
                    }
                }
                break;
            case 2: // 왼쪽으로 몰기
                for (int i = 0; i < N; i++) { // 행 순회
                    int idx = 0; // 현재 블록이 이동할 위치
                    int block = 0; // 이전에 처리한 블록 값

                    for (int j = 0; j < N; j++) { // 열 순회
                        if (map[i][j] != 0) { // 0 아니면 이동 필요
                            if (block == map[i][j]) { // block과 현재 블록 같다면
                                map[i][idx - 1] = block * 2; // 이전 위치 블록 값을 2배로
                                block = 0;
                                map[i][j] = 0; // 현재 위치는 비우기
                            }
                        } else { // 현재 블록을 idx 위치로 옮기고 idx 증가
                            block = map[i][j];
                            map[j][j] = 0;
                            map[i][idx] = block;
                            idx++;
                        }
                    }
                }
                break;
            case 3: // 오른쪽으로 몰기
                for (int i = 0; i < N; i++) { // 행 순회
                    int idx = N - 1; // 현재 블록이 이동할 위치
                    int block = 0; // 이전에 처리한 블록 값

                    for (int j = N - 1; j >= 0; j--) { // 열 역순회
                        if (map[i][j] != 0) { // 0 아니면 이동 필요
                            if (block == map[i][j]) { // block과 현재 블록 같다면
                                map[i][idx + 1] = block * 2; // 이전 위치 블록 값을 2배로
                                block = 0;
                                map[i][j] = 0; // 현재 위치는 비우기
                            }
                        } else { // 현재 블록을 idx 위치로 옮기고 idx 증가
                            block = map[i][j];
                            map[j][j] = 0;
                            map[i][idx] = block;
                            idx--;
                        }
                    }
                }
                break;
        }
    }

    // 현재 map에서 최대값 구하기
    public static void findMax() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, map[i][j]);
            }
        }
    }
}
