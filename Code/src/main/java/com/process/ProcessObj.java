package com.process;


public class ProcessObj {
    private String name;
    private int cup_time = 0;
    private int need_time;
    private int priority;
    private int arrive_time;
    private String state;

    public ProcessObj(String name, int cpu_time, int need_time, int priority,
                      int arrive_time, String state) {
        super();
        this.name = name;
        this.cup_time = cpu_time;
        this.need_time = need_time;
        this.priority = priority;
        this.arrive_time = arrive_time;
        this.state = state;
    }

    public ProcessObj() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCup_time() {
        return cup_time;
    }

    public void setCup_time(int cup_time) {
        this.cup_time = cup_time;
    }

    public int getNeed_time() {
        return need_time;
    }

    public void setNeed_time(int need_time) {
        this.need_time = need_time;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setArrive_time(int arriveTime) {
        this.arrive_time = arrive_time;
    }

    public int getArrive_time() {
        return this.arrive_time;
    }
}

