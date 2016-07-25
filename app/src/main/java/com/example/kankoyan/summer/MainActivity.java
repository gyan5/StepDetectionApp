package com.example.kankoyan.summer;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //sensor manager
    private SensorManager mSensorManager ;

    private Button start;
    private Button end;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 //       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
   //     setSupportActionBar(toolbar);

 /*       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acc =  (Switch) findViewById(R.id.i_accelerometer);
        grav  = (Switch) findViewById(R.id.i_gravity);
        gyro = (Switch) findViewById(R.id.i_gyroscope);
        linear  = (Switch) findViewById(R.id.i_linear);
        mag = (Switch) findViewById(R.id.i_magnetic);
        pressure = (Switch) findViewById(R.id.i_pressure);
        rotate  = (Switch) findViewById(R.id.i_rotation);

*/
        start = (Button) findViewById(R.id.button_start);

    //    sensorCheck();
        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                Intent service = new Intent(MainActivity.this,SensorActivity.class);
         /*       Bundle b = new Bundle();
                b.putIntArray("sensorList",sensorsList);
                service.putExtras(b);*/
                startActivity(service);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    private void sensorCheck()
    {


        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
            acc.setEnabled(false);
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) == null) {
            grav.setEnabled(false);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) == null) {
            linear.setEnabled(false);
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) == null) {
            gyro.setEnabled(false);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) == null) {
            mag.setEnabled(false);
        }

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) == null) {
            pressure.setEnabled(false);
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) == null) {
            rotate.setEnabled(false);
        }
    }
    /*checking which switch are turned on
    private int[] desiredSensors(){
        int [] sensor = new int [7];
        Arrays.fill(sensor,0);
        if(acc.isChecked()){
            sensor[0] = 1;
        }
        if(grav.isChecked()){
            sensor[1] = 1;
        }
        if(linear.isChecked()){
            sensor[2] = 1;
        }
        if(gyro.isChecked()){
            sensor[3] = 1;
        }
        if(mag.isChecked()){
            sensor[4] = 1;
        }
        if(pressure.isChecked()){
            sensor[5] = 1;
        }
        if(rotate.isChecked()){
            sensor[6] = 1;
        }
        return sensor;
    }
*/
}
