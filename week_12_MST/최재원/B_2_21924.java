package week_12_MST.최재원;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2_21924 {
    static int N, M;
    static int[] p;
    static Edge[] edgeList;

    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("week_12_MST/최재원/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = makeSet();
        edgeList = new Edge[M];

        long weightSum = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(from, to, weight);
            weightSum += weight;
        }

        Arrays.sort(edgeList);

        long totalCost = MST();

        System.out.println(totalCost == -1 ? totalCost : weightSum - totalCost);
        br.close();
    }

    static long MST() {
        long totalCost = 0;
        int edgeCnt = 0;

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                totalCost += edge.weight;

                if (++edgeCnt == N - 1) {
                    return totalCost;
                }
            }
        }

        return -1;
    }

    static boolean union(int a, int b) {
        a = findSet(a);
        b = findSet(b);

        if (a == b) {
            return false;
        }

        p[b] = a;
        return true;
    }

    static int findSet(int x) {
        if (x == p[x]) {
            return x;
        }

        return p[x] = findSet(p[x]);
    }

    static int[] makeSet() {
        int[] arr = new int[N + 1];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        return arr;
    }

}
