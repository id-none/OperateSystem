package com.process;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RoundCal {
    private Scanner str = new Scanner(System.in);
    private static int NUM = 3;
    private int cycle = 0;

    private List<ReProcessObj> processList = new LinkedList<ReProcessObj>();
    private List<String> listStateCode = new LinkedList<String>();
    private Random rand = new Random();

    //初始化状态码
    public void initStateCode() {
        listStateCode.add("ready");
        listStateCode.add("running");
        listStateCode.add("waiting");
        listStateCode.add("terminated");
    }

    //输入进程块
    public void input() {
        for (int i = 0; i < NUM; i++) {
            System.out.println("输入第" + (i + 1) + "进程的信息：进程ID 所需时间 进程时间片大小");
            ReProcessObj obj = new ReProcessObj();
            obj.setName(str.next());
            //int value = rand.nextInt(10) + 1;
            obj.setNeed_time(str.nextInt());
            initStateCode();
            obj.setState(listStateCode.get(0));
            obj.setOne_time(str.nextInt());
            processList.add(obj);
            //System.out.println(obj.toString());
        }
    }

    public void roundTime(int i) {

        ReProcessObj obj = processList.get(i);
        if (obj.getNeed_time() != 0) {
            int value = (obj.getNeed_time() - obj.getOne_time()) > 0 ? (obj.getNeed_time() - obj.getOne_time()) : 0;
            obj.setNeed_time(value);
            obj.setCount((obj.getCount() + 1));
            obj.setRound((obj.getRound() + 1));
            obj.setCpu_time((obj.getCpu_time() + 2));
            obj.setState(listStateCode.get(1));
            processList.set(i, obj);
            showProcess();
            if (obj.getNeed_time() == 0) {
                obj.setState(listStateCode.get(3));
            } else {
                obj.setState(listStateCode.get(0));
            }
            processList.set(i, obj);
            return;
        }

        if (obj.getNeed_time() == 0) {
            obj.setRound((obj.getRound() + 1));
            processList.set(i, obj);
            showProcess();
        }

    }

    public void showProcess() {
        System.out.println("name  cup_time  need_time  round  count  state");
        for (ReProcessObj obj : processList) {
            System.out.println(obj.toString());
        }
    }
}
