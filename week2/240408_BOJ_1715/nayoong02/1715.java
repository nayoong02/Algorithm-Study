import java.io.*;
import java.util.*;

class Main_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 카드묶음 개수
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        // 우선순위큐에 오름차순으로 카드 수 넣기
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }

        // 카드 묶음 수가 1개면 비교 필요 X
        if (N == 1) {
            System.out.println(0);
            System.exit(0);
        }

        int ans = 0;
        while(queue.size() > 1) { // 카드 묶음이 2개 이상이면 2번 뽑고 더해서 다시 넣어주기
            int sum = queue.poll() + queue.poll();
            ans += sum;
            queue.add(sum);
        }

        System.out.println(ans);
    }
}
