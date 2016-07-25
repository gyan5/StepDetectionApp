package com.example.kankoyan.summer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

public class SensorActivity extends AppCompatActivity {
    private Button run,end;
    private TableLayout tl;
    private TextView condition;
    private TextView step;
    private Vector<Integer> path = new Vector<>();
    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        run = (Button)findViewById(R.id.button_run);
        end = (Button)findViewById(R.id.button_stop);
        condition = (TextView)findViewById(R.id.status);
        step = (TextView)findViewById(R.id.step);
        final SensorOperations operation_x = new SensorOperations();
        int a =0;
        path.add(a);
        operation_x.setValue(SensorActivity.this,this.condition,this.step,this.path);


 /*       tl = (TableLayout) findViewById(R.id.table_lay);
        for (Sensor sensor : operation_x.deviceSensors) {
            if (operation_x.checkSensor(sensor)) {
                TableRow tr = new TableRow(SensorActivity.this);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                TextView t = new TextView(SensorActivity.this);
                t.setText(sensor.getStringType());
                t.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                tr.addView(t);
                tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        }*/

        run.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    operation_x.start(SensorActivity.this);

                }
            });
        end.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                operation_x.stop(SensorActivity.this,path);
                Intent back = new Intent(SensorActivity.this,Draw.class);
                back.putExtra("path",path);
                startActivity(back);
            }
        });

    }


}
