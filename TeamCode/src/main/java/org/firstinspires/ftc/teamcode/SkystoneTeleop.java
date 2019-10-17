package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Drivetrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsytems.PullerServos;

@TeleOp
public class SkystoneTeleop extends OpMode {

    MecanumDrive mecanumDrive = new MecanumDrive();
    PullerServos pullerServos = new PullerServos();
    public void init() {
        mecanumDrive.init();
        pullerServos.init();
    }
    public void loop(){
        mecanumDrive.loop();
        pullerServos.loop();

    }
}
