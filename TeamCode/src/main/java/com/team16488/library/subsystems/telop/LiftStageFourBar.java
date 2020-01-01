package com.team16488.library.subsystems.telop;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team16488.library.subsystems.Subsystem;


public class LiftStageFourBar extends Subsystem {

    private static final String[] FOUR_BAR_NAMES = {"Left", "Right"};
    private double[] powers;
    private CRServo[] crServos;
    private DigitalChannel extended;
    private boolean extend;

    public LiftStageFourBar(HardwareMap map) {
        powers = new double[2];
        crServos = new CRServo[2];

        for (int i = 0; i < 2; i++) {
            CRServo crServo = map.get(CRServo.class, FOUR_BAR_NAMES[i]);
            crServos[i] = crServo;
        }
        extended = map.digitalChannel.get("Extended");

    }

    private void setPowers(double power) {
        powers[0] = power;
        powers[1] = power;
    }

    private void setCrServos(double leftPower, double rightPower) {
        crServos[0].setPower(leftPower);
        crServos[1].setPower(rightPower);
    }

    @Override
    public void update() {
        if (extend) {
            setPowers(0.85);
            if (extended.getState()) {
                setPowers(0.1);
            }

        }
        if (!extend) {
            setPowers(-0.85);
        }

        setCrServos(powers[0], powers[1]);


    }

    public void setExtend(boolean extend) {
        this.extend = extend;

    }
}
