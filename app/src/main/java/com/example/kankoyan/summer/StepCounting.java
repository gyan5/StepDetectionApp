package com.example.kankoyan.summer;

/**
 * Created by kankoyan on 2016/07/01.
 */
public class StepCounting {
    private double threshold;
    private double a;
    private double b;
    private int i,k;
    private double max;
    private int step;
//    private double k_value;
//    private double i_value;

    StepCounting(){
        threshold = 0;
        a =477.6119;
        b =3.1541;
        k =0;
        i = 1;
        max = 0;
        step = 0;
//        k_value =0;
    }

//    public double getThreshold(){ return threshold;}

    public int getStep(){
        return step;
    }

    public void setMax(double value){
        max = value;
    }

//    public void setK_value(double value){k_value = Math.abs(max - value);}

//    public void setI_value(double value){i_value =Math.abs(max - value);}

    public void increment_i(){++i;}
    public void set_k(){k = i;}
    public void increment_step(){++step;}

    public void Calc_Threshold(){
        threshold = a/(i-k) + b;
    }

    public void Calc_First_Threshold(){
        threshold = a+b;
    }

    public boolean is_Step(double value){
        if(Math.abs(max - value)>threshold) return true;
        return false;
    }


    public void update(){
        this.increment_i();
        this.Calc_Threshold();

    }
    public void Check_Max(double value){
        max = (max>value ? max: value);
    }

}
