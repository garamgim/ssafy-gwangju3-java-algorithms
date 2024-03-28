package week_09_다익스트라.전기철;

import java.util.Arrays;
import java.util.Scanner;

public class B_2_11404 {
    static final int INF = (int) 1e9;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[][] lst=new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(lst[i],INF);
        }
        for(int i=0;i<=n;i++){
            lst[i][i]=0;
        }
        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            if(lst[a][b]>c){
                lst[a][b]=c;
            }
        }
        for(int k=1;k<n+1;k++){
            for(int i=1;i<n+1;i++){
                for(int j=1;j<n+1;j++){
                    lst[i][j]=Math.min(lst[i][j],lst[i][k]+lst[k][j]);
                }
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(lst[i][j]==INF){
                    lst[i][j]=0;
                }
            }
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                System.out.print(lst[i][j]+" ");
            }
            System.out.println();
        }
    }
}
