package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Drivetrain.TeleopMecanumDrive;
import org.firstinspires.ftc.teamcode.subsytems.PullerServos;

@TeleOp(name="Skystone 2019 Teleop", group="Iterative Opmode")
public class SkystoneTeleop extends OpMode {

    TeleopMecanumDrive mecanumDrive = new TeleopMecanumDrive();
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
