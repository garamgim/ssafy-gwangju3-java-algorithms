package week_09_다익스트라.김가람;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class B_4_1504 {
    static int N;
    static ArrayList[] adjl;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        int E = Integer.parseInt(s[1]);

        adjl = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adjl[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < E; i++) {
            String[] temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);
            adjl[u].add(new Node(w, v));
            adjl[v].add(new Node(w, u));
        }

        String[] temp = br.readLine().split(" ");
        int v1 = Integer.parseInt(temp[0]);
        int v2 = Integer.parseInt(temp[1]);

        int[] left = djk(1);     // 노드 1에서 다른 노드들까지의 최단 경로
        int[] middle = djk(v1);     // v1에서 다른 노드들까지의 최단 경로
        int[] right= djk(N);        // N번 노드에서 다른 노드들 까지의 최단 경로

        // 1 -> (v1 -> v2) -> N 까지의 최단거리
        int sToV1 = left[v1];
        int v1Tov2 = middle[v2];
        int v2ToE = right[v2];
        int[] v1v2 = new int[]{sToV1, v1Tov2, v2ToE};

        int path1 = 0;
        for (int i = 0; i < 3; i++) {
            if (v1v2[i] == Integer.MAX_VALUE) {
                path1 = Integer.MAX_VALUE;
                break;
            } else {
                path1 += v1v2[i];
            }
        }

        // 1 -> (v2 -> v1) -> N 까지의 최단거리
        int sToV2 = left[v2];
        int v1ToE = right[v1];
        int path2 = 0;

        int[] v2v1 = new int[]{sToV2, v1Tov2, v1ToE};
        for (int i = 0; i < 3; i++) {
            if (v1v2[i] == Integer.MAX_VALUE) {
                path2 = Integer.MAX_VALUE;
                break;
            } else {
                path2 += v2v1[i];
            }
        }

        // v1, v2 둘 중 누구를 먼져 거쳐 갈 것인가?
        int ans = Math.min(path1, path2);
        System.out.println((ans == Integer.MAX_VALUE)? -1 : ans);
    }

    static int[] djk(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, s));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        ArrayList[] path = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            path[i] = new ArrayList<Integer>();
        }

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
                }
            }
        }

        return dist;
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

