package com.example.kankoyan.summer;

/**
 adsasda* Created by kankoyan on 2016/07/05.
 */
public class Calorie {
    private double calorie;
    private double height;
    private double weight;
    private double foot_length;

    Calorie(double h,double w){
        height = h;
        weight = w*2.20462;
        calorie=0;
        foot_length=0;
    }

    public double getCalorie(){
        return calorie;
    }

    public void cal_foot(){
        foot_length = height*0.45;
    }

    public void cal_calorie(int step){
        this.cal_foot();
        double mile_calorie = weight*0.57;
        calorie = (mile_calorie*160934)*((step*foot_length)/160934);
    }
}

