package com.example.kankoyan.summer;

import java.util.Collections;
import java.util.Vector;

/**
 * Created by kankoyan on 2016/06/30.
 */
public class Walk{
    private Vector<Double> data ;
    private Vector<Double> sd   ;
    private double threshold;
    private double sd_mean;

    public Walk(){
        data = new Vector();
        sd   = new Vector();
        threshold = 0;
        sd_mean   = 0;
    }


    //basic functions
    public void add_data(double x){
        data.add(x);
    }

    public int sizeData(){
        return data.size();
    }

    public int sizeSd(){
        return sd.size();
    }

    public void delete(){
        data.removeAll(data);
    }

    public void add_sd(double x){
        sd.add(x);
    }
    public double getThreshold(){
        return threshold;
    }
    public double getSd_mean(){
        return sd_mean;
    }
    public void remove_sd_first(){sd.remove(0);}
    public double last_sd(){return sd.get(sd.size()-1);}

    public double data_last(){
        return data.lastElement();
    }

    public void setThreshold(double value){threshold = value;}
    public void setSd_mean(double value){sd_mean = value;}
    //calculating the mean of 25 standard deviation datas
    public void Calc_SD_Mean(){
        int count =0;
        double average =0;
        for(double i:sd){
            average += i;
            count++;
        }
        average = average/count;
        sd_mean = average;
    }
    //calculating standard deviation of ten data sets
    public double Calc_SD(){
        int count = 0;
        Vector<Double> v1 = new Vector();
        for(double i:data){
            v1.add(i);
        }
        double average = 0;
        for(double i:v1){
            average += i;
            count++;
        }
        average /= count;
        count =0;
        for(double i:v1){
            double temp = i - average;
            temp = Math.abs(temp * temp);
            v1.setElementAt(temp,count);
            count++;
        }
        double mean = 0;
        for(double i:v1){
            mean += i;
        }
        mean /= count;
        mean = Math.sqrt(mean);
        sd.add(mean);
        return mean;
    }
    public double threshold(){
        int count =0;
        for(double i:sd){
            double diff = Math.abs(i - sd_mean);
            sd.setElementAt(diff,count);
            count++;
        }
        double max = this.largest();
        threshold = this.check(max);
        return threshold;
    }
    public double largest(){
        double max =0;
        for(double i:sd){
            if(i>max) max = i;
        }
        return max;
    }
    public double check(double max){
        double x =100;
        while(x>max){
            x = x/10;
        }
        x = x*10;
        return max+x;
    }
    public double largest_data(){
        double max =0;
        for(double i:data){
            if(i>max) max = i;
        }
        return max;
    }

}
