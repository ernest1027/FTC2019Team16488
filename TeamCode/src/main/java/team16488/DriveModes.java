package team16488;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@Disabled
public class DriveModes extends OpMode {

    MotorControl motorControl = new MotorControl();

    public void arcadeDrive(double forwardAndBackValues, double rotationValue,DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){
        double leftPower = Range.clip(forwardAndBackValues + rotationValue, -1.0, 1.0) ;
        double rightPower   = Range.clip(forwardAndBackValues - rotationValue, -1.0, 1.0) ;
        motorControl.setLeftSidePower(leftPower,FrontLeftMotor,RearLeftMotor);
        motorControl.setRightSidePower(rightPower,FrontRightMotor,RearRightMotor);
    }



    public void strafe(double strafeValue,DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){
        motorControl.setFrontLeftToBottomRightDiagonalPower(strafeValue,FrontLeftMotor,RearRightMotor);
        motorControl.setFrontRightToBottomLeftDiagonalPower(-strafeValue,FrontRightMotor,RearLeftMotor);

    }






     /**
     * This Drivemode allows the drivers to select diagonal strafeing as a feature with wide turns
     */

    public void diagonalStrafe(double x, double y, double rotationValue, DcMotor FrontLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor, DcMotor RearLeftMotor){


        if(y > 0 && x > 0){
            motorControl.setFrontLeftToBottomRightDiagonalPower(y,FrontLeftMotor,RearRightMotor);
        }
        else if(y < 0 && x < 0){
            motorControl.setFrontLeftToBottomRightDiagonalPower(y,FrontLeftMotor,RearRightMotor);
        }
        else if(x < 0 && y > 0){
            motorControl.setFrontRightToBottomLeftDiagonalPower(y,FrontRightMotor,RearLeftMotor);
        }
        else if(x > 0 && y < 0){
            motorControl.setFrontRightToBottomLeftDiagonalPower(y,FrontRightMotor,RearLeftMotor);
        }


    }


    public void init(){}
    public void loop(){}
}
