package com.yangcq.zuo.bilibili.class01;

import java.util.Arrays;

/**
 * 初始100人，每人100元，重复以下操作
 * 1. 每个人拿出1元随机给其余的99人中的一人
 * 2. 如果当前人的金额为0元，则不给
 * 重复多次，评估财富分布
 * <p>
 * 衡量财富均匀的概念，基尼系数
 * 系数的计算方式: (财富差值的绝对值总和) / (2 * 人数 * 财富总和)
 *
 * @author yangcq6
 * @Date 2025/09/16 16:36:18
 */
public class GiniIndex {

    public static void solve(int n, int t) {
        double[] q = new double[n];
        Arrays.fill(q, 100.0);
        boolean[] st = new boolean[n];
        for (int i = 0; i < t; i++) {
            // 每轮开始前重置，是否需要给钱
            Arrays.fill(st, false);
            for (int j = 0; j < n; j++) {
                if (q[j] > 0) st[j] = true;
            }

            for (int j = 0; j < n; j++) {
                if (st[j]) {
                    int k;
                    do {
                        k = (int)(Math.random() * n);
                    } while (k == j);

                    q[j]--;
                    q[k]++;
                }
            }
        }

        Arrays.sort(q);
        System.out.println("财富排序：");
        for (int i = 0; i < n; i++) {
            System.out.printf("%5d ", (int)q[i]);
            if (i % 10 == 9) System.out.println();
        }
        System.out.println();

        double s = 0, abs = 0;
        for (int i = 0; i < n; i++) {
            s += q[i];
            for (int j = 0; j < n; j++) {
                abs += Math.abs(q[i] - q[j]);
            }
        }

        double gini =  (abs) / (2 * n * s);
        System.out.printf("Gini Index = %.6f\n", gini);
    }

    public static void main(String[] args) {
        int n = 100, t = 10000;
        System.out.printf("人数：%d\n试验次数：%d\n", n, t);
        solve(n, t);
    }
}
