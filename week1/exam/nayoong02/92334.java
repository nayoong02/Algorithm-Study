import java.util.*;
import java.io.*;

class Sol_92334 {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, HashSet<String>> map = new HashMap<>(); // 아이디, 신고한 아이디 집합
        Map<String, Integer> idMap = new HashMap<>(); // 아이디, 메일 받는 횟수
        int[] answer = new int[id_list.length]; // 정답 배열 
        
        // 초기화 
        for (int i = 0; i < id_list.length; i++) {
            map.put(id_list[i], new HashSet<String>());
            idMap.put(id_list[i], i);
        }
        
        for (String s : report) {
            String from = s.split(" ")[0]; // 신고한 사람
            String to = s.split(" ")[1]; // 신고 당한 사람
            map.get(to).add(from); // 신고 당한 사람에 신고한 사람 아이디 추가
        }

        // k 이상 신고 당한 사람을 신고한 사람 메일 발송
        for (int i = 0; i < id_list.length; i++){
           if (map.get(id_list[i]).size() >= k) { // HashSet의 크기가 k 이상일때 Set에 있는 사람들 값에게 메일 발송
               for(String name : map.get(id_list[i])){
                   answer[idMap.get(name)]++;
               }
           }
        }

        return answer;
    }
}
