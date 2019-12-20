package com.team16488.library.subsystems.telop;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team16488.library.subsystems.Subsystem;

public class IntakeRaise extends Subsystem {
    private static final String[] YEET_NAME = {"LeftYeet", "RightYeet"};
    private boolean yeet;
    private CRServo[] raise;
    private double[] power;


    public IntakeRaise(HardwareMap map) {
        power = new double[2];
        raise = new CRServo[2];

        for (int i = 0; i < 4; i++) {
            CRServo crServo = map.get(CRServo.class, YEET_NAME[i]);
            raise[i] = crServo;
        }
    }

    private void setPowers(double power) {
        this.power[0] = power;
        this.power[1] = power;
    }

    private void setCrServos(double leftPower, double rightPower) {
        raise[0].setPower(leftPower);
        raise[1].setPower(rightPower);
    }

    @Override
    public void update() {
        if (yeet) {
            setPowers(0.85);
        }
        if (!yeet) {
            setPowers(-0.85);
        }
        setCrServos(power[0], power[1]);
    }

    public void setYeet(boolean yeet) {
        this.yeet = yeet;
    }
}
