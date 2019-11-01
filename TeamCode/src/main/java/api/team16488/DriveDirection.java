package api.team16488;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class DriveDirection {
    MotorControl motorControl = new MotorControl();
/**
    public void driveStriaitAndMakeWideTurns(double forwardAndBackValues, double rotationValue){
        double leftPower = Range.clip(forwardAndBackValues + rotationValue, -1.0, 1.0) ;
        double rightPower   = Range.clip(forwardAndBackValues - rotationValue, -1.0, 1.0) ;
        setLeftSidePower(leftPower);
        setRightSidePower(rightPower);
    }

    public void makeTightTurns(double forwardAndBackValues, double rotationValue){
        double rotationLeftPower = rotationValue;
        double rotationRightPower = -rotationValue;
        setRightSidePower(rotationRightPower);
        setLeftSidePower(rotationLeftPower);
    }
*/
    public void driveStraight(double power, DcMotor FrontLeftMotor, DcMotor RearLeftMotor, DcMotor FrontRightMotor, DcMotor RearRightMotor){
        motorControl.setLeftSidePower(power, FrontLeftMotor, RearLeftMotor);
        motorControl.setRightSidePower(power, FrontRightMotor, RearRightMotor);
    }

    public void strafe(double strafeValue, DcMotor FrontLeftMotor, DcMotor RearRightMotor){
        motorControl.setFrontLeftToBottomRightDiagonalPower(-strafeValue, FrontLeftMotor, RearRightMotor);
        motorControl.setFrontLeftToBottomRightDiagonalPower(strafeValue, FrontLeftMotor, RearRightMotor);

    }

    public void diagonalRightstrafe(double power, DcMotor FrontLeftMotor , DcMotor RearRightMotor) {
        motorControl.setFrontLeftToBottomRightDiagonalPower(power, FrontLeftMotor, RearRightMotor);
    }

    public void diagonalLeftstrafe(double power, DcMotor FrontLeftMotor , DcMotor RearRightMotor){
        motorControl.setFrontLeftToBottomRightDiagonalPower(power, FrontLeftMotor, RearRightMotor);
    }

}
