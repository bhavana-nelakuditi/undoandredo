package com.example.undoandredo;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements
        TextUndoRedo.TextChangeInfo {
    private TextUndoRedo TUR;
    private SensorManager sensorManager;
    private Sensor sensor;
    private Sensor orientationSensor;
    private SensorEventListener gyroscopeListenener;
    private SensorEventListener orientationListener;
    private Integer gyroscopeTilt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gyroscopeTilt = Integer.valueOf(0);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        TUR = new TextUndoRedo((EditText) findViewById(R.id.editText), this);
        //AccSensorManager  = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //ACCSensor = AccSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(sensor == null) {
            Toast.makeText(this, "This device does not have a gyroscope", Toast.LENGTH_SHORT).show();
        }

        gyroscopeListenener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if((gyroscopeTilt==1 || gyroscopeTilt==0) && sensorEvent.values[2] > 1.0f ) {

                    System.out.println("You tilted the device right");
                    gyroscopeTilt+= 1;
                    if (TUR.canUndo()) {
                        TUR.exeUndo();
                    }


                }
                    else if ((gyroscopeTilt==-1 || gyroscopeTilt==0) && sensorEvent.values[2] < -1.0f) {
                    System.out.println("You tilted the device left");
                        if (TUR.canRedo()) {
                            gyroscopeTilt += 1;
                            TUR.exeRedo();
                        }
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        orientationListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                gyroscopeTilt = Math.round(sensorEvent.values[2]);
                System.out.println("Current tilt is " + gyroscopeTilt);


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        }
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroscopeListenener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(orientationListener, orientationSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    protected void onPause() {
        super.onPause();
        // Call disable to ensure that the trigger request has been canceled.
        sensorManager.unregisterListener(gyroscopeListenener);
        sensorManager.unregisterListener(orientationListener);


    }


    @Override
    public void textAction() {

    }
}