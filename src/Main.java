import Threads.CarGenerator;
import Threads.CarPositionUpdater;
import Views.MyFrame;

public class Main {
    public static void main(String[] args) {

        MyFrame frame = new MyFrame();
        Thread thread = new Thread(new CarPositionUpdater(frame));
        thread.start();
    }
}