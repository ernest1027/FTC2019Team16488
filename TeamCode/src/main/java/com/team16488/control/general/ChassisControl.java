package com.team16488.control.general;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
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

    private Gamepad chassisControl;

    private boolean reverse = false;

    private boolean On = false;


    private boolean pressedA;
    private boolean pressedB;
    private boolean arm;
    private boolean lock;

    private boolean pressedRT = false;
    private boolean pressedRB = false;
    public ChassisControl(OpMode ropMode, Robot oprobot) {
        robot = oprobot;
        this.opMode = ropMode;
        this.telemetry = opMode.telemetry;
        this.chassisControl = opMode.gamepad1;
    }
/*
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
*/

    public void joysticks(){
        robot.drive2.setVelocity(chassisControl.right_stick_x, -chassisControl.left_stick_y, chassisControl.left_stick_x);
    }

    public void buttons(){
        if(chassisControl.dpad_up){
            robot.stop();
        }
        if(chassisControl.dpad_down){
            robot.start();
        }
        if(chassisControl.start){
            robot.alternateIntake.ON = true;
        }

        if(!pressedB){
            if(chassisControl.b){
                arm = !arm;
                pressedB = true;
                for(int i = 0; i<600; i++){
                    telemetry.addData("delay", i);
                }
            }
        }
        if(chassisControl.b){
            pressedB = false;
            for(int i = 0; i<600; i++){
                telemetry.addData("delay", i);
            }
        }
        robot.alternateIntake.setDown(arm);

        if(!pressedA){
            lock = !lock;
            pressedA = true;
            for(int i = 0; i<600; i++){
                telemetry.addData("delay", i);
            }
        }
        if(chassisControl.a){
            pressedA = false;
            for(int i = 0; i<600; i++){
                telemetry.addData("delay", i);
            }
        }
        robot.alternateIntake.setLock(lock);

        if(chassisControl.x){
            robot.puller.setDown(true);
        }

        if(chassisControl.y){
            robot.puller.setDown(false);
        }

        if(chassisControl.dpad_up){
            robot.lIftStageOne.setPower(0);
            robot.intake.setOn(false);
            robot.liftStageFourBar.On = false;
            robot.alternateIntake.ON = false;
            robot.intakeRaise.On = false;
            robot.claw.setOpen(false);
            robot.stop();
            opMode.stop();
        }

    }

    public void leftTriggers(){
        if(chassisControl.left_trigger != 0){
            robot.intakeRaise.On = true;
            robot.intakeRaise.setYeet(true);
        }
        else if(chassisControl.left_bumper){
            robot.intakeRaise.On = true;
            robot.intakeRaise.setYeet(false);
        }else{
            robot.intakeRaise.On = false;
        }

    }

    public void rightTrigger(){
        if(!pressedRT) {
            if (chassisControl.right_trigger > 0)
            {
                On = !On;
                pressedRT = true;
            }


        }
        if(chassisControl.right_trigger == 0)
        {
            pressedRT = false;
        }
        if(!pressedRB)
        {
            if(chassisControl.right_bumper == true)
            {
                reverse = !reverse;
                pressedRB = true;
            }
        }
        if(chassisControl.right_bumper==false)
        {
            pressedRB = false;
        }
        robot.intake.setReverse(reverse);
        robot.intake.setOn(On);

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
        telemetry.addData("intake state", robot.intake.isOn);
        telemetry.addData("alternate intake raise", robot.alternateIntake.alternateIntakeRaise.getPosition());
        telemetry.addData("alternate intake Close", robot.alternateIntake.alternateIntakeClose.getPosition());
        telemetry.addData("puller posR",robot.puller.Right.getPosition() );
        telemetry.addData("puller posL",robot.puller.Left.getPosition() );


    }
}
