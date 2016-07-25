package com.example.kankoyan.summer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

/**
 * Created by kankoyan on 2016/06/28.
 */
public class SensorOperations {
    private SensorManager mSensorManager ;
    private SensorEventListener mSensorListener;
    public List<Sensor> deviceSensors;
    private Walk walk = new Walk();
    private Walk stand = new Walk();
    private StepCounting step_count = new StepCounting();
    private Magnetic mag = new Magnetic();
    private Orientation ori = new Orientation();
    private static int flag;
    private static int flag1;
    private static boolean start;


//setting values

    public void setValue(Context context, final TextView text, final TextView step, final Vector<Integer> path){
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        flag =0;
        flag1 =0;
        start = false;
        mSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                switch (sensor.getType()) {
                    case Sensor.TYPE_ACCELEROMETER:
                        if(flag == 0){
                           acc(event.values[2], walk);
                        }
                        else {
                            switch (flag1) {
                                case 0:
                                    text.setText("Ready..");
                                    flag1 = 1;
                                    break;
                                case 1:
                                    if(test(event.values[2], walk) > walk.getThreshold()) {
                                        text.setText("Started Walking..");
                                        flag1 = 2;
                                        stand.setThreshold(walk.getThreshold());
                                        stand.setSd_mean(walk.getSd_mean());
                                    }
                                    break;
                                case 2:
                                    // part for step counting algorithm
                                    stand_calc(stand,event.values[2]);
                                    step_counting(walk, step_count, event.values[2]);
                                    step.setText(String.valueOf(step_count.getStep()));
                                    break;
                            }
                        }
                        break;
                    case Sensor.TYPE_ORIENTATION:
                        //do something...
                        if(flag1==2) {
                            turn_detection(ori, mag, event.values[0], text,path,step_count);
                        }
                        break;
                    case Sensor.TYPE_MAGNETIC_FIELD:
                        if(flag1==2) {
                            mag.add_x(event.values[0]);
                            mag.add_y(event.values[1]);
                            mag.add_z(event.values[2]);
                            if(mag.length()>30) mag.remove_first();
                        }
                        break;
                    default:
                        //do something...
                        break;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public void start(Context context){
        listenerReg(deviceSensors,mSensorListener,mSensorManager);
        Toast.makeText(context,"start",Toast.LENGTH_SHORT).show();
    }

    public void stop(Context context, Vector<Integer> path){
        listenerUnReg(deviceSensors,mSensorListener,mSensorManager);
        Toast.makeText(context,"end",Toast.LENGTH_SHORT).show();
        int count =0;
        for(int data:path){
            if(data>=0){
                count += data;
            }
        }
        if(step_count.getStep()-count!=0){
            path.add(step_count.getStep()-count);
        }
    }

    public boolean checkSensor(Sensor sensor){

        if(mSensorManager.getDefaultSensor(sensor.getType())== null || sensor.getType()==
                (Sensor.TYPE_LIGHT | Sensor.TYPE_AMBIENT_TEMPERATURE |  Sensor.TYPE_PROXIMITY | Sensor.TYPE_RELATIVE_HUMIDITY)){
            return false;
        }
        return true;
    }

    public void listenerReg(List<Sensor> deviceSensors,SensorEventListener mSensorListener,SensorManager mSensorManager){
        for (Sensor sensor : deviceSensors)
        {
            if (sensor.getType()!=
                    (Sensor.TYPE_LIGHT | Sensor.TYPE_AMBIENT_TEMPERATURE |  Sensor.TYPE_PROXIMITY | Sensor.TYPE_RELATIVE_HUMIDITY))
                mSensorManager.registerListener(mSensorListener, sensor, 500);

        }
    }

    /*Unregister Sensor Listener.
* called by OnPause/OnStop	 */
    public void listenerUnReg(List<Sensor> deviceSensors,SensorEventListener mSensorListener,SensorManager mSensorManager){
        for (Sensor sensor : deviceSensors)
        {
            if (sensor.getType()!=
                    (Sensor.TYPE_LIGHT | Sensor.TYPE_AMBIENT_TEMPERATURE | Sensor.TYPE_PROXIMITY | Sensor.TYPE_RELATIVE_HUMIDITY))

                mSensorManager.unregisterListener(mSensorListener, sensor);
        }
    }



    public void acc(float value,Walk walk){
        walk.add_data(value);
        if(walk.sizeData()>=50){
            double sd = walk.Calc_SD();
            walk.add_sd(sd);
            walk.delete();
        }
        if(walk.sizeSd()>=25){
            walk.Calc_SD_Mean();
            walk.threshold();
            flag =1;
        }
    }
    public double test(float value, Walk walk) {
        double sd = 0;
        walk.add_data(value);
        if (walk.sizeData() >= 50) {
            sd = walk.Calc_SD();
        }
        if(walk.sizeData()>50){
            walk.delete();
        }
        double diff = Math.abs(sd - walk.getSd_mean());
        return diff;
    }

    public void step_counting(Walk walk,StepCounting step_count,double value){
        //first time entering the function
        if(!start){
            step_count.setMax(walk.largest_data());
//            step_count.setK_value(walk.largest_data());
//            step_count.setI_value(value);
            step_count.Calc_First_Threshold();
            step_count.Check_Max(value);
            step_count.increment_i();
            start = true;
            return;
        }
        //after first time
        step_count.update();
        if(step_count.is_Step(value)){
            step_count.increment_step();
            step_count.setMax(value);
            step_count.set_k();
        }
        step_count.Check_Max(value);
    }


    public void stand_calc(Walk stand, double value){
        stand.add_data(value);
        if (stand.sizeData() >= 50) {
            stand.Calc_SD();
        }
        if(stand.sizeData()>50){
            stand.delete();
        }
        if(stand.sizeSd()>50){
            stand.remove_sd_first();
        }
    }
    public boolean isStandingStill(Walk stand){
        double diff = Math.abs(stand.getSd_mean()- stand.last_sd());
        if(stand.sizeSd()>0){
            if(diff<stand.getThreshold()) return true;
            else return false;
        }
        else return false;
    }

    public void turn_detection(Orientation ori,Magnetic mag,double value,TextView text,Vector<Integer> path,StepCounting step_count){
            if(ori.length()>100) ori.remove_first();
            ori.add_x(value);
            if(ori.length()>20) {
                int result = ori.check();
                switch (result) {
                    case 1:
                        if (mag.check_intersection()&&isStandingStill(stand)) {
                            //do sth
                            text.setText("left");
                            ori.reset();
                            mag.refresh();
                            int count =0;
                            for(int data:path){
                                if(data>=0){
                                    count += data;
                                }
                            }
                            //if standingstill and changing direction
                            if(step_count.getStep()-count==0){
                                path.setElementAt(-1,path.size()-1);
                            }
                            else {
                                path.add(step_count.getStep() - count);
                                path.add(-1);
                            }
                        }
                        break;
                    case 2:
                        if (mag.check_intersection()&&isStandingStill(stand)) {
                            //do sth
                            text.setText("right");
                            ori.reset();
                            mag.refresh();
                            int count =0;
                            for(int data:path){
                                if(data>=0){
                                    count += data;
                                }
                            }
                            //if standingstill and changing direction
                            if(step_count.getStep()-count==0){
                                path.setElementAt(-2,path.size()-1);
                            }
                            else {
                                path.add(step_count.getStep() - count);
                                path.add(-2);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

    }
