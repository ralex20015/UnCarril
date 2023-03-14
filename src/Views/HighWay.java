package Views;

import Threads.CarGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class HighWay extends JPanel {

    private double x;
    private double y;
    private Dimension dimension;
    private Car currentCarOnTheHighWay;
    private final int startXPositionOfHighWay;
    private int counterOfCarsFromNorthToSouth = 0;
    private int counterOfCarsFromSouthToNorth = 0;
    private boolean isThereACar;
    private boolean wasTheNorthPriority;
    private boolean isCarOfNorthHavingThePriority;
    private ArrayList<Car> listOfcarsInNorth;
    private ArrayList<Car> listOfCarsInSouth;
    private CarGenerator carGenerator;

    private int indexOfCurrentThread;



    public HighWay(MyFrame myFrame){
        listOfcarsInNorth = new ArrayList<>();
        listOfCarsInSouth = new ArrayList<>();
        dimension =  myFrame.getSize();
        x = dimension.getWidth();
        y = dimension.getHeight();
        setSize(150,600);
        setLocation(225,0);
        setLayout(null);
        startXPositionOfHighWay = (int)x/4;
        setBackground(Color.gray);

        carGenerator = new CarGenerator(this);
        Thread thread = new Thread(carGenerator,"Generador de Carros");
        thread.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(startXPositionOfHighWay, 0,(int)x/4,(int)y);

        drawLinesOfHighWay(g);

        //Ver si hay carros añadidos entonces llamar al metodo para

        if(isThereACar){
            g.setColor(currentCarOnTheHighWay.getColorSelected());
            g.fillRect(currentCarOnTheHighWay.getXPOSITION_OF_CAR(),currentCarOnTheHighWay.getCurrentYPositionOfCar(),
                    currentCarOnTheHighWay.getCAR_WIDTH(),currentCarOnTheHighWay.getCAR_HEIGHT());

            if(currentCarOnTheHighWay.isTheCarDoneHerPath()){
                isThereACar = false;
                wasTheNorthPriority = currentCarOnTheHighWay.isNorth();
                currentCarOnTheHighWay = null;
                //Remove the car from the ArrayList according to who was the priority

                if (wasTheNorthPriority){
                    listOfcarsInNorth.remove(indexOfCurrentThread);
                    counterOfCarsFromNorthToSouth--;
                    for (int i = 0; i< listOfcarsInNorth.size();i++) {
                        if (listOfcarsInNorth.get(i) != null) {
                            currentCarOnTheHighWay = listOfcarsInNorth.get(i);
                            indexOfCurrentThread = i;
                        }
                    }

                    System.out.println("Carros esperando en norte: "+counterOfCarsFromNorthToSouth);
                    if (listOfcarsInNorth.size() == 0){
                        for (int i = 0; i< listOfCarsInSouth.size();i++) {
                            if (listOfCarsInSouth.get(i) != null) {
                                currentCarOnTheHighWay = listOfCarsInSouth.get(i);
                                indexOfCurrentThread = i;
                            }
                        }
                    }
                }else{
                    listOfCarsInSouth.remove(indexOfCurrentThread);
                    counterOfCarsFromSouthToNorth--;

                    for (int i = 0; i< listOfCarsInSouth.size();i++) {
                        if (listOfCarsInSouth.get(i) != null) {
                            currentCarOnTheHighWay = listOfCarsInSouth.get(i);
                            indexOfCurrentThread = i;
                        }
                    }

                    System.out.println("Carros esperando en sur: "+counterOfCarsFromSouthToNorth);
                    if (listOfCarsInSouth.size() == 0){
                        for (int i = 0; i< listOfcarsInNorth.size();i++) {
                            if (listOfcarsInNorth.get(i) != null) {
                                currentCarOnTheHighWay = listOfcarsInNorth.get(i);
                                indexOfCurrentThread = i;
                            }
                        }
                    }
                }

                if (isThereACarWaiting()){
                    getPriority();
                }



            }
        }

        if (!isThereACar){
            paintCar(g);
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
    public synchronized void addCarsToNorth(int numberOfCarsToAdd){
        counterOfCarsFromNorthToSouth += numberOfCarsToAdd;
    }

    public synchronized void addCarsToSouth(int numberOfCarsToAdd){
        counterOfCarsFromSouthToNorth += numberOfCarsToAdd;
    }

    private synchronized void paintCar(Graphics g) {
        if (currentCarOnTheHighWay != null){
            if (currentCarOnTheHighWay.isNorth()){
                paintCarFromNorth(g);
            }else{
                paintCarFromSouth(g);
            }
        }
    }
    private synchronized void paintCarFromNorth(Graphics g){
        //Check if there are more than one car waiting if yes then set priority set to north
        g.setColor(currentCarOnTheHighWay.getColorSelected());
        g.fillRect(currentCarOnTheHighWay.getXPOSITION_OF_CAR(),currentCarOnTheHighWay.getCurrentYPositionOfCar(),
                currentCarOnTheHighWay.getCAR_WIDTH(),currentCarOnTheHighWay.getCAR_HEIGHT());
        Thread thread = new Thread(currentCarOnTheHighWay);
        thread.start();
        isThereACar = true;
    }
    private synchronized void paintCarFromSouth(Graphics g){
        g.setColor(currentCarOnTheHighWay.getColorSelected());
        g.fillRect(currentCarOnTheHighWay.getXPOSITION_OF_CAR(),currentCarOnTheHighWay.getCurrentYPositionOfCar(),
                currentCarOnTheHighWay.getCAR_WIDTH(),currentCarOnTheHighWay.getCAR_HEIGHT());
        Thread thread = new Thread(currentCarOnTheHighWay);

        thread.start();
        isThereACar = true;
    }

    private boolean isThereACarWaiting(){
        return listOfcarsInNorth.size() > 0 || listOfCarsInSouth.size() > 0;
    }

    public Car getCurrentCarOnTheHighWay() {
        return currentCarOnTheHighWay;
    }

    public ArrayList<Car> getListOfCarsInNorth() {
        return listOfcarsInNorth;
    }

    public ArrayList<Car> getListOfCarsInSouth() {
        return listOfCarsInSouth;
    }

    public void setCurrentCarOnTheHighWay(Car currentCarOnTheHighWay) {
        this.currentCarOnTheHighWay = currentCarOnTheHighWay;
    }

    public boolean isThereACar(){
        return isThereACar;
    }

    private void getPriority(){
        if (wasTheNorthPriority){
            if (listOfcarsInNorth.size() > 1){
                isCarOfNorthHavingThePriority = true;
                //System.out.println("Norte tiene la prioridad");
               // System.out.println("Carros esperando en el sur: "+counterOfCarsFromSouthToNorth);
                //System.out.println();
            } else if (listOfCarsInSouth.size() > 1) {
                //System.out.println("Ahora Sur tiene la prioridad");
               // System.out.println();
                isCarOfNorthHavingThePriority = false;
            }
        }else{
            if (listOfCarsInSouth.size() > 1) {
                isCarOfNorthHavingThePriority = false;
               // System.out.println("Sur tiene la prioridad");
               // System.out.println("Carros esperando en el norte: "+counterOfCarsFromNorthToSouth);
                //System.out.println();

            } else if (listOfcarsInNorth.size() > 1) {
                isCarOfNorthHavingThePriority = true;
                //System.out.println("Ahora norte tiene la prioridad");
                //System.out.println();
            }
        }

    }
    private boolean isThereMoreCarsOnSouth(){
        return listOfCarsInSouth.size() == 0;
    }
}
