package week_09_다익스트라.전기철;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B_4_1504 {
    static final int INF=Integer.MAX_VALUE;
    static int n,m;
    static List<List<int[]>> lst=new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        for(int i=0;i<n+1;i++){
            lst.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            lst.get(a).add(new int[]{b,c});
            lst.get(b).add(new int[]{a,c});
        }
        int x1=sc.nextInt();
        int x2=sc.nextInt();
        int[] mid1=new int[3];
        int[] mid2=new int[3];
        int[] p = dijkstra(1);
        mid1[0]=p[x1];
        mid2[0]=p[x2];
        int[] q = dijkstra(x1);
        mid1[1]=q[x2];
        mid2[2]=q[n];
        int[] r =dijkstra(x2);
        mid1[2]=r[n];
        mid2[1]=r[x1];
        int chk1=0;
        int chk2=0;
        for (int i=0;i<3;i++){
            if (mid1[i]==INF){
                mid1[0]=0;
                mid1[1]=0;
                mid1[2]=0;
                chk1=1;
            }
            if (mid2[i]==INF){
                mid2[0]=0;
                mid2[1]=0;
                mid2[2]=0;
                chk2=1;
            }
        }
        if (chk1==1 &&chk2==1){
            System.out.println(-1);
        }
        else{
            int sm1=0;
            int sm2=0;
            for(int i=0;i<3;i++){
                sm1+=mid1[i];
                sm2+=mid2[i];
            }
            int ans=Math.min(sm1,sm2);
            System.out.println(ans);
        }
    }

    public static int[] dijkstra(int start){
        int[] dis=new int[n+1];
        Arrays.fill(dis,INF);
        PriorityQueue<int[]> q= new PriorityQueue<>(Comparator.comparingInt(e->e[1]));
        q.offer(new int[]{start,0});
        dis[start]=0;
        while(!q.isEmpty()){
            int[] edge=q.poll();
            int now=edge[0];
            int distance= edge[1];
            if(dis[now]<distance){
                continue;
            }
            for(int[] next: lst.get(now)){
                int go=next[0];
                int cost=distance+next[1];
                if(cost<dis[go]){
                    dis[go]=cost;
                    q.offer(new int[]{go,cost});
                }
            }
        }
        return dis;
    }
}
