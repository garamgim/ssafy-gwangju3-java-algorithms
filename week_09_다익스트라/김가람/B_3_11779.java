package week_09_다익스트라.김가람;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class B_3_11779 {
    static int N;
    static int[] path;
    static int pathCnt;
    static ArrayList<Integer> shortPath;
    static ArrayList[] adjl;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adjl = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adjl[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);
            adjl[u].add(new Node(w, v));
        }

        String[] temp2 = br.readLine().split(" ");
        int S = Integer.parseInt(temp2[0]);
        int E = Integer.parseInt(temp2[1]);
        djk(S, E);
    }

    static void djk(int s, int e) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, s));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        path = new int[N + 1];
        path[s] = s;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (dist[curr.n] < curr.w) continue;
            dist[curr.n] = curr.w;

            ArrayList<Node> connected = adjl[curr.n];
            for (int i = 0; i < connected.size(); i++) {
                Node next = connected.get(i);
                int acc = curr.w + next.w;
                if (dist[next.n] > acc) {
                    dist[next.n] = acc;
                    pq.add(new Node(acc, next.n));

                    // 경로 업데이트
                    path[next.n] = curr.n;
                }
            }
        }

        // path = [0, 1, 1, 1, 1, 4]
        // 각 번호의 노드들이 직전에 거쳐왔던 노드들의 번호들을 저장

        StringBuilder ans = new StringBuilder();
        pathCnt = 0;
        shortPath = new ArrayList<Integer>();
        ans.append(dist[e] + "\n");

        // e(도착점)의 최단 경로를 역추적
        trace(e);

        // shortPath = [5, 4, 1]

        ans.append(pathCnt + "\n");
        for (int i = shortPath.size() - 1; i >= 0; i--) {
            ans.append(shortPath.get(i) + " ");
        }

        System.out.println(ans);
    }

    // 경로 역추적 함수
    // 부모 노드가 자기 자신일 때까지 재귀를 사용하여 거슬러 올라간다
    static void trace(int n) {
        pathCnt++;
        shortPath.add(n);
        if (path[n] == n) return;
        trace(path[n]);
    }

    static class Node implements Comparable<Node> {
        int w, n;

        public Node(int w, int n) {
            this.w = w;
            this.n = n;
        }

        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}
