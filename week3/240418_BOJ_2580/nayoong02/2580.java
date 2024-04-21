import java.io.*;
import java.util.*;

class Main_2580 {
    static int[][] arr = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0 ; i < 9 ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0 ; j < 9; j++) {
                arr[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);

    }

    public static void dfs(int row, int col) {
        if (col == 9) { // 끝 열까지 갔다면 다음 행으로 이동해서 계속 찾기
            dfs(row + 1, 0);
            return;
        }

        if (row == 9) { // 스도쿠 모두 다 채웠을 때
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);
            System.exit(0);
        }

        if (arr[row][col] == 0) { // 빈칸이면
            for (int i = 1; i <= 9; i++) {
                if (check(row, col, i)) {
                    arr[row][col] = i;
                    dfs(row, col + 1);
                }
            }
            arr[row][col] = 0;
            return;
        }

        dfs(row, col + 1);
    }

    public static boolean check(int row, int col, int val) {
        // 같은 행에 어떤 숫자가 비었는지
        for (int i = 0; i < 9; i++) {
            if (arr[row][i] == val) {
                return false;
            }
        }

        // 같은 열에 어떤 숫자가 비었는지
        for (int i = 0; i < 9; i++) {
            if (arr[i][col] == val) {
                return false;
            }
        }

        // 같은 네모에 뭐가 있는지
        int id_row = (row / 3) * 3;
        int id_col = (col / 3) * 3;

        for (int i = id_row; i < id_row + 3; i++) {
            for (int j = id_col; j < id_col + 3; j++) {
                if (arr[i][j] == val) {
                    return false;
                }
            }
        }

        return true;
    }
}
