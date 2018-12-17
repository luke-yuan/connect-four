package com.company;

/**
 * Created by Luke on 5/2/16.
 */
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Cell extends JPanel {
    private char token = ' ';

    public Cell() {
        setBorder(new LineBorder(Color.BLACK, 1));
        setBackground(Color.BLACK);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 40, 40);
        if (token == 'R')
            g.setColor(Color.RED);
        else if (token == 'Y')
            g.setColor(Color.YELLOW);
        else
            g.setColor(Color.WHITE);

        g.fillOval(5, 5, 29, 29);
    }

    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
        repaint();
    }

    public boolean isFull() {
        return (token != ' ');
    }
}
