package com.example.kankoyan.summer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by kankoyan on 2016/07/06.
 */
public class Points {
    private float x,y;
    private int hist; // 1-up 2-down 3-left 4-right
    private float footlength;
    Points(){
        x = y = 0;
        hist =4;
        footlength = 1;
    }
    public float getX(){return x;}
    public float getY(){return y;}
    public void setX(float value){x = value;}
    public void setY(float value){y = value;}


    public void cal_new_pt(int step,int turn){
        switch(hist){
            case 1:
                if(turn==-1){
                    x += step*footlength;
                    hist = 4;
                }
                else{
                    x -= step*footlength;
                    hist = 3;

                }
                break;
            case 2:
                if(turn==-1){
                    x -= step*footlength;
                    hist = 3;
                }
                else{

                    x += step*footlength;
                    hist = 4;
                }
                break;
            case 3:
                if(turn==-1){
                    y += step*footlength;
                    hist = 1;
                }
                else{
                    y -= step*footlength;
                    hist = 2;
                }
                break;
            case 4:
                if(turn==-1){
                    y -= step*footlength;
                    hist = 2;
                }
                else{
                    y += step*footlength;
                    hist = 1;
                }
                break;
            default:
                break;
        }
    }

    public void reset_hist(){
        hist = 4;
    }
    public void scale(ArrayList<Integer> path, float verticle, float horizontal){
        float max_x =0;
        float min_x =0;
        float max_y =0;
        float min_y =0;
//      setX(max_x);
//        this.setY(max_y);
        path.set(0,-1);
        if(path.get(path.size()-1)<0){
            path.remove(path.size()-1);
        }
        for(int i =0;i<path.size();i+=2){
            int turn = path.get(i);
            int step = path.get(i+1);
            this.cal_new_pt(step,turn);
            max_x = (x>max_x)? x:max_x;
            min_x = (x<min_x)? x:min_x;
            max_y = (y>max_y)? y:max_y;
            min_y = (y<min_y)? y:min_y;
        }
        float tempx_max=0;
        float tempy_max=0;
        float tempx_min=0;
        float tempy_min=0;
        float smallest_x =0;
        float smallest_y =0;
        if(max_x!=0){
            tempx_max = (horizontal-100)/(2*max_x);
        }
        if(min_x!=0) {
            tempx_min = ((horizontal - 100) / (2 * Math.abs(min_x)));
        }
        if(tempx_max==0&&tempx_min==0){
            smallest_x = verticle;
        }
        else if(tempx_max==0){
            smallest_x = tempx_min;
        }
        else if(tempx_min==0){
            smallest_x = tempx_max;
        }
        else {
            smallest_x = (tempx_max > tempx_min) ? tempx_min : tempx_max;
        }
        if(max_y!=0) {
            tempy_max = (verticle - 100) / (2 * max_y);
        }
        if(min_y!=0) {
            tempy_min = ((verticle - 100) / (2 * Math.abs(min_y)));
        }
        if(tempy_max==0&&tempy_min==0){
            smallest_y = horizontal;
        }
        else if(tempy_max==0){
            smallest_y = tempy_min;
        }
        else if(tempy_min==0){
            smallest_y = tempy_max;
        }
        else {
            smallest_y = ( tempy_max>tempy_min )? tempy_min:tempy_max;
        }
        //float max_ver = Math.abs(verticle/(max_y - min_y));
        //float max_hori = Math.abs(horizontal/(max_x - min_x));
        footlength = (smallest_x>smallest_y)? smallest_y:smallest_x;
    }


}
