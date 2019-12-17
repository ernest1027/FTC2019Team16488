package com.team16488.library.subsystems.aoutonomus;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team16488.library.subsystems.Subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class ColourSensor extends Subsystem {

        final double SCALE_FACTOR = 255;
        public float[] hsvValues;
        private ColorSensor sensorColor;
        private DistanceSensor sensorDistance;


        public ColourSensor(HardwareMap map) {
            sensorColor = map.get(ColorSensor.class, "colour");
            sensorDistance = map.get(DistanceSensor.class, "colour");
            float[] hsvValues = {0F, 0F, 0F};
            final float[] values = hsvValues;

        }

    public void update() {
        // convert the RGB values to HSV values.
        // multiply by the SCALE_FACTOR.
        // then cast it back to int (SCALE_FACTOR is a double)
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);


    }

    public double[] getColour() {
        double[] colours = new double[6];
        colours[0] = sensorDistance.getDistance(DistanceUnit.CM);
        colours[1] = sensorColor.alpha();
        colours[2] = sensorColor.red();
        colours[3] = sensorColor.blue();
        colours[4] = sensorColor.green();
        colours[5] = hsvValues[0];
        return colours;
    }

}