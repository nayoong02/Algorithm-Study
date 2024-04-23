import java.util.*;
import java.io.*;

class Main_1753 {

    static ArrayList<Node>[] graph; // 각 노드 연결 정보 담을 리스트
    static int[] dist; // 최단거리 담을 배열
    static boolean[] visited; // 방문 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수
        int K = Integer.parseInt(br.readLine()); // 시작 정점 번호

        graph = new ArrayList[V + 1];
        dist = new int[V + 1];
        visited = new boolean[V + 1];

        // 초기화
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        // 간선 연결
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()); // 시작 정점
            int v = Integer.parseInt(st.nextToken()); // 도착 정점
            int w = Integer.parseInt(st.nextToken()); // 가중치

            graph[u].add(new Node(v, w));
        }

        dijkstra(K);

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }

    }

    static void dijkstra(int start) {
        // 가중치 기준 오름차순 정렬
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        // 시작 지점 -> 가중치 0
        queue.add(new Node(start, 0));
        // 최단거리 0
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();

            if (!visited[tmp.v]) visited[tmp.v] = true;

            for (Node n : graph[tmp.v]) {
                // 아직 방문 전 && 다른 노드까지 현재 노드 거쳐갔을 때 더 짧다면 최단거리 갱신
                if (!visited[n.v] && dist[n.v] > tmp.w + n.w) {
                    dist[n.v] = tmp.w + n.w;
                    queue.add(new Node(n.v, dist[n.v]));
                }
            }
        }
    }

    static class Node {
        int v; // 정점 번호
        int w; // 가중치

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
