package Threads;

import Views.MyFrame;

public class CarPositionUpdater implements Runnable{
    private int currentCarPosition;
    private MyFrame frame;
    public CarPositionUpdater(MyFrame myFrame) {
        this.frame = myFrame;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        while (currentCarPosition < 660){
            try {
                Thread.sleep(100);
                frame.getHighWay().getCurrentCarOnTheHighWay().updatePosition(30);
                frame.getHighWay().repaint();
                currentCarPosition = frame.getHighWay().getCurrentCarOnTheHighWay().getCurrentYPositionOfCar();
                System.out.println(currentCarPosition);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("done");
    }
}
