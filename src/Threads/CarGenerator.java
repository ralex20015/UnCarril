package Threads;

import Views.Car;
import Views.HighWay;

import java.security.SecureRandom;
import java.util.ArrayList;

public class CarGenerator implements Runnable {

    private SecureRandom generator= new SecureRandom();
    private ArrayList<Car> listOfNorthCars;
    private ArrayList<Car> listOfCarsInSouth;

    private HighWay highWay;
    public CarGenerator(HighWay highWay){
        this.highWay = highWay;
        listOfNorthCars = highWay.getListOfcarsInNorth();
        listOfCarsInSouth = highWay.getListOfCarsInSouth();
    }
    @Override
    public void run()  {
        int randomCars;
        while(true){
            try {
                int randomSeconds = (5 + generator.nextInt(4)) * 1000;
                Thread.sleep(randomSeconds);
                int side = 1;

                randomCars = 1 + generator.nextInt(3);

                if (side == 0){
                    highWay.addCarsToNorth(randomCars);
                    for (int i = 0; i < randomCars; i++) {
                        //Este es el problema
                        Car car = new Car(highWay);
                        car.setNorth(true);
                        listOfNorthCars.add(car);
                        System.out.println(highWay.getListOfcarsInNorth().get(i).isNorth());
                        //No hay ningun carro pasando
                        if (highWay.getCurrentCarOnTheHighWay() == null && !highWay.isThereACar()){
                            highWay.setCurrentCarOnTheHighWay(listOfNorthCars.get(i));
                            highWay.repaint();
                        }
                    }
                    System.out.println("Se generaron "+randomCars+" en el norte");
                }else{
                    highWay.addCarsToSouth(randomCars);
                    for (int i = 0; i < randomCars; i++) {
                        Car car = new Car(highWay);
                        car.setNorth(false);
                        listOfCarsInSouth.add(car);
                        //No hay ninguna carro pasando
                        if (highWay.getCurrentCarOnTheHighWay() == null && !highWay.isThereACar()){
                            highWay.setCurrentCarOnTheHighWay(listOfCarsInSouth.get(i));
                            highWay.repaint();
                        }
                    }
                    System.out.println("Se generaron "+randomCars+" en el sur");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        //If the generator produces 0 then add cars to
    }
}
