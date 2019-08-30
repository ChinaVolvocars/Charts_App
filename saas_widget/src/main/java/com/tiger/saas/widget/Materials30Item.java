package com.tiger.saas.widget;

public class Materials30Item {

    private String time;        //底部的时间
    private int countMaterial;  //入场几种原材料
    private double countTon;    //合计多少吨


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCountMaterial() {
        return countMaterial;
    }

    public void setCountMaterial(int countMaterial) {
        this.countMaterial = countMaterial;
    }

    public double getCountTon() {
        return countTon;
    }

    public void setCountTon(double countTon) {
        this.countTon = countTon;
    }


    @Override
    public String toString() {
        return "Materials30Item{" +
                "time='" + time + '\'' +
                ", countMaterial=" + countMaterial +
                ", countTon=" + countTon +
                '}';
    }
}
