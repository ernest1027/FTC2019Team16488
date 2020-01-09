/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.team16488.opmodes.auto;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team16488.skystone.Robot;

@Autonomous(name = "RedSideOneBlock", group = "Linear Opmode")
public class RedSideOneBlock extends LinearOpMode {


    private Robot robot;
    private ElapsedTime runtime = new ElapsedTime();
    public enum AutonomousStates
    {
        STARTED,
        DRIVEN_TO_BLOCKS,
        ALIGNED,
        PICKED_UP_BLOCK,
        DRIVEN_TO_BRIDGE,
        DROPPED,
        PARKED,
    }


    public RedSideOneBlock.AutonomousStates DrivingToBlocks()
    {
        robot.drive2.setVelocity(-0.7, 0, 0);
        sleep(500);
        robot.drive2.setVelocity(0,0,0);
        return RedSideOneBlock.AutonomousStates.DRIVEN_TO_BLOCKS;
    }
    public RedSideOneBlock.AutonomousStates Aligning()
    {
        while(true)
        {
            if(robot.vision.isTargetVisible())
            {
                if (89 <= robot.vision.angleFromPhone && robot.vision.angleFromPhone <= 91) {
                    break;

                }
                if (robot.vision.angleFromPhone > 91) {
                    robot.drive2.setVelocity(0, -0.4, 0);
                }
                if (robot.vision.angleFromPhone < 89) {
                    robot.drive2.setVelocity(0, 0.4, 0);
                }
            }
            else
            {
                robot.drive2.setVelocity(0, -0.3, 0);
            }
        }
        return AutonomousStates.ALIGNED;
    }
    public RedSideOneBlock.AutonomousStates PickingUpBlock()
    {
        robot.alternateIntake.setDown(true);
        sleep(500);
        return AutonomousStates.PICKED_UP_BLOCK;
    }
    public RedSideOneBlock.AutonomousStates DrivingToBridge()
    {
        robot.drive2.setVelocity(0.7, 0, 0);
        sleep(500);
        robot.drive2.setVelocity(0,0.5,0);
        sleep(2000);
        robot.drive2.setVelocity(0,0,0);
        return AutonomousStates.DRIVEN_TO_BRIDGE;
    }
    public RedSideOneBlock.AutonomousStates Dropping()
    {
        robot.alternateIntake.setDown(false);
        sleep(500);
        return AutonomousStates.DROPPED;
    }
    public RedSideOneBlock.AutonomousStates Parking()
    {
        while(true){
            robot.drive2.setVelocity(0, -0.2, 0);
            if(robot.colourSensor.getRed()>500) break;
        }

        robot.drive2.setVelocity(0, 0, 0);
        return AutonomousStates.PARKED;

    }
    @Override
    public void runOpMode() {
        robot = new Robot(this, telemetry);

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        double b = 0;
        double c = 0;
        double d = 0;
        boolean init = false;
        /**  Let all the trackable listeners know where the phone is.  */
//

        waitForStart();

        AutonomousStates currentState = AutonomousStates.STARTED;
        while (opModeIsActive()&& (currentState != AutonomousStates.PARKED)) {
            if (!init) {

                robot.start();
                runtime.reset();
                robot.vision.targetsSkyStone.activate();
                init = true;
            }




            if (robot.vision.isTargetVisible()) {

                telemetry.addData("Relative Angle", robot.vision.getRelativeAngle());
                telemetry.addData("Angle From Phone", robot.vision.angleFromPhone);
                telemetry.addData("Total distance", robot.vision.getTotalDistance());
                telemetry.addData("Alpha", robot.colourSensor.getAlpha());
                telemetry.addData("Red  ", robot.colourSensor.getRed());
                telemetry.addData("Green",robot.colourSensor.getGreen());
                telemetry.addData("Blue ", robot.colourSensor.getBlue());
                telemetry.addData("Hue", robot.colourSensor.getHue());

                relativeLayout.post(new Runnable() {
                    public void run() {
                        relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, robot.colourSensor.hsvValues));
                    }
                });
                telemetry.update();


            } else {
                telemetry.addData("Visible Target", "none");
                telemetry.addData("Alpha", robot.colourSensor.getAlpha());
                telemetry.addData("Red  ", robot.colourSensor.getRed());
                telemetry.addData("Green",robot.colourSensor.getGreen());
                telemetry.addData("Blue ", robot.colourSensor.getBlue());
                telemetry.addData("Hue", robot.colourSensor.getHue());


            }
            switch (currentState) {
                case STARTED:
                    currentState = DrivingToBlocks();
                    break;
                case DRIVEN_TO_BLOCKS:
                    currentState = Aligning();
                    break;
                case ALIGNED:
                    currentState = PickingUpBlock();
                    break;
                case PICKED_UP_BLOCK:
                    currentState = DrivingToBridge();
                    break;
                case DRIVEN_TO_BRIDGE:
                    currentState = Dropping();
                    break;
                case DROPPED:
                    currentState = Parking();
                    break;
            }
            telemetry.update();
            robot.drive.update();

        }

        // Disable Tracking when we are done;
        robot.vision.targetsSkyStone.deactivate();
        robot.stop();
    }
}

