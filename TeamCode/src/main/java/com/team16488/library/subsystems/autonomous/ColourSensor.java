package com.team16488.library.subsystems.autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team16488.library.subsystems.Subsystem;

public class ColourSensor extends Subsystem {

        final double SCALE_FACTOR = 255;
        public float[] hsvValues;
        private ColorSensor sensorColor;
        private DistanceSensor sensorDistance;


        public ColourSensor(HardwareMap hardwareMap) {
            sensorColor = hardwareMap.get(ColorSensor.class, "colour");
            sensorDistance = hardwareMap.get(DistanceSensor.class, "colour");
            float[] hsvValues = {0F, 0F, 0F};
            final float[] values = hsvValues;
            final double SCALE_FACTOR = 255;
        }

    public void update() {
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);


    }

    public int getAlpha() {
            return sensorColor.alpha();

    }
    public int getRed()
    {
        return sensorColor.red();
    }
    public int getGreen() {
            return sensorColor.green();
    }
    public int getBlue(){
            return sensorColor.blue();

    }

    public float getHue() {
        return hsvValues[0];
    }
}