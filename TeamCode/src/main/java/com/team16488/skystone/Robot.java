package com.team16488.skystone;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ThreadPool;
import com.team16488.library.subsystems.AlternateIntake;
import com.team16488.library.subsystems.Arm;
import com.team16488.library.subsystems.ArmHead;
import com.team16488.library.subsystems.ColourSensor;
import com.team16488.library.subsystems.Intake;
import com.team16488.library.subsystems.Lift;
import com.team16488.library.subsystems.MecanumDrive;
import com.team16488.library.subsystems.Puller;
import com.team16488.library.subsystems.Subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * This class is what does all of the
 * requred threading for the vairous subsystems to
 * run in parrallel
 * <p>
 * In this class we call all our subsystems and
 * give each subsystem's update method a thread that runs via the runnable
 * this will be then submitted in the executor service.
 * </p>
 *
 * @author Parham Baghbanbashi
 * <p>github: https://github.com/StrRamsRobotics/SkyStone/tree/Parham-Baghbanbashi</p>
 */
public class Robot {
    /** MecanumDrive object*/
    public MecanumDrive drive;
    /** Arm subsystem object*/
    public Arm arm;
    /** ArmHead subsystem object*/
    public ArmHead armHead;
    /** Puller subsystem object*/
    public Puller puller;
    /** Intake subsystem object */
    public Intake intake;
    /** Lift subsystem object*/
    public Lift lift;

    public ColourSensor colourSensor;

    public AlternateIntake alternateIntake;

    /** The executor service that is responsible for the submitting of tasks (subsystem.update()) */
    private ExecutorService subsystemUpdateExecutor;
    /**
     * Starts the robot/ Checks if robot is started
     */
    private boolean started;
    /**
     * List of all the classes that extend the Subsystem Class(MecanumDrive, Arm, etc)*
     *
     * <p>See: {@link Subsystem}</p>
     */
    private List<Subsystem> subsystems;
    /** telemtry for the OpMode*/
    private Telemetry telemetry;
    /** Runnable responsible for creating threads for each subsystem in the list of subsystems(any class that extends Subsystem)*/
    private Runnable subsystemUpdateRunnable = () -> {

        while (!Thread.currentThread().isInterrupted()) {

            try {

                for (Subsystem subsystem : subsystems) {

                    if (subsystem == null) continue;


                    try {

                        subsystem.update();

                    } catch (Throwable t) {

                        this.telemetry.addData("Exception running thread 1", "");
                        this.telemetry.update();

                    }

                }

            } catch (Throwable t) {

                this.telemetry.addData("Exception running thread 2", "");
                this.telemetry.update();
            }

        }

    };

    /**
     * The Class constructor, This is what calls all our subsystems and allows us to access them and their methods inside our
     * ChassisControl and SubsystemControl classes
     *
     * Also adds a new single thread executor.
     *
     * @param opMode The OpMode that the Class is used in.
     * @param telemetry The telemetry of the OpMode the Class is used in
     */
    public Robot(OpMode opMode, Telemetry telemetry){
        this.telemetry = telemetry;

        subsystems = new ArrayList<>();

        try{
            drive = new MecanumDrive(opMode.hardwareMap);
            subsystems.add(drive);
        } catch (IllegalArgumentException e){

        }

        try{
            arm = new Arm(opMode.hardwareMap);
            subsystems.add(arm);
        }catch(IllegalArgumentException e){

        }


        try{
            armHead = new ArmHead(opMode.hardwareMap);
            subsystems.add(armHead);
        }catch(IllegalArgumentException e){

        }

        try{
            intake = new Intake(opMode.hardwareMap);
            subsystems.add(intake);
        }catch(IllegalArgumentException e){}


        try{
            lift = new Lift(opMode.hardwareMap);
            subsystems.add(lift);
        }catch(IllegalArgumentException e){}

        try{
            puller = new Puller(opMode.hardwareMap);
            subsystems.add(puller);
        }catch(IllegalArgumentException e){

        }
        try {
            alternateIntake = new AlternateIntake(opMode.hardwareMap);
            subsystems.add(alternateIntake);
        } catch (IllegalArgumentException e) {
        }

        try {
            colourSensor = new ColourSensor((opMode.hardwareMap));
            subsystems.add(colourSensor);
        } catch (IllegalArgumentException e) {

        }

        subsystemUpdateExecutor = ThreadPool.newSingleThreadExecutor("subsystem update");

    }

    /**
     * This Method Starts the Robot
     */
    public void start(){
        if(!started){
            subsystemUpdateExecutor.submit(subsystemUpdateRunnable);
            started = true;
        }
    }

    /**
     * This Method Stops the Robot
     */
    public void stop(){
        if(subsystemUpdateExecutor != null){
            subsystemUpdateExecutor.shutdownNow();
            subsystemUpdateExecutor = null;
        }
    }


}
