package com.team16488.library.subsystems;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class AlternateIntake extends Subsystem {
    public DigitalChannel blockDetection;
    public boolean state;
    Servo alternateIntake;
    private boolean down;

    public AlternateIntake(HardwareMap map) {
        blockDetection = map.digitalChannel.get("Block");
        alternateIntake = map.servo.get("alternate intake");

        blockDetection.setMode(DigitalChannel.Mode.INPUT);
    }

    @Override
    public void update() {
        if (down) {
            alternateIntake.setPosition(0);
        } else {
            alternateIntake.setPosition(0.5);
        }
        if (blockDetection.getState()) {
            state = true;
        }
        if (!blockDetection.getState()) {
            state = false;
        }

    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
