package Views;

import javax.swing.*;
import java.awt.*;


public class HighWay extends JPanel {

    private double x;
    private double y;
    private Dimension dimension;
    private int startXPositionOfHighWay;

    public HighWay(MyFrame myFrame){
        dimension =  myFrame.getSize();
        x = dimension.getWidth();
        y = dimension.getHeight();
        setSize(150,600);
        setLocation(225,0);
        setLayout(null);
        startXPositionOfHighWay = (int)x/4;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(startXPositionOfHighWay, 0,(int)x/4,(int)y);
        setBackground(Color.gray);
    }

    public Color getHighWayColor(){
        return Color.gray;
    }
}
