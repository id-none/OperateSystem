package com.process;

public class ReProcessObj {

    private String name;
    private int need_time;
    private int cpu_time = 0;
    private String state;
    private int round = 0;
    private int count = 0;
    private int one_time;

    public String getName() {
        return name;
    }

    public void setOne_time(int one_time) {
        this.one_time = one_time;
    }

    public int getOne_time() {
        return this.one_time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeed_time() {
        return need_time;
    }

    public void setNeed_time(int need_time) {
        this.need_time = need_time;
    }

    public int getCpu_time() {
        return cpu_time;
    }

    public void setCpu_time(int cpu_time) {
        this.cpu_time = cpu_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ReProcessObj(String name, int needtime, int cpu_time, String state, int round, int count, int one_time) {
        super();
        this.name = name;
        this.need_time = needtime;
        this.cpu_time = cpu_time;
        this.state = state;
        this.round = round;
        this.count = count;
        this.one_time = one_time;
    }

    public ReProcessObj() {
        super();
    }

    @Override
    public String toString() {
        return name + "\t" + cpu_time + "\t" + need_time + "\t" + round
                + "\t" + count + "\t" + state;

    }
}


