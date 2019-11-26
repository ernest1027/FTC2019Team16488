package com.team16488.skystone;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team16488.library.subsystems.Arm;
import com.team16488.library.subsystems.ClawHead;
import com.team16488.library.subsystems.Intake;
import com.team16488.library.subsystems.Lift;
import com.team16488.library.subsystems.MecanumDrive;
import com.team16488.library.subsystems.Puller;
import com.team16488.library.subsystems.Subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import com.qualcomm.robotcore.util.ThreadPool;

public class Robot {
    private ExecutorService subsystemUpdateExecutor;
    private boolean started;

    public MecanumDrive drive;

    public Arm arm;

    public ClawHead clawHead;

    public Puller puller;

    public Intake intake;

    public Lift lift;

    private List<Subsystem> subsystems;

    private Telemetry telemetry;

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
            clawHead = new ClawHead(opMode.hardwareMap);
            subsystems.add(clawHead);
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
        }catch(IllegalArgumentException e){}





        subsystemUpdateExecutor = ThreadPool.newSingleThreadExecutor("subsystem update");

    }


    public void start(){
        if(!started){
            subsystemUpdateExecutor.submit(subsystemUpdateRunnable);
            started = true;
        }
    }


    public void stop(){
        if(subsystemUpdateExecutor != null){
            subsystemUpdateExecutor.shutdownNow();
            subsystemUpdateExecutor = null;
        }
    }









}
