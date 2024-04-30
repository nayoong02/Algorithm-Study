import java.util.*;
import java.io.*;

class Main_2638 {
    static int N, M;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Point> cheeseList;
    static int cheeseCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 모눈종이 세로 길이
        M = Integer.parseInt(st.nextToken()); // 모눈종이 가로 길이
        map = new int[N][M];
        cheeseList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) { // 치즈이면 리스트에 추가 & 카운트 증가
                    cheeseList.add(new Point(i, j));
                    cheeseCnt++;
                }
            }
        }

        int time = 0;
        // 모든 치즈 녹아 없어질 때까지
        while (cheeseCnt != 0) {
            time++;
            visited = new boolean[N][M];

            dfs(0, 0); // 외부 공기 찾기
            melting(); // 치즈 녹이기
        }

        System.out.println(time);
    }

    // 외부와 접촉한 공기 2로 표시
    static void dfs(int x, int y) {
        visited[x][y] = true;
        map[x][y] = 2;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위 밖 체크
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            // 방문 O or 치즈인 경우 체크
            if (visited[nx][ny] || map[nx][ny] == 1) continue;

            dfs(nx, ny); // 공기인 경우만 dfs 진행
        }
    }

    // 치즈 녹이기
    static void melting() {
        for (int i = 0; i < cheeseList.size(); i++) {
            int x = cheeseList.get(i).x;
            int y = cheeseList.get(i).y;
            int cnt = 0;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (map[nx][ny] == 2) { // 외부 공기 만나면
                    cnt++;
                }
            }

            if (cnt >= 2) { // 외부 공기와 2번 이상 접촉한 경우
                map[x][y] = 0;
                cheeseCnt--;
                cheeseList.remove(i);
                i--;
            }
        }
    }
    
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


