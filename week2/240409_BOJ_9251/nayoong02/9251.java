import java.util.*;
import java.io.*;

class Main_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) { // 같으면 대각선 + 1
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                }
                else { // 다르면 왼, 위 중 최대값 가져오기
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }

        System.out.println(matrix[str1.length()][str2.length()]);
    }
}