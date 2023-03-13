package Views;

import javax.swing.*;
import java.awt.*;


public class HighWay extends JPanel {

    private double x;
    private double y;
    private Dimension dimension;
    private Car currentCarOnTheHighWay;
    private final int startXPositionOfHighWay;
    private int counterOfCarsFromNorthToSouth = 0;
    private int counterOfCarsFromSouthToNorth = 0;
    private boolean isThereACar;

    public HighWay(MyFrame myFrame){
        dimension =  myFrame.getSize();
        x = dimension.getWidth();
        y = dimension.getHeight();
        setSize(150,600);
        setLocation(225,0);
        setLayout(null);
        startXPositionOfHighWay = (int)x/4;
        setBackground(Color.gray);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(startXPositionOfHighWay, 0,(int)x/4,(int)y);

        drawLinesOfHighWay(g);
        if (isThereACar){
            g.setColor(currentCarOnTheHighWay.getColorSelected());
            g.fillRect(currentCarOnTheHighWay.getXPOSITION_OF_CAR(),currentCarOnTheHighWay.getCurrentYPositionOfCar(),
                    currentCarOnTheHighWay.getCAR_WIDTH(),currentCarOnTheHighWay.getCAR_HEIGHT());
        }else{
            addCar(g);
            isThereACar = true;
        }

    }

    private void drawLinesOfHighWay(Graphics g){
        for (int i = 0; i <= 10; i++) {
            g.setColor(Color.YELLOW);
            int ySpaceBetweenEachLine = 20;
            int HEIGHT = 40;
            int yPositionOfNextLine = (HEIGHT + ySpaceBetweenEachLine) * i;
            int xPositionOfLines = 65;
            int WIDTH = 20;
            g.fillRect(xPositionOfLines, yPositionOfNextLine, WIDTH, HEIGHT);
        }
    }
    public void addCarsToNorth(int numberOfCarsToAdd){
        counterOfCarsFromNorthToSouth += numberOfCarsToAdd;
    }

    public void addCarsToSouth(int numberOfCarsToAdd){
        counterOfCarsFromSouthToNorth += numberOfCarsToAdd;
    }

    public void addCar(Graphics g){
        currentCarOnTheHighWay= new Car();
        g.setColor(currentCarOnTheHighWay.getColorSelected());
        g.fillRect(currentCarOnTheHighWay.getXPOSITION_OF_CAR(),currentCarOnTheHighWay.getCurrentYPositionOfCar(),
                currentCarOnTheHighWay.getCAR_WIDTH(),currentCarOnTheHighWay.getCAR_HEIGHT());
    }

    public Car getCurrentCarOnTheHighWay() {
        return currentCarOnTheHighWay;
    }
}
