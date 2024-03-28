package java_study.w9;

import java.util.*;

public class B_1_14938 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 지역 개수
        int m = sc.nextInt(); // 수색 범위
        int r = sc.nextInt(); // 길의 개수
        // 아이템
        int[] items = new int[n+1];
        List<List<int[]>> graph = new ArrayList<>();
        graph.add(new ArrayList<>()); // 0번 자리
        for (int i = 1; i <= n; i++) {
            items[i] = sc.nextInt();
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < r; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int l = sc.nextInt();
            graph.get(a).add(new int[]{b, l});
            graph.get(b).add(new int[]{a, l}); // 양방향
        }
        final int INF = Integer.MAX_VALUE;
        int[] D = new int[n+1];
        int max_sum = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            Arrays.fill(D, INF);
            // 다익스트라
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
            pq.offer(new int[] {i, 0});
            D[i] = 0;
            while (!pq.isEmpty()) {
                int[] poll = pq.poll();
                int nowI = poll[0];
                int nowD = poll[1];

                if (D[nowI] < nowD) continue;

                for (int[] nxt : graph.get(nowI)) {
                    int nxtI = nxt[0];
                    int nxtD = nxt[1];
                    int newD = nowD + nxtD;
                    if (D[nxtI] > newD) {
                        D[nxtI] = newD;
                        pq.offer(new int[] {nxtI, newD});
                    }
                }
            }
            for (int j = 1; j <= n ; j++) {
                if (D[j] <= m) {
                    sum += items[j];
                }
            }
            max_sum = Math.max(max_sum, sum);

        }
        System.out.println(max_sum);

    }
}
