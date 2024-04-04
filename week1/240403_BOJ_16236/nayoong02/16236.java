import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main_16236 {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 공간의 크기
        matrix = new int[N][N];
        int[] curr = null;

        // 공간에 각각의 상태 할당하기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());

                // 아기 상어 좌표
                if (matrix[i][j] == 9) {
                   curr = new int[]{i, j};
                   matrix[i][j] = 0;
                }
            }
        }

        int size = 2; // 아기 상어 크기
        int eat = 0; // 먹은 물고기 수
        int ans = 0; // 이동한 거리

        while (true) {
            // 거리 오름차순 -> x좌표 오름차순 -> y좌표 오름차순
            PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) ->
                    o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : (o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
            );
            boolean[][] visited = new boolean[N][N];

            queue.add(new int[]{curr[0], curr[1], 0}); // x좌표, y좌표, 이동한 거리 저장
            visited[curr[0]][curr[1]] = true; // 방문 처리

            boolean chk = false;

            while (!queue.isEmpty()) {
                curr = queue.poll();

                // 물고기가 있고, 상어의 크기보다 작으면
                if (matrix[curr[0]][curr[1]] != 0 && matrix[curr[0]][curr[1]] < size) {
                    matrix[curr[0]][curr[1]] = 0;
                    eat++;
                    ans += curr[2];
                    chk = true;
                    break;
                }

                // 상하좌우 이동
                for (int d = 0; d < 4; d++) {
                    int nx = curr[0] + dx[d];
                    int ny = curr[1] + dy[d];

                    if (ny < 0 || nx < 0 || nx >= N || ny >= N || visited[nx][ny] || matrix[nx][ny] > size)
                        continue;

                    queue.add(new int[]{nx, ny, curr[2] + 1});
                    visited[nx][ny] = true;
                }
            }

            if (!chk) { // 큐가 비워질 때까지, 먹이를 먹은 적 없다면 -> 더 이상 물고기 없는 것이므로 탈출
                break;
            }

            // 아기 상어읰 크기만큼 물고기 먹었다면, 크기 증가 후 리셋
            if (size == eat) {
                size++;
                eat = 0;
            }
        }

        System.out.println(ans);
    }
}
