import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_1_14938 {
    static final int INF = Integer.MAX_VALUE;
    static List<List<int[]>> graph;

    static int n, m, r;
    static int[] items;

    static int max_v = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("week_09_다익스트라/최재원/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int[] dis = new int[n + 1];
        Arrays.fill(dis, INF);


        items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});

        }

        for (int i = 1; i < n + 1; i++) {
            dijkstra(i);
        }

        System.out.println(max_v);
    }

    static void dijkstra(int start) {
        int[] dis = new int[n + 1];
        Arrays.fill(dis, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
        pq.add(new int[]{start, 0});
        dis[start] = 0;

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int now = edge[0];
            int distance = edge[1];

            if (dis[now] < distance) {
                continue;
            }

            for (int[] next : graph.get(now)) {
                int destination = next[0];
                int cost = distance + next[1];

                if (cost < dis[destination]) {
                    dis[destination] = cost;
                    pq.add(new int[]{destination, cost});
                }
            }
        }

        int count = 0;

        for (int i = 1; i < n + 1; i++) {
            if (dis[i] <= m) {
                count += items[i];
            }
        }

        max_v = Math.max(count, max_v);
    }
}
