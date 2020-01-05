package com.team16488.control.general;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.team16488.automated_proccess.StackBlocks;
import com.team16488.library.subsystems.MecanumDrive2;
import com.team16488.skystone.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This is the Class that Controls the Chassis of the robot
 * this class takes the gamepad inputs and transforms them
 * into movement using the robot class which uses the
 * various subsystem classes.
 *
 *
 * @author Parham Baghbanbashi
 * <p>See: {@link com.team16488.skystone.Robot}</p>
 * <p>
 *    github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi
 * </p>
 *
 */
public class ChassisControl {

    private Robot robot;

    private OpMode opMode;

    private Telemetry telemetry;

    private Gamepad chassisControl, subsystemChassisControl;

    /**
     * sets the intake on reverse mode
     */
    private boolean reverse = false;
    /**
     * this turns the intake on
     */
    private boolean On = false;

    private boolean yeet = false;
    /**
     * This is the constructor for a chassis control this allows the opmode to run the code in the class
     * @param ropMode The opmode that this class is being used in
     * @param oprobot   The robot object in the acctual OpMode that it
     *                  is being used ins.
     */
    public ChassisControl(OpMode ropMode, Robot oprobot) {
        robot = oprobot;
        this.opMode = ropMode;
        this.telemetry = opMode.telemetry;
        this.chassisControl = opMode.gamepad1;
        this.subsystemChassisControl = opMode.gamepad2;
    }

    public void alternateIntakeControl() {
        if (chassisControl.dpad_up) {
            robot.alternateIntake.setPos(-0.5);
        }
        if (chassisControl.start) {
            robot.alternateIntake.ON = true;
        }
        if (chassisControl.back) {
            robot.alternateIntake.setPos(1.00);
        }
        if (chassisControl.b) {
            robot.alternateIntake.setDown(true);
        }
        if (chassisControl.y) {
            robot.alternateIntake.setDown(false);
        }
        if (robot.alternateIntake.state) {
            telemetry.addData("Alternate intake state", "No Block");

        }
        if (!robot.alternateIntake.state) {
            telemetry.addData("Alternate intake state", "You Have Block");

        }
    }

    public void driveControl() {

        robot.drive2.setVelocity(chassisControl.right_stick_x, -chassisControl.left_stick_y, chassisControl.left_stick_x);

        if (chassisControl.right_stick_x == 0 && chassisControl.left_stick_y == 0 && chassisControl.left_stick_x == 0) {
            double slowmode = 0.5;
            robot.drive2.setVelocity(-subsystemChassisControl.left_stick_x * slowmode, -subsystemChassisControl.right_stick_y * slowmode, -subsystemChassisControl.right_stick_x * slowmode);
        }

    }

    public void intakeControl() {
        if (chassisControl.x) {
            On = false;
            reverse = false;

        }
        if (chassisControl.right_bumper) {
            On = true;

        }
        if (chassisControl.right_trigger != 0) {
            On = true;
            reverse = false;
        }
        if (chassisControl.right_bumper) {
            reverse = true;
        }

        if (chassisControl.dpad_up) {
            yeet = true;
        }
        if (chassisControl.dpad_down) {
            yeet = false;
        }
        robot.intakeRaise.setYeet(yeet);


        if (On) {
            robot.intake.setOn(true);
            telemetry.addData("state", "Intake on");
        }
        if (!On) {
            robot.intake.setOn(false);
            telemetry.addData("state", "Intake off");
        }

        robot.intake.setReverse(reverse);
    }

    public void pullerControl() {

        if (chassisControl.left_trigger != 0) {
            robot.puller.setDown(false);
        }

        if (chassisControl.left_bumper) {
            robot.puller.setDown(true);
        }

    }

    public void printState() {
        telemetry.addData("Drivetrian State", "ON");
        telemetry.addData("----------------------------------------------", " ");
        telemetry.addData("Motors","");
        for (int i = 0; i < 4; i++) {
            telemetry.addData("Motor" + String.valueOf(i), robot.drive2.motors[i].getPower());
        }
        telemetry.addData("Servos", "");
        telemetry.addData("Intake Raise l", robot.intakeRaise.leftyeet.getPower());
        telemetry.addData("Intake Raise R", robot.intakeRaise.rightyeet.getPower());
        telemetry.addData("Pullers", robot.puller.Left.getPosition());


    }
}
