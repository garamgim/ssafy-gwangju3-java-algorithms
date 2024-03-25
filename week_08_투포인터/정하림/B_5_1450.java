package java_study.w8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_5_1450 {
    static int N, C;
    static List<Integer> W1 = new ArrayList<>();
    static List<Integer> W2 = new ArrayList<>();
    static List<Integer> sum1 = new ArrayList<>();
    static List<Integer> sum2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 두 부분으로 나눠서
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (i < N / 2) W1.add(Integer.parseInt(st.nextToken()));

            else W2.add(Integer.parseInt(st.nextToken()));
        }
        dfs(W1, 0, 0, sum1);
        dfs(W2, 0, 0, sum2);

        Collections.sort(sum2); // 이분탐색을 위한 전처리
        int cnt = 0;

        for (int i = 0; i < sum1.size(); i++) {
            int target = C - sum1.get(i);
            // 나도 왜 이분 탐색 기본 반환값이 -1이고 여기서 +1 해주는지 모르겠어..
            cnt += binarySearch(target) + 1;
        }
        System.out.println(cnt);
    }


    public static void dfs(List<Integer> W, int idx, int now_sum, List<Integer> sum) {
        if (now_sum > C) return;
        if (idx == W.size()) {
            sum.add(now_sum);
            return;
        }
        dfs(W,idx + 1, now_sum + W.get(idx), sum);
        dfs(W, idx + 1, now_sum, sum);
    }

    public static int binarySearch(int tar) {
        int l = 0, r = sum2.size() - 1, mid, ans = -1;

        while (l <= r) {
            mid = (l + r) / 2;
            if (sum2.get(mid) <= tar) {
                ans = mid;
                l = mid + 1;
            } else r = mid - 1;
        }
        return ans;
    }
}
