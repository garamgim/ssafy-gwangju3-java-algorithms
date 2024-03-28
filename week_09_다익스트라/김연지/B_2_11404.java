package study_week9;

import java.util.Scanner;

public class B_2_11404 {
    static final int inf = (int)1e9;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][]dis = new int[n+1][n+1];
        for(int i = 0; i <n+1; i++){
            for(int j = 0; j < n+1; j++){
                dis[i][j] = inf;
            }
        }
        for(int i = 1; i <= n; i++){
            dis[i][i] = 0;
        }
        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if(c < dis[a][b]){
            dis[a][b] = c;
            }
        }

        for(int k = 1; k <= n; k++){
            for(int a = 1; a <= n; a++){
                for(int b = 1; b<= n; b++){
                    dis[a][b] = Math.min(dis[a][b], dis[a][k] + dis[k][b]);
                }
            }
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(dis[i][j] == inf){
                    dis[i][j] = 0;
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                System.out.print(dis[i][j]+" ");
            }
            System.out.println();
        }
    }
}
