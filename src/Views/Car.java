package Views;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

public class Car extends JPanel {

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
    private final SecureRandom generator = new SecureRandom();

    public Car(){
        int color = generator.nextInt(7);
        System.out.println(color);
        setBackground(colors[color]);
        setSize(80,60);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
