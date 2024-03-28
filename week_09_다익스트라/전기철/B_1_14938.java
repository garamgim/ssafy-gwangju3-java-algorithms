package week_09_다익스트라.전기철;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B_1_14938 {
    static final int INF = Integer.MAX_VALUE;
    static int n, m, r;
    static List<List<int[]>> lst;
    static int[] item, result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        r = sc.nextInt();
        sc.nextLine();
        item = new int[n + 1];
        String[] s = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            item[i + 1] = Integer.parseInt(s[i]);
        }
        lst = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            lst.add(new ArrayList<>());
        }
        result = new int[n + 1];
        for (int i = 0; i < r; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            lst.get(a).add(new int[] { b, c });
            lst.get(b).add(new int[] { a, c });
        }
        for (int i = 1; i < n + 1; i++) {
            dijkstra(i);
        }
        int cnt = 0;
        for (int i = 0; i < n + 1; i++) {
            if (result[i] > cnt) {
                cnt = result[i];
            }
        }
        System.out.println(cnt);
    }

    public static void dijkstra(int start) {
        int[] dis = new int[n + 1];
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
        Arrays.fill(dis, INF);
        dis[start] = 0;
        q.offer(new int[] { start,0 });
        while (!q.isEmpty()) {
            int[] edge = q.poll();
            int now = edge[0];
            int distance = edge[1];
            if (dis[now] < distance) {
                continue;
            }
            for (int[] next : lst.get(now)) {
                int cost = distance + next[1];
                if (cost < dis[next[0]]) {
                    dis[next[0]] = cost;
                    q.offer(new int[] { next[0], cost });
                }
            }
        }

        for (int j = 1; j < n + 1; j++) {
            if (dis[j] <= m) {
                result[start] += item[j];
            }
        }
    }
}