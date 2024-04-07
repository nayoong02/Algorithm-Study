import java.util.*;
import java.io.*;

class Sol_92341 {
    public int[] solution(int[] fees, String[] records) {
        Map<String, String> carTimeMap = new HashMap<>(); // 차 번호, 입차 시각
        Map<String, Integer> answerMap = new TreeMap<>(); // 차 번호, 누적 시간

        for (int i = 0; i < records.length; i++) {
            String time = records[i].split(" ")[0];
            String carNum = records[i].split(" ")[1];
            String op = records[i].split(" ")[2];

            if (op.equals("IN")) { // 입차일 때
                carTimeMap.put(carNum, time);
                continue;
            } else { // 출차일 때 시간 차 구하고, 입차시각 제거해주고, 누적시간 더해서 맵에 넣어주기 
                int sum = getSum(carTimeMap.get(carNum), time);
                carTimeMap.remove(carNum);
                answerMap.put(carNum, answerMap.getOrDefault(carNum, 0) + sum);
            }
        }

        // 출차 아직 안된 차가 있다면 23:59 출차시각으로 누적 시간 구하기
        if (!carTimeMap.isEmpty()) {
            for (String key : carTimeMap.keySet()) {
                int sum = getSum(carTimeMap.get(key), "23:59");
                answerMap.put(key, answerMap.getOrDefault(key, 0) + sum);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        int[] answer = new int[answerMap.size()];

        // 차 번호마다 주차 요금 구하기 
        for (String key : answerMap.keySet()) {
            list.add(getFee(answerMap.get(key), fees));
        }

        // 리스트 -> 정답 배열에 넣어주기 
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    // 출차 시각 - 입차시각 시간 구하기 
    public int getSum(String inTime, String outTime) {
        int inHour = Integer.parseInt(inTime.split(":")[0]); // 압처 사간
        int inMin = Integer.parseInt(inTime.split(":")[1]); // 입차 분
        int outHour = Integer.parseInt(outTime.split(":")[0]); // 출차 시간
        int outMin = Integer.parseInt(outTime.split(":")[1]); // 출차 분 

        int sum = (outHour * 60 + outMin) - (inHour * 60 + inMin);

        return sum;
    }

    // 주차 요금 구하기 
    public int getFee(int time, int[] fees) {
        int fee = 0;
        int defTime = fees[0]; // 기본 시간
        int defFee = fees[1]; // 기본 요금 
        int baseTime = fees[2]; // 단위 시간 
        int baseFee = fees[3]; // 단위 요금 

        if (time <= defTime) fee = defFee; // 기본 시간보다 적을 때
        else fee = (int) Math.ceil((double)(time - defTime) / baseTime) * baseFee + defFee; // 넘을 때

        return fee;
    }
}
