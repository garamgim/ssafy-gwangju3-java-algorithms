package study_week9;

import java.util.*;

public class B_3_11779 {
    static final int INF = Integer.MAX_VALUE;
    static int cnt;
    static Set<Integer>set;
    static StringBuilder sb;
    static int pre[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<int[]>>graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        int [] dis = new int[n+1];
        pre = new int[n+1]; // 이전 도시
        Arrays.fill(dis,INF);
        for(int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new int[]{b, c});
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
        sb = new StringBuilder();
        set = new HashSet<>();
        set.add(start);
        set.add(end);
        cnt = 0;
        dikjstra(start,graph,dis);
        System.out.println(dis[end]);
        Stack<Integer>stack = new Stack<>();
        stack.push(end);
        while(pre[end]!=0){
            cnt += 1;
            stack.push(pre[end]);
            end = pre[end];
        }
        System.out.println(cnt+1);
        while(!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
    }
    static void dikjstra(int start, List<List<int[]>>graph, int[]dis){
        PriorityQueue<int[]>pq = new PriorityQueue<>(Comparator.comparingInt(e ->e[1]));
        pq.offer(new int[]{start,0});
        dis[start] = 0;
        while(!pq.isEmpty()){
            int[]edge = pq.poll();
            int distance = edge[1];
            int now = edge[0];
            if(dis[now] < distance){
                continue;
            }
            for(int[]next : graph.get(now)){
                int dest = next[0];
                int cost = distance + next[1];
                if(cost < dis[dest]){
                    pre[dest] = now;
                    dis[dest] = cost;
                    pq.offer(new int[]{dest,cost});
                }
            }
        }
    }
}
