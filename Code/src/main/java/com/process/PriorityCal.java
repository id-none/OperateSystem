package com.process;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author 王波
 */
public class PriorityCal {

    Scanner str = new Scanner(System.in);

    private List<ProcessObj> processList = new LinkedList<ProcessObj>();
    private List<String> listStateCode = new LinkedList<String>();
    private Random rand = new Random();

    /**
     * 初始化状态码
     */
    public void initStateCode() {
        listStateCode.add("ready");
        listStateCode.add("running");
        listStateCode.add("waiting");
        listStateCode.add("terminated");
    }

    //输入进程块
    public void input() {
        for (int i = 0; i < 5; i++) {
            System.out.println("输入第" + (i + 1) + "进程的信息：进程ID 进程所需时间 优先级");
            ProcessObj obj = new ProcessObj();
            obj.setName(str.next());
            obj.setNeed_time(str.nextInt());
            initStateCode();
            obj.setState(listStateCode.get(0));

            //int value = rand.nextInt(11) + 40;
            //obj.setPriority(value);
            //obj.setArrive_time(str.nextInt());
            obj.setPriority(str.nextInt());
            processList.add(obj);
            //System.out.println(obj.getName()+"\t"+obj.getNeedtime()+"\t"+obj.getPriority()+"\t"+obj.getState()+"\t"+obj.getCuptime());
        }

    }

    //程序进程优先级运行
    public int priorControl() {
        /*
         * 读取prior数值最大的对象进cup调度
         */
        int max = 0;
        int i = 0;
        while (i < processList.size()) {
            if (!("terminated".equals(processList.get(i).getState()))) {
                int index = processList.get(max).getPriority() >= processList.get(i).getPriority() ? max : i;
                i = i + 1;
                max = index;
            } else {
                i = i + 1;
            }
        }
        return max;
    }

    public void showProcess() {
        System.out.println("name  cup_time  need_time priority state");
        for (ProcessObj obj : processList) {
            System.out.println(obj.getName() + "\t" + obj.getCup_time() + "\t" + obj.getNeed_time() + "\t" + obj.getPriority() + "\t" + obj.getState());
        }
    }

    public void judgeProcess() {
        /*
         * 优先级-3 need_time-1 cpu_time+1 先到先运行策略
         * 可有四种状态，分别为ready, running, waiting及terminated状态，并假定初始状态为ready状态。
         * 运行时为 running need_time为0时 为 terminated
         */

        int maxPrior = priorControl();
        ProcessObj tmp = processList.get(maxPrior);

        if (tmp.getNeed_time() > 0) {
            tmp.setState(listStateCode.get(1));
            int need_time = tmp.getNeed_time() - 1;
            int cpu_time = tmp.getCup_time() + 1;
            int prior_time = tmp.getPriority() - 3;
            tmp.setCup_time(cpu_time);
            tmp.setNeed_time(need_time);
            tmp.setPriority(prior_time);
            processList.set(maxPrior, tmp);
            showProcess();
        }

        tmp.setState(listStateCode.get(2));
        processList.set(maxPrior, tmp);

        if (tmp.getNeed_time() == 0) {
            tmp.setState(listStateCode.get(3));
            processList.set(maxPrior, tmp);
        }
    }

}

