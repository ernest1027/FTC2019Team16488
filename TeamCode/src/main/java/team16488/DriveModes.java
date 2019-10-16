package team16488;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class DriveModes extends OpMode {
    MapMotors motors = new MapMotors();
    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor RearRightMotor;
    DcMotor RearLeftMotor;
/**
    public void driveMode1() {
        motors.mapHardware(FrontLeftMotor, FrontRightMotor,RearRightMotor,RearLeftMotor);

        double forwardAndBackValues = -gamepad1.right_stick_y;
        double rotationValue = -gamepad1.left_stick_x;
        double strafeValue = -gamepad1.right_stick_x;

        driveStriaitAndMakeWideTurns(forwardAndBackValues, rotationValue);
        strafe(strafeValue);
    }
*/
    public void init(){}
    public void loop(){}
}
