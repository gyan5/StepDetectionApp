package com.example.kankoyan.summer;

import java.util.Vector;

/**
 * Created by kankoyan on 2016/07/04.
 */
public class Magnetic {
    private Vector<Double> x ;
    private Vector<Double> y ;
    private Vector<Double> z ;

    Magnetic(){
        x = new Vector();
        y = new Vector();
        z = new Vector();

    }
    public void add_x(double value){x.add(value);}
    public void add_y(double value){y.add(value);}
    public void add_z(double value){z.add(value);}

    public boolean check_intersection(){
        int length = x.size();
        for(int i =0;i<(length-1);i++){
            double x1 = x.elementAt(i);
            double x2 = x.elementAt(i+1);
            double y1 = y.elementAt(i);
            double y2 = y.elementAt(i+1);
            double z1 = z.elementAt(i);
            double z2 = z.elementAt(i+1);
            if((x1>=y1&&x2<=y2)||(x1<=y1&&x2>=y2)) return true;
            if((x1>=z1&&x2<=z2)||(x1<=z1&&x2>=z2)) return true;
            if((z1>=y1&&z2<=y2)||(z1<=y1&&z2>=y2)) return true;
        }
        return false;
    }
    public void remove_first(){
        x.removeElementAt(0);
        y.removeElementAt(0);
        z.removeElementAt(0);
    }
    public void refresh(){
        x.removeAll(x);
        y.removeAll(y);
        z.removeAll(z);

    }
    public int length(){return x.size();}
}
