/**
----------------------------------------------------------------------------------------------------
Name: MecanumDrive
Purpose: This program creates an arcade drive(Mecanum) and adds servo control these servos control
 the robots platform locking mechanism


Author: Baghbanbashi, Parham
        email: parhambagh@gmail.com

Date: 10/3/2019
Version: 2.0.0
----------------------------------------------------------------------------------------------------
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class MecanumDrive extends OpMode {

    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor RearRightMotor;
    DcMotor RearLeftMotor;
    Servo RightPullerServo;
    Servo LeftPullerServo;
    CRServo armservo1;
    public String Drivemode = "mode1";

    /**
     * runs on initilaization
     */

    public void setLeftSidePower(double power) {
            FrontLeftMotor.setPower(power);
            RearLeftMotor.setPower(power);
    }

    public void setRightSidePower(double power) {
        FrontRightMotor.setPower(power);
        RearRightMotor.setPower(power);
    }

    public void setFrontRightToBottomLeftDiagonalPower(double power){
        FrontRightMotor.setPower(power);
        RearLeftMotor.setPower(power);
    }

    public void setFrontLeftToBottomRightDiagonalPower(double power){
        FrontLeftMotor.setPower(power);
        RearRightMotor.setPower(power);
    }

    public void arcadeDrive(double forwardAndBackValues, double rotationValue){
        double leftPower = Range.clip(forwardAndBackValues + rotationValue, -1.0, 1.0) ;
        double rightPower   = Range.clip(forwardAndBackValues - rotationValue, -1.0, 1.0) ;
        setLeftSidePower(leftPower);
        setRightSidePower(rightPower);
    }



    public void strafe(double strafeValue){
        setFrontRightToBottomLeftDiagonalPower(-strafeValue);
        setFrontLeftToBottomRightDiagonalPower(strafeValue);

    }


    public void mapHardware(){
        /**
         * define motors
         */
        FrontLeftMotor = hardwareMap.dcMotor.get("m4");
        FrontRightMotor = hardwareMap.dcMotor.get("m3");
        RearRightMotor = hardwareMap.dcMotor.get("m2");
        RearLeftMotor = hardwareMap.dcMotor.get("m1");

        /**
         * define servos
         */
        RightPullerServo = hardwareMap.servo.get("s1");
        LeftPullerServo = hardwareMap.servo.get("s2");
        armservo1 = hardwareMap.crservo.get("arm1");

        /**
         * print out status
         */
        telemetry.addData("Status:", "Initialized");

        /**
         * Reverse Approprate Motors
         */
        FrontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        RearLeftMotor.setDirection(DcMotor.Direction.REVERSE);

    }


    /**
     * This drive mode is with wide turns and strafing using the arcade drive formula
     */
    public void arcadeDriveAndStrafe(){
        double forwardAndBackValues = -gamepad1.right_stick_y;
        //this is what your looking for
        double rotationValue = gamepad1.right_stick_x;
        double strafeValue = gamepad1.left_stick_x;

        arcadeDrive(forwardAndBackValues, rotationValue);
        strafe(strafeValue);

    }


    /**
     * This Drivemode allows the drivers to select diagonal strafeing as a feature with wide turns
     */
    public void diagonalStrafe(){
        double y = -gamepad1.right_stick_y;
        double rotationValue = -gamepad1.left_stick_x;
        double x = -gamepad1.right_stick_x;

        if(y > 0 && x > 0){
            setFrontLeftToBottomRightDiagonalPower(y);
        }
        else if(y < 0 && x < 0){
            setFrontLeftToBottomRightDiagonalPower(y);
        }
        else if(x < 0 && y > 0){
            setFrontRightToBottomLeftDiagonalPower(y);
        }
        else if(x > 0 && y < 0){
            setFrontRightToBottomLeftDiagonalPower(y);
        }


    }

    /**
     * This mode is with tight turns and diagonal strafing without the arcade formula
     */


    /**
     * mode selector(drivemode)
     */
     /*public void setDrivemode(){
         if(gamepad1.y == true){
            Drivemode = "arcadeDrive";
            telemetry.addData("Drive Mode",Drivemode);
         }
         if(gamepad1.b == true) {
             Drivemode = "diagonalStrafe";
             telemetry.addData("Drive Mode", Drivemode);
         }
         else{
             telemetry.addData("Please select a Drive mode"," ");
         }
     }*/

     public void setpullerServos(){
         if(gamepad1.right_bumper == true)
         {
             RightPullerServo.setPosition(1.0);
             LeftPullerServo.setPosition(-0.5);
         }

         /** set servos positon to 0.5 if button a is presed
          */
         if(gamepad1.right_trigger > 0){
             RightPullerServo.setPosition(-0.5);
             LeftPullerServo.setPosition(1.0);
         }
     }

    /**
     * Initates drive mode
     */
    /*public void activateDrivemode(){
         if(Drivemode == "arcadeDriveAndStrafe"){
             arcadeDriveAndStrafe();
         }
         else if(Drivemode == "diagonalStrafe"){
             diagonalStrafe();
         }

         else{
             telemetry.addData("Please select a Drive mode"," ");
         }
     }*/

    /**
     * print out satus
     */
    public void printStatus(){
         telemetry.addData("Game pad 1 stick status:","-------------------------");
         telemetry.addData("Left stick x", gamepad1.left_stick_x);
         telemetry.addData("Right stick y", gamepad1.right_stick_y);
         telemetry.addData("Right stick x", gamepad1.right_stick_x);
         telemetry.addData("Drive Mode","----------------------------------------");
         telemetry.addData("Drive Mode",Drivemode);
     }

    public void init(){

        mapHardware();

        telemetry.addData("Robot Status", "Initalized");
        telemetry.addData("Drive Mode", Drivemode);

    }

    public void subsystems(){


    }
    /**
     * runs repetedly after person hits play
     */
    public void loop() {
        telemetry.addData("Robot Status:","Running");
        printStatus();
        if(gamepad1.left_trigger>0)
        {
            diagonalStrafe();

        }
        else
        {
            arcadeDriveAndStrafe();
        }
        setpullerServos();

    }

    /**
     * myay
     * be
     * used
     * for
     * an
     * api
     */








}
