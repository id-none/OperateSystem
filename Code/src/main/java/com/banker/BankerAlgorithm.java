package com.banker;

import java.util.Scanner;

/**
 * @author 王波
 */
public class BankerAlgorithm {
    static int p;
    static int r;

    /**
     * 分配矩阵
     * 需求矩阵
     * 可用资源向量
     * 最大需求矩阵
     * 请求向量当前进程对各类资源的申请量,算法的入口参数
     */
    static int[][] maxes = new int[10][10];

    static int[][] allocation = new int[10][10];

    static int[][] need = new int[10][10];
    static int[] available = new int[10];
    static int[] request = new int[10];

    public static void infInput() {
        int i, j;
        Scanner input = new Scanner(System.in);
        System.out.println("请输入最大需求矩阵max");
        for (i = 0; i < p; i++) {
            for (j = 0; j < r; j++) {
                maxes[i][j] = input.nextInt();
            }
        }
        System.out.println("请输入分配矩阵allocation");
        for (i = 0; i < p; i++) {
            for (j = 0; j < r; j++) {
                allocation[i][j] = input.nextInt();
            }
        }
        System.out.println("请输入需求矩阵need");
        for (i = 0; i < p; i++) {
            for (j = 0; j < r; j++) {
                need[i][j] = input.nextInt();
            }
        }
        System.out.println("请输入可用资源向量available");
        for (i = 0; i < r; i++) {
            available[i] = input.nextInt();
        }
    }

    public static int compare(int[] m, int[] n) {
        int i;
        for (i = 0; i < r; i++) {
            if (m[i] < n[i]) {
                return 0;
            }
        }
        return 1;
    }

    public static int sTest() {
        int i, j, k, l, flag = 0;
        int[] finish = new int[p];
        int[] work = new int[r];
        for (i = 0; i < p; i++) {
            //vis为1即表示available满足第i进程的资源需要
            finish[i] = 0;
        }
        for (i = 0; i < r; i++) {
            work[i] = available[i];
        }
        System.out.println("分配序列：");
        System.out.println("            allocation            need              available");
        for (k = 0; k < p; k++) {
            for (i = 0; i < p; i++) {
                if (finish[i] == 1) {
                    continue;
                } else {
                    //available>=need
                    if (compare(work, need[i]) == 1) {
                        finish[i] = 1;
                        System.out.print('\n' + "进程" + i + 1 + '\t');
                        flag = 1;
                        for (j = 0; j < r; j++) {
                            System.out.printf("  %2d ", allocation[i][j]);
                        }
                        System.out.print("     ");
                        for (j = 0; j < r; j++) {
                            System.out.printf("  %2d ", need[i][j]);
                        }
                        System.out.print("     ");
                        for (j = 0; j < r; j++) {
                            System.out.printf("  %2d ", work[j] + allocation[i][j]);
                        }
                        for (l = 0; l < r; l++) {
                            work[l] = work[l] + allocation[i][l];
                            //进程完成，释放资源
                        }
                        break;
                    }
                }
                if (flag == 1) {
                    break;
                }
            }
        }
        System.out.print('\n' + "");
        for (l = 0; l < p; l++) {
            if (finish[l] == 0) {
                //不存在安全序列
                return 0;
            }
        }
        //存在安全序列
        return 1;
    }

    public static void retest(int n) {
        int j;
        //n=n-1;
        //available>=request 并且 need >=request
        if (compare(available, request) == 1 && compare(need[n - 1], request) == 1) {
            for (j = 0; j < r; j++) {
                allocation[n - 1][j] = allocation[n - 1][j] + request[j];
                need[n - 1][j] = need[n - 1][j] - request[j];
                available[j] = available[j] - request[j];
            }
            if (sTest() == 1) {
                System.out.println("允许" + n + "进程申请资源！");
            } else {
                System.out.println("不允许" + n + "进程申请资源！");
                for (j = 0; j < r; j++) {
                    allocation[n - 1][j] = allocation[n - 1][j] - request[j];
                    need[n - 1][j] = need[n - 1][j] + request[j];
                    available[j] = available[j] + request[j];
                }
            }
        } else {
            System.out.println("申请资源量越界！");
        }
    }

    public static void main(String[] args) {
        //n-第n个资源申请
        int i, n;
        Scanner in = new Scanner(System.in);
        System.out.print("请输入进程数：");
        p = in.nextInt();
        System.out.print("请输入资源种类数：");
        r = in.nextInt();
        //默认状态4、3
        infInput();//输入函数
        if (sTest() == 1) {
            System.out.print("存在安全序列，初始状态安全。\n");
        } else {
            System.out.print("不存在安全序列，初始状态不安全。\n");
        }
        while (true) {
            try {
                System.out.print("请输入发出请求向量request的进程编号：");
                n = in.nextInt();
                System.out.print("请输入请求向量request\n");
                for (i = 0; i < r; i++) {
                    request[i] = in.nextInt();
                }
                retest(n);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}