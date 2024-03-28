package week_09_다익스트라.전기철;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B_3_11779 {
    static final int INF = Integer.MAX_VALUE;
    static int[] vis;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<int[]>> lst = new ArrayList<>();
        vis = new int[n + 1];
        int[] dis = new int[n + 1];
        Arrays.fill(dis, INF);
        for (int i = 0; i < n + 1; i++) {
            lst.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            lst.get(a).add(new int[] { b, c });
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
        dijkstra(start, lst, dis);
        System.out.println(dis[end]);
        int[] result = new int[n + 1];
        result[0] = end;
        int cnt = 1;
        while (end != start) {
            end = vis[end];
            result[cnt] = end;
            cnt += 1;
        }
        System.out.println(cnt);
        for (int i = cnt - 1; i > -1; i--) {
            System.out.print(result[i] + " ");
        }
        sc.close();
    }

    public static void dijkstra(int start, List<List<int[]>> lst, int[] dis) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
        q.offer(new int[] { start, 0 });
        dis[start] = 0;
        vis[start] = start;
        while (!q.isEmpty()) {
            int[] edge = q.poll();
            int distance = edge[1];
            int now = edge[0];
            if (dis[now] < distance) {
                continue;
            }
            for (int[] next : lst.get(now)) {
                int go = next[0];
                int cost = distance + next[1];
                if (cost < dis[go]) {
                    dis[go] = cost;
                    vis[go] = now;
                    q.offer(new int[] { go, cost });
                }
            }
        }

    }

}
