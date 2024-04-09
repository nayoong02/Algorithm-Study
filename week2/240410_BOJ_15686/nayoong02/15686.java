import java.util.*;
import java.io.*;

class Main_15686 {
    static int N, M, cd, ans;
    static int[][] matrix;
    static ArrayList<Point> houses, chickens;
    static boolean[] chickenVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][N];
        houses = new ArrayList<>(); // 집 좌표 담을 리스트
        chickens = new ArrayList<>(); // 치킨집 좌표 담을 리스트

        // 2차원 배열에 할당
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());

                // 집, 치킨집 좌표 각 리스트에 저장
                if (matrix[i][j] == 1) {
                    houses.add(new Point(i, j));
                } else if (matrix[i][j] == 2) {
                    chickens.add(new Point(i, j));
                }
            }
        }

        ans = Integer.MAX_VALUE; // 최소 도시의 치킨 거리 답을 값
        chickenVisited = new boolean[chickens.size()]; // 치킨집 방문 배열

        dfs(0, 0);
        System.out.println(ans);
    }

    public static void dfs(int cnt, int start) {
        if (cnt == M) { // 최대 M개의 치킨집 영업
            cd = 0; // 도시의 치킨 거리

            for (int i = 0; i < houses.size(); i++) { // 집집마다
                int min = Integer.MAX_VALUE; // 치킨 거리 최소값

                for (int j = 0; j < chickens.size(); j++) {
                    if (chickenVisited[j]) { // 방문된 치킨집들 중에서 치킨 거리 구하고 갱신하기
                        int dist = Math.abs(houses.get(i).x - chickens.get(j).x) +
                                Math.abs(houses.get(i).y - chickens.get(j).y); // 치킨 거리
                        min = Math.min(min, dist); // 치킨 거리 최소값 갱신
                    }
                }
                cd += min;
            }

            ans = Math.min(cd, ans); // 도시의 치킨 거리 최소값 갱신
            return;
        }

        // 치킨집 조합 고르기 (중복 X)
        for (int i = start; i < chickens.size(); i++) {
            if (!chickenVisited[i]) {
                chickenVisited[i] = true;
                dfs(cnt + 1, i + 1);
                chickenVisited[i] = false;
            }
        }
    }

    // 좌표 클래스
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
