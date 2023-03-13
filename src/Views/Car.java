package Views;

import java.awt.*;
import java.security.SecureRandom;

public class Car {

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
    private Color colorSelected;
    private final int XPOSITION_OF_CAR = 45;
    private final int CAR_WIDTH = 60;
    private final int CAR_HEIGHT = 60;
    private boolean isNorth;

    public Car(){
        SecureRandom generator = new SecureRandom();
        int color = generator.nextInt(7);
        colorSelected = colors[color];
        currentYPositionOfCar = -60;

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

}
