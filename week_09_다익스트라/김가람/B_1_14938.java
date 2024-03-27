package week_09_다익스트라.김가람;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_1_14938 {
    public static void main(String[] args) throws IOException {

        /*
         *   플로이드 워셜:
         *   1. 모든 정점에서의 최단거리
         *   2. 다익스트라는 1차원 배열을 쓰지만 플로이드는 2차원
         *   3. 시간 복잡도 O(N^3)
         * */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        int R = Integer.parseInt(s[2]);

        int[] items = new int[N + 1];
        String[] item = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(item[i-1]);
        }

        // 2차원 distance 배열을 INF 값으로 채워줌
        long[][] dist = new long[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 자기 자신 -> 자기 자신은 거리 0
        for (int i = 1; i <= N; i++) {
            dist[i][i] = 0;
        }

        // 각 정점간의 거리 입력 받아서 배열에 저장
        for (int i = 0; i < R; i++) {
            String[] temp = br.readLine().split(" ");
            int u = Integer.parseInt(temp[0]);
            int v = Integer.parseInt(temp[1]);
            int w = Integer.parseInt(temp[2]);
            dist[u][v] = w;
            dist[v][u] = w;
        }

        // 이후 각 정점간의 거리는 min(원래 거리, k번 거쳐간 거리)
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            int maxSum = 0;
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] <= M) maxSum += items[j];
            }
            ans = Math.max(ans, maxSum);
        }

        System.out.println(ans);
    }
}