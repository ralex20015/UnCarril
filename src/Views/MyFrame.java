package Views;

import Views.HighWay;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private JPanel area;
    private HighWay highWay;
    public MyFrame(){
       setSize(600,600);
       setLocationRelativeTo(null);
       setResizable(false);
       setVisible(true);
       setLayout(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       area = new JPanel();
       add(area);

       area.setBounds(0,0,600,600);
       area.setBackground(new Color(230,177,80));
       highWay = new HighWay(this);

       area.setLayout(null);
       area.add(highWay);
    }

    public HighWay getHighWay() {
        return highWay;
    }
}
