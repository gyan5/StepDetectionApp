package com.example.kankoyan.summer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by kankoyan on 2016/07/07.
 */
public class DrawView extends View {
    private Paint paint = new Paint();
    private ArrayList<Integer> path;
    private Points points;
    public DrawView(Context context,ArrayList<Integer> path_new) {
        super(context);
        paint.setColor(Color.BLACK);
        path = path_new;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        points = new Points();
        points.scale(path,canvas.getHeight(),canvas.getWidth());
        points.reset_hist();
        float current_x = canvas.getWidth()/2;
        float current_y = canvas.getHeight()/2;
        points.setX(current_x);
        points.setY(current_y);
        Paint text = new Paint();
        text.setColor(Color.BLUE);
        text.setTextSize(50);
        canvas.drawText("start",current_x,current_y,text);
        for(int i=0;i<path.size();i+=2){
            int turn = path.get(i);
            int step = path.get(i+1);
            points.cal_new_pt(step,turn);
            canvas.drawLine(current_x,current_y,points.getX(),points.getY(),paint);
            Paint ppp = new Paint();
            ppp.setColor(Color.DKGRAY);
            ppp.setTextSize(35);
            canvas.drawText(String.valueOf(step),(current_x+points.getX())/2,(current_y+points.getY())/2,ppp);
            current_x = points.getX();
            current_y = points.getY();

        }
        canvas.drawText("end",current_x,current_y,text);

    }
}
