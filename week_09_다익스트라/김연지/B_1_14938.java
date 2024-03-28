package study_week9;

import java.util.Arrays;
import java.util.Scanner;

public class B_1_14938 {
    static final int INF = (int) 1e9;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        int [] item = new int[n+1];
        for(int i = 1; i <= n; i++){
            item[i] = sc.nextInt();
        }
        int [][]x = new int[n+1][n+1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(x[i],INF);
        }
        for(int i = 1; i <= n; i++){
            x[i][i] = 0;
        }
        for(int i = 0; i < r; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            x[a][b] = c;
            x[b][a] = c;
        }
        for(int k = 1; k <= n; k++){
            for(int a = 1; a <= n; a++){
                for(int b = 1; b<= n; b++){
                    x[a][b] = Math.min(x[a][b], x[a][k] + x[k][b]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            int total1 = item[i];
            for(int j = 1; j<= n; j++){
                if(i!=j && x[i][j] <= m ){
                    total1 += item[j];
                }

            }
            max = Math.max(max,total1);
        }
        System.out.println(max);
    }
}
