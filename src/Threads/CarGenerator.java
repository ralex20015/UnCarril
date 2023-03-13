package Threads;

import Views.HighWay;

import java.security.SecureRandom;

public class CarGenerator implements Runnable {

    private SecureRandom generator= new SecureRandom();
    private HighWay highWay;
    public CarGenerator(HighWay highWay){
        this.highWay = highWay;
    }
    @Override
    public void run() {
        int side = generator.nextInt(2);
        //If the generator produces 0 then add cars to
        if (side == 0){
            highWay.addCarsToNorth(1 + generator.nextInt(3));
        }else{
            highWay.addCarsToSouth(1 + generator.nextInt(3));
        }
    }
}
