package Lv1;

import java.util.*;

class Sol_258712 {
    public int solution(String[] friends, String[] gifts) {
        int ans = 0;
        int len = friends.length;

        // 인덱스맵에 이름, 인덱스 넣기
        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            idxMap.put(friends[i], i);
        }

        int[][] giftsArr = new int[len][len]; // 주고받은 선물 개수 배열
        int[] giftNumArr = new int[len]; // 선물지수 배열

        for (String gift : gifts) {
            String give = gift.split(" ")[0]; // 준 사람
            String receive = gift.split(" ")[1]; // 받은 사람

            int giveIdx = idxMap.get(give); // 준 사람 인덱스
            int receiveIdx = idxMap.get(receive); // 받은 사람 인덱스
            giftsArr[giveIdx][receiveIdx]++;

            // 선물지수
            giftNumArr[giveIdx]++;
            giftNumArr[receiveIdx]--;
        }

        for (int i = 0; i < len; i++) {
            int cnt = 0;

            for (int j = 0; j < len; j++) {
                if (i == j) continue;

                // 준 선물이 받은 선물보다 더 많으면 카운트 증가
                if (giftsArr[i][j] > giftsArr[j][i]) cnt++;
                // 준 선물과 받은 선물이 같고, 선물 지수가 더 높으면 카운트 증가
                else if (giftsArr[i][j] == giftsArr[j][i] && giftNumArr[i] > giftNumArr[j]) cnt++;
            }

            ans = Math.max(ans, cnt); // 최대값 갱신
        }

        return ans;
    }
}
