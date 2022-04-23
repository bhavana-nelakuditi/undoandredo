package com.example.undoandredo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;

class TriggerListener extends TriggerEventListener {
    public void onTrigger(TriggerEvent event) {
        // Do Work.

        // As it is a one shot sensor, it will be canceled automatically.
        // SensorManager.requestTriggerSensor(this, mSigMotion); needs to
        // be called again, if needed.
    }
}
public class SensorActivity extends Activity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private final TriggerEventListener mListener = new TriggerListener();

    public SensorActivity() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.requestTriggerSensor(mListener, sensor);
    }

    protected void onPause() {
        super.onPause();
        // Call disable to ensure that the trigger request has been canceled.
        sensorManager.cancelTriggerSensor(mListener, sensor);
    }

}