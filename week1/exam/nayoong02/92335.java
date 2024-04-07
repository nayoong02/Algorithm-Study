import java.util.*;
import java.io.*;

class Sol_92335 {
    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        String remainBefore = "";

        // 진수 변환 위해 나머지 문자열 안에 넣어주기
        while (n >= k) {
            remainBefore += (n % k);
            n /= k;
        }
        remainBefore += n; // 마지막 남은 몫까지 넣어주기

        // 나머지 뒤집어서 진수 형태로
        sb.append(remainBefore);
        String remainAfter = sb.reverse().toString();
        String[] arr = remainAfter.split("0"); // 0 기준으로 문자열 자르기

        int ans = 0; // 소수 카운트
        for (int i = 0; i < arr.length; i++) {
            // 각각 잘린 문자열 숫자로 변환 (long형으로 !!!)
            long p = 0;
            if (!arr[i].equals("")) {
                p = Long.parseLong(arr[i]);
            }

            // 소수 판별
            if (isPrime(p)) {
                ans++;
            }
        }

        return ans;
    }

    // 소수 판별하는 함수
    public boolean isPrime(long num) {
        if (num <= 1) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
