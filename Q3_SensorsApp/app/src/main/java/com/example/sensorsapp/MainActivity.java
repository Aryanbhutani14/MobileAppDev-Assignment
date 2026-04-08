package com.example.sensorsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView accelStatus, accelX, accelY, accelZ;
    TextView lightStatus, lightReading;
    TextView proximityStatus, proximityReading;

    SensorManager sensorManager;
    Sensor accelerometer, lightSensor, proximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelStatus = findViewById(R.id.accelStatus);
        accelX = findViewById(R.id.accelX);
        accelY = findViewById(R.id.accelY);
        accelZ = findViewById(R.id.accelZ);

        lightStatus = findViewById(R.id.lightStatus);
        lightReading = findViewById(R.id.lightReading);

        proximityStatus = findViewById(R.id.proximityStatus);
        proximityReading = findViewById(R.id.proximityReading);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        accelStatus.setText(accelerometer != null ? "Status: Available" : "Status: Not Available");
        lightStatus.setText(lightSensor != null ? "Status: Available" : "Status: Not Available");
        proximityStatus.setText(proximitySensor != null ? "Status: Available" : "Status: Not Available");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(accelerometer != null)
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        if(lightSensor != null)
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

        if(proximitySensor != null)
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accelX.setText("X: " + event.values[0]);
            accelY.setText("Y: " + event.values[1]);
            accelZ.setText("Z: " + event.values[2]);
        }

        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            lightReading.setText("Reading: " + event.values[0]);
        }

        if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            proximityReading.setText("Reading: " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}







