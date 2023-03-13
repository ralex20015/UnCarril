package Views;

import Threads.CarPositionUpdater;

import java.awt.*;
import java.security.SecureRandom;

public class Car implements Runnable {

    //Hacer hilo para agregarCarros a la cola
    private final Color [] colors = {
            new Color(51,255,202),
            new Color(51,134,255),
            new Color(35,182,30),
            new Color(131,233,127),
            new Color(233,139,127),
            new Color(234,36,13),
            new Color(187,13,234)
    };
    private int currentYPositionOfCar;
    private HighWay highWay;
    private Color colorSelected;
    private final int XPOSITION_OF_CAR = 45;
    private final int CAR_WIDTH = 60;
    private final int CAR_HEIGHT = 60;
    private boolean isNorth;
    private boolean isTheCarDoneHerPath;

    public Car(HighWay highWay){
        this.highWay = highWay;
        SecureRandom generator = new SecureRandom();
        int color = generator.nextInt(7);
        colorSelected = colors[color];
        if (isNorth){
            currentYPositionOfCar = -60;
        }else{
            currentYPositionOfCar = 660;
        }
    }

    public void updatePosition(int newPosition){
        currentYPositionOfCar += newPosition;
    }

    public Color getColorSelected() {
        return colorSelected;
    }

    public boolean isNorth() {
        return isNorth;
    }

    public void setNorth(boolean north) {
        isNorth = north;
    }

    public int getCurrentYPositionOfCar() {
        return currentYPositionOfCar;
    }

    public int getXPOSITION_OF_CAR() {
        return XPOSITION_OF_CAR;
    }

    public int getCAR_WIDTH() {
        return CAR_WIDTH;
    }

    public int getCAR_HEIGHT() {
        return CAR_HEIGHT;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (isNorth){
            while (currentYPositionOfCar < 650){
                try {
                    Thread.sleep(100);
                    updatePosition(30);
                    System.out.println(currentYPositionOfCar);
                    highWay.repaint();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("No entra ni siquiera");
            }
        }else{
            while (currentYPositionOfCar > -60) {
                try {
                    Thread.sleep(100);
                    updatePosition(-30);
                    System.out.println(currentYPositionOfCar);
                    highWay.repaint();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        }
        isTheCarDoneHerPath = true;
    }

    public boolean isTheCarDoneHerPath() {
        return isTheCarDoneHerPath;
    }
}
