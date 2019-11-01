package com.team16488.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.skystone.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TestTeleopForNewStructure", group = "teleop")
public class TeleOp extends OpMode {
    private Robot robot;

    public void init(){
        robot = new Robot(this, telemetry);
        robot.start();
    }


    public void start(){}


    public void loop(){
        /*
        robot.drive.setVelocity(-gamepad1.left_stick_x, -gamepad1.left_stick_y,
                -gamepad1.right_stick_x);

        if(gamepad1.right_bumper == true){
            robot.pullerServos.setOpen(true);
        }
        if(gamepad1.right_trigger != 0){
            robot.pullerServos.setOpen(false);
        }

         */
        if(gamepad1.left_bumper == true){
            robot.clawHeadMovement.setOpen(true);
        }
        if(gamepad1.left_trigger != 0){
            robot.clawHeadMovement.setOpen(false);
        }

    }

    @Override
    public void stop() {
        robot.clawHeadMovement.setOpen(true);
    }
}
