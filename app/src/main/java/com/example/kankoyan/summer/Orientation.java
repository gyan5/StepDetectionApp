package com.example.kankoyan.summer;

import java.util.Vector;

/**
 * Created by kankoyan on 2016/07/04.
 */
public class Orientation {
    private Vector<Double> x;
    private double offset;
    Orientation(){
        x = new Vector();
        offset =0;
    }

    public void add_x(double value){
        x.add(value);
  /*      if(this.length()>1){
            x.insertElementAt(x.elementAt(this.length()-2)-value,this.length()-1);
            if(Math.abs(x.elementAt(this.length()-2)-value)>100){
                double a = 0;
                x.insertElementAt(a,this.length()-1);
            }
        }*/
        if(this.length()>1){
            double diff = x.elementAt(this.length()-2)-value;
            if(Math.abs(diff)<250){
                offset += diff;
            }

        }
    }
    public int length(){return x.size();}

    public int check(){
/*        double pos = 0;
        double neg = 0;
        //left turn pos
        //right turn neg
        for(double data:x){
            if(data>=0) pos++;
            if(data<=0) neg++;
        }
        double pos_percentage = pos/this.length();
        double neg_percentage = neg/this.length();
        if(pos_percentage>0.85) return 1;
        if(neg_percentage>0.85) return 2;
        return 0;*/
        if(offset>60) return 1;
        if(offset<-60) return 2;
        return 0;
    }

    public void reset(){offset =0;}
    public void remove_first(){
        x.removeElementAt(0);
    }
    public void refresh(){x.removeAll(x);}
}
