package com.example.hw1_lanesgame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class moveDecoretor
{

    public interface CallBack_moves
    {
        void car1Step();
        void car2Step();
        void car3Step();
        void car4Step();
        void car5Step();
    }


    private SensorManager mSensorManager;
    private Sensor sensor;

    private CallBack_moves callBack_moves;
    long timeStamp = 0;


    public moveDecoretor(Context context, CallBack_moves _callBack_moves) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        this.callBack_moves = _callBack_moves;
    }


    public void start() {
        mSensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        mSensorManager.unregisterListener(sensorEventListener);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];

            calculateMove(x);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void calculateMove(float x) {
        if (x <= -6.0) {
            if (System.currentTimeMillis() - timeStamp > 500) {
                timeStamp = System.currentTimeMillis();
                if (callBack_moves != null) {
                    callBack_moves.car5Step();
                }
            }
        }
        if (x > -6.0 && x <= -2.0) {
            if (System.currentTimeMillis() - timeStamp > 500) {
                timeStamp = System.currentTimeMillis();
                if (callBack_moves != null) {
                    callBack_moves.car4Step();
                }
            }
        }
        if (x > -2.0 && x < 2.0) {
            if (System.currentTimeMillis() - timeStamp > 500) {
                timeStamp = System.currentTimeMillis();
                if (callBack_moves != null) {
                    callBack_moves.car3Step();
                }
            }
        }
        if (x >= 2.0 && x < 6.0) {
            if (System.currentTimeMillis() - timeStamp > 500) {
                timeStamp = System.currentTimeMillis();
                if (callBack_moves != null) {
                    callBack_moves.car2Step();
                }
            }
        }
        if (x >= 6.0) {
            if (System.currentTimeMillis() - timeStamp > 500) {
                timeStamp = System.currentTimeMillis();
                if (callBack_moves != null) {
                    callBack_moves.car1Step();
                }
            }
        }

    }
}


