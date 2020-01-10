package com.team16488.control.general;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.library.subsystems.Claw;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This is the class that controls the vairous subsystems on the robot
 * Its job is to map each subsystem to gamepad2
 *
 * @author Parham Baghbanbashi
 * <p>See: {@link com.team16488.skystone.Robot}</p>
 * <p>
 * github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi
 * </p>
 */
public class SubsystemControl {
    private Robot robot;
    /**
     * Sets the Virtical rotation power
     *
     * @see Claw
     */
    private double vPower;
    /**
     * Sets the Horizontal rotation power
     *
     * @see Claw
     */
    private double hPower;
    /**
     * Sets the position of the claw
     *
     * @see Claw
     */
    private boolean clawOpen = true;

    private Gamepad subsystemDriver, chassisControl;


    private boolean shift;

    private double tickCount = 0;


    /**
     * Constructs the subsystemControl class in the main OpMode
     *
     * @param opMode  The OpMode the class is being used in
     * @param oprobot The robot object in the acctual OpMode that it
     *                is being used in.
     */
    public SubsystemControl(OpMode opMode, Robot oprobot) {
        robot = oprobot;
        subsystemDriver = opMode.gamepad2;
        this.chassisControl = opMode.gamepad1;

    }


    public void dpad() {
        if(chassisControl.dpad_up){
            robot.lIftStageOne.setPower(0);
            robot.intake.setOn(false);
            robot.liftStageFourBar.On = false;
            robot.alternateIntake.ON = false;
            robot.intakeRaise.On = false;
            robot.claw.setOpen(false);
            robot.stop();
        }

    }


    public void joySticks() {
        if (chassisControl.right_stick_x == 0 && chassisControl.left_stick_y == 0 && chassisControl.left_stick_x == 0) {
            double slowmode = 0.5;
            robot.drive2.setVelocity(-subsystemDriver.left_stick_x * slowmode, -subsystemDriver.left_stick_y * slowmode, -subsystemDriver.right_stick_x * slowmode);
        }

        if (robot.lIftStageOne.LiftLeft.getCurrentPosition() > 3210) {
            if (subsystemDriver.right_stick_y < 0) {
                robot.lIftStageOne.setPower(0);
            } else {
                robot.lIftStageOne.setPower(-subsystemDriver.right_stick_y);
            }
        }else if(robot.lIftStageOne.LiftLeft.getCurrentPosition() <= 0 ){
            if (subsystemDriver.right_stick_y > 0) {
                robot.lIftStageOne.setPower(0);
            }else{
                robot.lIftStageOne.setPower(-subsystemDriver.right_stick_y);
            }
        } else{
            robot.lIftStageOne.setPower(-subsystemDriver.right_stick_y);
        }

    }


    public void buttons() {
        if (subsystemDriver.x) {

        }
        if (subsystemDriver.y) {
        }
        if (subsystemDriver.a) {
        }
        if (subsystemDriver.b){}
    }


    public void rightTriggers() {
        if (subsystemDriver.right_bumper) {
            clawOpen = false;
        }
        if (subsystemDriver.right_trigger != 0) {
            clawOpen = true;
        }
        if (clawOpen) {
            robot.claw.setOpen(true);
        }
        if (!clawOpen) {
            robot.claw.setOpen(false);
        }


    }

    public void leftTriggers() {
        if (subsystemDriver.left_bumper) {
            robot.liftStageFourBar.On = true;
            robot.liftStageFourBar.setExtend(true);
        }
        else if (subsystemDriver.left_trigger != 0) {
            robot.liftStageFourBar.On = true;
            robot.liftStageFourBar.setExtend(false);
        }else{
            robot.liftStageFourBar.On = false;
        }
    }

    public void printState(Telemetry telemetry) {
        telemetry.addData("Subsystem Status", "ON");
        telemetry.addData("------------------------------", "----------------");
        telemetry.addData("Gamepad2 start", subsystemDriver.start);
        telemetry.addData("Gamepad2 right bumper", subsystemDriver.right_bumper);
        telemetry.addData("Gamepad 2 left bumper", subsystemDriver.left_bumper);
        telemetry.addData("Ticks", robot.lIftStageOne.LiftLeft.getCurrentPosition());
        telemetry.addData("Lift Velocity", robot.lIftStageOne.LiftLeft.getVelocity());


    }


}
