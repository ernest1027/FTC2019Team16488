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

    @Override
    public void start(){
    }

    @Override
    public void loop(){
/*        robot.drive.setVelocity(-gamepad1.left_stick_x, gamepad1.left_stick_y, -gamepad1.right_stick_x);

        if(gamepad1.right_bumper == true){
            robot.pullerServos.setOpen(true);
        }
        if(gamepad1.right_trigger != 0){
            robot.pullerServos.setOpen(false);
        }

        if(gamepad2.y){
            robot.intake.setOn(true);
        }
        else if(gamepad2.a){
            robot.intake.setOn(false);
        }
 */




        //swich gampad 1 to gamepad2
        if(gamepad1.dpad_right ){
            robot.clawHeadMovement.setOpen(false);
        }
        if(gamepad1.dpad_left){
            robot.clawHeadMovement.setOpen(true);
        }


    }

    @Override
    public void stop(){
        robot.stop();
    }

}
