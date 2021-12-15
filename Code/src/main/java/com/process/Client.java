package com.process;

//程序调用

import java.util.List;
import java.util.Scanner;

/**
 * @author 王波
 */
public class Client {

    private static Scanner str = new Scanner(System.in);

    //程序入口函数
    public static void main(String[] args) {
        menuChose();
        int choose = str.nextInt();
        switch (choose) {
            case 1:
                RunPriority();
                break;
            case 2:
                roundCal();
                break;
            case 0:
                System.out.println("程序结束运行");
                break;
            default:
                break;
        }
    }

    public static void menuChose() {
        System.out.println("CHOOSE THE ALGORITHM");
        System.out.println("1   PRIORITY");
        System.out.println("2   ROUND ROBIN");
        System.out.println("0 exit");
    }

    public static void RunPriority() {

        PriorityCal pcb = new PriorityCal();
        System.out.println("Priority scheduling,input name and need_time：");
        pcb.input();
        System.out.println("ready process queue");
        pcb.showProcess();
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println("cpu_time=" + (i + 1));
            pcb.judgeProcess();
        }
        System.out.println("all process have finished!");
    }

    public static void roundCal() {
        RoundCal cal = new RoundCal();
        System.out.println("Priority scheduling,input name and need_time：");
        cal.input();
        System.out.println("ready process queue");
        cal.showProcess();
        int result = 0;
        int num = 2;
        for (int i = 1; i < 10; i++) {
            System.out.println("cpu_time=" + (i * num));
            if (result < 3) {
                cal.roundTime(result);
            } else {
                result = 0;
                cal.roundTime(result);
            }
            result++;
        }
    }

}
