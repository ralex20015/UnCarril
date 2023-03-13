package Views;

import javax.swing.*;
import java.awt.*;

public class HighWayLines extends JPanel {

    private double x;
    private double y;
    private Dimension dimension;
    private final int HEIGHT = 40;
    private final int WIDTH = 20;
    private int xPositionOfLines = 65;
    private int yPositionOfNextLine;
    private int ySpaceBetweenEachLine = 20;
    private final HighWay highWay;

    public HighWayLines(HighWay highWay){
        this.highWay = highWay;
        dimension =  highWay.getSize();
        x = dimension.getWidth();
        y = dimension.getHeight();
        setSize(dimension);

        setLocation(0,0);
        setLayout(null);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <= 10; i++) {
            g.setColor(Color.YELLOW);
            yPositionOfNextLine = (HEIGHT + ySpaceBetweenEachLine)*i;
            g.fillRect(xPositionOfLines,yPositionOfNextLine,WIDTH,HEIGHT);
        }
        setBackground(highWay.getHighWayColor());

    }
}
